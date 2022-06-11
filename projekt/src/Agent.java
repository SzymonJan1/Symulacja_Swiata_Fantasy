import java.util.ArrayList;
import java.util.List;

abstract class Agent {

    protected int positionX, positionY;
    protected int health;
    protected String name;
    protected List<Integer> waysForX = new ArrayList<>();//możliwe przesunięcia w osi xy
    protected List<Integer> waysForY = new ArrayList<>();
    protected static int wayX, wayY;//przesunięcie w osi xy, wyliczane przed zmianą pozycji

    protected Agent(String name, int positionX, int positionY, int health) {

        this.name = name;
        this.positionX = positionX;
        this.positionY = positionY;
        this.health = health;
    }

    protected void Shift_X_Y() {

    }

    protected void Interaction() {

    }
}
