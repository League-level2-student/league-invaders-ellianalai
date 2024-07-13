import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager implements ActionListener{

	Rocketship rocket;
	ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	ArrayList<Alien> aliens = new ArrayList<Alien>();
	Random ran = new Random();
	
	
	public ObjectManager(Rocketship r) {
		rocket = new Rocketship(0, 0, 0, 0);
		this.rocket = r;
	}
	
	void addProjectile(Projectile p) {
		projectiles.add(p);
	}
	
	void addAlien() {
		aliens.add(new Alien(ran.nextInt(LeagueInvaders.WIDTH),0,50,50));
		
		
		}
	
	void update() {
		for(Alien a: aliens) {
			a.update();
			if(a.y>LeagueInvaders.HEIGHT) {
				a.isActive = false;
			}
		}
		
		for(Projectile pro: projectiles) {
			pro.update();
			if(pro.y>LeagueInvaders.HEIGHT) {
				pro.isActive = false;
			}
		}
	}
	
	void draw(Graphics g) {
		rocket.draw(g);
		
		for(Alien a: aliens) {
			a.draw(g);
		}
		for(Projectile p: projectiles) {
			p.draw(g);
		}
	}
	
	void purgeObjects() {
		for(int i = 0; i<projectiles.size(); i++) {
			Projectile p = projectiles.get(i);
			
			if(p.isActive == false) {
				projectiles.remove(i);
			}
			
		}
		
		for(int j = 0; j<aliens.size(); j++) {
			Alien a = aliens.get(j);
			
			if(a.isActive == false) {
				aliens.remove(j);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		addAlien();
		
	}
}
