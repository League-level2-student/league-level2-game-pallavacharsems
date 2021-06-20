package Game;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager implements ActionListener {
	Knight k;
	ArrayList<Sword> sword = new ArrayList<Sword>();
	ArrayList<Dragon> dragons = new ArrayList<Dragon>();
	Random random = new Random();
	int score = 0;

	ObjectManager(Knight k) {
		this.k = k;

	}

	public int getScore() {
		return score;

	}

	void addSword(Sword p) {
		sword.add(p);
	}

	void addDragon() {
		dragons.add(new Dragon(random.nextInt(SavePrincess.WIDTH), 0, 50, 50));
	}

	void update() {
		for (int i = 0; i < dragons.size(); i++) {
			Dragon a = dragons.get(i);
			a.update();
			if (a.x >= SavePrincess.HEIGHT || a.x <= 0) {
				a.isActive = false;
			}
		}
		for (int h = 0; h < sword.size(); h++) {
			Sword t = sword.get(h);
			t.update();
			if (t.y < 0) {
				t.isActive = false;

			}
		}
		checkCollision();
		purgeObjects();
	}

	void purgeObjects() {
		for (int i = 0; i < dragons.size(); i++) {
			Dragon c = dragons.get(i);
			if (c.isActive == false) {
				dragons.remove(c);
			}
		}
		for (int i = 0; i < sword.size(); i++) {
			Sword a = sword.get(i);
			if (a.isActive == false) {
				sword.remove(a);
			}
		}

	}

	void draw(Graphics g) {
		k.draw(g);
		for (int i = 0; i < dragons.size(); i++) {
			Dragon a = dragons.get(i);
			a.draw(g);
		}
		for (int i = 0; i < sword.size(); i++) {
			Sword pr = sword.get(i);
			pr.draw(g);
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
			for (int k = 0; k < sword.size(); k++) {
				Sword d = sword.get(k);
				if (d.collisionBox.intersects(b.collisionBox)) {
					d.isActive = false;
					b.isActive = false;
					score+=1;
				}
			}

		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		addDragon();
	}
}
