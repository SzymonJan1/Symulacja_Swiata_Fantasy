import java.util.Collections;
import java.util.Random;

public class Ludzie extends Agent {

    protected Ludzie(String name, int positionX, int positionY, int health) {

        super(name, positionX, positionY, health);
    }

    @Override
    protected void Shift_X_Y() {

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
    protected void Interaction() {

            if (Mapa.map[wayX][wayY] == null) { //na wybranej pozycji nie ma innego agenta
                Mapa.map[wayX][wayY] = name;       //przypisywanie nowemu polu nazwy agenta
                Mapa.map[positionX][positionY] = null;     //przypisywanie staremu polu wartosci null
                positionX = wayX;   //przypisywanie nowych wspolrzednych agentowi
                positionY = wayY;
            } else {    //na wybranej pozycji znajduje sie jakis agent
                String nazwa;
                switch (Mapa.map[wayX][wayY].charAt(0)) {  //sprawdzanie pierszego znaku nazwy agenta
                    case 'k':       //pierwszy znak to k, czyli spotyka kosmite
                        nazwa = "k" + Symulacja.birthCount;     //tworzenie nazwy dla nowego kosmity
                        Mapa.map[positionX][positionY] = nazwa;    //przypisujemy polu na ktorym stal czlowiek, nazwe nowego kosmity
                        Symulacja.agents.add(new Kosmici(nazwa, positionX, positionY, 0)); //deklaracja nowego kosmity
                        name = "0";
                        positionX = -1;     // zmieniamy wartosci obiektu do usunięcia
                        positionY = -1;
                        Symulacja.birthCount++;     //aktualizujemy dane
                        Symulacja.deathCount++;
                        Symulacja.initialKosmici++;
                        Symulacja.initialLudzie--;
                        break;
                    case 's':       //pierwszy znak to s, czyli spotyka smoka
                        Mapa.map[positionX][positionY] = null; //przypisujemy null polu na ktorym stal czlowiek
                        name = "0";
                        positionX = -1;     // zmieniamy wartosci obiektu do usunięcia
                        positionY = -1;
                        Symulacja.deathCount++;     //aktualizujemy dane
                        Symulacja.initialLudzie--;
                        nazwa = Mapa.map[wayX][wayY];     //przypisujemy zmiennej "nazwa" wartosc pola ze smokiem
                        for (int j = 0; j < Symulacja.agents.size(); j++) {
                            if (Symulacja.agents.get(j).name.equals(nazwa)) {       //wyszukanie agenta o nazwie "nazwa" na liscie
                                if (Symulacja.agents.get(j).health != 5) Symulacja.agents.get(j).health = 5;    //jesli smok nie ma wszystkich zyć, odzyskuje je
                            }
                        }
                        break;
                    case 'l':       //pierwszy znak to l, czyli spotyka czlowieka
                        Random random = new Random();
                        nazwa = "l" + Symulacja.birthCount;     //tworzymy nazwe dla nowego czlowieka
                        int pozycjaX = random.nextInt(Mapa.x);     // losujemy wspolrzedne dla nowego czlowieka
                        int pozycjaY = random.nextInt(Mapa.y);
                        for (int j = 0; j < Symulacja.agents.size(); j++) {
                            if (Symulacja.agents.get(j).positionX == pozycjaX && Symulacja.agents.get(j).positionY == pozycjaY) {
                                pozycjaX = random.nextInt(Mapa.x);
                                pozycjaY = random.nextInt(Mapa.y);
                                j = -1;     // przypisujemy iteratorowi wartosc -1 zeby petla wykonala sie od nowa od zerowego elementu listy
                            }
                        }
                        Symulacja.agents.add(new Ludzie(nazwa, pozycjaX, pozycjaY, 2));     //dodajemy nowego czlowieka do listy
                        Mapa.map[pozycjaX][pozycjaY] = nazwa;      //umieszczamy na polu o wylosowanych wspolrzednych nazwe nowego agenta
                        Symulacja.birthCount++;         //aktualizujemy dane
                        Symulacja.initialLudzie++;
                        break;
                    case 'w':       //pierwszy znak to w, czyli spotyka wrozke
                        if (health == 1) health++;      // wrozka uzdrawia czlowieka(odzyskuje utracone punkty zycia)
                        break;
                    case 'r':
                        health--;       //roslina atakuje czlowieka(minus jeden pkt zycia)
                        if (health == 0) {      //jesli ma zero punktow zycia to umiera
                            Mapa.map[positionX][positionY] = null;
                            name = "0";
                            positionX = -1;     // zmieniamy wartosci obiektu do usunięcia
                            positionY = -1;
                            Symulacja.deathCount++;
                            Symulacja.initialLudzie--;
                        }
                        nazwa = Mapa.map[wayX][wayY];
                        for (int j = 0; j < Symulacja.agents.size(); j++) {     //roslina odzyskuje zycie po ataku na czlowieka
                            if (Symulacja.agents.get(j).name.equals(nazwa)) {
                                if (Symulacja.agents.get(j).health != 8) Symulacja.agents.get(j).health = 8;
                            }
                        }
                        break;
                }
                waysForX.clear();
                waysForY.clear();       // czyscimy przetasowane listy
            }

    }
}
