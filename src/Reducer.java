import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Reducer implements Runnable {
    private int i;

    public Reducer(int i) {
        this.i = i;
    }

    public void reduce(){
        ArrayList<Map.Entry<String, Integer>> wordMap = DataStorage.sufflerRes.get(i);
        Map<String, Integer> map = new HashMap<>();
        for (Map.Entry<String, Integer> word : wordMap){
            if (map.containsKey(word.getKey())) {
                map.put(word.getKey(), map.get(word.getKey()) + word.getValue());
            } else {
                map.put(word.getKey(), word.getValue());
            }
        }
        DataStorage.reducerRes.add(map);
    }

    @Override
    public void run() {
        reduce();
        System.out.println("Reducer "+i+" terminé");
    }
}
