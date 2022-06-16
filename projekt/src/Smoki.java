public class Smoki extends Agent {

    protected Smoki(String name, int positionX, int positionY, int health) {

        super(name, positionX, positionY, health);

    }

    @Override
    protected void Interaction() {

        if(Mapa.map[wayX][wayY] == null) { //na wybranej pozycji nie ma innego agenta
            health--;       //traci punkt zycia bo sie nie pozywil
            if(health == 0) {
                Mapa.map[positionX][positionY] = null;
                name = "0";     // zmieniamy wartosci obiektu do usunięcia
                Symulacja.deathCount++;
                Symulacja.initialSmoki--;
            }
            else {
                Mapa.map[wayX][wayY] = name;       //przechodzi na puste pole
                Mapa.map[positionX][positionY] = null;
                positionX = wayX;
                positionY = wayY;
            }
        }
        else {
            String nazwa;
            switch(Mapa.map[wayX][wayY].charAt(0)) {

                case 'l':       // spotyka czlowieka
                    Mapa.map[wayX][wayY] = name;   //wpisujemy nazwe smoka, w pole czlowieka
                    Mapa.map[positionX][positionY] = null;     //stare pole smoka dajemy jako null
                    prepareForRemoval();
                    positionX = wayX;       //zmieniamy wspolrzedne agenta
                    positionY = wayY;
                    Symulacja.deathCount++;     //aktualizacja danych
                    Symulacja.initialLudzie--;
                    if(health != 5) health = 5;     //smok odzyskuje punkty zycia
                    break;

                case 's':       //spotyka innego smoka
                    if(health>1) {
                    nazwa = "s" + Symulacja.birthCount;     //nazwa nowego smoka
                    Mapa.randomizePositions();
                        Symulacja.agents.add(new Smoki(nazwa, Mapa.pozycjaX, Mapa.pozycjaY, 5));     //deklaracja nowego smoka
                        Mapa.map[Mapa.pozycjaX][Mapa.pozycjaY] = nazwa;
                        Symulacja.birthCount++;     //aktualizacja danych
                        Symulacja.initialSmoki++;
                    }
                    health--;       //agent traci punkt zycia
                    if(health == 0) {
                        Mapa.map[positionX][positionY] = null;
                        name = "0";     // zmieniamy wartosci obiektu do usunięcia
                        Symulacja.deathCount++;     //aktualizacja danych
                        Symulacja.initialSmoki--;
                    }
                    break;

                default:
                    health--;       //smok traci punkt zycia
                    if(health == 0) {
                        Mapa.map[positionX][positionY] = null;
                        name = "0";     // zmieniamy wartosci obiektu do usunięcia
                        Symulacja.deathCount++;     //aktualizacja danych
                        Symulacja.initialSmoki--;
                        break;
                    }
            }
        }
        waysForX.clear();       //czyscimy przetasowane listy
        waysForY.clear();
    }
}
