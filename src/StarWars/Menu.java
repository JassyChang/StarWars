package StarWars;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;

public class Menu extends JPanel {

	ImageIcon menu = new ImageIcon(Menu.class.getResource("Menu.jpeg"));
	ImageIcon help = new ImageIcon(Menu.class.getResource("Help.jpeg"));
	
	private static final long serialVersionUID = 1L;
	private static Color play_button = Color.red;
	private static Color help_button = Color.yellow;
	private static Color exit_button = Color.yellow;
	private static int scroll_button =  1;
	private boolean helpwindow = false;
	private static Clip clip = null;
	public Clip BGM = null;
	public boolean ChooseWindow = false;
	
	public Menu() {
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
		
		try {
			 AudioInputStream audioInputStream =AudioSystem.getAudioInputStream(this.getClass().getResource("/StarWars/BackGround.wav"));
		     BGM = AudioSystem.getClip();
		     BGM.open(audioInputStream);
		}catch (Exception e) {
			System.out.println("Wrong music");
		};
		
		BGM.start();
		BGM.loop(Clip.LOOP_CONTINUOUSLY);
		animationTimer.start();
		addKeyListener(new Key());
		setFocusable(true);
	}
	
	public void paint (Graphics g){
		super.paint(g);
		if(!helpwindow) {
			g.drawImage(menu.getImage(), 0, 0, null);
			g.setFont(new Font("ScanSerif", Font.ITALIC, 45));
			g.setColor(play_button);
			g.drawString("PLAY", 250, 410);
			g.setColor(help_button);
			g.drawString("HELP", 250, 500);
			g.setColor(exit_button);
			g.drawString("EXIT", 250, 590);
		}
		else {
			g.drawImage(help.getImage(), 0, 0, null);
			g.setFont(new Font("ScanSerif", Font.ITALIC, 40));
			g.setColor(Color.red);
			g.drawString("RETURN", 220, 750);
		}
	}

	private class Key extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				clip.start();
				clip.setFramePosition(0);
				switch(scroll_button) {
					case 1:
						scroll_button = 3;
						play_button = Color.yellow;
						exit_button = Color.red;
						break;
					case 2:
						scroll_button = 1;
						help_button = Color.yellow;
						play_button = Color.red;
						break;
					case 3:
						scroll_button = 2;
						exit_button = Color.yellow;
						help_button = Color.red;
						break;
				}
			}
			else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				clip.start();
				clip.setFramePosition(0);
				switch(scroll_button) {
					case 1:
						scroll_button = 2;
						play_button = Color.yellow;
						help_button = Color.red;
						break;
					case 2:
						scroll_button = 3;
						help_button = Color.yellow;
						exit_button = Color.red;
						break;
					case 3:
						scroll_button = 1;
						exit_button = Color.yellow;
						play_button = Color.red;
						break;
				}
			}
			else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				
				if(!helpwindow) {
					switch(scroll_button) {
						case 1:
							clip.start();
							clip.setFramePosition(0);
							ChooseWindow = true;
							break;
						case 2:
							clip.start();
							clip.setFramePosition(0);
							helpwindow = true;
							break;
						case 3:
							System.exit(0);
							break;
					}
				}
				else {
					clip.start();
					clip.setFramePosition(0);
					helpwindow = false;
				} 
			}
		}
	}
}
	


