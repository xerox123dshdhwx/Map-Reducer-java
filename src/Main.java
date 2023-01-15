import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class Main{

    public static void main(String[] args) throws IOException, InterruptedException {
        long chrono = Chrono.Go_Chrono();
        //Initialisation des listes de threads
        ArrayList<Thread> listThreadMapper = new ArrayList<>();
        ArrayList<Thread> listThreadReducer = new ArrayList<>();

        //Choix du nombre de reducer
        int reducerNumber = 8;

        //Split du fichier texte à traiter en n parties, 1 pour chaque reducer
        FileSplitter.splitFile("input.txt", reducerNumber);

        //Création de la liste des reducers et initialisation de la liste des résultats des shufflers
        for (int i = 0; i < reducerNumber; i++) {
            DataStorage.listReducer.add(new Reducer(i));
        }
        DataStorage.initSufflerRes(reducerNumber);

        //Création des threads mapper
        for (int i = 0; i < reducerNumber; i++) {
            Thread thread = new Thread(new Mapper(i, DataStorage.listReducer));
            listThreadMapper.add(thread);
            thread.start();
        }
        System.out.println("[*] ================ Les threads mapper sont lancé ================ [*]");

        //Attente que les threads mapper se terminent
        for (Thread t : listThreadMapper) {
            t.join();
        }
        System.out.println("[*] ================ Les threads mapper sont terminés ================ [*]\n");

        //Shuffle du resultat des mappers
        for (Map<String, Integer> map : DataStorage.mapperRes) {
            Shuffler.shuffle(map);
        }

        ////Création des threads reducer
        for (int i = 0; i < reducerNumber; i++) {
            Thread thread = new Thread(DataStorage.listReducer.get(i));
            listThreadReducer.add(thread);
            thread.start();
        }
        System.out.println("[*] ================ Les threads reducer sont lancés ================ [*]");

        //Attente que les threads reducer se terminent
        for (Thread t : listThreadReducer) {
            t.join();
        }
        System.out.println("[*] ================ Les threads reducer sont terminés ================ [*]\n");

        //Affichage de la map finale
        DataStorage.setFinalOutput();
        System.out.println("La map finale : \n"+DataStorage.finalOutput+"\n");
        Chrono.Stop_Chrono(chrono);
    }
}
