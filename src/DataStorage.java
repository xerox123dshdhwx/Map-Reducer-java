import java.util.*;
import java.util.Map.Entry;

public class DataStorage {
    static ArrayList<Map<String, Integer>> mapperRes = new ArrayList<>();
    static ArrayList<ArrayList<Map.Entry<String, Integer>>> sufflerRes = new ArrayList<>();
    static ArrayList<Map<String, Integer>> reducerRes = new ArrayList<>();
    static ArrayList<Reducer> listReducer = new ArrayList<>();
    static List<Map.Entry<String, Integer>> finalOutput = new ArrayList<>();

    public static void setFinalOutput() {
        Map<String, Integer> concatMap = concatenateMap(reducerRes);
        List<Entry<String, Integer>> list = new ArrayList<>(concatMap.entrySet());
        list.sort(Entry.comparingByValue(Comparator.reverseOrder()));
        finalOutput = list;
    }

    public static Map<String, Integer> concatenateMap(ArrayList<Map<String, Integer>> mapList) {
        Map<String, Integer> map = mapList.get(0);
        for (int i = 1; i < mapList.size(); ++i) {
            for (Map.Entry<String, Integer> m : mapList.get(i).entrySet()) {
                if (!map.containsKey(m.getKey())) map.put(m.getKey(), m.getValue());
            }
        }
        return map;
    }

    public static void initSufflerRes(int nbReducer) {
        for (int i = 0; i < nbReducer; i++) {
            sufflerRes.add(new ArrayList<>());
        }
    }


}

