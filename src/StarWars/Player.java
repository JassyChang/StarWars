package StarWars;

import javax.swing.ImageIcon;

public class Player extends Alien{
	
    private  static final ImageIcon player1 = new ImageIcon(Player.class.getResource("player1.png")); //static
    private  static final ImageIcon player2 = new ImageIcon(Player.class.getResource("player2.png"));
    private  static final ImageIcon player3 = new ImageIcon(Player.class.getResource("player3.png"));
    
    public boolean strong = false;
    
    public int blood_max;
    public int upgrade = 1;
   
    public Player(int playertype) {
    	switch(playertype) {
    		case 1:
    			image = player1;
    			blood_max = blood = 13;
    			speed = 55;
    			Width = 75;
    			Height = 60;
    			break;
    		case 2:
    			image = player2;
    			blood_max = blood = 18;
    			speed = 40;
    			Width = 100;
    			Height = 80;
    			break;
    		case 3:
    			image = player3;
    			blood_max = blood = 25;
    			speed = 18;
    			Width = 110;
    			Height = 88;
    			break;
    		default:
    			System.out.println("assignment error");
    			break;
    	}
    	pos_X = 278;
		pos_Y= 700;
    }
    
    public void Attacked(int hurt) {
    	if(strong) return;
    	blood -= hurt;
    }
    
    public boolean IsDead() {
    	return (blood <= 0)? true: false;
    }
    
    public void ItemGet(int itemtype) {
    	switch(itemtype){
    		case 1:
    			upgrade += 1;
    			break;
    		case 2:
    			blood += 3;
    			break;
    		case 3:
    			blood += 6;
    			break;
    	}
    	if(blood > blood_max) blood = blood_max;
    	if(upgrade > 3) upgrade = 3;
    }
}
