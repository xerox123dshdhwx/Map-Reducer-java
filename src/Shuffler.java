import java.util.Map;

public class Shuffler {


    public static void shuffle(Map<String, Integer> wordCount){
        int nbReducer =  DataStorage.listReducer.size();
        for (Map.Entry<String, Integer> entryWord : wordCount.entrySet()) {
            int reducerIndex = getShuffleIndex(entryWord, nbReducer);
            affectWordToReducer(entryWord, reducerIndex);
        }
    }

    public static int getShuffleIndex(Map.Entry<String,Integer> entryWord, int nbReducer){
        int wordSize = entryWord.getKey().length();
        return wordSize % nbReducer;
    }

    public static void affectWordToReducer(Map.Entry<String,Integer> entryWord, int index){
        DataStorage.sufflerRes.get(index).add(entryWord);
    }
}
