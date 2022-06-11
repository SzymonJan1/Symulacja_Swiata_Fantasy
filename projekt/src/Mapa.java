import java.util.Random;
import java.util.List;
import java.util.Scanner;

public class Mapa {
    protected static int x, y;
    protected static String[][] map;

    //generowanie mapy
    protected void mapInitialization() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Długość mapy: ");
        x = scanner.nextInt();
        System.out.println("Szerekość mapy: ");
        y = scanner.nextInt();
        map = new String[x][y];
    }

    //generowanie agentow z warunkow poczatkowych
    protected void addAgents() {
        Random random = new Random();
        String nazwa;//zmienna tymczasowa odpowiadająca za tworzenie unikalnej nazwy dla każdego obiektu
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ilość smoków (Nie może przekraczać " + x * y + "): ");
        Symulacja.initialSmoki = scanner.nextInt();
        System.out.println("Ilość ludzi (Nie może przekraczać " + (x * y - Symulacja.initialSmoki) + "): ");
        Symulacja.initialLudzie = scanner.nextInt();
        System.out.println("Ilość wróżek (Nie może przekraczać " + (x * y - Symulacja.initialSmoki - Symulacja.initialLudzie) + "): ");
        Symulacja.initialWrozki = scanner.nextInt();
        System.out.println("Ilość kosmitów (Nie może przekraczać " + (x * y - Symulacja.initialSmoki - Symulacja.initialLudzie - Symulacja.initialWrozki) + "): ");
        Symulacja.initialKosmici = scanner.nextInt();
        System.out.println("Ilość roślin (Nie może przekraczać " + (x * y - Symulacja.initialSmoki - Symulacja.initialLudzie - Symulacja.initialWrozki - Symulacja.initialKosmici) + "): ");
        Symulacja.initialRosliny = scanner.nextInt();
        //generowanie smokow
        //
        for (int i = 0; i < Symulacja.initialSmoki; i++) {

            //tworzenie nazwa; dla smokow poczatek s i numerki kolejnych smoków
            nazwa = "s";
            StringBuilder sB = new StringBuilder(nazwa);
            sB.append(Symulacja.birthCount);
            nazwa = sB.toString();
            //koniec tworzenia nazwy
            //losowanie pozycji
            int pozycjaX = random.nextInt(x);
            int pozycjaY = random.nextInt(y);

            //sprawdzanie czy podane koordynaty nie są już przypisane do jakiegos agenta
            for (int j = 0; j < Symulacja.agents.size(); j++) {
                if (Symulacja.agents.get(j).positionX == pozycjaX && Symulacja.agents.get(j).positionY == pozycjaY) {
                    pozycjaX = random.nextInt(x);
                    pozycjaY = random.nextInt(y);
                    j = -1;
                }
            }

            //tworzenie obiektu i dodanie jego reprezentacji na mapie
            Symulacja.agents.add(new Smoki(nazwa, pozycjaX, pozycjaY, 5));
            map[pozycjaX][pozycjaY] = nazwa;
            Symulacja.birthCount++;

        }

        //generowanie ludzi
        for (int i = 0; i < Symulacja.initialLudzie; i++) {
            nazwa = "l";
            StringBuilder sB = new StringBuilder(nazwa);
            sB.append(Symulacja.birthCount);
            nazwa = sB.toString();
            int pozycjaX = random.nextInt(x);
            int pozycjaY = random.nextInt(y);
            for (int j = 0; j < Symulacja.agents.size(); j++) {
                if (Symulacja.agents.get(j).positionX == pozycjaX && Symulacja.agents.get(j).positionY == pozycjaY) {
                    pozycjaX = random.nextInt(x);
                    pozycjaY = random.nextInt(y);
                    j = -1;
                }
            }
            Symulacja.agents.add(new Ludzie(nazwa, pozycjaX, pozycjaY, 2));
            map[pozycjaX][pozycjaY] = nazwa;
            Symulacja.birthCount++;
        }

        //generowanie kosmitow
        for (int i = 0; i < Symulacja.initialKosmici; i++) {
            nazwa = "k";
            StringBuilder sB = new StringBuilder(nazwa);
            sB.append(Symulacja.birthCount);
            nazwa = sB.toString();
            int pozycjaX = random.nextInt(x);
            int pozycjaY = random.nextInt(y);
            for (int j = 0; j < Symulacja.agents.size(); j++) {
                if (Symulacja.agents.get(j).positionX == pozycjaX && Symulacja.agents.get(j).positionY == pozycjaY) {
                    pozycjaX = random.nextInt(x);
                    pozycjaY = random.nextInt(y);
                    j = -1;
                }
            }
            Symulacja.agents.add(new Kosmici(nazwa, pozycjaX, pozycjaY, 0));
            map[pozycjaX][pozycjaY] = nazwa;
            Symulacja.birthCount++;
        }

        //generowanie wrozek
        for (int i = 0; i < Symulacja.initialWrozki; i++) {
            nazwa = "w";
            StringBuilder sB = new StringBuilder(nazwa);
            sB.append(Symulacja.birthCount);
            nazwa = sB.toString();
            int pozycjaX = random.nextInt(x);
            int pozycjaY = random.nextInt(y);
            for (int j = 0; j < Symulacja.agents.size(); j++) {
                if (Symulacja.agents.get(j).positionX == pozycjaX && Symulacja.agents.get(j).positionY == pozycjaY) {
                    pozycjaX = random.nextInt(x);
                    pozycjaY = random.nextInt(y);
                    j = -1;
                }
            }
            Symulacja.agents.add(new Wrozki(nazwa, pozycjaX, pozycjaY, 0));
            map[pozycjaX][pozycjaY] = nazwa;
            Symulacja.birthCount++;
        }

        //generowanie roslin
        for (int i = 0; i < Symulacja.initialRosliny; i++) {
            nazwa = "r";
            StringBuilder sB = new StringBuilder(nazwa);
            sB.append(Symulacja.birthCount);
            nazwa = sB.toString();
            int pozycjaX = random.nextInt(x);
            int pozycjaY = random.nextInt(y);
            for (int j = 0; j < Symulacja.agents.size(); j++) {
                if (Symulacja.agents.get(j).positionX == pozycjaX && Symulacja.agents.get(j).positionY == pozycjaY) {
                    pozycjaX = random.nextInt(x);
                    pozycjaY = random.nextInt(y);
                    j = -1;
                }
            }
            Symulacja.agents.add(new MiesozerneRosliny(nazwa, pozycjaX, pozycjaY, 8));
            map[pozycjaX][pozycjaY] = nazwa;
            Symulacja.birthCount++;
        }
    }
}
