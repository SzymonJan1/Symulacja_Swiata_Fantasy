import java.util.Collections;

public class MiesozerneRosliny extends Agent {

    public MiesozerneRosliny(String name, int positionX, int positionY, int health) {

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

        int wayX, wayY;
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

        if (Mapa.map[wayX][wayY] == null) { //na wybranej pozycji nie ma innego agenta
            health--;       //roslina traci punkt zycia
            if (health == 0) {
                Mapa.map[positionX][positionY] = null;
                name = "0";
                positionX = -1;     // zmieniamy wartosci obiektu do usunięcia
                positionY = -1;
                Symulacja.deathCount++;
                Symulacja.initialRosliny--;
            }
        }
        else {
            String nazwa;
            switch (Mapa.map[wayX][wayY].charAt(0)) {
                case 'w':       //spotyka wrozke
                    Mapa.map[wayX][wayY] = null;
                    for (int i = 0; i < Symulacja.agents.size(); i++) { // zmieniamy wartosci obiektu do usunięcia
                        if(Symulacja.agents.get(i).positionX == wayX && Symulacja.agents.get(i).positionY == wayY) {
                            Symulacja.agents.get(i).name = "0";
                            Symulacja.agents.get(i).positionX = -1;
                            Symulacja.agents.get(i).positionY = -1;
                        }
                    }
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
                                for (int i = 0; i < Symulacja.agents.size(); i++) { // zmieniamy wartosci obiektu do usunięcia
                                    if(Symulacja.agents.get(i).positionX == wayX && Symulacja.agents.get(i).positionY == wayY) {
                                        Symulacja.agents.get(i).name = "0";
                                        Symulacja.agents.get(i).positionX = -1;
                                        Symulacja.agents.get(i).positionY = -1;
                                    }
                                }
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
                        name = "0";
                        positionX = -1;     // zmieniamy wartosci obiektu do usunięcia
                        positionY = -1;
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
