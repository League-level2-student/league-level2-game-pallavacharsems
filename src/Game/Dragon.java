package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Dragon extends GameObject {
	Knight k;
	int w = 110;
int s = 2;
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	Dragon(int x, int y, int width, int height, Knight k) {
		super(x, y, width, height);
		this.k = k;
		speed = 3;
		if (needImage) {
		    loadImage ("dragon.png");
		}
}
	void update() {
		collisionBox.setBounds(x+45, y+20, width-90, height-40);
		if (x>k.x) {
			x = x-s;
		} else if (x<k.x) {
			x = x+s;
		
		}if (y>k.y) {
			y = y-s;
		}else if (y<k.y) {
			y = y+s;
		}
		}
		
	
	void draw(Graphics g) {
		
		g.setColor(Color.GRAY);
		g.fillRect(x+50, y-50, 110, 50);
		g.setColor(Color.BLUE);
		g.fillRect(x+50, y-50, w, 50);
		
		
		
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
}