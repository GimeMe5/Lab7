import jdk.swing.interop.SwingInterOpUtils;
import org.w3c.dom.ls.LSOutput;

/**
 * @author Artyom Kulagin
 */
public class Supervisor {

    private final Program program;

    public Supervisor(Program program) {
        this.program = program;
    }

    public void startProgram() {
        program.start();
    }

    public void stopProgram() {
        program.interrupt();
    }

    public void checkStatus() throws InterruptedException {
        while (true) {
            Thread.sleep(1000);
            if (Program.status == Status.UNKNOWN || Program.status == Status.STOPPING) {
                System.out.println(program.getName() + " trying to stop");
                stopProgram();
                try {
                    program.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(program.getName() + " trying to restart");
                startProgram();
            } else if (Program.status == Status.FATAL_ERROR) {
                System.out.println(program.getName() + " trying to interrupt");
                stopProgram();
                break;
            }
            System.out.println("before");
            synchronized (program) {
                System.out.println("notify");
                notify();
            }
            System.out.println("after");
        }
    }

}
