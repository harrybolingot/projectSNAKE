package com.zetcode;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {

    private final int B_WIDTH = 300;
    private final int B_HEIGHT = 300;
//    private final int DOT_SIZE = 10;
    private final int ALL_DOTS = 900;
    private final int RAND_POS = 29;
//    private final int DELAY = 140;
    
    private int DELAY = 140;

    private final int x[] = new int[ALL_DOTS];
    private final int y[] = new int[ALL_DOTS];

    private int dots;
    private int apple_x;
    private int apple_y;
    
    //Adding Fake Apple
    private int fakeApple_x;
    private int fakeApple_y;
    
    //Adding gameScore
    private int gameScore;
    
    //Adding keyPressed counters
    private int keyRightCount;
    private int keyLeftCount;
    private int keyUpCount;
    private int keyDownCount;
    
    //Adding random number for faking
    private int fakeFactor;
    
    //Adding counter
    private int counter;
    
    //Adding help status
    private boolean showingHelp = false;
    
    //Success indicator
    public boolean success = false;
    
    private int DOT_SIZE = 10;

    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;
    private boolean inGame = true;

    private Timer timer;
    private Image ball;
    private Image apple;
    private Image head;

    public Board() {

        addKeyListener(new TAdapter());
        setBackground(Color.black);
        setFocusable(true);

        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        loadImages();
        initGame();
        
		addKeyListener(new KeyListener(){
    	
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
	
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyChar() == 'r'){
					gameRestart();
				}
				
				if(e.getKeyChar() == 'h'){
				}
			}
	
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
    	
		});
    }

    private void loadImages() {

        ImageIcon iid = new ImageIcon("Heady.png");
        ball = iid.getImage();

        ImageIcon iia = new ImageIcon("Wally.png");
        apple = iia.getImage();

        ImageIcon iih = new ImageIcon("Snakey.png");
        head = iih.getImage();
    }

    private void initGame() {

        dots = 3;

        for (int z = 0; z < dots; z++) {
            x[z] = 50 - z * 10;
            y[z] = 50;
        }

        locateApple();
        
        //Adding fake Apple
        locateFakeApple();

        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
        
        //showHelp
        addKeyListener(new KeyListener(){
        	
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
	
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyChar() == 'r'){
					//gameRestart();
				}
				
				if(e.getKeyChar() == 'h'){
					showHelp(g);
				}
			}
	
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
    	
		});
    }
    
    private void doDrawing(Graphics g) {
        
        if (inGame && !(showingHelp)) {

            g.drawImage(apple, apple_x, apple_y, this);
            
            if((dots*fakeFactor) % 3 == 0){
            	g.drawImage(apple, apple_x, apple_y, this);
            	g.drawImage(apple, fakeApple_x, fakeApple_y, this);
            }
            

            for (int z = 0; z < dots; z++) {
                if (z == 0) {
                    g.drawImage(head, x[z], y[z], this);
                } else {
                    g.drawImage(ball, x[z], y[z], this);
                }
            }

            Toolkit.getDefaultToolkit().sync();
            
            //Adding display of gameScore while inGame
            displayInGameScore(g);
        } 
        
        else if(inGame && showingHelp) {
            	showHelp(g);
            	showingHelp = false;
        } 
        
        if(!(inGame) && !(showingHelp)){
        	gameOver(g);
        }
        
    }

    void displayInGameScore(Graphics g){
    	String inGameScore = String.valueOf(gameScore);
        Font big = new Font("Helvetica", Font.BOLD, 20);

        g.setColor(Color.white);
        g.setFont(big);
        g.drawString(inGameScore, 280, 20);
        
        //Function tester
        success = true;
    }
    
    void gameOver(Graphics g) {
        
        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        Font big = new Font("Helvetica", Font.BOLD, 20);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.red);
        g.setFont(big);
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);
        
        //Adding display gameScore upon gameOver
        String msgScore = "Your score is: " + String.valueOf(gameScore);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msgScore, (B_WIDTH - metr.stringWidth(msgScore)) / 2, B_HEIGHT / 2+30);
        
        if(showingHelp){
        	showHelp(g);
        }
        
        //Function tester
        success = true;
        
    }
    
    //Adding showHelp
    void showHelp(Graphics g) {
    	
        String msg = "Instructions:";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        Font big = new Font("Helvetica", Font.BOLD, 20);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.red);
        g.setFont(big);
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);
        
        //Adding display gameScore upon gameOver
        String msgInstructions = "Move snake using arrow keys.";
        String msgHelp = "Beware of poisoned apples!";

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msgInstructions, (B_WIDTH - metr.stringWidth(msgInstructions)) / 2, B_HEIGHT / 2+30);
        
        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msgHelp, (B_WIDTH - metr.stringWidth(msgInstructions)) / 2, B_HEIGHT / 2+50);
     
        //Function tester
        success = true;
    }
    
    //gameRestart
    void gameRestart(){
    	inGame = true;
		timer.stop();
		loadImages();
		initGame();
		
        //Function tester
        success = true;
    }

    
    private void checkApple() {

        for(int i = 0; i < dots; i++){
//        	if ((x[i] == apple_x) && (y[i] == apple_y)) {
        	if (((x[0] == apple_x) && (y[0] == apple_y)) || ((x[1] == apple_x) && (y[1] == apple_y)) || ((x[2] == apple_x) && (y[2] == apple_y))) {
                dots++;
                fakeFactor = (int) (Math.random()*(10-1));
                locateApple();
                locateFakeApple();
            }
        	
        	if ((x[i] == fakeApple_x) && (y[i] == fakeApple_y)) {
        		inGame = false;
        		locateApple();
        		locateFakeApple();
            }
        }
        
        gameScore = dots - 3;
    }

    //Adding moveAccelerate
    void moveAccelerate(){
    	
    	for (int z = dots; z > 0; z--) {
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }

        if (leftDirection && keyLeftCount > 0) {
        	timer.setDelay(DELAY/(2*keyLeftCount));
            x[0] -= DOT_SIZE;
        }

        if (rightDirection && keyLeftCount > 0) {
        	timer.setDelay(DELAY/(2*keyRightCount));
        	x[0] += DOT_SIZE;
        }

        if (upDirection && keyLeftCount > 0) {
        	timer.setDelay(DELAY/(2*keyUpCount));
        	y[0] -= DOT_SIZE;
        }

        if (downDirection && keyLeftCount > 0) {
        	timer.setDelay(DELAY/(2*keyDownCount));
        	y[0] += DOT_SIZE;
        }
        
        resetKeyCount();
        
        //Function tester
        success = true;
    }

    private void checkCollision() {

        for (int z = dots; z > 0; z--) {

            if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
                inGame = false;
            }
        }

        if (y[0] >= B_HEIGHT) {
            inGame = false;
        }

        if (y[0] < 0) {
            inGame = false;
        }

        if (x[0] >= B_WIDTH) {
            inGame = false;
        }

        if (x[0] < 0) {
            inGame = false;
        }
        
        if(!inGame) {
            timer.stop();
        }
    }

    private void locateApple() {

        int r = (int) (Math.random() * RAND_POS);
        apple_x = ((r * DOT_SIZE));

        r = (int) (Math.random() * RAND_POS);
        apple_y = ((r * DOT_SIZE));
    }
    
    void locateFakeApple() {

        int r = (int) (Math.random() * RAND_POS);
        fakeApple_x = ((r * DOT_SIZE));

        r = (int) (Math.random() * RAND_POS);
        fakeApple_y = ((r * DOT_SIZE));
        
        //Function tester
        success = true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (inGame) {

            checkApple();
            checkCollision();
            moveAccelerate();
        }

        repaint();
    }
    
    void resetKeyCount(){
    	keyLeftCount = 1;
    	keyRightCount = 1;
    	keyUpCount = 1;
    	keyDownCount = 1;
    	
        //Function tester
        success = true;
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();
            

            if ((key == KeyEvent.VK_LEFT) && (!rightDirection)) {
                leftDirection = true;
                upDirection = false;
                downDirection = false;
                keyLeftCount++;
            }

            if ((key == KeyEvent.VK_RIGHT) && (!leftDirection)) {
                rightDirection = true;
                upDirection = false;
                downDirection = false;
                keyRightCount++;
            }

            if ((key == KeyEvent.VK_UP) && (!downDirection)) {
                upDirection = true;
                rightDirection = false;
                leftDirection = false;
                keyUpCount++;
            }

            if ((key == KeyEvent.VK_DOWN) && (!upDirection)) {
                downDirection = true;
                rightDirection = false;
                leftDirection = false;
                keyDownCount++;
            }
            
            if (key == KeyEvent.VK_H) {
                //will show help?
            	showingHelp = true;
            }
           
        }
        
    }
}