package model;

import javax.imageio.ImageIO;
import java.io.IOException;

// can not fire
public class Foe1 extends Foe{
    // ==============================================================
    // FIELDS
    // ==============================================================

    // ==============================================================
    // CONSTRUCTOR
    // ==============================================================

    public Foe1() throws IOException {
        super();
        setHealth(20);
        //these are falcon specific
        setImage(ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/foe1.png")));
    }

    // ==============================================================
    // METHODS
    // ==============================================================
}
