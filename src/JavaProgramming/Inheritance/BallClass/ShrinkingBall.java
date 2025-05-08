package JavaProgramming.Inheritance.BallClass;
import java.awt.Color;
import java.awt.Graphics;

public class ShrinkingBall extends Ball {

    private int originalRadius;
    private int minRadius;

    public ShrinkingBall(int startx, int starty, int startrad, int startxspeed, int startyspeed, Color startcolor) {
        super(startx, starty, startrad, startxspeed, startyspeed, startcolor);
        this.originalRadius = startrad;
        this.minRadius = 5;
    }

    public void move() {
        super.move();
        if (getRad() > minRadius) {
            setRad(getRad() - 1);
        } else {
            setRad(originalRadius);
        }

        if (getX() <= 0 || getX() + getRad() >= WIDTH) {
            setXSpeed(-getXSpeed());
        }
        if (getY() <= 0 || getY() + getRad() >= HEIGHT) {
            setYSpeed(-getYSpeed());
        }
    }
}