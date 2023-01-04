import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        int reducerNumber = 3;
        FileSplitter.splitFile("input.txt", reducerNumber);
        ArrayList<Thread> listThread = new ArrayList<>();

        for (int i = 0; i < reducerNumber; i++) {
            Thread thread = new Thread(new Mapper(i));
            listThread.add(thread);
            thread.start();
        }
        System.out.println("[*] Les thread sont lancé");
        for (Thread t: listThread) {
            t.join();
        }
        System.out.println("[*] Les thread sont FINITO");
    }
}
