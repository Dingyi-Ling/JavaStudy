package JavaProgramming.ArraysAndObjects.CreateAClass;

public class ServerRackRun {
    public static void main(String[] args) {
        ServerRack serverRack = new ServerRack(1, 96, 1024, 10240, true, "General", 8);
        serverRack.addDisk(0);
        serverRack.addDisk(1);
        serverRack.addDisk(2);
        serverRack.addDisk(3);
        serverRack.addDisk(4);
        serverRack.addDisk(5);
        serverRack.addDisk(6);
        serverRack.addDisk(7);
        serverRack.displayDisks();
        serverRack.removeDisk(3);
        serverRack.removeDisk(5);
        serverRack.displayDisks();
        System.out.println("Server ID: " + serverRack.getServerid());
        System.out.println("CPU Core Number: " + serverRack.getCpuCoreNumber());
        System.out.println("Memory: " + serverRack.getMemory());
        System.out.println("Storage: " + serverRack.getStorage());
        System.out.println("Server Status: " + serverRack.isServerStatus());
        System.out.println("Server Type: " + serverRack.getServerType());
        System.out.println("Disk Amount: " + serverRack.getNumberOfDisks());
    }
}