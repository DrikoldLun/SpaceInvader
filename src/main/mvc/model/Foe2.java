package model;

import javax.imageio.ImageIO;
import java.io.IOException;

// bullet1
public class Foe2 extends Foe{
    public Foe2() throws IOException {
        super();
        setHealth(40);
        setBullettype(3);
        setImage(ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/foe2.png")));
    }
}
