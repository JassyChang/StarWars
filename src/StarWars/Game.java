package StarWars;

import java.util.Set;
import java.util.Iterator;
import java.util.HashSet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Game extends JPanel{
	 
	private  static final ImageIcon BackGround = new ImageIcon(Game.class.getResource("BackGround.jpg"));  //static
	private static final long serialVersionUID = 1L;
	
	private Player fighter;
	private AllMonster allmonster;
	private MonsterArray monsterarray = null;
	public boolean gameend = false;
	public int score = 0;
	private Set<Bullet> playerbullet = new HashSet<>();
	private Set<Bullet> monsterbullet = new HashSet<>();
	private Set<Item> item = new HashSet<>();
	
	private static int FRAME_HEIGHT = 800;						
	private static int FRAME_WIDTH = 600;	
	private BufferedImage myImage;
	private Graphics myBuffer;
	private Timer game_PB_timer;
	private Timer game_process_timer;
	private Timer game_move_timer;
	private Timer game_collide_timer;
	private Timer game_MB_timer;
	private Timer game_paint_timer;
	private boolean key_up = false;
	private boolean key_down = false;
	private boolean key_left = false;
	private boolean key_right = false;
	
	public Game(int playertype, boolean inf) {
		
		myImage =  new BufferedImage(FRAME_WIDTH, FRAME_HEIGHT, BufferedImage.TYPE_INT_RGB);
        myBuffer = myImage.getGraphics();
 
        fighter = new Player(playertype);
        allmonster = new AllMonster(0, 5, inf);
        
		game_paint_timer = new Timer(1, new ListenerPaint());
		game_collide_timer = new Timer(1, new ListenerCollide());
		game_PB_timer = new Timer(125, new ListenerPB());
		game_MB_timer = new Timer(150, new ListenerMB());
		game_move_timer = new Timer(80, new ListenerMove());
		game_process_timer = new Timer(1, new Listener());
		
		game_paint_timer.start();
		game_process_timer.start();
		game_move_timer.start();
		game_collide_timer.start();
		game_MB_timer.start();
		game_PB_timer.start();
		addKeyListener(new Key());
		setFocusable(true);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
	}
	
	private class ListenerPaint implements ActionListener {
		public void actionPerformed(ActionEvent e) {			
			repaint();
		}
	}

	//collide listener
	private class ListenerCollide implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			for(Iterator<Bullet> iterator = monsterbullet.iterator(); iterator.hasNext();) {
				Bullet mb = iterator.next();
				if(mb.getY() == 820-mb.getHeight()) iterator.remove();
				else {
					if(attacked(mb, fighter)){
						fighter.Attacked(mb.blood);
						iterator.remove();				
					}
				}
			}
			for(Iterator<Bullet> iterator = playerbullet.iterator(); iterator.hasNext();) {
				Bullet b = iterator.next();
				if(b.getY() == -30) iterator.remove();
				else {
					if(monsterarray != null) {
						for(Iterator<Monster> iteratorM = monsterarray.array.iterator(); iteratorM.hasNext();) {
							Monster m = iteratorM.next();
							if(attack(b,m)){
								m.Attacked(b.blood);
								b.collision = true;	
							}
						}
					}
				}
			}
			for(Iterator<Item> iterator = item.iterator(); iterator.hasNext();) {
				Item it = iterator.next();
				if(it.getY() == 820-it.getHeight()) iterator.remove();
				else {
					if(Upgrade(it, fighter)){
						fighter.ItemGet(it.itemtype);
						iterator.remove();				
					}
				}
			}
		}
	}
	
	//PBullet timer
	private class ListenerPB implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(fighter.upgrade == 1) {
				Bullet pbullet = new Bullet(fighter.getX()+fighter.getWidth()/2, fighter.getY(), 1);
				playerbullet.add(pbullet);
			}
			else if(fighter.upgrade == 2) {
				Bullet pbullet1 = new Bullet(fighter.getX()+fighter.getWidth()/3, fighter.getY(), 1);
				Bullet pbullet2 = new Bullet(fighter.getX()+fighter.getWidth()/3*2, fighter.getY(), 1);
				playerbullet.add(pbullet1);
				playerbullet.add(pbullet2);
			}
			else if(fighter.upgrade == 3) {
				Bullet pbullet1 = new Bullet(fighter.getX()+fighter.getWidth()/4, fighter.getY(), 1);
				Bullet pbullet2 = new Bullet(fighter.getX()+fighter.getWidth()/2, fighter.getY(), 1);
				Bullet pbullet3 = new Bullet(fighter.getX()+fighter.getWidth()/4*3, fighter.getY(), 1);
				
				playerbullet.add(pbullet1);
				playerbullet.add(pbullet2);
				playerbullet.add(pbullet3);
			}
			for(Bullet b:playerbullet) {
				b.move();
			}
			
			if(key_up) fighter.FlyUP();
			if(key_down) fighter.FlyDOWN();
			if(key_left) fighter.FlyLEFT();
			if(key_right) fighter.FlyRIGHT();
		}
	}
	
	//MBullet timer
	private class ListenerMB implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(monsterarray != null) {
				for(Monster m:monsterarray.array){
					if(allmonster.IsBossWave()) {
						if(m.isMonsterFire()){
							Bullet mbullet1 = new Bullet(m.getX()+m.getWidth()/3, m.getY()+m.Height, 3);
							Bullet mbullet2 = new Bullet(m.getX()+m.getWidth()/3*2, m.getY()+m.Height, 3);
							monsterbullet.add(mbullet1);
							monsterbullet.add(mbullet2);
						}
					}
					else {
						if(m.isMonsterFire()){
							Bullet mbullet = new Bullet(m.getX()+m.getWidth()/2, m.getY()+m.Height, 2);
							monsterbullet.add(mbullet);
						}
					}
					
				}
				for(Bullet mb:monsterbullet) {
					mb.move();
				}
			}
			for(Item it:item) {
				it.move();
			}
		}
	}
	
	//Move Timer
	private class ListenerMove implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(monsterarray != null)
			monsterarray.Move();
		}
	}
	
	//gameProcess
	private class Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
		
			myBuffer.drawImage(BackGround.getImage(), 0, 0, FRAME_WIDTH, FRAME_HEIGHT, null);
			myBuffer.drawImage(fighter.Image().getImage(), fighter.getX(), fighter.getY(), fighter.getWidth(), fighter.getHeight(), null);
			
			if(fighter.strong) myBuffer.setColor(Color.green);
			else myBuffer.setColor(Color.red);
			
			myBuffer.drawRect(10, 760, 15+fighter.blood_max*20, 35);
			for(int i = 0 ; i<fighter.blood; i++) myBuffer.fillRect(20+i*20, 768, 15, 19);
			
			myBuffer.setColor(Color.yellow);
			myBuffer.setFont(new Font("ScanSerif", Font.ITALIC, 30));
			myBuffer.drawString("Score: " + score+ "  Wave: "+ allmonster.current_wave, 15, 32);
			
			myBuffer.setColor(Color.red);
			
			if(monsterarray != null) {
				for(Iterator<Monster> iterator = monsterarray.array.iterator(); iterator.hasNext();) {
					Monster m = iterator.next();
					if(m.IsDead()) {
						if (allmonster.IsBossWave()) {
							Item it = new Item(m.getX(), m.getY(),true);
							item.add(it);
						}
						else {
							if(Random()) {
								Item it = new Item(m.getX(), m.getY(),false);
								item.add(it);
							}
						}
						iterator.remove();
						score += 1;
					}
					else {
						if(Crash(fighter, m)) GameEnd();
						myBuffer.fillRect(m.getX()+(m.getWidth()-1*m.blood)/2, m.getY()-4, 1*m.blood, 3);
						myBuffer.drawImage(m.Image().getImage(), m.getX(), m.getY(), m.getWidth(), m.getHeight(), null);
						if(m.getY() >= 800-m.getHeight()) GameEnd();
					}
				}
			}
			else {
				allmonster.Update_wave();
				if(allmonster.Is_final()) GameEnd();
				else monsterarray = allmonster.Launch();
			}
			
			for(Iterator<Bullet> iterator = playerbullet.iterator(); iterator.hasNext();) {
				Bullet b = iterator.next();
				if(b.IsCollide()) iterator.remove();
				else myBuffer.drawImage(b.Image().getImage(), b.getX(), b.getY(), b.getWidth(), b.getHeight(), null);
			}
			
			for(Bullet it:item) {
				myBuffer.drawImage(it.Image().getImage(), it.getX(), it.getY(), it.getWidth(), it.getHeight(), null);
			}
			
			for(Bullet mb:monsterbullet) {
				myBuffer.drawImage(mb.Image().getImage(), mb.getX(), mb.getY(), mb.getWidth(), mb.getHeight(), null);
			}
			
			if(fighter.IsDead()) {
				GameEnd();
			}
			else if(monsterarray != null && monsterarray.Isempty()) monsterarray = null;
		}
	}
	
	//KEYin
	private class Key extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()){
				case KeyEvent.VK_UP:
					key_up = true;
					break;
				case KeyEvent.VK_DOWN:
					key_down = true;
					break;
				case KeyEvent.VK_LEFT:
					key_left = true;
					break;
				case KeyEvent.VK_RIGHT:
					key_right = true;
					break;
				case KeyEvent.VK_ESCAPE:
					GameEnd();
					break;
				case KeyEvent.VK_SPACE:
					fighter.strong = !fighter.strong;
					break;
				default:
					break;
			}
		}
		public void keyReleased(KeyEvent e) {
			switch(e.getKeyCode()){
				case KeyEvent.VK_UP:
					key_up = false;
					break;
				case KeyEvent.VK_DOWN:
					key_down = false;
					break;
				case KeyEvent.VK_LEFT:
					key_left = false;
					break;
				case KeyEvent.VK_RIGHT:
					key_right = false;
					break;
				default:
					break;
			}
		}
	}
	
	//Collide
	public boolean Crash(Player p, Monster m){
		if(p.strong) return false;
		
		int middle_p_X = average(p.getX(), p.getX()+p.getWidth());
		int middle_m_X = average(m.getX(), m.getX()+m.getWidth());
		int middle_p_Y = average(p.getY(), p.getY()+p.getHeight());
		int middle_m_Y = average(m.getY(), m.getY()+m.getHeight());
		int abs_X = abs(middle_p_X, middle_m_X);
		int abs_Y = abs(middle_p_Y, middle_m_Y);
		if(abs_X < (p.getWidth()+m.getWidth())/2 && abs_Y < (p.getHeight()+m.getHeight())/2) {
			return true;
		}
		else return false;
	}
	public boolean attack(Bullet p, Monster m){
		if(p.bullet_type == 2) return false;
		
		int middle_p_X = average(p.getX(), p.getX()+p.getWidth());
		int middle_m_X = average(m.getX(), m.getX()+m.getWidth());
		int middle_p_Y = average(p.getY(), p.getY()+p.getHeight());
		int middle_m_Y = average(m.getY(), m.getY()+m.getHeight());
		int abs_X = abs(middle_p_X, middle_m_X);
		int abs_Y = abs(middle_p_Y, middle_m_Y);
		if(abs_X < (p.getWidth()+m.getWidth())/2 && abs_Y < (p.getHeight()+m.getHeight())/2) {
			return true;
		}
		else return false;
	}
	public boolean attacked(Bullet p, Player m){
		if(p.bullet_type == 1) return false;
		
		int middle_p_X = average(p.getX(), p.getX()+p.getWidth());
		int middle_m_X = average(m.getX(), m.getX()+m.getWidth());
		int middle_p_Y = average(p.getY(), p.getY()+p.getHeight());
		int middle_m_Y = average(m.getY(), m.getY()+m.getHeight());
		int abs_X = abs(middle_p_X, middle_m_X);
		int abs_Y = abs(middle_p_Y, middle_m_Y);
		if(abs_X < (p.getWidth()+m.getWidth())/2 && abs_Y < (p.getHeight()+m.getHeight())/2) {
			return true;
		}
		else return false;
	}
	
	public boolean Upgrade(Item p, Player m){
		if(p.bullet_type == 1) return false;
		
		int middle_p_X = average(p.getX(), p.getX()+p.getWidth());
		int middle_m_X = average(m.getX(), m.getX()+m.getWidth());
		int middle_p_Y = average(p.getY(), p.getY()+p.getHeight());
		int middle_m_Y = average(m.getY(), m.getY()+m.getHeight());
		int abs_X = abs(middle_p_X, middle_m_X);
		int abs_Y = abs(middle_p_Y, middle_m_Y);
		if(abs_X < (p.getWidth()+m.getWidth())/2 && abs_Y < (p.getHeight()+m.getHeight())/2) {
			return true;
		}
		else return false;
	}
	public static int average(int a, int b) {
		return (a+b)/2;
	}
	public static int abs(int a, int b) {
		return Math.abs(a-b);
	}
	
	private boolean Random() {     
		int random = (int)(Math.random()*10%3);
		return (random == 1)? true: false;
	}
	
	//end
	private void GameEnd() {
		game_paint_timer.stop();
		game_process_timer.stop();
		game_move_timer.stop();
		game_collide_timer.stop();
		game_MB_timer.stop();
		game_PB_timer.stop();
		gameend = true; 
	}

}


