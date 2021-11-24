package StarWars;

import java.util.ArrayList;

public class MonsterArray {
	public ArrayList<Monster> array = new ArrayList<>();
	public int row;
	public int col;
	
	public int border;
	
	MonsterArray(int r, int c,int mult, boolean isboss){
		row = r;
		col = c;
		border = (600 - 80*col)/2;
		for(int i = 0; i<row; i++) {
			for(int k=0; k<col; k++){
				Monster monster = new Monster(border+80*k, 120+80*i, mult, isboss);	
				array.add(monster);
		}
	}}
	
	public void Move(){
		for(Monster m:array){
			m.Move();
		}
	}
	
	public boolean Isempty(){
		return array.isEmpty();
	}
}
