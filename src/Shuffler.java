import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Shuffler {
    public static List<Map.Entry<String, Integer>> shuffle(Map<String, Integer> wordCounts) {
        List<Map.Entry<String, Integer>> shuffledList = new ArrayList<>(wordCounts.entrySet());
        shuffledList.sort(Map.Entry.comparingByKey());
        return shuffledList;
    }
}