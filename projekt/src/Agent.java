import java.util.ArrayList;
import java.util.List;

abstract class Agent {

    int positionX, positionY;
    int health;
    String name;
    List<Integer> waysForX = new ArrayList<>();//możliwe przesunięcia w osi xy
    List<Integer> waysForY = new ArrayList<>();
    static int wayX, wayY;//przesunięcie w osi xy, wyliczane przed zmianą pozycji

    public Agent(String name, int positionX, int positionY, int health) {

        this.name = name;
        this.positionX = positionX;
        this.positionY = positionY;
        this.health = health;
    }

    void Shift_X_Y() {

    }

    void Interaction() {

    }
}
