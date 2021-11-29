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


    public Program() {
        StatusRandomizer statusRandomizer = new StatusRandomizer(this);
        statusRandomizer.setDaemon(true);
        statusRandomizer.start();
        this.setName("Abstract program");
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " is started");
        while (!isInterrupted()) {
            try {
                System.out.println(Thread.currentThread().getName() + " is working");
                sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " is stopped");

            }
        }
    }
}

