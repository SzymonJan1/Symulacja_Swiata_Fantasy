public class Wrozki extends Agent {

    protected Wrozki(String name, int positionX, int positionY, int health){

        super( name, positionX, positionY, health);
    }

    @Override
    protected void Interaction() {

        /**na wybranej pozycji nie ma innego agenta*/
        if(Mapa.map[wayX][wayY] == null) {
            Mapa.map[wayX][wayY] = name;
            Mapa.map[positionX][positionY] = null;
            positionX = wayX;
            positionY = wayY;
        }
        else {
            String nazwa;
            switch (Mapa.map[wayX][wayY].charAt(0)) {

                /** interakcja z kosmita*/
                case 'k':
                    prepareForRemoval();
                    nazwa = "l" + Symulacja.birthCount;
                    Mapa.map[wayX][wayY] = nazwa;
                    /**deklaracja czlowieka*/
                    Symulacja.agents.add(new Ludzie(nazwa, wayX, wayY, 2));
                    /**nowy czlowiek*/
                    Symulacja.birthCount++;
                    /**smierc kosmity*/
                    Symulacja.deathCount++;
                    Symulacja.initialKosmici--;
                    Symulacja.initialLudzie++;
                    break;

                /** interakcja z miesozerna roslina*/
                case 'r':
                    /**wrozka umiera*/
                    Mapa.map[positionX][positionY] = null;
                    /** zmieniamy wartosci obiektu do usuniecia*/
                    name = "0";
                    Symulacja.deathCount++;
                    Symulacja.initialWrozki--;
                    /**przypisujemy zmiennej "nazwa" nazwe rosliny*/
                    nazwa = Mapa.map[wayX][wayY];
                    /**roslina odzyskuje punkty zycia*/
                    for (int j = 0; j < Symulacja.agents.size(); j++) {
                        if (Symulacja.agents.get(j).name.equals(nazwa)) {
                            if(Symulacja.agents.get(j).health != 8) Symulacja.agents.get(j).health = 8;
                        }
                    }
                    break;

                /** interakcja z czlowiekiem*/
                case'l':
                    /** czlowiek odzyskuje punkt zycia*/
                    nazwa = Mapa.map[wayX][wayY];
                    for (int j = 0; j < Symulacja.agents.size(); j++) {
                        if(Symulacja.agents.get(j).name.equals(nazwa)) {
                            if(Symulacja.agents.get(j).health == 1) Symulacja.agents.get(j).health++;
                        }
                    }
                    break;
            }
        }
        /**czyscimy przetasowane listy*/
        waysForX.clear();
        waysForY.clear();
    }
}
