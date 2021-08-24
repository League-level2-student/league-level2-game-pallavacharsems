package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Knight extends GameObject {
	boolean up;
	boolean down;
	boolean left;
	boolean right;
	boolean isFacingRight;
	public static BufferedImage imageR;
	public static BufferedImage imageL;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	

	Knight(int x, int y, int width, int height) {
		super(x, y, width, height);
		speed = 10;
		if (needImage) {
		    loadImage ("walk animation/right13.png");
		    
		}

		// TODO Auto-generated constructor stub
	}

	void draw(Graphics g) {
		
		
		if (gotImage) {
			if(isFacingRight) {
			g.drawImage(imageR, x, y, width, height, null);
		} else {
			g.drawImage(imageL, x, y, width, height, null);
		}
	}
	}
	void update() {
		
		collisionBox.setBounds(x+20, y+5, width-50, height-20);
		
		if (up == true) {
			y += -speed;
			
		}
		if (down == true) {
			y += speed;
		}

		if (left == true) {
			x += -speed;
			isFacingRight = false;

		}
		if (right == true) {
			x += speed;
			isFacingRight = true;
		}
		if (y <= 0) {
			y = 0;
		} else if (y >= SavePrincess.HEIGHT-150) {
			y = SavePrincess.HEIGHT-150;
		} if (x >= SavePrincess.WIDTH-15) {
			x = SavePrincess.WIDTH-15;
		} else if (x <= 0) {
			x = 0;
		}

	}
	void loadImage(String imageFile) {
	    if (needImage) {
	        try {
	            imageR = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
	            imageL = horizontalFlip(imageR);
		    gotImage = true;
	        } catch (Exception e) {
	            
	        }
	        needImage = false;
	    }
	}
	public static BufferedImage horizontalFlip(BufferedImage img) {
	    int w = img.getWidth();
	    int h = img.getHeight();
	    BufferedImage flippedImage = new BufferedImage(w, h, img.getType());
	    Graphics2D g = flippedImage.createGraphics();
	    g.drawImage(img, 0, 0, w, h, w, 0, 0, h, null);
	    g.dispose();
	    return flippedImage;
	}

	
} 

