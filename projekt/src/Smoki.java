public class Smoki extends Agent {

    protected Smoki(String name, int positionX, int positionY, int health) {

        super(name, positionX, positionY, health);

    }

    @Override
    protected void Interaction() {

        /**na wybranej pozycji nie ma innego agenta*/
        if(Mapa.map[wayX][wayY] == null) {
            /**traci punkt zycia bo sie nie pozywil*/
            health--;
            if(health == 0) {
                Mapa.map[positionX][positionY] = null;
                /** zmieniamy wartosci obiektu do usuniecia*/
                name = "0";
                Symulacja.deathCount++;
                Symulacja.initialSmoki--;
            }
            else {
                /**przechodzi na puste pole*/
                Mapa.map[wayX][wayY] = name;
                Mapa.map[positionX][positionY] = null;
                positionX = wayX;
                positionY = wayY;
            }
        }
        else {
            String nazwa;
            switch(Mapa.map[wayX][wayY].charAt(0)) {

                /** spotyka czlowieka*/
                case 'l':
                    /**wpisujemy nazwe smoka, w pole czlowieka*/
                    Mapa.map[wayX][wayY] = name;
                    /**stare pole smoka dajemy jako null*/
                    Mapa.map[positionX][positionY] = null;
                    prepareForRemoval();
                    /**zmieniamy wspolrzedne agenta*/
                    positionX = wayX;
                    positionY = wayY;
                    /**aktualizacja danych*/
                    Symulacja.deathCount++;
                    Symulacja.initialLudzie--;
                    /**smok odzyskuje punkty zycia*/
                    if(health != 9) health = 9;
                    break;

                /**spotyka innego smoka*/
                case 's':
                    if(health>1) {
                        /**nazwa nowego smoka*/
                    nazwa = "s" + Symulacja.birthCount;
                    Mapa.randomizePositions();
                        /**deklaracja nowego smoka*/
                        Symulacja.agents.add(new Smoki(nazwa, Mapa.pozycjaX, Mapa.pozycjaY, 9));
                        Mapa.map[Mapa.pozycjaX][Mapa.pozycjaY] = nazwa;
                        /**aktualizacja danych*/
                        Symulacja.birthCount++;
                        Symulacja.initialSmoki++;
                    }
                    /**agent traci punkt zycia*/
                    health--;
                    if(health == 0) {
                        Mapa.map[positionX][positionY] = null;
                        /** zmieniamy wartosci obiektu do usuniecia*/
                        name = "0";
                        /**aktualizacja danych*/
                        Symulacja.deathCount++;
                        Symulacja.initialSmoki--;
                    }
                    break;

                default:
                    /**smok traci punkt zycia*/
                    health--;
                    if(health == 0) {
                        Mapa.map[positionX][positionY] = null;
                        /** zmieniamy wartosci obiektu do usuniecia*/
                        name = "0";
                        /**aktualizacja danych*/
                        Symulacja.deathCount++;
                        Symulacja.initialSmoki--;
                        break;
                    }
            }
        }
        /**czyscimy przetasowane listy*/
        waysForX.clear();
        waysForY.clear();
    }
}
