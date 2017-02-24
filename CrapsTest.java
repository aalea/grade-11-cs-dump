package testing;

public class CrapsTest {
	
	public static void main(String[] args) {
		
		JFrame application = new JFrame();
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Craps game = new Craps(); 
		game.play();
			
	}
	
}
