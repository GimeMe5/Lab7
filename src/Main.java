/**
 * @author Artyom Kulagin
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Supervisor supervisor = new Supervisor();
        Thread.sleep(1000);
        supervisor.checkStatus();
    }
}
