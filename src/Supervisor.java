

/**
 * @author Artyom Kulagin
 */
public class Supervisor {

    private Program program;
    private final Object lock;

    public Supervisor() {
        lock = new Object();
        StatusRandomizer statusRandomizer = new StatusRandomizer(lock);
        statusRandomizer.start();
    }

    public void startProgram() {
        program = new Program();
        program.start();
    }

    public void stopProgram() {
        program.interrupt();
    }

    public void checkStatus() throws InterruptedException {
        startProgram();
        while (true) {
//            Thread.sleep(1000);
            if (Program.status == Status.UNKNOWN || Program.status == Status.STOPPING) {
                System.out.println(program.getName() + " trying to stop");
                stopProgram();
                program.join();
                System.out.println(program.getName() + " trying to restart");
                startProgram();
            } else if (Program.status == Status.FATAL_ERROR) {
                System.out.println(program.getName() + " trying to interrupt");
                stopProgram();
                break;
            }
            System.out.println("before");
            synchronized (lock) {
                System.out.println("notify");
                lock.notify();
                lock.wait();
            }
            System.out.println("after");
        }
    }

}
