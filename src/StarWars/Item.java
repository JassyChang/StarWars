package StarWars;

import javax.swing.ImageIcon;

public class Item extends Bullet {

	private  static final ImageIcon heal = new ImageIcon(Item.class.getResource("heal.png"));   //static
	private  static final ImageIcon power = new ImageIcon(Item.class.getResource("power.png"));
	private  static final ImageIcon bossheal = new ImageIcon(Item.class.getResource("bossheal.png"));
	public int itemtype;
	
	Item(int X, int Y, boolean boss){
		pos_X = X;
		pos_Y = Y;
		Width = (boss)? 45: 30;
		Height = (boss)? 45: 30;
		speed = 40;
		
		Random();
		if(boss) image = bossheal;
		else image = (itemtype == 1)? power: heal;
		bullet_type = 2;
	}
	
	private void Random() {     
		int randomtype = (int)(Math.random()*10%4);
		if(randomtype == 3) itemtype = 1;
		else itemtype = 2;
	}
	
}
