import java.util.Collections;

public class Wrozki extends Agent {

    public Wrozki(String name, int positionX, int positionY, int health){

        super( name, positionX, positionY, health);
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
            Mapa.map[wayX][wayY] = name;
            Mapa.map[positionX][positionY] = null;
            positionX = wayX;
            positionY = wayY;
        }
        else {
            String nazwa;
            switch (Mapa.map[wayX][wayY].charAt(0)) {
                case 'k':    // interakcja z kosmita
                    for (int i = 0; i < Symulacja.agents.size(); i++) { // zmieniamy wartosci obiektu do usunięcia
                        if(Symulacja.agents.get(i).positionX == wayX && Symulacja.agents.get(i).positionY == wayY) {
                            Symulacja.agents.get(i).name = "0";
                            Symulacja.agents.get(i).positionX = -1;
                            Symulacja.agents.get(i).positionY = -1;
                        }
                    }
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
                    name = "0";
                    positionX = -1;     // zmieniamy wartosci obiektu do usunięcia
                    positionY = -1;
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
