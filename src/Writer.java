import java.util.concurrent.BlockingQueue;

/**
 * @author Artyom Kulagin
 */
public class Writer implements Runnable {

    BlockingQueue<String> queue;
    String name;

    public Writer(BlockingQueue<String> queue, String name) {
        this.queue = queue;
        this.name = name;
    }


    @Override
    public void run() {
        while (true) {
            try {
                queue.put(name);
                System.out.println("put "+name);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}