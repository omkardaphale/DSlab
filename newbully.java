import java.util.Scanner;

public class Bully {
    int coordinator, max;
    boolean[] processes;

    public Bully(int max) {
        this.max = max;
        this.coordinator = max;
        this.processes = new boolean[max];
        System.out.println("Creating processes..");
        for (int i = 0; i < max; i++) {
            processes[i] = true;
            System.out.println("P" + (i + 1) + " created");
        }
        System.out.println("Process P" + coordinator + " is the coordinator");
    }

    void displayProcesses() {
        for (int i = 0; i < max; i++)
            System.out.println("P" + (i + 1) + " is " + (processes[i] ? "up" : "down"));
        System.out.println("Process P" + coordinator + " is the coordinator");
    }

    void toggleProcess(int id, boolean up) {
        if (processes[id - 1] == up) {
            System.out.println("Process " + id + " is already " + (up ? "up" : "down") + ".");
        } else {
            processes[id - 1] = up;
            System.out.println("Process " + id + " is now " + (up ? "up" : "down") + ".");
        }
    }

    void runElection(int id) {
        for (int i = id; i < max; i++) {
            System.out.println("Election message sent from process " + id + " to process " + (i + 1));
            if (processes[i]) {
                coordinator = i + 1;
                runElection(i + 1);
                break;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Bully bully = null;
        int choice;

        while (true) {
            System.out.println("\nBully Algorithm\n1. Create\n2. Display\n3. Up\n4. Down\n5. Elect\n6. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            if (choice == 6) break;

            switch (choice) {
                case 1:
                    System.out.print("Enter number of processes: ");
                    bully = new Bully(sc.nextInt());
                    break;
                case 2:
                    if (bully != null) bully.displayProcesses();
                    break;
                case 3:
                    System.out.print("Enter process number to up: ");
                    if (bully != null) bully.toggleProcess(sc.nextInt(), true);
                    break;
                case 4:
                    System.out.print("Enter process number to down: ");
                    if (bully != null) bully.toggleProcess(sc.nextInt(), false);
                    break;
                case 5:
                    System.out.print("Enter process number to start election: ");
                    if (bully != null) {
                        bully.runElection(sc.nextInt());
                        bully.displayProcesses();
                    }
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
