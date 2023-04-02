package com.brainmentors.game.basics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.brainmentors.game.settings.GameConstants;
import com.brainmentors.game.sprites.Player;
import com.brainmentors.game.sprites.Power;
import com.brainmentors.game.sprites.SumitPlayer;
import com.brainmentors.game.sprites.SurajPlayer;

import jaco.mp3.player.MP3Player;

public class Board extends JPanel implements GameConstants,ActionListener,KeyListener{
	BufferedImage backgroundimage;
	Player Sumit;
	Player Suraj;
	Timer timer;
	MP3Player player;
	Power player1Power;
	Power player2Power;
	boolean isGameOver;
	Board() throws Exception{

		loadbackground();
	    Sumit = new SumitPlayer();
	    Suraj = new SurajPlayer();
	    bindEvents();
        setFocusable(true);
	    gameLoop();
	    player = new MP3Player(Board.class.getResource("gamesong.mp3"));
	    player.setRepeat(true);
	    PlayBackGroundMusic();
	    player1Power =new Power(20,"Sumit");
	    player2Power=new Power(BOARD_WIDTH/2+200,"Suraj");
	}
	public void drawPower(Graphics g) {
		player1Power.draw(g);
		player2Power.draw(g);

		
	}
	 void PlayBackGroundMusic() {
		player.play();
	}
	public void bindEvents(){
		this.addKeyListener(this);
	}
	public void gameLoop() {
		timer = new Timer(DELAY,this);
		timer.start();
	}
	void loadbackground() throws Exception {
		backgroundimage=ImageIO.read(Board.class.getResource(BACKGROUND_IMAGE));
	}
    @Override
    protected void paintComponent(Graphics pen) {
    	super.paintComponent(pen);
        pen.drawImage(backgroundimage, 0,0,BOARD_WIDTH,BOARD_HEIGHT,null );
        displayMessage(pen);
        Sumit.draw(pen);
        Suraj.draw(pen);
        drawPower(pen);
        playerAttackHit();
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		Sumit.fall();
		Suraj.fall();
          repaint();		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	public void displayMessage(Graphics pen){
		if(isGameOver) {
			pen.setFont(new Font("times",Font.BOLD,50));
			pen.setColor(Color.RED);
		pen.drawString("Game Over......",BOARD_HEIGHT/2+55,BOARD_WIDTH/4);                                
			timer.stop();
		}

	}
	public boolean playerAttackHit() {
		if(isCollide()) {
		if(Sumit.isAttacking()) {
			Suraj.setAction(HIT);
			Suraj.setPower(Suraj.getPower()-8);
			player2Power.setW(player2Power.getW()-8);
		}
		else if(Suraj.isAttacking()) {
			Sumit.setAction(HIT);
			Sumit.setPower(Sumit.getPower()-7);
			player1Power.setW(player1Power.getW()-7);
		}
		else if(Sumit.isAttacking()&& Suraj.isAttacking()) {
			Suraj.setAction(HIT);
			Sumit.setAction(HIT);
			Suraj.setPower(Suraj.getPower()-8);
			player2Power.setW(player1Power.getW()-8);
			Sumit.setPower(Sumit.getPower()-7);
			player1Power.setW(player1Power.getW()-7);
		}
		if(Sumit.getPower()<=0||Suraj.getPower()<=0) {
			isGameOver = true;
		}
		return true;
		}
		return false;
	}
	public boolean isCollide() {
		int xDistance= Math.abs(Sumit.getX()-Suraj.getX());
		int yDistance = Math.abs(Sumit.getY()-Suraj.getY());
		int maxH = Math.max(Sumit.getH(),Suraj.getH());
		int maxW = Math.max(Sumit.getW(),Suraj.getW());
		return xDistance<=maxW-25&& yDistance<=maxH;
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_S) {
			Sumit.setAction(KICK);
			Sumit.setAttacking(true);
		}
		else if(e.getKeyCode()==KeyEvent.VK_D) {
			Sumit.setSpeed(SPEED);
			Sumit.move();
		}
		else if(e.getKeyCode()==KeyEvent.VK_A) {
			Sumit.setSpeed(SPEED*-1);
			Sumit.move();
		     }
		else if(e.getKeyCode()==KeyEvent.VK_W) {
			Sumit.jump();
		}
		else if(e.getKeyCode()==KeyEvent.VK_I) {
			Suraj.jump();
		}
		else if(e.getKeyCode()==KeyEvent.VK_K) {
			Suraj.setAction(KICK);
			Suraj.setAttacking(true);
		}
		else if(e.getKeyCode()==KeyEvent.VK_J) {
			Suraj.setSpeed(SPEED*-1);
			Suraj.move();
		}
		else if(e.getKeyCode()==KeyEvent.VK_L) {
			Suraj.setSpeed(SPEED);
			Suraj.move();
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		
	}
    
}
