import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class Main {


    static ArrayList<Map<String, Integer>> res = new ArrayList<>();

    public static void main(String[] args) throws IOException, InterruptedException {
        //Init list of thread is needed, for using the join() function after all the thread started
        ArrayList<Thread> listThreadMapper = new ArrayList<>();
        ArrayList<Thread> listThreadReducer = new ArrayList<>();
        //List of reducer created to be sent on the mapper to make the sufflling in the mapper
        ArrayList<Reducer> listReducer = new ArrayList<>();
        int reducerNumber = 3;

        ShufflerWithFirstLetter.suffle();
        FileSplitter.splitFile("input.txt", reducerNumber);

        for (int i = 0; i < reducerNumber; i++) {
            listReducer.add(new Reducer(i));
        }


        for (int i = 0; i < reducerNumber; i++) {
            Thread thread = new Thread(new Mapper(i, listReducer));
            listThreadMapper.add(thread);
            thread.start();
        }


        System.out.println("[*] Les thread mapper sont lancé");
        for (Thread t : listThreadMapper) {
            t.join();
        }
        System.out.println("[*] Les thread mapper sont FINITO");

        //Print the content of the list of each reducer to see if the affection is well done
        for (Reducer r : listReducer) {
            System.out.println(r.getWordMap());
        }


        for (int i = 0; i < reducerNumber; i++) {
            Thread thread = new Thread(listReducer.get(i));
            listThreadReducer.add(thread);
            thread.start();
        }
        System.out.println("[*] Les thread reducer sont lancé");


        for (Thread t : listThreadReducer) {
            t.join();
        }
        System.out.println("[*] Les thread reducer sont FINITO");


    }

    public void awaitThread(ArrayList<Thread> listOfThread) throws InterruptedException {
        for (Thread t : listOfThread) {
            t.join();
        }
        System.out.println("[*] Les thread sont FINITO");

    }

}
