import java.util.List;
import java.util.Random;

/**
 * @author Artyom Kulagin
 */
class StatusRandomizer extends Thread {
    private static final List<Status> VALUES = List.of(Status.values());
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();
    private final Program program;

    public StatusRandomizer(Program program) {
        this.program = program;
        this.setName("Daemon");
    }


    @Override
    public synchronized void run() {
        while (true) {
            try {
                Program.status = VALUES.get(RANDOM.nextInt(SIZE));
                System.out.println("new status is - "+Program.status.toString());
                synchronized (program) {
                    System.out.println(Thread.currentThread().getName()+" wait");
                    wait();
                    System.out.println(Thread.currentThread().getName()+ " resume");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
