package StarWars;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.Timer;

public class EndGame extends JPanel {
	
	private static final long serialVersionUID = 1L;
	public boolean reset = true;
	public int score;
	
	public EndGame() {
		Timer animationTimer = new Timer(40, new ActionListener(){		
			  public void actionPerformed(ActionEvent event){
				  repaint();
			  };
		});
		
		animationTimer.start();
		addKeyListener(new Key());
		setFocusable(true);
	}
	
	public void Setscore(int s) {
		score = s;
	}
		
	public void paint (Graphics g){
		super.paint(g);

		g.setColor(Color.black);
		g.fillRect(0, 0, 600, 800);
		g.setColor(Color.red);
		g.setFont(new Font("ScanSerif", Font.ITALIC, 60));
		g.drawString("Your Score", 140, 350);
		
		g.setFont(new Font("ScanSerif", Font.ITALIC, 45));
		FontMetrics fontMetrics = g.getFontMetrics(getFont());
		int sw = fontMetrics.stringWidth(""+ score)/8*28;
		g.setColor(Color.yellow);
		g.drawString(""+ score, 300-sw/2, 420);
	}
	

	private class Key extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				reset = true;
			}
		}
	}
}