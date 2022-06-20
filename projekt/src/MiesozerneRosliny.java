public class MiesozerneRosliny extends Agent {

    protected MiesozerneRosliny(String name, int positionX, int positionY, int health) {

        super(name, positionX, positionY, health);
    }

    @Override
    protected void Interaction() {

        /**na wybranej pozycji nie ma innego agenta*/
        if (Mapa.map[wayX][wayY] == null) {
            /**roslina traci punkt zycia*/
            health--;
            if (health == 0) {
                Mapa.map[positionX][positionY] = null;
                /** zmieniamy wartosci obiektu do usuniecia*/
                name = "0";
                Symulacja.deathCount++;
                Symulacja.initialRosliny--;
            }
        }
        else {
            String nazwa;
            switch (Mapa.map[wayX][wayY].charAt(0)) {

                /**spotyka wrozke*/
                case 'w':
                    Mapa.map[wayX][wayY] = null;
                    prepareForRemoval();
                    Symulacja.deathCount++;
                    Symulacja.initialWrozki--;
                    /**odzyskuje zycie*/
                    if (health != 8) health = 8;
                    break;

                /**spotyka czlowieka*/
                case 'l':
                    /**przypisujemy zmiennej "nazwa" nazwe czlowieka*/
                    nazwa = Mapa.map[wayX][wayY];
                    for (int j = 0; j < Symulacja.agents.size(); j++) {
                        if (Symulacja.agents.get(j).name.equals(nazwa)) {
                            /**czlowiek traci punkt zycia*/
                            Symulacja.agents.get(j).health--;
                            /**czlowiek umiera*/
                            if (Symulacja.agents.get(j).health == 0) {
                                Mapa.map[wayX][wayY] = null;
                                prepareForRemoval();
                                /**aktualizacja danych*/
                                Symulacja.deathCount++;
                                Symulacja.initialLudzie--;
                            }
                        }
                    }
                    /**roslina odzyskuje zycie*/
                    if (health != 8) health = 8;
                    break;

                default:
                    /**roslina traci punkt zycia*/
                    health--;
                    /**roslina umiera*/
                    if(health == 0) {
                        Mapa.map[positionX][positionY] = null;
                        /** zmieniamy wartosci obiektu do usuniecia*/
                        name = "0";
                        Symulacja.deathCount++;
                        Symulacja.initialRosliny--;
                        break;
                    }
            }
        }
        waysForX.clear();
        waysForY.clear();
    }
}
