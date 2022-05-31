public class Main {

    public static void main(String[] args) {
        Symulacja symulacja = new Symulacja();
        Mapa mapa = new Mapa();
        mapa.mapInitialization();
        mapa.addAgents();
        symulacja.simulate();
    }
}
