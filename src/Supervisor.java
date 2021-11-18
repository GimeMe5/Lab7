import jdk.swing.interop.SwingInterOpUtils;
import org.w3c.dom.ls.LSOutput;

/**
 * @author Artyom Kulagin
 */
public class Supervisor {
    static Program program = new Program();

    static void stop() {
        program.interrupt();
    }

    static void start() {
        program.start();
    }

    public synchronized static void main(String[] args) throws InterruptedException {
        start();
        while (true) {

            switch (Program.status.toString()) {
                case "STOPPING":
                    System.out.println("I interrupt this!");
                    program.wait();
                    start();
                    return;
                case "FATAL_ERROR":
                    System.out.println("I interrupt this!");
                    stop();
                    return;
                case "RUNNING":
                    if (!program.isAlive()) {
                        start();
                        break;
                    }
            }
            Thread.sleep(1000);
        }
    }
}
