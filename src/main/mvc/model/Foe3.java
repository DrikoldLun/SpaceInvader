package model;

import javax.imageio.ImageIO;
import java.io.IOException;

// bullet2
public class Foe3 extends Foe {
    public Foe3() throws IOException {
        super();
        setHealth(60);
        setBullettype(2);
        setImage(ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/foe3.png")));
    }
}
