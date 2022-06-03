import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class Tests {

    private ArrayList<Agent> expected = new ArrayList<>();

    @Test
    public void KosmiciMeetLudzieTest(){

        Mapa.x = 2;
        Mapa.y = 2;
        Mapa.map = new String[Mapa.x][Mapa.y];
        Agent.wayX = 1;
        Agent.wayY = 1;
        Symulacja.agents.add(new Kosmici("k", 0, 0, 0));
        Symulacja.agents.add(new Ludzie("l", 1, 1, 2));
        Mapa.map[0][0] = "k";
        Mapa.map[1][1] = "l";
        Symulacja.agents.get(0).Interaction();
        for (int n = (Symulacja.agents.size()-1); n >= 0; n--) {
            if(Symulacja.agents.get(n).name.equals("0")) Symulacja.agents.remove(n);
        }
        System.out.println(Symulacja.agents);
        for (int k = 0; k < Mapa.y; k++) {
            for (int l = 0; l < Mapa.x; l++) {
                System.out.print(Mapa.map[l][k] + "       ");
            }
            System.out.println();
        }
        expected.add(new Kosmici("k", 0, 0, 0));
        expected.add(new Kosmici("k0", 1,1,0));
        assertEquals(expected.get(1).name, Symulacja.agents.get(1).name);
    }

    @Test
    public void KosmiciMeetWrozkiTest() {

        Mapa.x = 2;
        Mapa.y = 2;
        Mapa.map = new String[Mapa.x][Mapa.y];
        Agent.wayX = 1;
        Agent.wayY = 1;
        Symulacja.agents.add(new Kosmici("k", 0, 0, 0));
        Symulacja.agents.add(new Ludzie("w", 1, 1, 2));
        Mapa.map[0][0] = "k";
        Mapa.map[1][1] = "w";
        Symulacja.agents.get(0).Interaction();
        for (int n = (Symulacja.agents.size() - 1); n >= 0; n--) {
            if (Symulacja.agents.get(n).name.equals("0")) Symulacja.agents.remove(n);
        }
        System.out.println(Symulacja.agents);
        for (int k = 0; k < Mapa.y; k++) {
            for (int l = 0; l < Mapa.x; l++) {
                System.out.print(Mapa.map[l][k] + "       ");
            }
            System.out.println();
        }
        expected.add(new Kosmici("l0", 0, 0, 0));
        expected.add(new Kosmici("w", 1, 1, 0));
        assertEquals(expected.get(0).name, Symulacja.agents.get(1).name);
    }

    @Test
    public void WrozkiMeetKosmiciTest(){

        Mapa.x = 2;
        Mapa.y = 2;
        Mapa.map = new String[Mapa.x][Mapa.y];
        Agent.wayX = 1;
        Agent.wayY = 1;
        Symulacja.agents.add(new Wrozki("w", 0, 0, 0));
        Symulacja.agents.add(new Kosmici("k", 1, 1, 0));
        Mapa.map[0][0] = "w";
        Mapa.map[1][1] = "k";
        Symulacja.agents.get(0).Interaction();
        for (int n = (Symulacja.agents.size()-1); n >= 0; n--) {
            if(Symulacja.agents.get(n).name.equals("0")) Symulacja.agents.remove(n);
        }
        System.out.println(Symulacja.agents);
        for (int k = 0; k < Mapa.y; k++) {
            for (int l = 0; l < Mapa.x; l++) {
                System.out.print(Mapa.map[l][k] + "       ");
            }
            System.out.println();
        }
        expected.add(new Kosmici("w", 0, 0, 0));
        expected.add(new Kosmici("l0", 1,1,2));
        assertEquals(expected.get(1).name, Symulacja.agents.get(1).name);
    }

}
