package JavaProgramming.ArraysAndObjects.CreateAClass;

import java.util.ArrayList;
import java.util.List;

class ServerRack {
    private int serverid;
    private int cpuCoreNumber;
    private int memory;
    private int storage;
    private int numberOfDisks;
    private boolean serverStatus;
    private String serverType;
    private List<Boolean> diskPositions;

    public ServerRack(int serverid, int cpuCoreNumber, int memory, int storage, boolean serverStatus, String serverType, int numberOfDisks) {
        this.serverid = serverid;
        this.cpuCoreNumber = cpuCoreNumber;
        this.memory = memory;
        this.storage = storage;
        this.serverStatus = serverStatus;
        this.serverType = serverType;
        this.numberOfDisks = numberOfDisks;
        this.diskPositions = new ArrayList<>(numberOfDisks);
        for (int i = 0; i < numberOfDisks; i++) {
            diskPositions.add(false);
        }
    }
    public int getServerid() {
        return serverid;
    }
    public void setServerid(int serverid) {
        this.serverid = serverid;
    }
    public int getCpuCoreNumber() {
        return cpuCoreNumber;
    }
    public void setCpuCoreNumber(int cpuCoreNumber) {
        this.cpuCoreNumber = cpuCoreNumber;
    }
    public int getMemory() {
        return memory;
    }
    public void setMemory(int memory) {
        this.memory = memory;
    }
    public int getStorage() {
        return storage;
    }
    public void setStorage(int storage) {
        this.storage = storage;
    }
    public boolean isServerStatus() {
        return serverStatus;
    }
    public void setServerStatus(boolean serverStatus) {
        this.serverStatus = serverStatus;
    }
    public String getServerType() {
        return serverType;
    }
    public void setServerType(String serverType) {
        this.serverType = serverType;
    }
    public int getNumberOfDisks() {
        return numberOfDisks;
    }
    public void setNumberOfDisks(int numberOfDisks) {
        this.numberOfDisks = numberOfDisks;
    }
    public List<Boolean> getDiskPositions() {
        return diskPositions;
    }
    public void setDiskPositions(List<Boolean> diskPositions) {
        this.diskPositions = diskPositions;
    }
    public void addDisk(int diskPosition) {
        diskPositions.set(diskPosition, true);
        System.out.println("Added Disk: " + diskPosition);
    }
    public void removeDisk(int diskPosition) {
        diskPositions.set(diskPosition, false);
        System.out.println("Deleted Disk: " + diskPosition);
    }
    public void displayDisks() {
        for (int i = 0; i < diskPositions.size(); i++) {
            if (diskPositions.get(i)) {
                System.out.println("Disk " + i + " is present");
            }
        }
    }
}


