package Game;
import javax.swing.JFrame;

public class DefeatDragon {
	GamePanel gpanel;
	JFrame frame;
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 600;
	public static void main(String[] args) {
		DefeatDragon sp = new DefeatDragon();
		sp.setup();
		sp.run();
		

		
	}
	DefeatDragon(){
		frame = new JFrame();
		gpanel = new GamePanel();
		frame.addKeyListener(gpanel);

	}

	
	private void run() {
		// TODO Auto-generated method stub
		
	}
void setup() {
	frame.add(gpanel);
	frame.setVisible(true);
	frame.setSize(WIDTH, HEIGHT);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
}
}


