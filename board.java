board.java


package snakegame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Board extends JPanel implements ActionListener{
    
    private Image apple ;  // variable holds/stores images
    private Image dot ;
    private Image head  ; 
    
    private boolean inGame  = true ;
    
    
    private final int ALL_DOTS =  1600;
    private final int DOT_SIZE = 10 ;
    private final int RANDOM_POSITION = 29 ;
    
    private final int x[] = new int[ALL_DOTS];
    private final int y[] = new int[ALL_DOTS];
    
//    if i want to move my snake in 1 particular direction let say right
    
    private boolean leftDirection = false ;
    private boolean rightDirection = true ;
    private boolean upDirection = false ;
    private boolean downDirection = false ;
    
    private int dots ;
     private int apple_x ;
      private int apple_y ;
      
      private Timer timer ; 
    
    
    Board()//constructor
    {
        addKeyListener(new TAdapter());
         
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(400 , 400));
        setFocusable(true);
        
        loadImages();
        initGame();
    }
    
    public void loadImages()
    {
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("snakegame/icons/apple.png"));
        apple = i1.getImage();
        
        ImageIcon i2 = new ImageIcon(ClassLoader.getSystemResource("snakegame/icons/dot.png"));
        dot = i2.getImage();
        
        ImageIcon i3 = new ImageIcon(ClassLoader.getSystemResource("snakegame/icons/head.png"));
        head = i3.getImage();
    }
    
    public void initGame()
    {
        dots =3 ; //snake length;images
//        y distance is same but x distence is diff for diff images
        for(int i = 0 ; i< dots ; i++)
        {
            y[i] = 50;
            x[i] = 50 - (i*DOT_SIZE);
        }
        
        locateApple() ; 
        
        timer = new Timer(140 , this);
        timer.start();
    }
    
    public void locateApple()
    {
//        G BOTH X XY 
       
         int r = (int)(Math.random() *RANDOM_POSITION) ; 
         apple_x = r* DOT_SIZE;
         
           r = (int)(Math.random() *RANDOM_POSITION) ; 
           apple_y = r*DOT_SIZE;
        
    }
    
   
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        draw(g);
        
    }
    
    public void draw(Graphics g)
    {
         if(inGame)
        {
        g.drawImage(apple, apple_x, apple_y, this);
        
        for(int i = 0 ; i< dots ; i++)
        {
            if( i == 0 )
            {
                g.drawImage(head, x[i], y[i], this);
            }
            else{
                g.drawImage(dot, x[i], y[i], this);
            }
        }
        
        Toolkit.getDefaultToolkit().sync();
        }else
         {
             gameOver(g); 
         }
    }
    
    public void gameOver(Graphics g)
    {
         String msg = "Game Over !";
         Font font = new Font("SAN_SERIF" , Font.BOLD , 14) ;
         FontMetrics metrics = getFontMetrics(font);
         
         
         g.setColor(Color.WHITE);
         g.setFont(font);
         g.drawString(msg, (400 - metrics.stringWidth(msg))/2, 400/2);
         
    }
    public void move()
    {
        for(int i = dots ; i>0 ; i--)
        {
//            kkk if it id moving k moving forward , n prev k is taking its place
            x[i] = x[i - 1] ;
            y[i] = y[i - 1] ;
        }
        
        if(leftDirection)
        {
//            if my snake going in left direction cordinates keeps on decreasing
            x[0] = x[0] - DOT_SIZE ;
        }
        if(rightDirection)
        {
            x[0] = x[0] + DOT_SIZE ;
        }
        if(upDirection)
        {
            y[0] = y[0] - DOT_SIZE ;
        }
        if(downDirection)
        {
            y[0] = y[0] + DOT_SIZE ; //Y + MOVES DOWN
        }

    }
    
    public void checkApple()
    {
//        checking for the collisioon of head with the apple if yes increment the snake;s size by one
        if(x[0] == apple_x && y[0] == apple_y)
        {
            dots++ ; 
            locateApple();
        }// snakee eadting apple
    }
    
    public void checkCollision()
    {
          for(int i = dots ; i> 0 ; i--)
          {
              if(dots > 4 && (x[0] == x[i] && y[0] == y[i]))
              {
                  inGame = false ;
              }
              if(x[0] >= 400)
              {
                   inGame = false ;
              }
              if(y[0] >= 400)
              {
                   inGame = false ;
              }
              if(x[0] < 0)
              {
                   inGame = false ;
              }
              if(y[0] < 0)
              {
                   inGame = false ;
              }
              if(!inGame)
              {
                  timer.stop();
              }
          }
    }
    public void actionPerformed(ActionEvent e)
    {
        if(inGame)
        {
        checkApple();
        checkCollision();
        move();
        }
        
        repaint() ; 
    }
    
//    sub class
     public class TAdapter extends KeyAdapter
             {
                 @Override
                 public void keyPressed(KeyEvent e)
                 {
                     int key = e.getKeyCode();
                     if(key == KeyEvent.VK_LEFT &&(!rightDirection))
                     {
                         leftDirection = true;
                         upDirection = false ;
                         downDirection = false ;
                     }
                     if(key == KeyEvent.VK_RIGHT &&(!leftDirection))
                     {
                         rightDirection = true;
                         upDirection = false ;
                         downDirection = false ;
                     }
                     if(key == KeyEvent.VK_UP &&(!downDirection))
                     {
                         upDirection = true;
                         leftDirection = false;
                         rightDirection = false ;
                     }
                     if(key == KeyEvent.VK_DOWN &&(!upDirection))
                     {
                         downDirection = true;
                         leftDirection = false;
                         rightDirection = false ;
                        
                     }
                 }
             }
    
}
