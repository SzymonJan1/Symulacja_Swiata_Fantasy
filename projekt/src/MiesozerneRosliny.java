public class MiesozerneRosliny extends Agent {

    protected MiesozerneRosliny(String name, int positionX, int positionY, int health) {

        super(name, positionX, positionY, health);
    }

    @Override
    protected void Interaction() {

        if (Mapa.map[wayX][wayY] == null) { //na wybranej pozycji nie ma innego agenta
            health--;       //roslina traci punkt zycia
            if (health == 0) {
                Mapa.map[positionX][positionY] = null;
                name = "0";     // zmieniamy wartosci obiektu do usunięcia
                Symulacja.deathCount++;
                Symulacja.initialRosliny--;
            }
        }
        else {
            String nazwa;
            switch (Mapa.map[wayX][wayY].charAt(0)) {

                case 'w':       //spotyka wrozke
                    Mapa.map[wayX][wayY] = null;
                    prepareForRemoval();
                    Symulacja.deathCount++;
                    Symulacja.initialWrozki--;
                    if (health != 8) health = 8;  //odzyskuje zycie
                    break;

                case 'l': //spotyka czlowieka
                    nazwa = Mapa.map[wayX][wayY];      //przypisujemy zmiennej "nazwa" nazwe czlowieka
                    for (int j = 0; j < Symulacja.agents.size(); j++) {
                        if (Symulacja.agents.get(j).name.equals(nazwa)) {
                            Symulacja.agents.get(j).health--;       //czlowiek traci punkt zyciea
                            if (Symulacja.agents.get(j).health == 0) {      //czlowiek umiera
                                Mapa.map[wayX][wayY] = null;
                                prepareForRemoval();
                                Symulacja.deathCount++;     //aktualizacja danych
                                Symulacja.initialLudzie--;
                            }
                        }
                    }
                    if (health != 8) health = 8;      //roslina odzyskuje zycie
                    break;

                default:
                    health--;       //roslina traci punkt zycia
                    if(health == 0) {       //roslina umiera
                        Mapa.map[positionX][positionY] = null;
                        name = "0";     // zmieniamy wartosci obiektu do usunięcia
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
