public class Mapper implements Runnable {
    int i;
    public Mapper(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        System.out.println(WordCounter.countAndPrintWords("./output/output"+i+".txt"));
    }
}
