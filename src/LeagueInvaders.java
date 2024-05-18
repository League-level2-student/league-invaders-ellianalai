import javax.swing.JFrame;
import javax.swing.JPanel;

public class LeagueInvaders {
	
	JFrame frame;	
	public static final int WIDTH = 500;
	public static final int HEIGHT = 800;
	GamePanel panel;
	
	public LeagueInvaders() {
		frame = new JFrame();
		panel = new GamePanel();
	}
	
	void setup() {
		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.setVisible(true);
		
		
	}
	public static void main(String [] args) {
		LeagueInvaders invaders = new LeagueInvaders();
		invaders.setup();
	}
}
