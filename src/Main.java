/**
 * @author Artyom Kulagin
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Program program = new Program();
        program.start();
        Supervisor supervisor = new Supervisor(program);
        Thread.sleep(1000);
        supervisor.checkStatus();
    }
}
