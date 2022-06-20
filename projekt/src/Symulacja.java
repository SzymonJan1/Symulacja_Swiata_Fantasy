import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class Symulacja {

    /**licznik tury*/
    private static int iteration;
    /**licznik narodzin potrzebny do tworzenia nazw kolejnych obiektow, licznik smierci*/
    protected static int birthCount = 0, deathCount = 0, agentsCount;
    /**wartosci poczatkowe*/
    protected static int initialSmoki, initialLudzie, initialKosmici, initialWrozki, initialRosliny;
    protected static List<Agent> agents = new ArrayList<>();
    private BufferedWriter bw;
    {
        try {
            bw = new BufferedWriter(new FileWriter("C:\\Users\\Szymon Wichrowski\\Desktop\\projekt\\pliki do projektu\\plik.txt"));
            bw.write("currentIteration birthCount deathCount initialSmoki initialLudzie initialKosmici initialWrozki initialRosliny\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**funkcja odpowiadajaca za dzialanie symulacji*/
    protected void simulate() {

        /**drukowanie mapy z poczatkowymi populacjami*/
        System.out.println("Początek symulacji:");
        printMap();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Liczba iteracji: ");
        /**uzytkownik wpisuje liczbe iteracji*/
        iteration = scanner.nextInt();

        /**przesuwanie kolejnych tur/iteracji*/
        for (int j = 0; j < iteration; j++) {
            /**zmienna z aktualnym numerem iteracji*/
            int currentIteration = j +1;
            /**jesli bedzie true - liczba agentów przekroczyła liczbe pol na mapie*/
            boolean bool=false;

            /**poruszanie sie kolejnych agentow w ramach tury*/
            /**poruszaja sie wszyscy agenci z listy(oprocz zerowych)*/
            for (int i = 0; i < agents.size(); i++) {
                /**jesli nazwa agenta to nie zero, to nastepuje ruch*/
                if(!(agents.get(i).name.equals("0"))) {
                    agents.get(i).Shift_X_Y();
                    agents.get(i).Interaction();
                    agentsCount = birthCount - deathCount;
                }

                /**sprawdzenie, czy liczba agentow przekracza liczbe pol na mapie*/
                if(agentsCount == Mapa.x * Mapa.y){
                    System.out.println("Koniec symulacji - liczba agentów przekroczyła liczbę pól na mapie.");
                    bool = true;
                    System.out.println("Mapa w momencie przerwania:");
                    /**wyswietlanie mapy w momencie przerwania*/
                    printMap();
                    break;
                }
            }

            /**zapisywanie do pliku txt*/
            String addToFile= currentIteration + " " + birthCount + " " + deathCount+ " " + initialSmoki + " " + initialLudzie+ " " + initialKosmici + " " + initialWrozki + " " + initialRosliny + "\n";
            try {
                bw.append(addToFile);
            } catch (IOException e) {
                e.printStackTrace();
            }

            /**zamykanie pliku txt*/
            if(bool == true || currentIteration == iteration){
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            /**przerywa symulacje, gdy liczba agentow przekroczy liczbe pol na mapie.*/
            if(bool == true){
                break;
            }

            /**usuwanie agentow z listy*/
            deleteAgents();

            /**wyswietlanie mapy po kazdej iteracji*/
            System.out.println("Po " + currentIteration + " iteracji:");
            printMap();
        }
    }
    /**usuwanie agentow*/
    protected static void deleteAgents() {
        for (int n = (agents.size()-1); n >= 0; n--) {
            if(agents.get(n).name.equals("0")) agents.remove(n);
        }
    }
    /**wyswietlanie mapy*/
    protected static void printMap() {
        for (int k = 0; k < Mapa.y; k++) {
            for (int l = 0; l < Mapa.x; l++) {
                System.out.print(Mapa.map[l][k] + "       ");
            }
            System.out.println();
        }
    }
}
