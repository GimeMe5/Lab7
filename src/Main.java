import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Artyom Kulagin
 */
public class Main {
    static BlockingQueue<String> queue = new ArrayBlockingQueue<>(5);


    public static void main(String[] args) throws InterruptedException {
        ExecutorService serviceWriters = Executors.newFixedThreadPool(5);
        ExecutorService serviceReaders = Executors.newFixedThreadPool(5);
        for (int i=1;i<=5;i++) {
            serviceWriters.execute(new Writer(queue, "Writer"+i));
            serviceReaders.execute(new Reader(queue, "Reader"+i));
        }

    }
}
