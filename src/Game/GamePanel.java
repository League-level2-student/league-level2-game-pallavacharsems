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
	final int MENU = 0;
	final int KEY = 1;
	final int GAME = 2;
	final int END = 3;
	int currentState = MENU;
	Font titleFont;
	Font tf;
	Timer frameDraw;
	Timer alienspawn;
	Knight k = new Knight(105, 250, 75, 100);
	Sword s = new Sword(k.x, k.y, 20, 40);
	Dragon d = new Dragon(550, 250, 250 ,250, k);
	
	
	

	ObjectManager om = new ObjectManager(k, s, d, this);

	GamePanel() {
		JOptionPane.showMessageDialog(null, "The princess is captured and you must save her. Get passed the dungeon, and then fight the dragon to save the princess! SPACE BAR to throw the knife and the ARROW KEYS to move!");
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
		}else if (currentState == KEY) {
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
		

	}

	void updateEndState() {
	}

	void drawMenuState(Graphics g) {
		g.setColor(Color.MAGENTA);
		g.fillRect(0, 0, SavePrincess.WIDTH, SavePrincess.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.BLUE);
		g.drawString("Save The Princess", 300, 50);
		g.setFont(tf);
		g.setColor(Color.BLUE);
		g.drawString("Press ENTER to start", 375, 300);
		g.setFont(tf);
		
		
	}
	void drawKeyState(Graphics g) {
		if (gotImage) {
			g.drawImage(image, 0, 0, SavePrincess.WIDTH, SavePrincess.HEIGHT, null);
			g.setColor(Color.RED);
			g.setFont(tf);
			

		} else {
			g.setColor(Color.BLUE);
			g.fillRect(0, 0, SavePrincess.WIDTH, SavePrincess.HEIGHT);
		}
		om.draw(g);

	}
	void drawDungeonState(Graphics g) {
		if (gotImage) {
			g.drawImage(image, 0, 0, SavePrincess.WIDTH, SavePrincess.HEIGHT, null);
			g.setColor(Color.RED);
			g.setFont(tf);
			

		} else {
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, SavePrincess.WIDTH, SavePrincess.HEIGHT);
		}
		om.draw(g);

	}
	void drawGameState(Graphics g) {
		if (gotImage) {
			g.drawImage(image, 0, 0, SavePrincess.WIDTH, SavePrincess.HEIGHT, null);
			g.setColor(Color.RED);
			g.setFont(tf);
			

		} else {
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, SavePrincess.WIDTH, SavePrincess.HEIGHT);
		}
		om.draw(g);

	}

	void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, SavePrincess.WIDTH, SavePrincess.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		g.drawString("Game Over", 375, 50);
		g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		g.drawString("Press ENTER to restart", 310, 250);
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
				d = new Dragon(550, 250, 250 ,250, k);
				om = new ObjectManager(k, s, d, this);
				
			} else {
				currentState++;

			}
			if (currentState == KEY) {
				startGame();
				JOptionPane.showMessageDialog(null, "You have to find the key. To find the key, you must solve the riddle");
				String g = JOptionPane.showInputDialog("One knight, a king and a queen go out sailing. \n"
						 +"They get into a horrible crash, and they all die. They were the only people on the boat. \n"
						 +"When the police are taking the bodies, they take the King's body, then the Queen's and then a third person's body. Whose body is the third body?");
				if(g.equalsIgnoreCase("knight")) {
				JOptionPane.showMessageDialog(null, "Great Job. You found the key! You can now move on to fighting the dragon by clicking OK and then pressing ENTER!");
			} else {
				JOptionPane.showMessageDialog(null, "Sorry you were wrong! You lost!");
				currentState = END;
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