package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Sword extends GameObject implements KeyListener {
	int targetX;
	int targetY;
	int speed = 10;
	
	int followState = 0;
	int moveState = 1;
	int pauseState = 2;
	int currentState = followState;
	boolean returningToPlayer = false;
	
	long pauseStartTime;
	
	boolean up;
	boolean down;
	boolean left;
	boolean right;
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;
	int knightX;
	int knightY;

	Sword(int x, int y, int width, int height) {
		super(x, y, width, height);
		speed = 10;
		if (needImage) {
			loadImage("Sword animation/sword.png");
		}
	}


	public void update() {
		super.update();
		if(currentState == followState) {
			x = knightX + 85;
			y = knightY + 45;
		}else if(currentState == moveState) {
			if(returningToPlayer) {
				targetX = knightX + 85;
				targetY = knightY + 45;
				if(x < targetX) {
					x += speed;
				}else if(x > targetX){
					x -= speed;
				}
				if(y < targetY) {
					y += speed;
				}else if(y > targetY){
					y -= speed;
				}
				
				if(targetX == x && targetY == y) {
					currentState = followState;
					returningToPlayer = false;
				}
				
			}else {
				
				if(x < (targetX-speed)) {
					x += speed;
				}else if(x > (targetX+speed)){
					x -= speed;
				}else {
					returningToPlayer = true;
					pauseStartTime = System.currentTimeMillis();
					currentState = pauseState;
				}
				
			}
				
				
		}else if(currentState == pauseState) {
			if(System.currentTimeMillis() - pauseStartTime > 1000) {
				System.out.println("pauseState time expired");
				currentState = moveState;
				targetX = knightX + 85;
	
				targetY = knightY + 45;
			}
		}
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

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
