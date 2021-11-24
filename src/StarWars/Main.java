package StarWars;

import javax.swing.JFrame;

public class Main {
	static JFrame frame = new JFrame();
	public int mode = 0;
	
	public static void main(String[] args) throws InterruptedException{
		frame.setSize(600, 800);							
		frame.setVisible(true);									
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Star Invader");
		frame.setLocationRelativeTo(null);						
		run();
	}
	
	public static void run() throws InterruptedException{ 
		final Menu menu = new Menu();
		final Plane plane = new Plane(); 
		final EndGame endgame= new EndGame();
		
		while(endgame.reset) {
			endgame.reset = false;
			plane.GameStart = false;
			plane.ModeWindow = false;
			menu.ChooseWindow = false;
			menu.BGM.setFramePosition(0);
		
			frame.setContentPane(menu);
			frame.setVisible(true);
	        menu.requestFocus();
	        while (menu.ChooseWindow == false) Thread.sleep(5);
	        menu.ChooseWindow = false;
	        frame.getContentPane().remove(menu);
	        frame.revalidate();
	        
	        frame.setContentPane(plane);
	        frame.setVisible(true);
	        plane.requestFocus();
	        while(plane.GameStart == false) Thread.sleep(5);
	        frame.getContentPane().remove(plane);
	        frame.revalidate();
	        
	        Game game = new Game(plane.scroll_button, plane.inf);
	        frame.setContentPane(game);
	        frame.setVisible(true);
	        game.requestFocus();
	        while(game.gameend == false) Thread.sleep(5);
	        frame.getContentPane().remove(game);
	        frame.revalidate();
	       
	        endgame.Setscore(game.score);
	        game = null;
	        System.gc();
	        frame.setContentPane(endgame);
	        frame.setVisible(true);
	        endgame.requestFocus();
	        while(endgame.reset == false) Thread.sleep(5);
	        frame.getContentPane().remove(endgame);;
	        frame.revalidate();
		}
	}
}
