package Game;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

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

	} void update() {
		checkCollision();
	}

	void draw(Graphics g) {
		k.draw(g);
		if (gp.currentState != gp.KEY) {
			s.draw(g);
		}
		if (gp.currentState == gp.GAME) {
			d.draw(g);
		}

	}

	void checkCollision() {

		if (s.collisionBox.intersects(d.collisionBox)) {
			d.w -= 1;
			if (d.w <= 0) {
				JOptionPane.showMessageDialog(null, "You Win! You have saved the princess");
				gp.currentState = gp.END;

			}
		} if(d.collisionBox.intersects(k.collisionBox)) {
		JOptionPane.showMessageDialog(null, "You Lose!");
		gp.currentState = gp.END;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
