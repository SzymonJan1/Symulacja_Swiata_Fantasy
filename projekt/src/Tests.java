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
        Symulacja.agents.get(0).Interaction();
        for (int n = (Symulacja.agents.size()-1); n >= 0; n--) {
            if(Symulacja.agents.get(n).name.equals("0")) Symulacja.agents.remove(n);
        }
        expected.add(new Kosmici("k", 0, 0, 0));
        expected.add(new Kosmici("k0", 1,1,0));
        assertEquals(expected.get(1).name, Symulacja.agents.get(1).name);
    }
}
