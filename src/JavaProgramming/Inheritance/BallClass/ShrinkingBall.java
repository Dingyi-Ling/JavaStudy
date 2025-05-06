package JavaProgramming.Inheritance.BallClass;
import java.awt.Color;
import java.awt.Graphics;

public class ShrinkingBall extends Ball {

    private int originalRadius;
    private int minRadius;

    public ShrinkingBall(int startx, int starty, int startrad, int startxspeed, int startyspeed, Color startcolor) {
        super(startx, starty, startrad, startxspeed, startyspeed, startcolor);
        this.originalRadius = startrad;
        this.minRadius = 5; // 最小半径为5
    }

    @Override
    public void move() {
        super.move();

        // 每次移动稍微缩小一点点
        if (getRad() > minRadius) {
            setRad(getRad() - 1);
        } else {
            // 当缩小到最小半径时，重置为原始大小
            setRad(originalRadius);
        }

        // 简单的边界检测，防止小球离开屏幕
        if (getX() <= 0 || getX() + getRad() >= WIDTH) {
            setXSpeed(-getXSpeed());
        }

        if (getY() <= 0 || getY() + getRad() >= HEIGHT) {
            setYSpeed(-getYSpeed());
        }
    }
}