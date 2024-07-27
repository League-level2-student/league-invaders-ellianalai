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
	ObjectManager obj = new ObjectManager(rocket);
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	Timer alienSpawn;
	Font score;
	
	

	public GamePanel() {
		titleFont = new Font("Arial", Font.PLAIN, 48);
		enterKey = new Font("Arial", Font.PLAIN, 20);
		spaceBar = new Font("Arial", Font.PLAIN, 20);
		gameOver = new Font("Arial", Font.PLAIN, 48);
		enemiesKilled = new Font("Arial", Font.PLAIN, 20);
		restart = new Font("Arial", Font.PLAIN, 20);
		frameDraw = new Timer(1000/60,this);
		frameDraw.start();
		if (needImage) {
		    loadImage ("images/space (2).png");
		}
		score = new Font("Arial", Font.PLAIN, 25);
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
	void updateGameState() { 
		obj.update();
		if(rocket.isActive == false) {
			currentState = END;
		}
	}
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
		if (gotImage) {
			g.drawImage(image, 0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT, null);
		} else {
			g.setColor(Color.BLACK);
			g.fillRect(0,0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		}
		
		obj.draw(g);
		g.setFont(score);
		g.setColor(Color.BLUE);
		g.drawString("Score:" + obj.getScore(), 25, 50);
	}
	void drawEndState(Graphics g)  { 
		g.setColor(Color.RED);
		g.fillRect(0,0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		g.setFont(gameOver);
		g.setColor(Color.YELLOW);
		g.drawString("Game Over", 125, 100);
		g.setFont(enemiesKilled);
		g.drawString("You killed " + obj.getScore() + " enemies", 160, 350);
		g.setFont(restart);
		g.drawString("Press ENTER to restart", 150, 550);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
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
			
			if(currentState == GAME) {
				startGame();
			}
			
			if(currentState == MENU) {
				alienSpawn.stop();
				rocket = new Rocketship(250,700,50,50);
				rocket.isActive = true;
				obj = new ObjectManager(rocket);
			}
		}
		
		if(e.getKeyCode()==KeyEvent.VK_SPACE) {
			if(currentState == GAME) {
				obj.addProjectile(rocket.getProjectile());
			}
			
			else if(currentState == MENU) {
				System.out.println("Use arrow keys to move. Press SPACE to fire. Try not to die. Good luck.");
			}
		}

		if (e.getKeyCode()==KeyEvent.VK_UP) {
			rocket.up();
		}
		else if (e.getKeyCode()==KeyEvent.VK_DOWN) {
			rocket.down();
		}
		else if (e.getKeyCode()==KeyEvent.VK_LEFT) {
			rocket.left();
		}
		else if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
			rocket.right();
		}


	}
	
	void loadImage(String imageFile) {
	    if (needImage) {
	        try {
	            image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
		    gotImage = true;
	        } catch (Exception e) {
	            
	        }
	        needImage = false;
	    }
	}
	
	void startGame() {
		alienSpawn = new Timer(1000, obj);
		alienSpawn.start();
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
