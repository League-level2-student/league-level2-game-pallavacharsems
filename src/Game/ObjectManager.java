package Game;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager implements ActionListener {
	Knight k;
	Sword s;
	Dragon d;
	GamePanel gp;
	Random random = new Random();
	int score = 0;
	GamePanel gpanel;

	ObjectManager(Knight k, Sword s, Dragon d, GamePanel gp) {
		this.k = k;
		this.s = s;
		this.d = d;
		this.gp = gp;

	}

	public int getScore() {
		return score;

	}


	

	void draw(Graphics g) {
		k.draw(g);
		if(gp.currentState != gp.KEY) {
		s.draw(g);
		}
		if(gp.currentState == gp.GAME) {
			d.draw(g);
		}
		
			
		
	}

	void checkCollision() {
		
			if (k.collisionBox.intersects(d.collisionBox)) {
				k.isActive = false;
				d.isActive = false;
				System.out.println("t");
			}
			
				}
			

		
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
