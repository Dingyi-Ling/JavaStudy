package JavaProgramming.Inheritance.BallClass;
import java.awt.Color;
import java.util.Random;

public class ColorBall extends Ball {

    private int colorChangeCounter;

    public ColorBall(int startx, int starty, int startrad, int startxspeed, int startyspeed) {
        super(startx, starty, startrad, startxspeed, startyspeed, getRandomColor());
        colorChangeCounter = 0;
    }

    private static Color getRandomColor() {
        return new Color(
                (int)(Math.random() * 256),
                (int)(Math.random() * 256),
                (int)(Math.random() * 256) 
        );
    }

    public void move() {
        super.move();
        colorChangeCounter++;
        if (colorChangeCounter >= 80) {
            colorChangeCounter = 0;
            setColor(getRandomColor());
        }
        if (getX() <= 0 || getX() + getRad() >= WIDTH) {
            setXSpeed(-getXSpeed());
        }
        if (getY() <= 0 || getY() + getRad() >= HEIGHT) {
            setYSpeed(-getYSpeed());
        }
    }
}