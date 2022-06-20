public class Ludzie extends Agent {

    protected Ludzie(String name, int positionX, int positionY, int health) {

        super(name, positionX, positionY, health);
    }

    @Override
    protected void Interaction() {

            if (Mapa.map[wayX][wayY] == null) { /**na wybranej pozycji nie ma innego agenta*/
                Mapa.map[wayX][wayY] = name;       /**przypisywanie nowemu polu nazwy agenta*/
                Mapa.map[positionX][positionY] = null;     /**przypisywanie staremu polu wartosci null*/
                positionX = wayX;   /**przypisywanie nowych wspolrzednych agentowi*/
                positionY = wayY;
            }
            else {    /**na wybranej pozycji znajduje sie jakis agent*/
                String nazwa;
                switch (Mapa.map[wayX][wayY].charAt(0)) {  /**sprawdzanie pierszego znaku nazwy agenta*/

                    case 'k':       /**pierwszy znak to k, czyli spotyka kosmite*/
                        nazwa = "k" + Symulacja.birthCount;     /**tworzenie nazwy dla nowego kosmity*/
                        Mapa.map[positionX][positionY] = nazwa;    /**przypisujemy polu na ktorym stal czlowiek, nazwe nowego kosmity*/
                        Symulacja.agents.add(new Kosmici(nazwa, positionX, positionY, 0)); /**deklaracja nowego kosmity*/
                        name = "0";     /** zmieniamy wartosci obiektu do usunięcia*/
                        Symulacja.birthCount++;     /**aktualizujemy dane*/
                        Symulacja.deathCount++;
                        Symulacja.initialKosmici++;
                        Symulacja.initialLudzie--;
                        break;

                    case 's':       /**pierwszy znak to s, czyli spotyka smoka*/
                        Mapa.map[positionX][positionY] = null; /**przypisujemy null polu na ktorym stal czlowiek*/
                        name = "0";     /** zmieniamy wartosci obiektu do usunięcia*/
                        Symulacja.deathCount++;     /**aktualizujemy dane*/
                        Symulacja.initialLudzie--;
                        nazwa = Mapa.map[wayX][wayY];     /**przypisujemy zmiennej "nazwa" wartosc pola ze smokiem*/
                        for (int j = 0; j < Symulacja.agents.size(); j++) {
                            if (Symulacja.agents.get(j).name.equals(nazwa)) {       /**wyszukanie agenta o nazwie "nazwa" na liscie*/
                                if (Symulacja.agents.get(j).health != 9) Symulacja.agents.get(j).health = 9;    /**jesli smok nie ma wszystkich zyć, odzyskuje je*/
                            }
                        }
                        break;

                    case 'l':       /**pierwszy znak to l, czyli spotyka czlowieka*/
                        nazwa = "l" + Symulacja.birthCount;     /**tworzymy nazwe dla nowego czlowieka*/
                        Mapa.randomizePositions();
                        Symulacja.agents.add(new Ludzie(nazwa, Mapa.pozycjaX, Mapa.pozycjaY, 2));     /**dodajemy nowego czlowieka do listy*/
                        Mapa.map[Mapa.pozycjaX][Mapa.pozycjaY] = nazwa;      /**umieszczamy na polu o wylosowanych wspolrzednych nazwe nowego agenta*/
                        Symulacja.birthCount++;         /**aktualizujemy dane*/
                        Symulacja.initialLudzie++;
                        break;

                    case 'w':       /**pierwszy znak to w, czyli spotyka wrozke*/
                        if (health == 1) health++;      /** wrozka uzdrawia czlowieka(odzyskuje utracone punkty zycia)*/
                        break;

                    case 'r':
                        health--;       /**roslina atakuje czlowieka(minus jeden pkt zycia)*/
                        if (health == 0) {      /**jesli ma zero punktow zycia to umiera*/
                            Mapa.map[positionX][positionY] = null;
                            name = "0";     /** zmieniamy wartosci obiektu do usunięcia*/
                            Symulacja.deathCount++;
                            Symulacja.initialLudzie--;
                        }
                        nazwa = Mapa.map[wayX][wayY];
                        for (int j = 0; j < Symulacja.agents.size(); j++) {     /**roslina odzyskuje zycie po ataku na czlowieka*/
                            if (Symulacja.agents.get(j).name.equals(nazwa)) {
                                if (Symulacja.agents.get(j).health != 8) Symulacja.agents.get(j).health = 8;
                            }
                        }
                        break;
                }
                waysForX.clear();
                waysForY.clear();       /** czyscimy przetasowane listy*/
            }

    }
}
