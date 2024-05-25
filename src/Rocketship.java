import java.awt.Color;
import java.awt.Graphics;

public class Rocketship extends GameObject {
	
	public Rocketship(int x, int y, int width, int height) {
		super(x,y,width,height);
		speed = 10;
	}
	
	public void up() {
		y-=speed;
		if(y<50) {
			y=50;
		}
	}
	
	public void down() {
		y+=speed;
		if(y>750) {
			y=750;
		}
	}
	
	public void left() {
		x-=speed;
		if(x<50) {
			x=50;
		}
	}
	
	public void right() {
		x+=speed;
		if(x>450) {
			x=450;
		}
	}
	
	void draw(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(x, y, width, height);
		
	}

}
