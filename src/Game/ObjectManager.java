package Game;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager implements ActionListener {
	Knight k;
	Sword s;
	ArrayList<Dragon> dragons = new ArrayList<Dragon>();
	Random random = new Random();
	int score = 0;

	ObjectManager(Knight k, Sword s) {
		this.k = k;
		this.s = s;

	}

	public int getScore() {
		return score;

	}

	

	void addDragon() {
		dragons.add(new Dragon(random.nextInt(SavePrincess.HEIGHT), 0, 50, 100));
	}

	void update() {
		for (int i = 0; i < dragons.size(); i++) {
			Dragon a = dragons.get(i);
			a.update();
			if (a.x >= SavePrincess.WIDTH || a.x <= 0) {
				a.isActive = false;
			}
		}
		

			}
		
		
	

	void purgeObjects() {
		for (int i = 0; i < dragons.size(); i++) {
			Dragon c = dragons.get(i);
			if (c.isActive == false) {
				dragons.remove(c);
			}
		}
		
			}
		

	

	void draw(Graphics g) {
		k.draw(g);
		s.draw(g);
		for (int i = 0; i < dragons.size(); i++) {
			Dragon a = dragons.get(i);
			a.draw(g);
		}
		
			
		}
	

	void checkCollision() {
		for (int i = 0; i < dragons.size(); i++) {
			Dragon b = dragons.get(i);
			if (k.collisionBox.intersects(b.collisionBox)) {
				k.isActive = false;
				b.isActive = false;
				System.out.println("t");
			}
			
				}
			}

		
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		addDragon();
	}
}
