package model;

import javax.imageio.ImageIO;
import java.io.IOException;

// bullet3
public class Foe4 extends Foe {
    public Foe4() throws IOException {
        super();
        setHealth(80);
        setBullettype(4);
        setImage(ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/foe4.png")));
    }
}
