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
	final int GAME = 1;
	final int END = 2;
	int currentState = MENU;
	Font titleFont;
	Font tf;
	Timer frameDraw;
	Timer alienspawn;
	Knight k = new Knight(105, 250, 150, 125);
	Sword s = new Sword(k.x, k.y, 20, 40);

	ObjectManager om = new ObjectManager(k, s);

	GamePanel() {
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
		} else if (currentState == GAME) {
			drawGameState(g);
		} else if (currentState == END) {
			drawEndState(g);
		}
	}

	void updateMenuState() {
	}

	void updateGameState() {
		om.update();
		k.update();
		s.update();
		s.knightX = k.x;
		s.knightY = k.y;
		if (k.isActive == false) {
			currentState = END;
		}
		om.score = om.getScore();

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
		g.setColor(Color.BLUE);
		g.drawString("Press SPACE for instructions", 350, 500);

	}

	void drawGameState(Graphics g) {
		if (gotImage) {
			g.drawImage(image, 0, 0, SavePrincess.WIDTH, SavePrincess.HEIGHT, null);
			g.setColor(Color.RED);
			g.setFont(tf);
			g.drawString("Score: " + om.score, 15, 25);

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
		g.drawString("Game Over", 125, 50);
		g.setFont(tf);
		g.setColor(Color.YELLOW);
		g.drawString("You killed " + om.score + " enemies", 130, 300);
		g.setFont(tf);
		g.setColor(Color.YELLOW);
		g.drawString("Press ENTER to restart", 125, 500);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (currentState == MENU) {
			updateMenuState();
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
				k = new Knight(105, 250, 150, 125);
				s = new Sword(225, 250, 25, 50);
				om = new ObjectManager(k, s);
			} else {
				currentState++;

			}
			if (currentState == GAME) {
				startGame();

			}

		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
		s.x+=50;
		try {
			Thread.sleep(400);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		s.x-=50;
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