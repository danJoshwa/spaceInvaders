import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.Timer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.imageio.*;
import java.awt.image.*;
import java.io.*;

public class Board  extends JPanel implements Runnable, MouseListener
{
    boolean ingame = true;
    private Dimension d;
    int BOARD_WIDTH=500;
    int BOARD_HEIGHT=500;
    int x = 0;
    BufferedImage alienImg;
    BufferedImage shotImg;
    BufferedImage playerImg;
    String message = "Click Board to Start";
    private Thread animator;
    Player p;
    shot s;
    Alien[] a = new Alien[10];
    public Board()
    {
        addKeyListener(new TAdapter());
        addMouseListener(this);
        setFocusable(true);
        d = new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
        p = new Player(BOARD_WIDTH/2, BOARD_HEIGHT-60, 5);

        int ax = 10;
        int ay = 10;

        for (int i = 0; i < a.length; i++)
        {
            a[i] = new Alien(ax, ay, 10);
            ax += 40;
            if (i == 4)
            {
                ax = 10;
                ay += 40;
            }
        }

        setBackground(Color.black);

        try {
            alienImg = ImageIO.read(this.getClass().getResource("alien.png"));
        } catch (IOException e) {
            System.out.println("Image could not be read");
            // System.exit(1);
        }
        try {
            shotImg = ImageIO.read(this.getClass().getResource("pshot.png"));
        } catch(IOException e){
            System.out.println("Image could not be read");
        }
        try {
            playerImg = ImageIO.read(this.getClass().getResource("player.png"));
        } catch(IOException e){
            System.out.println("Image could not be read");
        }
        //add similar code for other images 
        if (animator == null || !ingame) {
            animator = new Thread(this);
            animator.start();
        }

        setDoubleBuffered(true);
    }

    public void paint(Graphics g)
    {
        super.paint(g);

        g.setColor(Color.white);
        g.fillRect(0, 0, d.width, d.height);
        //g.fillOval(x,y,r,r);

        //represents player
        //g.setColor(Color.red);
        //g.fillRect(p.x, p.y, 20, 20);
        g.drawImage(playerImg, p.x, p.y, 20, 20, null);
        if (p.moveRight == true)
        {
            p.x += p.speed;
        }
        if (p.moveLeft == true)
        {
            p.x -= p.speed;
        }
        //shoot();
        if (p.shoot == true)
        {
            //g.drawRect(p.x, 50, 10, 10);
            g.drawImage(shotImg, p.x, s.y, 10, 20, null);
        }
        for (int i = 0; i < a.length; i++)
        {
            if (a[i].moveLeft == true)
            {
                a[i].x -= 2;
            }
            if (a[i].moveRight == true)
            {
                a[i].x += 2;
            }
            //a[i].x += a[i].speed;

            //a[i].y += a[i].speed;
        }
        for (int i = 0; i < a.length; i++)
        {
            if (a[i].x > BOARD_WIDTH)
            {
                for (int j = 0; j < a.length; j++)
                {
                    a[j].moveLeft = true;
                    a[j].moveRight = false;
                    a[j].y += 5;
                }
            }
            if (a[i].x < 0)
            {
                for (int j = 0; j < a.length; j++)
                {
                    a[j].moveRight = true;
                    a[j].moveLeft = false;
                    a[j].y += 5;
                }
            }
        }
        for (int i = 0; i < a.length; i++)
        {
            if (a[i].isVisible == true)
            {
                //g.fillRect(a[i].x, a[i].y, 30, 30);
                g.drawImage(alienImg, a[i].x, a[i].y, 30, 30, null);
            }
        }

        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = this.getFontMetrics(small);
        g.setColor(Color.black);
        g.setFont(small);
        g.drawString(message, 10, d.height-60);

        if (ingame) {

            // g.drawImage(img,0,0,200,200 ,null);
        }
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }

    public void shoot()
    {
        s.y += s.s;
    }

    public void moveAliens()
    {

    }

    private class TAdapter extends KeyAdapter {

        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            p.moveRight = false;
            p.moveLeft = false;
            p.shoot = false;
        }

        public void keyPressed(KeyEvent e) {
            //System.out.println( e.getKeyCode());
            // message = "Key Pressed: " + e.getKeyCode();
            int key = e.getKeyCode();
            if(key==39){
                p.moveRight = true;
            }
            if(key==37){
                p.moveLeft = true;
            }
            if (key==32)
            {
                p.shoot = true;
            }

        }

    }

    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void mouseClicked(MouseEvent e) {

    }

    public void run() {

        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();
        int animationDelay = 10;
        long time = 
            System.currentTimeMillis();
        while (true) {//infinite loop
            // spriteManager.update();
            repaint();
            try {
                time += animationDelay;
                Thread.sleep(Math.max(0,time - 
                        System.currentTimeMillis()));
            }catch (InterruptedException e) {
                System.out.println(e);
            }//end catch
        }//end while loop

    }//end of run
}//end of class