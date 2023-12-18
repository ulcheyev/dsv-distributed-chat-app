// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ChatService.proto

package generated;

public final class ChatService {
  private ChatService() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_generated_Empty_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_generated_Empty_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_generated_StringPayload_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_generated_StringPayload_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_generated_Message_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_generated_Message_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_generated_Rooms_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_generated_Rooms_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_generated_RoomEntry_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_generated_RoomEntry_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_generated_Remote_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_generated_Remote_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_generated_RemoteResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_generated_RemoteResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_generated_Neighbours_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_generated_Neighbours_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_generated_JoinRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_generated_JoinRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_generated_JoinResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_generated_JoinResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_generated_PermissionRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_generated_PermissionRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_generated_PermissionResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_generated_PermissionResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_generated_Health_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_generated_Health_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\021ChatService.proto\022\tgenerated\"\007\n\005Empty\"" +
      "\034\n\rStringPayload\022\013\n\003msg\030\001 \001(\t\"9\n\007Message" +
      "\022!\n\006remote\030\001 \001(\0132\021.generated.Remote\022\013\n\003m" +
      "sg\030\002 \001(\t\"B\n\005Rooms\022#\n\005rooms\030\001 \003(\0132\024.gener" +
      "ated.RoomEntry\022\024\n\014isNotVisited\030\002 \001(\010\"j\n\t" +
      "RoomEntry\022$\n\troomOwner\030\001 \001(\0132\021.generated" +
      ".Remote\022%\n\nroomBackup\030\002 \001(\0132\021.generated." +
      "Remote\022\020\n\010roomName\030\003 \001(\t\"J\n\006Remote\022\016\n\006no" +
      "deId\030\001 \001(\003\022\020\n\010hostname\030\002 \001(\t\022\014\n\004port\030\003 \001" +
      "(\005\022\020\n\010username\030\004 \001(\t\"-\n\016RemoteResponse\022\r" +
      "\n\005added\030\001 \001(\010\022\014\n\004room\030\002 \001(\t\"\226\001\n\nNeighbou" +
      "rs\022\037\n\004next\030\001 \001(\0132\021.generated.Remote\022#\n\010n" +
      "extNext\030\002 \001(\0132\021.generated.Remote\022\037\n\004prev" +
      "\030\003 \001(\0132\021.generated.Remote\022!\n\006leader\030\004 \001(" +
      "\0132\021.generated.Remote\"B\n\013JoinRequest\022\020\n\010r" +
      "oomName\030\001 \001(\t\022!\n\006remote\030\002 \001(\0132\021.generate" +
      "d.Remote\"n\n\014JoinResponse\022\020\n\010isLeader\030\001 \001" +
      "(\010\022!\n\006leader\030\002 \001(\0132\021.generated.Remote\022)\n" +
      "\nneighbours\030\003 \001(\0132\025.generated.Neighbours" +
      "\"N\n\021PermissionRequest\022*\n\017requestByRemote" +
      "\030\001 \001(\0132\021.generated.Remote\022\r\n\005clock\030\002 \001(\005" +
      "\"R\n\022PermissionResponse\022\017\n\007granted\030\001 \001(\010\022" +
      "+\n\020responseByRemote\030\002 \001(\0132\021.generated.Re" +
      "mote\"\031\n\006Health\022\017\n\007isAlive\030\001 \001(\0102\361\010\n\016Remo" +
      "tesService\022;\n\010joinRoom\022\026.generated.JoinR" +
      "equest\032\027.generated.JoinResponse\022/\n\010exitR" +
      "oom\022\021.generated.Remote\032\020.generated.Empty" +
      "\0224\n\tpreflight\022\021.generated.Remote\032\022.gener" +
      "ated.Message0\001\0222\n\014receiveRooms\022\020.generat" +
      "ed.Rooms\032\020.generated.Empty\0225\n\013receiveRoo" +
      "m\022\024.generated.RoomEntry\032\020.generated.Empt" +
      "y\0224\n\nremoveRoom\022\024.generated.RoomEntry\032\020." +
      "generated.Empty\022J\n\030receivePermissionRequ" +
      "est\022\034.generated.PermissionRequest\032\020.gene" +
      "rated.Empty\022L\n\031receivePermissionResponse" +
      "\022\035.generated.PermissionResponse\032\020.genera" +
      "ted.Empty\0226\n\020updateRoomsTable\022\020.generate" +
      "d.Empty\032\020.generated.Empty\0226\n\016receiveMess" +
      "age\022\022.generated.Message\032\020.generated.Empt" +
      "y\022G\n\031receiveGetRoomListRequest\022\020.generat" +
      "ed.Empty\032\030.generated.StringPayload\022T\n&re" +
      "ceiveGetNodeListInCurrentRoomRequest\022\020.g" +
      "enerated.Empty\032\030.generated.StringPayload" +
      "\0225\n\016changeNextNext\022\021.generated.Remote\032\020." +
      "generated.Empty\0222\n\nchangePrev\022\021.generate" +
      "d.Remote\032\021.generated.Remote\022/\n\010election\022" +
      "\021.generated.Remote\032\020.generated.Empty\022.\n\007" +
      "elected\022\021.generated.Remote\032\020.generated.E" +
      "mpty\0225\n\016repairTopology\022\021.generated.Remot" +
      "e\032\020.generated.Empty\022A\n\020changeNeighbours\022" +
      "\026.generated.JoinRequest\032\025.generated.Neig" +
      "hbours\022+\n\004beat\022\020.generated.Empty\032\021.gener" +
      "ated.HealthB\002P\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_generated_Empty_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_generated_Empty_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_generated_Empty_descriptor,
        new java.lang.String[] { });
    internal_static_generated_StringPayload_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_generated_StringPayload_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_generated_StringPayload_descriptor,
        new java.lang.String[] { "Msg", });
    internal_static_generated_Message_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_generated_Message_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_generated_Message_descriptor,
        new java.lang.String[] { "Remote", "Msg", });
    internal_static_generated_Rooms_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_generated_Rooms_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_generated_Rooms_descriptor,
        new java.lang.String[] { "Rooms", "IsNotVisited", });
    internal_static_generated_RoomEntry_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_generated_RoomEntry_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_generated_RoomEntry_descriptor,
        new java.lang.String[] { "RoomOwner", "RoomBackup", "RoomName", });
    internal_static_generated_Remote_descriptor =
      getDescriptor().getMessageTypes().get(5);
    internal_static_generated_Remote_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_generated_Remote_descriptor,
        new java.lang.String[] { "NodeId", "Hostname", "Port", "Username", });
    internal_static_generated_RemoteResponse_descriptor =
      getDescriptor().getMessageTypes().get(6);
    internal_static_generated_RemoteResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_generated_RemoteResponse_descriptor,
        new java.lang.String[] { "Added", "Room", });
    internal_static_generated_Neighbours_descriptor =
      getDescriptor().getMessageTypes().get(7);
    internal_static_generated_Neighbours_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_generated_Neighbours_descriptor,
        new java.lang.String[] { "Next", "NextNext", "Prev", "Leader", });
    internal_static_generated_JoinRequest_descriptor =
      getDescriptor().getMessageTypes().get(8);
    internal_static_generated_JoinRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_generated_JoinRequest_descriptor,
        new java.lang.String[] { "RoomName", "Remote", });
    internal_static_generated_JoinResponse_descriptor =
      getDescriptor().getMessageTypes().get(9);
    internal_static_generated_JoinResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_generated_JoinResponse_descriptor,
        new java.lang.String[] { "IsLeader", "Leader", "Neighbours", });
    internal_static_generated_PermissionRequest_descriptor =
      getDescriptor().getMessageTypes().get(10);
    internal_static_generated_PermissionRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_generated_PermissionRequest_descriptor,
        new java.lang.String[] { "RequestByRemote", "Clock", });
    internal_static_generated_PermissionResponse_descriptor =
      getDescriptor().getMessageTypes().get(11);
    internal_static_generated_PermissionResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_generated_PermissionResponse_descriptor,
        new java.lang.String[] { "Granted", "ResponseByRemote", });
    internal_static_generated_Health_descriptor =
      getDescriptor().getMessageTypes().get(12);
    internal_static_generated_Health_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_generated_Health_descriptor,
        new java.lang.String[] { "IsAlive", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
