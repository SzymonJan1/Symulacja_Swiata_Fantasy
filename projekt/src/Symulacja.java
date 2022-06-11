import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class Symulacja {

    private static int iteration;
    protected static int birthCount = 0, deathCount = 0, agentsCount; //narodzin potrzebny do tworzenia nazw kolejnych obiektow, licznik narodzin, licznik smierci
    protected static int initialSmoki, initialLudzie, initialKosmici, initialWrozki, initialRosliny; //wartosci poczatkowe
    protected static List<Agent> agents = new ArrayList<>();
    private BufferedWriter bw;
    {
        try {
            bw = new BufferedWriter(new FileWriter("C:\\Users\\Szymon Wichrowski\\Desktop\\plik do projektu\\plik.txt"));
            bw.write("currentIteration - birthCount - deathCount - initialSmoki - initialLudzie - initialKosmici - initialWrozki - initialRosliny \n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //funkcja odpowiadajaca za dzialanie symulacji
    protected void simulate() {

        //drukowanie mapy z poczatkowymi populacjami
        System.out.println("Początek symulacji:");
        for (int k = 0; k < Mapa.y; k++) {
            for (int l = 0; l < Mapa.x; l++) {
                System.out.print(Mapa.map[l][k] + "    ");
            }
            System.out.println();
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Liczba iteracji: ");
        iteration = scanner.nextInt();      //uzytkownik wpisuje liczbe iteracji

        //przesuwanie kolejnych tur/iteracji
        for (int j = 0; j < iteration; j++) {       //
            int currentIteration = j +1;       //zmienna z aktualnym numerem iteracji
            boolean bool=false; //jesli będzie true - liczba agentów przekroczyła liczbę pól na mapie

            //poruszanie sie kolejnych agentów w ramach tury
            for (int i = 0; i < agents.size(); i++) {       //poruszaja sie wszyscy agenci z listy(oprocz zerowych)
                if(!(agents.get(i).name.equals("0"))) {      //jesli nazwa agenta to nie zero, to nastepuje ruch
                    agents.get(i).Shift_X_Y();
                    agents.get(i).Interaction();
                    agentsCount = birthCount - deathCount;
                }

                //sprawdzenie, czy liczba agentów przekracza liczbę pól na mapie
                if(agentsCount == Mapa.x * Mapa.y){
                    System.out.println("Koniec symulacji - liczba agentów przekroczyła liczbę pól na mapie.");
                    bool = true;
                    System.out.println("Mapa w momencie przerwania:");        //wyswietlanie mapy w momencie przerwania
                    for (int k = 0; k < Mapa.y; k++) {
                        for (int l = 0; l < Mapa.x; l++) {
                            System.out.print(Mapa.map[l][k] + "       ");
                        }
                        System.out.println();
                    }
                    break;
                }
            }

            //zapisywanie do pliku txt
            String addToFile= currentIteration + " " + birthCount + " " + deathCount+ " " + initialSmoki + " " + initialLudzie+ " " + initialKosmici + " " + initialWrozki + " " + initialRosliny + "\n";
            try {
                bw.append(addToFile);
            } catch (IOException e) {
                e.printStackTrace();
            }

            //zamykanie pliku txt
            if(bool == true || currentIteration == iteration){
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            //przerywa symulację, gdy liczba agentów przekroczy liczbę pól na mapie.
            if(bool == true){
                break;
            }

            //usuwanie agentów z listy
            for (int n = (agents.size()-1); n >= 0; n--) {
                if(agents.get(n).name.equals("0")) agents.remove(n);
            }

            //wyswietlanie mapy po kazdej iteracji
            System.out.println("Po " + currentIteration + " iteracji:");
            for (int k = 0; k < Mapa.y; k++) {
                for (int l = 0; l < Mapa.x; l++) {
                    System.out.print(Mapa.map[l][k] + "       ");
                }
                System.out.println();
            }

        }
    }
}
