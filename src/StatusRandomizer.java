import java.util.List;
import java.util.Random;

/**
 * @author Artyom Kulagin
 */
class StatusRandomizer extends Thread {
    private static final List<Status> VALUES = List.of(Status.values());
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();
    @Override
    public synchronized void run() {
        while (true) {
            try {
                sleep(1000);
                Program.status = VALUES.get(RANDOM.nextInt(SIZE));
                System.out.println("new status is - "+Program.status.toString());
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
