package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;
	public static boolean hasWon = false;
	final int MENU = 0;
	final int KEY = 1;
	final int GAME = 2;
	final int END = 3;
	int times = 0;
	int currentState = MENU;
	Font titleFont;
	Font tf;
	long post = 0;
	boolean active = false;
	Timer frameDraw;
	Timer alienspawn;
	Knight k = new Knight(105, 250, 75, 100);
	Sword s = new Sword(k.x, k.y, 20, 40);
	Dragon d = new Dragon(550, 250, 250, 250, k);

	ObjectManager om = new ObjectManager(k, s, d, this);

	GamePanel() {
		JOptionPane.showMessageDialog(null,
				"You must defeat the dragon because it is destroying too much of your castle!");
		titleFont = new Font("Arial", Font.PLAIN, 48);
		tf = new Font("Arial", Font.PLAIN, 24);
		frameDraw = new Timer(1000 / 60, this);
		frameDraw.start();
		if (needImage) {
			loadImage("castle.png");
		}

	}

	@Override
	public void paintComponent(Graphics g) {
		if (currentState == MENU) {
			drawMenuState(g);
		} else if (currentState == KEY) {
			drawKeyState(g);
		} else if (currentState == GAME) {
			drawGameState(g);
		} else if (currentState == END) {
			drawEndState(g);
		}
	}

	void updateMenuState() {
	}

	void updateKeyState() {

		k.update();
		s.update();
		s.knightX = k.x;
		s.knightY = k.y;
		if (k.isActive == false) {
			currentState = END;
		}
	}

	void updateDungeonState() {

		k.update();
		s.update();
		s.knightX = k.x;
		s.knightY = k.y;
		if (k.isActive == false) {
			currentState = END;
		}
	}

	void updateGameState() {

		k.update();
		s.update();
		d.update();
		om.update();
		s.knightX = k.x;
		s.knightY = k.y;
		if (k.isActive == false) {
			currentState = END;
		}
		if (active == true) {
			if (System.currentTimeMillis() > post + 3000) {
				active = false;
				k.speed = 10;
			}
		}

	}

	void updateEndState() {
	}

	void drawMenuState(Graphics g) {
		g.setColor(Color.MAGENTA);
		g.fillRect(0, 0, DefeatDragon.WIDTH, DefeatDragon.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.BLUE);
		g.drawString("Defeat The Dragon", 300, 50);
		g.setFont(tf);
		g.setColor(Color.BLUE);
		g.drawString("Press ENTER to start", 375, 300);
		g.setFont(tf);

	}

	void drawKeyState(Graphics g) {
		if (gotImage) {
			g.drawImage(image, 0, 0, DefeatDragon.WIDTH, DefeatDragon.HEIGHT, null);
			g.setColor(Color.RED);
			g.setFont(tf);

		} else {
			g.setColor(Color.BLUE);
			g.fillRect(0, 0, DefeatDragon.WIDTH, DefeatDragon.HEIGHT);
		}
		om.draw(g);
		g.setFont(tf);g.setColor(Color.BLACK);
		g.drawString("ARROW KEYS to move", 300, 100);
		g.drawString("SPACE BAR to throw the sword ", 300, 150);
		g.drawString("Q to dash if you've earned it", 300, 200);
		g.drawString("Press enter to fight the dragon once your ready ", 300, 250);

	}

	void drawDungeonState(Graphics g) {
		if (gotImage) {
			g.drawImage(image, 0, 0, DefeatDragon.WIDTH, DefeatDragon.HEIGHT, null);
			g.setColor(Color.RED);
			
			

		} else {
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, DefeatDragon.WIDTH, DefeatDragon.HEIGHT);
		}
		om.draw(g);
	

	}

	void drawGameState(Graphics g) {
		if (gotImage) {
			g.drawImage(image, 0, 0, DefeatDragon.WIDTH, DefeatDragon.HEIGHT, null);
			g.setColor(Color.RED);
			g.setFont(tf);

		} else {
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, DefeatDragon.WIDTH, DefeatDragon.HEIGHT);
		}
		om.draw(g);

	}

	void drawEndState(Graphics g) {
		if(hasWon == false) {
			g.setColor(Color.RED);
			g.fillRect(0, 0, DefeatDragon.WIDTH, DefeatDragon.HEIGHT);
			g.setFont(titleFont);
			g.setColor(Color.YELLOW);
			g.drawString("Game Over", 375, 50);
			g.setFont(titleFont);
			g.setColor(Color.YELLOW);
			g.drawString("Press ENTER to restart", 310, 250);

	} else {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, DefeatDragon.WIDTH, DefeatDragon.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.MAGENTA);
		g.drawString("YOU WIN", 375, 50);
		g.setFont(titleFont);
		g.setColor(Color.MAGENTA);
		g.drawString("Press ENTER to play again", 210, 250);
		
	}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (currentState == MENU) {
			updateMenuState();
		} else if (currentState == KEY) {
			updateKeyState();

		} else if (currentState == GAME) {
			updateGameState();
		} else if (currentState == END) {
			updateEndState();

		}

		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

		if (e.getKeyCode() == KeyEvent.VK_ENTER) {

			if (currentState == END) {
				currentState = MENU;
				alienspawn.stop();
				k = new Knight(105, 250, 75, 100);
				s = new Sword(225, 250, 25, 50);
				d = new Dragon(550, 250, 250, 250, k);
				om = new ObjectManager(k, s, d, this);

			} else {
				currentState++;
				if (currentState == GAME) {
					k.x = 105;
					k.y = 250;
					s.x  = 225;
					s.y = 250;
				
				}
			}
			if (currentState == KEY) {
				startGame();
				JOptionPane.showMessageDialog(null, "To get 3 speed boosts you must solve the riddle!");
				String g = JOptionPane.showInputDialog("I have a tail but Im not a mouse\r\n"
						+ "I have scales but Im not a fish\r\n" + "I have wings but Im not an airplane\r\n"
						+ "I'm a mythical creature but Im not a unicorn\r\n"
						+ "I have fire coming out of my mouth but Im not a flame thrower\r\n" + "What am i?");
				g = g.toLowerCase();
				if (g.contains("dragon")) {
					times = 3;
					JOptionPane.showMessageDialog(null,
							"Great Job. You have earned 3 speed boosts! You can now move on to testing your abilities!");
				} else {
					JOptionPane.showMessageDialog(null,
							"Sorry you were wrong! You don't get the speed boosts! You can test your abilities!");

				}

			}
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			s.targetY = k.x + 85;
			s.targetX = k.y + 345;
			s.currentState = s.moveState;

		}

		if (e.getKeyCode() == KeyEvent.VK_UP) {
			k.up = true;

		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {

			k.down = true;

		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {

			k.left = true;

		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

			k.right = true;

		}

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getKeyCode() == KeyEvent.VK_UP) {
			k.up = false;

		}
		if (arg0.getKeyCode() == KeyEvent.VK_DOWN) {
			k.down = false;

		}
		if (arg0.getKeyCode() == KeyEvent.VK_LEFT) {
			k.left = false;

		}
		if (arg0.getKeyCode() == KeyEvent.VK_RIGHT) {
			k.right = false;

		}
		if (times > 0) {

			if (arg0.getKeyCode() == KeyEvent.VK_Q) {

				k.speed += 15;
				active = true;
				post = System.currentTimeMillis();

				if (currentState == GAME) {
					times -= 1;

				}

			}
		}

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

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
		alienspawn = new Timer(1000, om);
		alienspawn.start();
	}

}