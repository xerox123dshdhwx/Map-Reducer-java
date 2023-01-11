public class Chrono {
    static long Go_Chrono() {
        long chrono = java.lang.System.currentTimeMillis() ;
        return chrono;
    }
    static void Stop_Chrono(long chrono) {
        long chrono2 = java.lang.System.currentTimeMillis() ;
        long temps = chrono2 - chrono ;
        System.out.println("Temps ecoule = " + temps + " ms") ;
    }
}
