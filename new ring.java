import java.util.*;

public class Ring {
    int max;
    int coordinator;
    boolean[] active;
    ArrayList<Integer> pids = new ArrayList<>();

    public Ring(int max) {
        this.max = max;
        this.coordinator = max;
        this.active = new boolean[max];
        Arrays.fill(active, true);
        for (int i = 0; i < max; i++) System.out.println("P" + (i + 1) + " created.");
        System.out.println("P" + coordinator + " is the coordinator.");
    }

    void displayProcesses() {
        for (int i = 0; i < max; i++)
            System.out.println("P" + (i + 1) + " is " + (active[i] ? "up" : "down") + ".");
        System.out.println("P" + coordinator + " is the coordinator.");
    }

    void toggleProcess(int id, boolean up) {
        int index = id - 1;
        if (active[index] == up)
            System.out.println("Process P" + id + " is already " + (up ? "up" : "down") + ".");
        else {
            active[index] = up;
            System.out.println("Process P" + id + " is " + (up ? "up" : "down") + ".");
        }
    }

    void initElection(int id) {
        if (!active[id - 1]) return;
        pids.clear();
        int current = id - 1;
        do {
            if (active[current]) {
                pids.add(current + 1);
                System.out.print("Process P" + (current + 1) + " sending list: " + pids + "\n");
            }
            current = (current + 1) % max;
        } while (current != id - 1);
        coordinator = Collections.max(pids);
        System.out.println("Process P" + id + " has declared P" + coordinator + " as the coordinator.");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Ring ring = null;
        while (true) {
            System.out.print("""
                Ring Algorithm
                1. Create processes
                2. Display processes
                3. Up a process
                4. Down a process
                5. Run election
                6. Exit
                Enter choice: """);
            int choice = sc.nextInt();
            switch (choice) {
                case 1 -> {
                    System.out.print("Enter number of processes: ");
                    ring = new Ring(sc.nextInt());
                }
                case 2 -> ring.displayProcesses();
                case 3 -> {
                    System.out.print("Enter process to up: ");
                    ring.toggleProcess(sc.nextInt(), true);
                }
                case 4 -> {
                    System.out.print("Enter process to down: ");
                    ring.toggleProcess(sc.nextInt(), false);
                }
                case 5 -> {
                    System.out.print("Enter initiating process: ");
                    ring.initElection(sc.nextInt());
                }
                case 6 -> System.exit(0);
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}
