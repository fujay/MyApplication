package fra_uas.project.eu.hmi.Games.GameKugel;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root
public class Level {
    @Attribute
    private int number;

    @Element
    private Ball ball;

    @Element
    private Hole hole;

    @Attribute
    private int points;

    @Attribute
    private int time;

    @ElementList
    private List<Obstacle> obstacles;

    public int getNumber() {
        return number;
    }

    public Ball getBall() {
        return ball;
    }

    public Hole getHole() {
        return hole;
    }

    public int getPoints() {
        return points;
    }

    public int getTime() {
        return time;
    }

    public List<Obstacle> getObstacles() {
        return obstacles;
    }
}
