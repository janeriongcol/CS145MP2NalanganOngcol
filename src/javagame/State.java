package javagame;

public enum State {
	menu(0),
	choose(1),
	play(2),
	cham(3);
	int value;
	
	State(int n) {
		this.value = n;
	}
	
	public int getValue(){
		return this.value;
	}
}
