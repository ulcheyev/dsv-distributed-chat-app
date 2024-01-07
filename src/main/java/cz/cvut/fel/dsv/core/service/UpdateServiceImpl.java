package cz.cvut.fel.dsv.core.service;

import cz.cvut.fel.dsv.core.Node;
import cz.cvut.fel.dsv.core.data.Address;
import cz.cvut.fel.dsv.core.data.DsvPair;
import cz.cvut.fel.dsv.core.data.SharedData;
import cz.cvut.fel.dsv.core.service.MEUtils.CSManager;
import cz.cvut.fel.dsv.utils.DsvConditionLock;
import cz.cvut.fel.dsv.utils.DsvLogger;
import cz.cvut.fel.dsv.utils.Utils;
import generated.*;
import generated.Remote;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import static cz.cvut.fel.dsv.core.infrastructure.Config.ANSI_PURPLE_SERVICE;

public class UpdateServiceImpl extends generated.UpdateServiceGrpc.UpdateServiceImplBase {

    private static final Logger logger = DsvLogger.getLogger("UPDATE SERVICE", ANSI_PURPLE_SERVICE, UpdateServiceImpl.class);
    private final Node node = Node.getInstance();

    private  DsvConditionLock lock;

    private CSManager csManager;

    public UpdateServiceImpl(ElectionServiceImpl electionService) {
        electionService.setUpdateServiceToManager(this);
        csManager = new CSManager();
        lock = new DsvConditionLock(true);
    }

    @Override
    public void receiveRoom(generated.RoomEntry request, StreamObserver<generated.Message> responseObserver) {
        logger.log(Level.INFO, "Receiving room [{0} - {1}:{2} - {3}]", new Object[]{request.getRoomOwner().getUsername(), request.getRoomOwner().getHostname(), request.getRoomOwner().getPort(), request.getRoomName()});
        var leader = Utils.Mapper.remoteToAddress(request.getRoomOwner());
        var backupNode = Utils.Mapper.remoteToAddress(request.getRoomBackup());
        SharedData.put(request.getRoomName(), DsvPair.of(leader, backupNode));
        responseObserver.onNext(generated.Message.newBuilder().setMsg("OK: Room received").build());
        responseObserver.onCompleted();
    }

    @Override
    public synchronized void receiveRooms(generated.Rooms request, StreamObserver<generated.Message> responseObserver) {
        logger.log(Level.INFO, "Receiving rooms. Count = {0}", request.getRoomsCount());
        var tempMap = new ConcurrentHashMap<String, DsvPair<Address, Address>>();
        for (var room : request.getRoomsList()) {
            Remote roomOwner = room.getRoomOwner();
            logger.log(Level.INFO, "\t\t[{0}:{1} - {2}] [{3}:{4}]", new Object[]{roomOwner.getHostname(), roomOwner.getPort(), room.getRoomName(), room.getRoomBackup().getHostname(), room.getRoomBackup().getPort()});
            var leader = Utils.Mapper.remoteToAddress(roomOwner);
            var backupNode = Utils.Mapper.remoteToAddress(room.getRoomBackup());
            tempMap.put(room.getRoomName(), DsvPair.of(leader, backupNode));
        }
        SharedData.updateData(tempMap);
        csManager.dataReceived();
        responseObserver.onNext(generated.Message.newBuilder().setMsg("OK: Rooms received").build());
        responseObserver.onCompleted();
    }

    @Override
    public void removeRoom(generated.RoomEntry request, StreamObserver<generated.Message> responseObserver) {
        logger.log(Level.INFO, "Remove room [{0}]", request.getRoomName());
        SharedData.remove(request.getRoomName());
        responseObserver.onNext(generated.Message.newBuilder().setMsg("OK: Room removed").build());
        responseObserver.onCompleted();
    }

    @Override
    public void receivePermissionRequest(generated.PermissionRequest request, StreamObserver<generated.GrantMessage> responseObserver) {
        csManager.receiveRequest(Utils.Mapper.remoteToAddress(request.getRequestByRemote()), request.getClock(), request.getDelay(), request.getId());
        responseObserver.onNext(generated.GrantMessage.newBuilder().setGrant(false).build());
        responseObserver.onCompleted();
    }

    @Override
    public void receivePermissionReply(generated.PermissionResponse response, StreamObserver<generated.Empty> responseObserver) {
        logger.log(Level.INFO, "[CS] received permit by {0}", Utils.Mapper.remoteToAddress(response.getResponseByRemote()));
        responseObserver.onNext(generated.Empty.getDefaultInstance());
        responseObserver.onCompleted();
        csManager.receivePermit(response.getId());
    }

    public void requestCS(Integer delay) {
        csManager.requestCriticalSection(delay);
    }

    public void awaitPermitToEnterCS() {
        csManager.awaitReplies();
    }

    public void updateTables() {
        csManager.broadcastDataUpdate();
    }

    public void releaseCS() {
        csManager.receiveRelease();
    }

    private UpdateServiceImpl() {
    }
}
