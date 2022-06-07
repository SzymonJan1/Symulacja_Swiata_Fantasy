import java.util.Collections;
import java.util.Random;

public class Smoki extends Agent {

    public Smoki(String name, int positionX, int positionY, int health) {

        super(name, positionX, positionY, health);

    }

    @Override
    public void Shift_X_Y() {

        if (positionX == 0 && positionY == 0) {  //lewy gorny rog mapy
            waysForX.add(0);
            waysForX.add(1);
            waysForY.add(0);
            waysForY.add(1);
        } else if (positionX == Mapa.x - 1 && positionY == 0) { //prawy gorny rog mapy
            waysForX.add(Mapa.x - 1);
            waysForX.add(Mapa.x - 2);
            waysForY.add(0);
            waysForY.add(1);
        } else if (positionX == 0 && positionY == Mapa.y - 1) { //lewy dolny rog mapy
            waysForX.add(0);
            waysForX.add(1);
            waysForY.add(Mapa.y - 1);
            waysForY.add(Mapa.y - 2);
        } else if (positionX == Mapa.x - 1 && positionY == Mapa.y - 1) { //prawy dolny rog mapy
            waysForX.add(Mapa.x - 1);
            waysForX.add(Mapa.x - 2);
            waysForY.add(Mapa.y - 1);
            waysForY.add(Mapa.y - 2);
        } else if (positionX > 0 && positionX < Mapa.x - 1 && positionY == 0) { //przy gornej krawedzi mapy
            waysForX.add(positionX);
            waysForX.add(positionX - 1);
            waysForX.add(positionX + 1);
            waysForY.add(0);
            waysForY.add(1);
        } else if (positionX > 0 && positionX < Mapa.x - 1 && positionY == Mapa.y - 1) { //przy dolnej krawedzi mapy
            waysForX.add(positionX);
            waysForX.add(positionX - 1);
            waysForX.add(positionX + 1);
            waysForY.add(Mapa.y - 1);
            waysForY.add(Mapa.y - 2);
        } else if (positionX == 0 && positionY > 0 && positionY < Mapa.y - 1) { // przy krawedzi z lewej strony mapy
            waysForX.add(0);
            waysForX.add(1);
            waysForY.add(positionY);
            waysForY.add(positionY - 1);
            waysForY.add(positionY + 1);
        } else if (positionX == Mapa.x - 1 && positionY > 0 && positionY < Mapa.y - 1) { //przy krawedzi z prawej strony mapy
            waysForX.add(Mapa.x - 1);
            waysForX.add(Mapa.x - 2);
            waysForY.add(positionY);
            waysForY.add(positionY - 1);
            waysForY.add(positionY + 1);
        } else if (positionX > 0 && positionX < Mapa.x - 1 && positionY > 0 && positionY < Mapa.y - 1) {
            waysForX.add(positionX);
            waysForX.add(positionX - 1);
            waysForX.add(positionX + 1);
            waysForY.add(positionY);
            waysForY.add(positionY - 1);
            waysForY.add(positionY + 1);
        }
        Collections.shuffle(waysForX);
        Collections.shuffle(waysForY);
        // niech pierwsze elementy przetasowanych list beda nowymi wspolrzednymi

        wayX = waysForX.get(0);
        wayY = waysForY.get(0);

        for (; ; ) {        //nieskonczona petla
            if (wayX == positionX && wayY == positionY) {        //warunek, aby nie wylosowac tej samej pozycji
                Collections.shuffle(waysForX);
                Collections.shuffle(waysForY);
                wayX = waysForX.get(0);
                wayY = waysForY.get(0);
            } else break;
        }
    }

    @Override
    public void Interaction() {

        if(Mapa.map[wayX][wayY] == null) { //na wybranej pozycji nie ma innego agenta
            health--;       //traci punkt zycia bo sie nie pozywil
            if(health == 0) {
                Mapa.map[positionX][positionY] = null;
                name = "0";
                positionX = -1;     // zmieniamy wartosci obiektu do usunięcia
                positionY = -1;
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
                    for (int i = 0; i < Symulacja.agents.size(); i++) { // zmieniamy wartosci obiektu do usunięcia
                        if(Symulacja.agents.get(i).positionX == wayX && Symulacja.agents.get(i).positionY == wayY) {
                            Symulacja.agents.get(i).name = "0";
                            Symulacja.agents.get(i).positionX = -1;
                            Symulacja.agents.get(i).positionY = -1;
                        }
                    }
                    positionX = wayX;       //zmieniamy wspolrzedne agenta
                    positionY = wayY;
                    Symulacja.deathCount++;     //aktualizacja danych
                    Symulacja.initialLudzie--;
                    if(health != 5) health = 5;     //smok odzyskuje punkty zycia
                    break;
                case 's':       //spotyka innego smoka
                    Random random = new Random();
                    nazwa = "s" + Symulacja.birthCount;     //nazwa nowego smoka
                    int pozycjaX = random.nextInt(Mapa.x);     //losujemy wspolrzedne
                    int pozycjaY = random.nextInt(Mapa.y);
                    for (int j = 0; j < Symulacja.agents.size(); j++) {
                        if (Symulacja.agents.get(j).positionX == pozycjaX && Symulacja.agents.get(j).positionY == pozycjaY) {
                            pozycjaX = random.nextInt(Mapa.x);
                            pozycjaY = random.nextInt(Mapa.y);
                            j = -1;
                        }
                    }
                    if(health>=3) {
                        Symulacja.agents.add(new Smoki(nazwa, pozycjaX, pozycjaY, 5));     //deklaracja nowego smoka
                        Mapa.map[pozycjaX][pozycjaY] = nazwa;
                        Symulacja.birthCount++;     //aktualizacja danych
                        Symulacja.initialSmoki++;
                    }
                    health--;       //agent traci punkt zycia
                    if(health == 0) {
                        Mapa.map[positionX][positionY] = null;
                        name = "0";
                        positionX = -1;     // zmieniamy wartosci obiektu do usunięcia
                        positionY = -1;
                        Symulacja.deathCount++;     //aktualizacja danych
                        Symulacja.initialSmoki--;
                    }
                    break;
                default:
                    health--;       //smok traci punkt zycia
                    if(health == 0) {
                        Mapa.map[positionX][positionY] = null;
                        name = "0";
                        positionX = -1;     // zmieniamy wartosci obiektu do usunięcia
                        positionY = -1;
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
