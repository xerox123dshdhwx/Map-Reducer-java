import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Mapper implements Runnable {
    private int i;
    ArrayList<Reducer> listReducer;

    public Mapper(int i, ArrayList<Reducer> listReducer) {
        this.i = i;
        this.listReducer = listReducer;
    }

    public Map<String, Integer> countAndPrintWords(String fileName) {
        Map<String, Integer> wordCount = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    if (wordCount.containsKey(word)) {
                        wordCount.put(word, wordCount.get(word) + 1);
                    } else {
                        wordCount.put(word, 1);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wordCount;
    }

    @Override
    public void run() {
        Map<String, Integer> wordCount = countAndPrintWords("./output/output" + i + ".txt");
        DataStorage.mapperRes.add(wordCount);
        System.out.println("Mapper "+i+" terminé");
    }
}
