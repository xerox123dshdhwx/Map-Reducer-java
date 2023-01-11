import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

public class Reducer implements Runnable {
    private int i;
    private final ArrayList<Map.Entry<String, Integer>> wordMap = new ArrayList<>();

    public Reducer(int i) {
        this.i = i;
    }

    public void reduce(){
        Map<String, Integer> map = new HashMap<>();
        for (Map.Entry<String, Integer> word : wordMap){
            if (map.containsKey(word.getKey())) {
                map.put(word.getKey(), map.get(word.getKey()) + word.getValue());
            } else {
                map.put(word.getKey(), word.getValue());
            }
        }
        Main.res.add(map);
        System.out.println(map);
    }

    @Override
    public void run() {
        reduce();
    }


    public int getI() {
        return i;
    }

    public ArrayList<Map.Entry<String, Integer>> getWordMap() {
        return wordMap;
    }

    @Override
    public String toString() {
        return "Reducer{" +
                "i=" + i +
                ", wordMap=" + wordMap +
                '}';
    }
}
