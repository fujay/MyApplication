package fra_uas.project.eu.hmi.Games.GameKugel;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;


@Root
public class Ball {
    @Attribute
    private int startx;
    @Attribute
    private int starty;

    public int getStartx() {
        return startx;
    }

    public int getStarty() {
        return starty;
    }
}