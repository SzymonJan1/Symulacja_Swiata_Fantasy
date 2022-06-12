public class Kosmici extends Agent {

    protected Kosmici(String name, int positionX, int positionY, int health){

        super(name, positionX, positionY, health);
    }

    @Override
    protected void Interaction() {

        if(Mapa.map[wayX][wayY] == null) { //na wybranej pozycji nie ma innego agenta
            Mapa.map[wayX][wayY] = name;
            Mapa.map[positionX][positionY] = null;
            positionX = wayX;
            positionY = wayY;
        }
        else { //na wybranej pozycji znajduje sie inny agent
            String nazwa;
            switch(Mapa.map[wayX][wayY].charAt(0)) {

                case 'l':    // interakcja z czlowiekiem
                    prepareForRemoval();
                    nazwa = "k" + Symulacja.birthCount;     //tworzymy nazwe nowego kosmity
                    Mapa.map[wayX][wayY] = nazwa;
                    Symulacja.agents.add(new Kosmici(nazwa, wayX, wayY, 0));     //deklaracja kosmity
                    Symulacja.birthCount++;     //aktualizacja danych
                    Symulacja.deathCount++;
                    Symulacja.initialLudzie--;
                    Symulacja.initialKosmici++;
                    break;

                case 'w':    // interakcja z wrozka
                    nazwa = "l" + Symulacja.birthCount;     //nazwa nowego czlowieka
                    Mapa.map[positionX][positionY] = nazwa;
                    Symulacja.agents.add(new Ludzie(nazwa, positionX, positionY, 2));   //deklaracja czlowieka
                    name = "0";     // zmieniamy wartosci obiektu do usunięcia
                    Symulacja.birthCount++;     //aktualizacja danych
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
