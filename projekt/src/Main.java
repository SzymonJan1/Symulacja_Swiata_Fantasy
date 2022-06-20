public class Main {

    public static void main(String[] args) {
        Symulacja symulacja = new Symulacja();
        Mapa mapa = new Mapa();
        /**inicjalizacja mapy*/
        mapa.mapInitialization();
        /**dodanie agentow*/
        mapa.addAgents();
        /**wywolanie symulacji*/
        symulacja.simulate();
    }
}
