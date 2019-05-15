
/**
 * Write a description of class Player here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Player extends Character
{
    boolean moveRight;
    boolean moveLeft;
    boolean shoot;
    public Player(int x, int y, int s)
    {
        super(x,y,s);
        moveLeft = false;
        moveRight = false;
    }
}