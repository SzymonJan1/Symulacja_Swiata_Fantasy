public class Wrozki extends Agent {

    protected Wrozki(String name, int positionX, int positionY, int health){

        super( name, positionX, positionY, health);
    }

    @Override
    protected void Interaction() {

        if(Mapa.map[wayX][wayY] == null) { //na wybranej pozycji nie ma innego agenta
            Mapa.map[wayX][wayY] = name;
            Mapa.map[positionX][positionY] = null;
            positionX = wayX;
            positionY = wayY;
        }
        else {
            String nazwa;
            switch (Mapa.map[wayX][wayY].charAt(0)) {

                case 'k':    // interakcja z kosmita
                    prepareForRemoval();
                    nazwa = "l" + Symulacja.birthCount;
                    Mapa.map[wayX][wayY] = nazwa;
                    Symulacja.agents.add(new Ludzie(nazwa, wayX, wayY, 2));     //deklaracja czlowieka
                    Symulacja.birthCount++;     //nowy czlowiek
                    Symulacja.deathCount++;     //smierc kosmity
                    Symulacja.initialKosmici--;
                    Symulacja.initialLudzie++;
                    break;

                case 'r':    // interakcja z miesozerna roslina
                    Mapa.map[positionX][positionY] = null;     //wrozka umiera
                    name = "0";     // zmieniamy wartosci obiektu do usunięcia
                    Symulacja.deathCount++;
                    Symulacja.initialWrozki--;
                    nazwa = Mapa.map[wayX][wayY];      //przypisujemy zmiennej "nazwa" nazwe rosliny
                    for (int j = 0; j < Symulacja.agents.size(); j++) {     //roslina odzyskuje punkty zycia
                        if (Symulacja.agents.get(j).name.equals(nazwa)) {
                            if(Symulacja.agents.get(j).health != 8) Symulacja.agents.get(j).health = 8;
                        }
                    }
                    break;

                case'l':    // interakcja z czlowiekiem
                    nazwa = Mapa.map[wayX][wayY];      // czlowiek odzyskuje punkt zycia
                    for (int j = 0; j < Symulacja.agents.size(); j++) {
                        if(Symulacja.agents.get(j).name.equals(nazwa)) {
                            if(Symulacja.agents.get(j).health == 1) Symulacja.agents.get(j).health++;
                        }
                    }
                    break;
            }
        }
        waysForX.clear();       //czyscimy przetasowane listy
        waysForY.clear();
    }
}
