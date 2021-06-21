package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Sword extends GameObject {
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

	void update() {
	x = knightX+85;
	y = knightY+45;

		super.update();
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
}
