package StarWars;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Plane extends JPanel {

	ImageIcon plane = new ImageIcon(Plane.class.getResource("Plane.jpeg"));
	ImageIcon square = new ImageIcon(Plane.class.getResource("sq.jpeg"));
	private static final long serialVersionUID = 1L;
	private static Color player1 = Color.red;
	private static Color player2 = Color.yellow;
	private static Color player3 = Color.yellow;
	private static Color practice = Color.red;
	private static Color challenge = Color.yellow;
	public int scroll_button = 1;
	public int ModeWindow_scroll_button = 1;
	private Clip clip = null;
	public Clip BGM = null;
	public boolean GameStart = false;
	public boolean ModeWindow = false;
	public boolean inf = false;
	public static int player = 1;
	
	public Plane() {
		Timer animationTimer = new Timer(1, new ActionListener(){		
			  public void actionPerformed(ActionEvent event){
				  repaint();
			  };
		});
		
		try {
			AudioInputStream audioInputStream =AudioSystem.getAudioInputStream(this.getClass().getResource("/StarWars/Water.wav"));
		     clip = AudioSystem.getClip();
		     clip.open(audioInputStream);
		}catch (Exception e) {
			System.out.println("Wrong music");
		};
		
		animationTimer.start();
		addKeyListener(new Key());
		setFocusable(true);
	}
	
	public void paint (Graphics g){
		super.paint(g);
		Graphics2D g2D = (Graphics2D)g;
		g2D.setStroke(new BasicStroke(4));
		g.drawImage(plane.getImage(), 0, 0, null);
		g.setColor(player1);
		g.drawRect(35, 80, 530, 193);
		g.setColor(player2);
		g.drawRect(35, 322, 530, 193);
		g.setColor(player3);
		g.drawRect(35, 558, 530, 193);
		if(ModeWindow) {
			g.drawImage(square.getImage(),81, 229, null);
			g.setColor(practice);
			g.drawRect(150, 285, 300, 100);
			g.setFont(new Font("ScanSerif", Font.ITALIC, 30));
			g.drawString("NORMAL", 237, 345);
			g.setColor(challenge);
			g.drawRect(150, 415, 300, 100);
			g.drawString("CHALLENGE", 210, 475);
			g.setColor(challenge);
		}
	}

	private class Key extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			if(ModeWindow) {
				if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
					clip.start();
					clip.setFramePosition(0);
					switch(ModeWindow_scroll_button) {
						case 1:
							ModeWindow_scroll_button = 2;
							practice = Color.yellow;
							challenge = Color.red;
							break;
						case 2:
							ModeWindow_scroll_button = 1;
							practice = Color.red;
							challenge = Color.yellow;
							break;
					}
				}
				else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					switch(ModeWindow_scroll_button) {
						case 1:
							inf = false;
							break;
						case 2:
							inf = true;
							break;
					}	
					GameStart = true;
				}
				else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) ModeWindow = false;
			}
			else {
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					clip.start();
					clip.setFramePosition(0);
					switch(scroll_button) {
						case 1:
							scroll_button = 3;
							player1 = Color.yellow;
							player3 = Color.red;
							break;
						case 2:
							scroll_button = 1;
							player2 = Color.yellow;
							player1 = Color.red;
							break;
						case 3:
							scroll_button = 2;
							player3 = Color.yellow;
							player2 = Color.red;
							break;
					}
				}
				else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					clip.start();
					clip.setFramePosition(0);
					switch(scroll_button) {
						case 1:
							scroll_button = 2;
							player1 = Color.yellow;
							player2 = Color.red;
							break;
						case 2:
							scroll_button = 3;
							player2 = Color.yellow;
							player3 = Color.red;
							break;
						case 3:
							scroll_button = 1;
							player3 = Color.yellow;
							player1 = Color.red;
							break;
					}
					
				}
				else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					switch(scroll_button) {
						case 1:
							player = 1;
							break;
						case 2:
							player = 2;
							break;
						case 3:
							player = 3;
							break;
					}
					ModeWindow = true;
				}
			}
		}
	}
}
