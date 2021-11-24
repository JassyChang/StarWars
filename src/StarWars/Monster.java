package StarWars;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;

public class Monster extends Alien {
	
	private  static final ImageIcon monsterimg = new ImageIcon(Monster.class.getResource("monster.png"));  //static
	private  static final ImageIcon bossimg = new ImageIcon(Monster.class.getResource("boss.png"));
	
	private Clip clip = null;
	
    private int direct_x;
    private int direct_y;
    private int launch;
    public boolean boss;
   
    public Monster() {}
	
	public Monster(int x, int y, int mult, boolean b) {
		boss = b;
		image = (boss)? bossimg: monsterimg;
		pos_X = x;
		pos_Y = y;
		Width = (boss)? 200: 40;
		Height = (boss)? 71: 40;
		direct_x = speed = (boss)? 20: 15;
		direct_y = 1;
		blood = (boss)? 20*mult: 8;
	}
	
	public void Move() {
		
		if(pos_X == 0) {
			direct_x = (direct_x < 0)? direct_x*-1: direct_x;
		}
		else if(pos_X+Width > 600) {
			direct_x = (direct_x > 0)? direct_x*-1: direct_x;
		}
		
		pos_X += direct_x;
		pos_Y += direct_y;
		
		if(pos_X < 0)pos_X = 0;
		if(pos_Y+Height > 800) pos_Y = 800-Height;
	}
	
	public boolean isMonsterFire() {     //隨機發射子彈
		if(boss) launch = (int)(Math.random()*100%4);
		else launch = (int)(Math.random()*100%25);
		return (launch == 0)? true: false;
	}
	
	public void Attacked(int hurt) {
    	blood -= hurt;
    	if(IsDead()) {
    		try {
    			AudioInputStream audioInputStream =AudioSystem.getAudioInputStream(this.getClass().getResource("/StarWars/explosion.wav"));
    			clip = AudioSystem.getClip();
    			clip.open(audioInputStream);
	   		}catch (Exception e) {
	   			System.out.println("Wrong music");
	   		};
	   		clip.start();
    	}
    }
    
    public boolean IsDead() {
    	if(blood <= 0) {
    		return true;
    	}else 
    		return false;
    }
    

}