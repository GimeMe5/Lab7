import java.util.List;
import java.util.Random;

/**
 * @author Artyom Kulagin
 */
class StatusRandomizer extends Thread {
    private static final List<Status> VALUES = List.of(Status.values());
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();
    private final Object lock;

    public StatusRandomizer(Object lock) {
        this.lock = lock;
        this.setName("Daemon");
        this.setDaemon(true);
    }


    @Override
    public void run() {
        while (true) {
            try {
                Program.status = VALUES.get(RANDOM.nextInt(SIZE));
                System.out.println("new status is - "+Program.status.toString());
                synchronized (lock) {
                    System.out.println(Thread.currentThread().getName() + " wait");
                    lock.notify();
                    lock.wait();
                    System.out.println(Thread.currentThread().getName()+ " resume");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
