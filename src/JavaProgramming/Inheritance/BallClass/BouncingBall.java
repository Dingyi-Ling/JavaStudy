package JavaProgramming.Inheritance.BallClass;
import java.awt.Color;

public class BouncingBall extends Ball {

    public BouncingBall(int startx, int starty, int startrad, int startxspeed, int startyspeed, Color startcolor) {
        super(startx, starty, startrad, startxspeed, startyspeed, startcolor);
    }

    public void move() {
        super.move();
        if (getX() <= 0 || getX() + getRad() >= WIDTH) {
            setXSpeed(-getXSpeed());
        }
        if (getY() <= 0 || getY() + getRad() >= HEIGHT) {
            setYSpeed(-getYSpeed());
        }
    }
}