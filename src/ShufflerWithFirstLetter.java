import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;

public class ShufflerWithFirstLetter {

    public static void suffle() throws IOException {
        String filePath = "./input/input.txt";
        Map<Character, Double> percentages = calculateFirstLetterPercentages(filePath);

        // Affiche le pourcentage d'apparition de chaque lettre
        for (Map.Entry<Character, Double> entry : percentages.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue() + "%");
        }

        System.out.println(getMaxValueFromMap(percentages));
    }



    public static Map<Character, Double> calculateFirstLetterPercentages(String filePath) throws IOException {
        Map<Character, Double> percentages = new HashMap<>();

        // Lecture du fichier ligne par ligne
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Traitement de chaque ligne
                String[] words = line.toLowerCase().split(" ");
                for (String word : words) {
                    // On ignore les lignes vides
                    if (!word.isEmpty()) {
                        char firstLetter = word.charAt(0);
                        if (percentages.containsKey(firstLetter)) {
                            percentages.put(firstLetter, percentages.get(firstLetter) + 1);
                        } else {
                            percentages.put(firstLetter, 1.0);
                        }
                    }
                }
            }
        }

        int totalWords = getTotalNumberOfWords(filePath);
        for (Map.Entry<Character, Double> entry : percentages.entrySet()) {
            percentages.put(entry.getKey(), entry.getValue() / totalWords * 100);
        }

        return percentages;
    }

    public static int getTotalNumberOfWords(String filePath) throws IOException {
        int totalWords = 0;

        // Lecture du fichier ligne par ligne
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Comptage du nombre de mots dans chaque ligne
                String[] words = line.split(" ");
                for (String word : words) {
                    // On ignore les lignes vides
                    if (!word.isEmpty()) {
                        totalWords++;
                    }
                }
            }
        }
        return totalWords;
    }

    public static Map.Entry<Character, Double> getMaxValueFromMap(Map<Character, Double> charMap) {
        Map.Entry<Character, Double> max = charMap.entrySet().iterator().next();
        for (Map.Entry<Character, Double> entry : charMap.entrySet()) {
            if (entry.getValue() > max.getValue()) {
                max = entry;
            }
        }
        return max;
    }

    public static double getSumValueFromMap(Map<Character, Double> charMap){
        double sum = 0.0;
        for (Map.Entry<Character, Double> entry : charMap.entrySet()) {
            sum += entry.getValue();
        }
        return sum;
    }



    public static int getMinIndexFromList(ArrayList<Double> list){
        double min = 0;
        int index = 0;
        for (int i=0 ; i < list.size() ; i++){
            if (list.get(i) < min){
                min = list.get(i);
                index = i;
            }
        }
        return index;
    }

}