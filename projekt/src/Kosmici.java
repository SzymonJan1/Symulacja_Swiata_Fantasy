public class Kosmici extends Agent {

    protected Kosmici(String name, int positionX, int positionY, int health){

        super(name, positionX, positionY, health);
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
        /**na wybranej pozycji znajduje sie inny agent*/
        else {
            String nazwa;
            switch(Mapa.map[wayX][wayY].charAt(0)) {

                /** interakcja z czlowiekiem*/
                case 'l':
                    prepareForRemoval();
                    /**tworzymy nazwe nowego kosmity*/
                    nazwa = "k" + Symulacja.birthCount;
                    Mapa.map[wayX][wayY] = nazwa;
                    /**deklaracja kosmity*/
                    Symulacja.agents.add(new Kosmici(nazwa, wayX, wayY, 0));
                    /**aktualizacja danych*/
                    Symulacja.birthCount++;
                    Symulacja.deathCount++;
                    Symulacja.initialLudzie--;
                    Symulacja.initialKosmici++;
                    break;

                /** interakcja z wrozka*/
                case 'w':
                    /**nazwa nowego czlowieka*/
                    nazwa = "l" + Symulacja.birthCount;
                    Mapa.map[positionX][positionY] = nazwa;
                    /**deklaracja czlowieka*/
                    Symulacja.agents.add(new Ludzie(nazwa, positionX, positionY, 2));
                    /** zmieniamy wartosci obiektu do usuniacia*/
                    name = "0";
                    /**aktualizacja danych*/
                    Symulacja.birthCount++;
                    Symulacja.deathCount++;
                    Symulacja.initialLudzie++;
                    Symulacja.initialKosmici--;
                    break;
            }
        }
        waysForX.clear();
        waysForY.clear();
    }
}
