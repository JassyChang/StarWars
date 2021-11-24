package StarWars;

public class AllMonster {
	public int first_wave;
	public int final_wave;
	public int current_wave;
	public boolean infinity;
	public 
	
	AllMonster(int first_w, int final_w, boolean inf){
		current_wave = first_wave = first_w;
		final_wave = final_w;
		infinity = inf;
	}
	
	public void Update_wave() {
		current_wave += 1;
	}
	
	public boolean Is_final() {
		if(infinity) return false;
		return (current_wave > final_wave)? true: false;
	}
	
	public MonsterArray Launch() {
		MonsterArray monsterarray = null;
		if(IsBossWave()) monsterarray = new MonsterArray(1, 1, current_wave/5, true);
		else if(current_wave >= 21) monsterarray = new MonsterArray(3, 7, 0, false);
		else {
			switch(current_wave) {
				case 1:
					monsterarray = new MonsterArray(1, 1, 0, false);
					break;
				case 2:
					monsterarray = new MonsterArray(1, 2, 0, false);
					break;
				case 3:
					monsterarray = new MonsterArray(1, 3, 0, false);
					break;
				case 4:
					monsterarray = new MonsterArray(1, 4, 0, false);
					break;
				case 6:
					monsterarray = new MonsterArray(2, 3, 0, false);
					break;
				case 7:
					monsterarray = new MonsterArray(1, 7, 0, false);
					break;
				case 8:
					monsterarray = new MonsterArray(2, 4, 0, false);
					break;
				case 9:
					monsterarray = new MonsterArray(3, 3, 0, false);
					break;
				case 11:
					monsterarray = new MonsterArray(2, 5, 0, false);
					break;
				case 12:
					monsterarray = new MonsterArray(2, 6, 0, false);
					break;
				case 13:
					monsterarray = new MonsterArray(2, 7, 0, false);
					break;
				case 14:
					monsterarray = new MonsterArray(3, 5, 0, false);
					break;
				case 16:
					monsterarray = new MonsterArray(2, 8, 0, false);
					break;
				case 17:
					monsterarray = new MonsterArray(4, 4, 0, false);
					break;
				case 18:
					monsterarray = new MonsterArray(3, 6, 0, false);
					break;
				case 19:
					monsterarray = new MonsterArray(4, 5, 0, false);
					break;
			}
		}
		
		return monsterarray;
	}
	
	public boolean IsBossWave() {
		if(current_wave%5 == 0) return true;
		else return false;
	}
}
