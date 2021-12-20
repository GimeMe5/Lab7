import java.util.concurrent.BlockingQueue;

/**
 * @author Artyom Kulagin
 */
public class Reader implements Runnable {

    BlockingQueue<String> queue;
    String name;

    public Reader(BlockingQueue<String> queue, String name) {
        this.queue = queue;
        this.name = name;
    }


    @Override
    public void run() {
        while (true) {
            try {
                System.out.println(name+ " take "+queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
