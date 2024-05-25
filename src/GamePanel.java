import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener{

	final int MENU = 0;
	final int GAME = 1;
	final int END = 2;
	int currentState = MENU;
	Font titleFont;
	Font enterKey;
	Font spaceBar;
	Font gameOver;
	Font enemiesKilled;
	Font restart;
	Timer frameDraw;
	Rocketship rocket = new Rocketship(250, 700,50,50);

	public GamePanel() {
		titleFont = new Font("Arial", Font.PLAIN, 48);
		enterKey = new Font("Arial", Font.PLAIN, 20);
		spaceBar = new Font("Arial", Font.PLAIN, 20);
		gameOver = new Font("Arial", Font.PLAIN, 48);
		enemiesKilled = new Font("Arial", Font.PLAIN, 20);
		restart = new Font("Arial", Font.PLAIN, 20);
		frameDraw = new Timer(1000/60,this);
		frameDraw.start();
	}
	@Override
	public void paintComponent(Graphics g){
		if(currentState==MENU) {
			drawMenuState(g);
		}
		else if (currentState==GAME) {
			drawGameState(g);
		}
		else if (currentState==END) {
			drawEndState(g);
		}
	}

	void updateMenuState() {  }
	void updateGameState() {  }
	void updateEndState()  {  }

	void drawMenuState(Graphics g) {  
		g.setColor(Color.BLUE);
		g.fillRect(0,0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		g.drawString("LEAGUE INVADERS", 25, 100);
		g.setFont(enterKey);
		g.drawString("Press ENTER to Start", 150, 350);
		g.setFont(spaceBar);
		g.drawString("Press SPACE for Instructions", 120, 550);
	}
	void drawGameState(Graphics g) {  
		g.setColor(Color.BLACK);
		g.fillRect(0,0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		rocket.draw(g);
	}
	void drawEndState(Graphics g)  { 
		g.setColor(Color.RED);
		g.fillRect(0,0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		g.setFont(gameOver);
		g.setColor(Color.YELLOW);
		g.drawString("Game Over", 125, 100);
		g.setFont(enemiesKilled);
		g.drawString("You killed __ enemies", 150, 350);
		g.setFont(restart);
		g.drawString("Press ENTER to restart", 150, 550);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println("action");
		repaint();

		if(currentState == MENU) {
			updateMenuState();
		}
		else if(currentState == GAME) {
			updateGameState();
		}
		else if (currentState == END) {
			updateEndState();
		}

	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			if(currentState == END) {
				currentState = MENU;
			}
			else {
				currentState++;
			}
		}


		if (e.getKeyCode()==KeyEvent.VK_UP) {
			System.out.println("UP");
			rocket.up();
		}
		else if (e.getKeyCode()==KeyEvent.VK_DOWN) {
			System.out.println("DOWN");
			rocket.down();
		}
		else if (e.getKeyCode()==KeyEvent.VK_LEFT) {
			System.out.println("LEFT");
			rocket.left();
		}
		else if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
			System.out.println("RIGHT");
			rocket.right();
		}


	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
