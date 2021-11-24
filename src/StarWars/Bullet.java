package StarWars;

import javax.swing.ImageIcon;

public class Bullet extends Alien {
	
	private   static final ImageIcon shot1 = new ImageIcon(Bullet.class.getResource("shot1.png"));   
	private  static final ImageIcon shot2 = new ImageIcon(Bullet.class.getResource("shot2.png"));
	private   static final ImageIcon bossshot = new ImageIcon(Bullet.class.getResource("bossbullet.png"));

	public int bullet_type;
	public boolean collision = false;
	
	public Bullet() {}

	public Bullet(int x, int y, int bullettype) {
		pos_X = x;
		pos_Y = y;
		bullet_type = bullettype;
		if(bullettype == 1) {
			Height = 25;
			Width = 5;
			image = shot1;
			speed = 60;
			blood = 1;
		}
		else if(bullettype == 2) {
			Height = 23;
			Width = 15;
			image = shot2;
			speed = 20;
			blood = 1;
		}
		else if(bullettype == 3) {
			Height = 41;
			Width = 15;
			image = bossshot;
			speed = 25;
			blood = 2;
		}
	}

	public void move() {
		switch(bullet_type) {
			case 1:
				FlyUP();
				break;
			case 2:
				FlyDOWN();
				break;
			case 3:
				FlyDOWN();
				break;
			default:
				break;
		}
	}
	
	public boolean IsCollide() {
		return collision;
	}
}
