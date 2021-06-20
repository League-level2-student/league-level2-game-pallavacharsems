package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Knight extends GameObject {
	boolean up;
	boolean down;
	boolean left;
	boolean right;
	public static BufferedImage image;
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
			g.drawImage(image, x, y, width, height, null);
		} else {
			g.setColor(Color.BLUE);
			g.fillRect(x, y, width, height);
		}
	}

	void update() {
		super.update();
		
		if (up == true) {
			y += -speed;
		}
		if (down == true) {
			y += speed;
		}

		if (left == true) {
			x += -speed;

		}
		if (right == true) {
			x += speed;
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
	            image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
		    gotImage = true;
	        } catch (Exception e) {
	            
	        }
	        needImage = false;
	    }
	}
	public Sword getSword() {
        return new Sword(x+width/2, y, 25, 50);
} 

}