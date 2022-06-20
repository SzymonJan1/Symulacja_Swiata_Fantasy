import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**klasa do przeprowadzania testow jednostkowych interakcji miedzy agentami*/
public class Tests {

    private ArrayList<Agent> expected = new ArrayList<>();

    /**inicjalizacja mapy potrzebnej do testow*/
    void MapToTests() {
        Mapa.x = 2;
        Mapa.y = 2;
        Mapa.map = new String[Mapa.x][Mapa.y];
        Agent.wayX = 1;
        Agent.wayY = 1;
    }
    /**wspolne elementy kazdego testu(rozpoczecie interakcji, usuwanie "zerowych" agentow, wyswietlanie listy i mapy)*/
    void TestInteraction() {
        /**interakcja*/
        Symulacja.agents.get(0).Interaction();
        Symulacja.deleteAgents();
        System.out.println("Oczekiwana lista agentow: " + expected);
        /**wyswietlanie listy agentow*/
        System.out.println("Aktualna lista agentow: " + Symulacja.agents);
        Symulacja.printMap();
    }

    void AfterTheTest() {
        Symulacja.birthCount = 0;
        Symulacja.agents.clear();
        expected.clear();
        System.out.println();
    }

    /**test interakcji, gdy kosmita spotyka czlowieka*/
    @Test
    public void KosmiciMeetLudzieTest() {
        MapToTests();
        Symulacja.agents.add(new Kosmici("k", 0, 0, 0));
        Symulacja.agents.add(new Ludzie("l", 1, 1, 2));
        Mapa.map[0][0] = "k";
        Mapa.map[1][1] = "l";
        expected.add(new Kosmici("k", 0, 0, 0));
        /**oczekiwany agent*/
        expected.add(new Kosmici("k0", 1,1,0));
        TestInteraction();
        /**sprawdzamy czy nazwa sie zgadza*/
        assertEquals(expected.get(1).name, Symulacja.agents.get(1).name);
        AfterTheTest();
    }

    /**test interakcji, gdy kosmita spotyka wrozke*/
    @Test
    public void KosmiciMeetWrozkiTest() {
        MapToTests();
        Symulacja.agents.add(new Kosmici("k", 0, 0, 0));
        Symulacja.agents.add(new Wrozki("w", 1, 1, 2));
        Mapa.map[0][0] = "k";
        Mapa.map[1][1] = "w";
        expected.add(new Ludzie("l0", 0, 0, 0));
        expected.add(new Wrozki("w", 1, 1, 0));
        TestInteraction();
        assertEquals(expected.get(0).name, Symulacja.agents.get(1).name);
        AfterTheTest();
    }

    /**test interakcji, gdy wrozka spotyka kosmite*/
    @Test
    public void WrozkiMeetKosmiciTest() {
        MapToTests();
        Symulacja.agents.add(new Wrozki("w", 0, 0, 0));
        Symulacja.agents.add(new Kosmici("k", 1, 1, 0));
        Mapa.map[0][0] = "w";
        Mapa.map[1][1] = "k";
        expected.add(new Wrozki("w", 0, 0, 0));
        expected.add(new Ludzie("l0", 1,1,2));
        TestInteraction();
        assertEquals(expected.get(1).name, Symulacja.agents.get(1).name);
        AfterTheTest();
    }

    /**test interakcji, gdy wrozka spotyka miesozerna rosline*/
    @Test
    public void WrozkiMeetMiesozerneRoslinyTest() {
        MapToTests();
        Symulacja.agents.add(new Wrozki("w", 0, 0, 0));
        Symulacja.agents.add(new MiesozerneRosliny("r", 1,1,8));
        Mapa.map[0][0] = "w";
        Mapa.map[1][1] = "r";
        expected.add(new MiesozerneRosliny("r", 1, 1, 8));
        TestInteraction();
        assertEquals(expected.get(0).name, Symulacja.agents.get(0).name);
        AfterTheTest();
    }

    /**test interakcji, gdy wrozka spotyka czlowieka*/
    @Test
    public void WrozkiMeetLudzieTest() {
        MapToTests();
        Symulacja.agents.add(new Wrozki("w", 0, 0, 0));
        Symulacja.agents.add(new Ludzie("l", 1,1,1));
        Mapa.map[0][0] = "w";
        Mapa.map[1][1] = "l";
        expected.add(new Wrozki("w", 0, 0, 0));
        expected.add(new Ludzie("l", 1,1,2));
        System.out.println("Punkty zycia czlowieka przed interakcja: " + Symulacja.agents.get(1).health);
        TestInteraction();
        System.out.println("Punkty zycia czlowieka po interakcji: " + Symulacja.agents.get(1).health);
        assertEquals(expected.get(1).health, Symulacja.agents.get(1).health);
        AfterTheTest();
    }

    /**test interakcji, gdy czlowiek spotyka kosmite*/
    @Test
    public void LudzieMeetKosmiciTest() {
        MapToTests();
        Symulacja.agents.add(new Ludzie("l", 0, 0, 2));
        Symulacja.agents.add(new Kosmici("k", 1,1,0));
        Mapa.map[0][0] = "l";
        Mapa.map[1][1] = "k";
        expected.add(new Kosmici("k0", 0, 0, 0));
        expected.add(new Kosmici("k", 1,1,0));
        TestInteraction();
        assertEquals(expected.get(0).name, Symulacja.agents.get(1).name);
        AfterTheTest();
    }

    /**test interakcji, gdy czlowiek spotyka smoka*/
    @Test
    public void LudzieMeetSmokiTest() {
        MapToTests();
        Symulacja.agents.add(new Ludzie("l", 0, 0, 2));
        Symulacja.agents.add(new Smoki("s", 1,1,9));
        Mapa.map[0][0] = "l";
        Mapa.map[1][1] = "s";
        expected.add(new Smoki("s", 1,1,9));
        TestInteraction();
        assertEquals(expected.get(0).name, Symulacja.agents.get(0).name);
        AfterTheTest();
    }

    /**test interakcji, gdy czlowiek spotyka innego czlowieka*/
    @Test
    public void LudzieMeetLudzieTest() {
        MapToTests();
        Symulacja.agents.add(new Ludzie("l", 0, 0, 2));
        Symulacja.agents.add(new Ludzie("l", 1,1,2));
        Mapa.map[0][0] = "l";
        Mapa.map[1][1] = "l";
        expected.add(new Ludzie("l", 0, 0, 2));
        expected.add(new Ludzie("l", 1,1,2));
        expected.add(new Ludzie("l0", -1,-1,2));
        TestInteraction();
        assertEquals(expected.get(2).name, Symulacja.agents.get(2).name);
        AfterTheTest();
    }

    /**test interakcji, gdy czlowiek spotyka wrozke*/
    @Test
    public void LudzieMeetWrozkiTest() {
        MapToTests();
        Symulacja.agents.add(new Ludzie("l", 0, 0, 1));
        Symulacja.agents.add(new Wrozki("w", 1,1,0));
        Mapa.map[0][0] = "l";
        Mapa.map[1][1] = "w";
        expected.add(new Ludzie("l", 0, 0, 2));
        expected.add(new Wrozki("w", 1,1,0));
        System.out.println("Punkty zycia czlowieka przed interakcja: " + Symulacja.agents.get(0).health);
        TestInteraction();
        System.out.println("Punkty zycia czlowieka po interakcji: " + Symulacja.agents.get(0).health);
        assertEquals(expected.get(0).health, Symulacja.agents.get(0).health);
        AfterTheTest();
    }

    /**test interakcji, gdy czlowiek spotyka miesozerna roslina (czlowiek ma dwa zycia)*/
    @Test
    public void LudzieWithTwoLivesMeetMiesozerneRoslinyTest() {
        MapToTests();
        Symulacja.agents.add(new Ludzie("l", 0, 0, 2));
        Symulacja.agents.add(new MiesozerneRosliny("r", 1, 1, 8));
        Mapa.map[0][0] = "l";
        Mapa.map[1][1] = "r";
        expected.add(new Ludzie("l", 0, 0, 1));
        expected.add(new MiesozerneRosliny("r", 1, 1, 8));
        System.out.println("Punkty zycia czlowieka przed interakcja: " + Symulacja.agents.get(0).health);
        TestInteraction();
        System.out.println("Punkty zycia czlowieka po interakcji: " + Symulacja.agents.get(0).health);
        assertEquals(expected.get(0).health, Symulacja.agents.get(0).health);
        AfterTheTest();
    }

    /**test interakcji, gdy czlowiek spotyka miesozerna roslina (czlowiek ma jedno zycie)*/
    @Test
    public void LudzieWithOneLifeMeetMiesozerneRoslinyTest() {
        MapToTests();
        Symulacja.agents.add(new Ludzie("l", 0, 0, 1));
        Symulacja.agents.add(new MiesozerneRosliny("r", 1, 1, 8));
        Mapa.map[0][0] = "l";
        Mapa.map[1][1] = "r";
        expected.add(new MiesozerneRosliny("r", 1, 1, 8));
        TestInteraction();
        assertEquals(expected.get(0).name, Symulacja.agents.get(0).name);
        AfterTheTest();
    }

    /**test interakcji, gdy miesozerna roslina spotyka wrozke*/
    @Test
    public void MiesozerneRoslinyMeetWrozkiTest() {
        MapToTests();
        Symulacja.agents.add(new MiesozerneRosliny("r", 0, 0, 1));
        Symulacja.agents.add(new Wrozki("w", 1, 1, 0));
        Mapa.map[0][0] = "r";
        Mapa.map[1][1] = "w";
        expected.add(new MiesozerneRosliny("r", 0, 0, 8));
        System.out.println("Punkty życia miesozernej rosliny przed interakcja: " + Symulacja.agents.get(0).health);
        TestInteraction();
        System.out.println("Punkty życia miesozernej rosliny po interakcji: " + Symulacja.agents.get(0).health);
        assertEquals(expected.get(0).health, Symulacja.agents.get(0).health);
        AfterTheTest();
    }

    /**test interakcji, gdy miesozerna roslina spotyka czlowieka (czlowiek ma jedno zycie)*/
    @Test
    public void MiesozerneRoslinyMeetLudziWithOneLifeTest() {
        MapToTests();
        Symulacja.agents.add(new MiesozerneRosliny("r", 0, 0, 1));
        Symulacja.agents.add(new Ludzie("l", 1, 1, 1));
        Mapa.map[0][0] = "r";
        Mapa.map[1][1] = "l";
        expected.add(new MiesozerneRosliny("r", 0, 0, 8));
        System.out.println("Punkty życia miesozernej rosliny przed interakcja: " + Symulacja.agents.get(0).health);
        TestInteraction();
        System.out.println("Punkty życia miesozernej rosliny po interakcji: " + Symulacja.agents.get(0).health);
        assertEquals(expected.get(0).health, Symulacja.agents.get(0).health);
        AfterTheTest();
    }

    /**test sytuacji, gdy miesozerna roslina nie spotyka wrozki, ani czlowieka*/
    @Test
    public void MiesozerneRoslinyNoMeetTest() {
        MapToTests();
        Symulacja.agents.add(new MiesozerneRosliny("r", 0, 0, 8));
        Mapa.map[0][0] = "r";
        expected.add(new MiesozerneRosliny("r", 0, 0, 7));
        System.out.println("Punkty życia miesozernej rosliny przed tura: " + Symulacja.agents.get(0).health);
        TestInteraction();
        System.out.println("Punkty życia miesozernej rosliny po turze: " + Symulacja.agents.get(0).health);
        assertEquals(expected.get(0).health, Symulacja.agents.get(0).health);
        AfterTheTest();
    }

    /**test interakcji, gdy smok spotyka czlowieka*/
    @Test
    public void SmokiMeetLudzieTest() {
        MapToTests();
        Symulacja.agents.add(new Smoki("s", 0, 0, 1));
        Symulacja.agents.add((new Ludzie("l", 1, 1, 2)));
        Mapa.map[0][0] = "s";
        Mapa.map[1][1] = "l";
        expected.add(new Smoki("s", 1, 1, 9));
        System.out.println("Punkty życia smoka przed interakcja: " + Symulacja.agents.get(0).health);
        TestInteraction();
        System.out.println("Punkty życia smoka po interakcji: " + Symulacja.agents.get(0).health);
        assertEquals(expected.get(0).health, Symulacja.agents.get(0).health);
        AfterTheTest();
    }

    /**test interakcji, gdy smok spotyka ineego smoka*/
    @Test
    public void SmokiMeetSmokiTest() {
        MapToTests();
        Symulacja.agents.add(new Smoki("s", 0, 0, 9));
        Symulacja.agents.add(new Smoki("s", 1, 1, 9));
        Mapa.map[0][0] = "s";
        Mapa.map[1][1] = "s";
        expected.add(new Smoki("s", 0, 0, 9));
        expected.add(new Smoki("s", 0, 0, 9));
        expected.add(new Smoki("s0", -1, -1, 9));
        TestInteraction();
        assertEquals(expected.get(2).name, Symulacja.agents.get(2).name);
        AfterTheTest();
    }

    /**test sytuacji, gdy smok nikogo nie spotyka na swojej drodze*/
    @Test
    public void SmokiNoMeetTest() {
        MapToTests();
        Symulacja.agents.add(new Smoki("s", 0, 0, 9));
        Mapa.map[0][0] = "s";
        expected.add(new Smoki("s", 1, 1, 8));
        TestInteraction();
        assertEquals(expected.get(0).health, Symulacja.agents.get(0).health);
        AfterTheTest();
    }


}
