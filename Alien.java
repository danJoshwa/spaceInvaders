
/**
 * Write a description of class Alien here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Alien extends Character
{
    boolean moveRight;
    boolean moveLeft;
    boolean isVisible;
    
    public Alien(int x, int y, int s)
    {
        super(x,y,s);
        moveLeft = false;
        moveRight = true;
        isVisible = true;
    }
}