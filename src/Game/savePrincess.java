package Game;
import javax.swing.JFrame;

public class savePrincess {
	GamePanel gpanel;
	JFrame frame;
	public static final int WIDTH = 500;
	public static final int HEIGHT = 700;
	public static void main(String[] args) {
		new savePrincess().run();
		savePrincess sp = new savePrincess();
		sp.setup();
		

		
	}
	savePrincess(){
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


