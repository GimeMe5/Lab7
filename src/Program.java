import org.w3c.dom.ls.LSOutput;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.DoubleToIntFunction;

/**
 * @author Artyom Kulagin
 */
public class Program extends Thread {
    public static Status status = Status.UNKNOWN;
    public static final StatusRandomizer daemon = new StatusRandomizer();

    public Program() {
        daemon.setDaemon(true);
        daemon.start();
        this.setName("AbstractProgram");
    }

    @Override
    public synchronized void run() {
        while (!isInterrupted()) {
            try {
                System.out.println(Thread.currentThread().getName() + " is active, status is - " + status);
                sleep(1000);
                notify();
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " is interrupted");
                notify();
                return;
            }

        }
    }
}

