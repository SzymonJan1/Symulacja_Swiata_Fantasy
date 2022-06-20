import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.System.exit;

/**abstrakcyjna klasa, po ktorej dziedzicza wszystkie klasy poszczegolnych agentow*/
public abstract class Agent {

    /**aktualne wspolrzedne agenta*/
    protected int positionX, positionY;
    protected int health;
    /**nazwa wlasna agenta, wyswietlana na mapie*/
    protected String name;
    /**mozliwe przesuniecia w osi x*/
    protected List<Integer> waysForX = new ArrayList<>();
    /**mozliwe przesuniecia w osi y*/
    protected List<Integer> waysForY = new ArrayList<>();
    /**przesuniecie w osi x lub y, wyliczane przed zmiana pozycji*/
    protected static int wayX, wayY;

    protected Agent(String name, int positionX, int positionY, int health) {

        this.name = name;
        this.positionX = positionX;
        this.positionY = positionY;
        this.health = health;
    }

    /**wyznaczenie wspolrzednych pola, na ktore przemieszcza sie agent*/
    protected void Shift_X_Y() {

        /**parametry mapy dla ktorych nie mozna przeprowadzic symulacji*/
        if ((Mapa.x == 1 && Mapa.y == 1) || Mapa.x < 1 || Mapa.y < 1) {
            System.out.println("Dla podanych parametrów nie można przeprowadzić symulacji!");
            exit(0);
        }

        /**warunek, ze szerokosc mapy jest rowna 1*/
        else if (Mapa.x == 1) {
            waysForX.add(0);
            if (positionY == 0) {
                waysForY.add(1);
            }
            else if (positionY == Mapa.y - 1) {
                waysForY.add(Mapa.y - 2);
            }
            else {
                waysForY.add(positionY - 1);
                waysForY.add(positionY + 1);
            }
        }
        /**warunek, ze dlugosc mapy jest rowna 1*/
        else if (Mapa.y == 1) {
            waysForY.add(0);
            if (positionX == 0) {
                waysForX.add(1);
            }
            else if (positionX == Mapa.x - 1) {
                waysForX.add(Mapa.x - 2);
            }
            else {
                waysForX.add(positionX - 1);
                waysForX.add(positionX + 1);
            }
        }
        else {
            /**lewy gorny rog mapy*/
            if (positionX == 0 && positionY == 0) {
                waysForX.add(0);
                waysForX.add(1);
                waysForY.add(0);
                waysForY.add(1);
                /**prawy gorny rog mapy*/
            } else if (positionX == Mapa.x - 1 && positionY == 0) {
                waysForX.add(Mapa.x - 1);
                waysForX.add(Mapa.x - 2);
                waysForY.add(0);
                waysForY.add(1);
                /**lewy dolny rog mapy*/
            } else if (positionX == 0 && positionY == Mapa.y - 1) {
                waysForX.add(0);
                waysForX.add(1);
                waysForY.add(Mapa.y - 1);
                waysForY.add(Mapa.y - 2);
                /**prawy dolny rog mapy*/
            } else if (positionX == Mapa.x - 1 && positionY == Mapa.y - 1) {
                waysForX.add(Mapa.x - 1);
                waysForX.add(Mapa.x - 2);
                waysForY.add(Mapa.y - 1);
                waysForY.add(Mapa.y - 2);
                /**przy gornej krawedzi mapy*/
            } else if (positionX > 0 && positionX < Mapa.x - 1 && positionY == 0) {
                waysForX.add(positionX);
                waysForX.add(positionX - 1);
                waysForX.add(positionX + 1);
                waysForY.add(0);
                waysForY.add(1);
                /**przy dolnej krawedzi mapy*/
            } else if (positionX > 0 && positionX < Mapa.x - 1 && positionY == Mapa.y - 1) {
                waysForX.add(positionX);
                waysForX.add(positionX - 1);
                waysForX.add(positionX + 1);
                waysForY.add(Mapa.y - 1);
                waysForY.add(Mapa.y - 2);
                /** przy krawedzi z lewej strony mapy*/
            } else if (positionX == 0 && positionY > 0 && positionY < Mapa.y - 1) {
                waysForX.add(0);
                waysForX.add(1);
                waysForY.add(positionY);
                waysForY.add(positionY - 1);
                waysForY.add(positionY + 1);
                /**przy krawedzi z prawej strony mapy*/
            } else if (positionX == Mapa.x - 1 && positionY > 0 && positionY < Mapa.y - 1) {
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
        }
        /**przetasowanie list wspolrzednych*/
        Collections.shuffle(waysForX);
        Collections.shuffle(waysForY);
        /** niech pierwsze elementy przetasowanych list beda nowymi wspolrzednymi*/
        wayX = waysForX.get(0);
        wayY = waysForY.get(0);
        /**nieskonczona petla*/
        for (; ; ) {
            /**warunek, aby nie wylosowac tej samej pozycji*/
            if (wayX == positionX && wayY == positionY) {
                Collections.shuffle(waysForX);
                Collections.shuffle(waysForY);
                wayX = waysForX.get(0);
                wayY = waysForY.get(0);
            } else break;
        }
    }
    /** zmieniamy wartosci obiektu do usuniecia*/
    protected void prepareForRemoval() {
        for (int i = 0; i < Symulacja.agents.size(); i++) {
            if(Symulacja.agents.get(i).positionX == wayX && Symulacja.agents.get(i).positionY == wayY) {
                Symulacja.agents.get(i).name = "0";
            }
        }
    }

    /**interakcje miedzy agentami*/
    protected void Interaction() {

    }
}
