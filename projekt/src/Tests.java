import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class Tests {        //klasa do przeprowadzania testow jednostkowych interakcji miedzy agentami

    private ArrayList<Agent> expected = new ArrayList<>();

    void MapToTests() {     //inicjalizacja mapy potrzebnej do testow
        Mapa.x = 2;
        Mapa.y = 2;
        Mapa.map = new String[Mapa.x][Mapa.y];
        Agent.wayX = 1;
        Agent.wayY = 1;
    }

    void TestInteraction() {     //wspolne elementy kazdego testu(rozpoczecie interakcji, usuwanie "zerowych" agentow, wyswietlanie listy i mapy)
        Symulacja.agents.get(0).Interaction();  //interakcja
        Symulacja.deleteAgents();
        System.out.println("Oczekiwana lista agentow: " + expected);
        System.out.println("Aktualna lista agentow: " + Symulacja.agents);   //wyswietlanie listy agentow
        Symulacja.printMap();
    }

    void AfterTheTest() {
        Symulacja.birthCount = 0;
        Symulacja.agents.clear();
        expected.clear();
        System.out.println();
    }

    @Test
    public void KosmiciMeetLudzieTest() {    //test interakcji, gdy kosmita spotyka czlowieka
        MapToTests();
        Symulacja.agents.add(new Kosmici("k", 0, 0, 0));
        Symulacja.agents.add(new Ludzie("l", 1, 1, 2));
        Mapa.map[0][0] = "k";
        Mapa.map[1][1] = "l";
        expected.add(new Kosmici("k", 0, 0, 0));
        expected.add(new Kosmici("k0", 1,1,0));     //oczekiwany agent
        TestInteraction();
        assertEquals(expected.get(1).name, Symulacja.agents.get(1).name);       //sprawdzamy czy nazwa sie zgadza
        AfterTheTest();
    }

    @Test
    public void KosmiciMeetWrozkiTest() {   //test interakcji, gdy kosmita spotyka wrozke
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

    @Test
    public void WrozkiMeetKosmiciTest() {   //test interakcji, gdy wrozka spotyka kosmite
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

    @Test
    public void WrozkiMeetMiesozerneRoslinyTest() {   //test interakcji, gdy wrozka spotyka miesozerna rosline
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

    @Test
    public void WrozkiMeetLudzieTest() {    //test interakcji, gdy wrozka spotyka czlowieka
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

    @Test
    public void LudzieMeetKosmiciTest() {    //test interakcji, gdy czlowiek spotyka kosmite
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

    @Test
    public void LudzieMeetSmokiTest() {    //test interakcji, gdy czlowiek spotyka smoka
        MapToTests();
        Symulacja.agents.add(new Ludzie("l", 0, 0, 2));
        Symulacja.agents.add(new Smoki("s", 1,1,5));
        Mapa.map[0][0] = "l";
        Mapa.map[1][1] = "s";
        expected.add(new Smoki("s", 1,1,5));
        TestInteraction();
        assertEquals(expected.get(0).name, Symulacja.agents.get(0).name);
        AfterTheTest();
    }

    @Test
    public void LudzieMeetLudzieTest() {    //test interakcji, gdy czlowiek spotyka innego czlowieka
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

    @Test
    public void LudzieMeetWrozkiTest() {    //test interakcji, gdy czlowiek spotyka wrozke
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

    @Test
    public void LudzieWithTwoLivesMeetMiesozerneRoslinyTest() {     //test interakcji, gdy czlowiek spotyka miesozerna roslina (czlowiek ma dwa zycia)
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

    @Test
    public void LudzieWithOneLifeMeetMiesozerneRoslinyTest() {      //test interakcji, gdy czlowiek spotyka miesozerna roslina (czlowiek ma jedno zycie)
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

    @Test
    public void MiesozerneRoslinyMeetWrozkiTest() {     //test interakcji, gdy miesozerna roslina spotyka wrozke
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

    @Test
    public void MiesozerneRoslinyMeetLudziWithOneLifeTest() {       //test interakcji, gdy miesozerna roslina spotyka czlowieka (czlowiek ma jedno zycie)
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

    @Test
    public void MiesozerneRoslinyNoMeetTest() {        //test sytuacji, gdy miesozerna roslina nie spotyka wrozki, ani czlowieka
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

    @Test
    public void SmokiMeetLudzieTest() {     //test interakcji, gdy smok spotyka czlowieka
        MapToTests();
        Symulacja.agents.add(new Smoki("s", 0, 0, 1));
        Symulacja.agents.add((new Ludzie("l", 1, 1, 2)));
        Mapa.map[0][0] = "s";
        Mapa.map[1][1] = "l";
        expected.add(new Smoki("s", 1, 1, 5));
        System.out.println("Punkty zycia smoka przed interakcja: " + Symulacja.agents.get(0).health);
        TestInteraction();
        System.out.println("Punkty zycia smoka po interakcji: " + Symulacja.agents.get(0).health);
        assertEquals(expected.get(0).health, Symulacja.agents.get(0).health);
        AfterTheTest();
    }

    @Test
    public void SmokiMeetSmokiTest() {      //test interakcji, gdy smok spotyka ineego smoka
        MapToTests();
        Symulacja.agents.add(new Smoki("s", 0, 0, 5));
        Symulacja.agents.add(new Smoki("s", 1, 1, 5));
        Mapa.map[0][0] = "s";
        Mapa.map[1][1] = "s";
        expected.add(new Smoki("s", 0, 0, 5));
        expected.add(new Smoki("s", 0, 0, 5));
        expected.add(new Smoki("s0", -1, -1, 5));
        TestInteraction();
        assertEquals(expected.get(2).name, Symulacja.agents.get(2).name);
        AfterTheTest();
    }

    @Test
    public void SmokiNoMeetTest() {
        MapToTests();
        Symulacja.agents.add(new Smoki("s", 0, 0, 5));
        Mapa.map[0][0] = "s";
        expected.add(new Smoki("s", 1, 1, 4));
        TestInteraction();
        assertEquals(expected.get(0).health, Symulacja.agents.get(0).health);
        AfterTheTest();
    }


}
