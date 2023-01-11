import java.util.ArrayList;
import java.util.Map;

public class Mapper implements Runnable {
    private int i;
    ArrayList<Reducer> listReducer;

    public Mapper(int i, ArrayList<Reducer> listReducer) {
        this.i = i;
        this.listReducer = listReducer;
    }

    @Override
    public void run() {
        Map<String, Integer> wordCount = WordCounter.countAndPrintWords("./output/output" + i + ".txt");
        DataStorage.mapperRes.add(wordCount);
        System.out.println("Mapper "+i+" terminé");
    }
}
