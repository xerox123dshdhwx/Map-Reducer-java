import java.util.ArrayList;
import java.util.Map;

public class Shuffler {

    public static void shuffle(ArrayList<Reducer> reducerList, Map<String, Integer> wordCount){
        int nbReducer = reducerList.size();
        for (Map.Entry<String, Integer> entryWord : wordCount.entrySet()) {
            int reducerIndex = getShuffleIndex(entryWord, nbReducer);
            affectWordToReducer(entryWord, reducerIndex, reducerList);
        }
    }

    public static int getShuffleIndex(Map.Entry<String,Integer> entryWord, int nbReducer){
        int wordSize = entryWord.getKey().length();
        return wordSize % nbReducer;
    }

    public static void affectWordToReducer(Map.Entry<String,Integer> entryWord, int index, ArrayList<Reducer> reducerList){
        Reducer reducer = reducerList.get(index);
        reducer.getWordMap().add(entryWord);
    }
}
