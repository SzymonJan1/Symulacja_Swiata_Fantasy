import javax.lang.model.type.NullType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

abstract class Agent implements AgentInterface{

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
}
