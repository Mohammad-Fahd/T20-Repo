
public class Obstacle {
	private String name;
	//Data type of speed is set in int. You can only move in the board composed by arry which indexes are all integer numbers
	// The data type of speed will fixed before moving in the diagram 
	private int speed; 
	private int xCood, yCood;
	
	//Regular Constructor to create a new obstacle iterm
	public Obstacle (int x, int y, int Speed){
		this.xCood = x;
		this.yCood = y;
		this.speed = Speed;
				
	}
	public Obstacle (int x, int y) {
		this.xCood = x;
		this.yCood = y;
	}
	//Obstacle moving
	public void move() {
		int newX = this.xCood;
		int newY = this.yCood;
		int newS = this.speed;
		newX += newS;
		if (newX <= -1 || newX >= 15) {
			newX -= 1;
		}
		return(new Obstacle(newX newY));
	}
	//overlapsWith method to check if there is a intersect between Avatar and Obstacle
	public boolean overlapsWith(Avatar a) {
		if (a.getxCoord() == xCood && a.getyCoord() == yCood)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	//public boolean isDeadly(Avatar a) {
		//if (a.getyCood()>=1 && a.getyCood()<=8 && overlapsWith(a) == true) {
			//return true;
		//}
		//else if (a.getyCood()>=9 && a.getyCood()<=14 && overlapsWith(a) == false) {
			//return true;
		//}
		//else {
			//return false;
		//}
	//}
	//The data type of multipletimes is int, the seem reason as the data type of speed should be int 
	public void accelerate(int multipletimes) {
		int newX = this.xCood;
		int newY = this.yCood;
		int newS = this.speed;
		newX += newS*multipletimes;
		if (newX <= -1 || newX >= 15) {
			newX -= 1;
		}
	}
}

