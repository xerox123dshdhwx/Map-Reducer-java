import java.io.*;
import java.util.Arrays;

public class FileSplitter {

    static String removeChar(String s, char c) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != c) {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

    public static void splitFile(String file, int numberOfPieces) {
        String path = "./input/";
        String inputFile = path + file;
        String outputFile = "./output/output";

        BufferedReader reader = null;
        BufferedWriter[] writers = new BufferedWriter[numberOfPieces];

        try {
            reader = new BufferedReader(new FileReader(inputFile));

            String line;
            int lineCount = 0;
            while ((line = reader.readLine()) != null) {
                lineCount++;
            }
            reader.close();

            reader = new BufferedReader(new FileReader(inputFile));

            int linesPerPiece = (int) Math.ceil((double) lineCount / numberOfPieces);
            for (int i = 0; i < numberOfPieces; i++) {
                writers[i] = new BufferedWriter(new FileWriter(outputFile + i + ".txt"));
            }

            int currentPiece = 0;
            int currentLine = 0;
            while ((line = reader.readLine()) != null) {
                String l = removeChar(line, '.');
                writers[currentPiece].write(l.toLowerCase());
                writers[currentPiece].newLine();
                if (++currentLine == linesPerPiece) {
                    currentLine = 0;
                    if (++currentPiece == numberOfPieces) {
                        currentPiece = 0;
                    }
                }
            }

            for (int i = 0; i < numberOfPieces; i++) {
                writers[i].close();
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
