package cz.cvut.fel.dsv.core.data;

import cz.cvut.fel.dsv.core.Node;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class SharedData {
    private static ConcurrentMap<String, DsvPair<Address, Address>> data = new ConcurrentHashMap<>();

    public static synchronized ConcurrentMap<String, DsvPair<Address, Address>> getData() {
        return data;
    }

    public static synchronized void put(String key, DsvPair<Address, Address> value) {
        data.put(key, value);
    }

    public static synchronized void updateData(ConcurrentMap<String, DsvPair<Address, Address>> repl) {
        data = new ConcurrentHashMap<>(repl);
    }

    public static synchronized List<Address> getNodeAddressesWithoutCurrent() {
        return data.values().stream().map(DsvPair::getKey).filter(key -> !key.equals(Node.getInstance().getAddress())).toList();
    }

    public static int getSize() {
        return data.size();
    }

    public static void clear() {
        data.clear();
    }

    public static DsvPair<Address, Address> get(String key) {
        return data.get(key);
    }

    public static int getSizeNecessaryForUpdate() {
        return getSize() - 1;
    }

    public static DsvPair<Address, Address> remove(String key) {
        return data.remove(key);
    }

    public static String stringify() {
        StringBuilder sb = new StringBuilder();
        for (var room : data.entrySet()) {
            sb.append("\n\t\t\t")
                    .append(room.getKey())
                    .append(" : ")
                    .append(room.getValue());
        }
        return sb.toString();
    }


}
