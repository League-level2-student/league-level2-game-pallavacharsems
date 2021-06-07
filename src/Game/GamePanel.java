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

public class GamePanel extends JPanel implements ActionListener, KeyListener{
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
	    Rocketship rs = new Rocketship(225, 650, 50, 50);
	    ObjectManager om = new ObjectManager(rs);
	    GamePanel(){
	    	titleFont = new Font("Arial", Font.PLAIN, 48);	
	    	tf = new Font("Arial", Font.PLAIN, 24);	
	    	frameDraw = new Timer(1000/60,this);
	        frameDraw.start();
	    	if (needImage) {
			    loadImage ("space.png");
			}
	    }
	@Override
	public void paintComponent(Graphics g){
		if(currentState == MENU){
		    drawMenuState(g);
		}else if(currentState == GAME){
		    drawGameState(g);
		}else if(currentState == END){
		    drawEndState(g);
		}
	}
	void updateMenuState() {  }
    void updateGameState() {
    	om.update(); 
    	rs.update();
    	if (rs.isActive ==  false) {
    		currentState = END;
    	}
    	om.score = om.getScore();
    
    }
    void updateEndState()  {  }
    void drawMenuState(Graphics g) {  
    	g.setColor(Color.BLUE);
        g.fillRect(0, 0, savePrincess.WIDTH, savePrincess.HEIGHT); 
        g.setFont(titleFont);
        g.setColor(Color.YELLOW);
        g.drawString("LEAGUE INVADERS", 15 ,50 );
        g.setFont(tf);
        g.setColor(Color.YELLOW);
        g.drawString("Press ENTER to start", 125 ,300 );
        g.setFont(tf);
        g.setColor(Color.YELLOW);
        g.drawString("Press SPACE for instructions", 90 ,500 );
        
    }
    void drawGameState(Graphics g) {  
    	if (gotImage) {
			g.drawImage(image,0, 0, savePrincess.WIDTH, savePrincess.HEIGHT, null);
			g.setColor(Color.BLUE);
			g.setFont(tf);
			g.drawString("Score: " + om.score, 15 ,50 );
			
		} else {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, savePrincess.WIDTH, savePrincess.HEIGHT);
		}
    	om.draw(g);	 
    }
    void drawEndState(Graphics g)  {  
    	g.setColor(Color.RED);
        g.fillRect(0, 0, savePrincess.WIDTH, savePrincess.HEIGHT);
        g.setFont(titleFont);
        g.setColor(Color.YELLOW);
        g.drawString("Game Over", 125 ,50 );
        g.setFont(tf);
        g.setColor(Color.YELLOW);
        g.drawString("You killed " + om.score + " enemies", 130 ,300 );
        g.setFont(tf);
        g.setColor(Color.YELLOW);
        g.drawString("Press ENTER to restart", 125 ,500 );
    }
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(currentState == MENU){
		    updateMenuState();
		}else if(currentState == GAME){
		    updateGameState();
		}else if(currentState == END){
		    updateEndState();
		    
		}
		
	    repaint();
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode()==KeyEvent.VK_ENTER) {
		    if (currentState == END) {
		        currentState = MENU;
		        alienspawn.stop();
		    	rs = new Rocketship(225, 650, 50, 50);
		    	om = new ObjectManager(rs);
		    } else {
		        currentState++;
		      
		    } if (currentState == GAME) {
		    	startGame();
		    	
		    
		    }
		  
		}   
		 if (e.getKeyCode()==KeyEvent.VK_SPACE) {
		    	om.addProjectile(rs.getProjectile());
		    	
			}
		if (e.getKeyCode()==KeyEvent.VK_UP) {
			
		    rs.up = true;
		    
		}
		if (e.getKeyCode()==KeyEvent.VK_DOWN) {
		    
		    rs.down = true;
		    
		}
		if (e.getKeyCode()==KeyEvent.VK_LEFT) {
		   
		    rs.left = true;
		   
		}
		if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
		    
		    rs.right = true;
		    
		}
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getKeyCode()==KeyEvent.VK_UP) {
			rs.up = false;
		}
		if(arg0.getKeyCode()==KeyEvent.VK_DOWN) {
			rs.down = false;
		}
		if(arg0.getKeyCode()==KeyEvent.VK_LEFT) {
			rs.left = false;
		}
		if(arg0.getKeyCode()==KeyEvent.VK_RIGHT) {
			rs.right = false;
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
	    alienspawn = new Timer(1000 , om);
	    alienspawn.start();
	}

}