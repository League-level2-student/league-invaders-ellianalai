import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Rocketship extends GameObject {

	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	

	public Rocketship(int x, int y, int width, int height) {
		super(x,y,width,height);
		speed = 10;
		if (needImage) {
			loadImage ("images/rocket (1).png");
		}
	}

	public Projectile getProjectile() {
		return new Projectile(x+width/2, y, 10, 10);
	} 

	public void up() {
		y-=speed;
		
		if(y<0) {
			y=0;
			
		}
		super.update();
	}

	public void down() {
		y+=speed;
		if(y>750) {
			y=750;
		}
		super.update();
	}

	public void left() {
		x-=speed;
		if(x<0) {
			x=0;
		}
		super.update();
	}

	public void right() {
		x+=speed;
		if(x>435) {
			x=435;
		}
		super.update();
	}

	void draw(Graphics g) {
		if (gotImage) {
			g.drawImage(image, x, y, width, height, null);
		} else {
			g.setColor(Color.BLUE);
			g.fillRect(x, y, width, height);
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



}
