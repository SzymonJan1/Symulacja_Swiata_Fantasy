import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.System.exit;

abstract class Agent {

    protected int positionX, positionY;
    protected int health;
    protected String name;
    protected List<Integer> waysForX = new ArrayList<>(); //możliwe przesunięcia w osi x
    protected List<Integer> waysForY = new ArrayList<>(); //możliwe przesunięcia w osi y
    protected static int wayX, wayY; //przesunięcie w osi x lub y, wyliczane przed zmianą pozycji

    protected Agent(String name, int positionX, int positionY, int health) {

        this.name = name;
        this.positionX = positionX;
        this.positionY = positionY;
        this.health = health;
    }

    protected void Shift_X_Y() {

        if ((Mapa.x == 1 && Mapa.y == 1) || Mapa.x < 1 || Mapa.y < 1) {
            System.out.println("Dla podanych parametrów nie można przeprowadzić symulacji!");
            exit(0);
        }

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

    protected void prepareForRemoval() {
        for (int i = 0; i < Symulacja.agents.size(); i++) { // zmieniamy wartosci obiektu do usunięcia
            if(Symulacja.agents.get(i).positionX == wayX && Symulacja.agents.get(i).positionY == wayY) {
                Symulacja.agents.get(i).name = "0";
            }
        }
    }

    protected void Interaction() {

    }
}
