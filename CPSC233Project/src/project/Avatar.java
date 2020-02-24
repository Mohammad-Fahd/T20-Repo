
public class Avatar 
{
	private String name = "DEF";
	private int health = 3;
	private int score = 0;
	private int xCoord;
	private int yCoord;
	
	public Avatar(int xCoord, int yCoord) {
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.score = 0;
	}
	
	public Avatar(int xCoord, int yCoord, int cScore) {
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.score = cScore;
	}
	
	//REQUIREMENT : char direction must be capitalized.
	//EDIT: Returns a new object with new coordinates to avoid privacy leak
	public Avatar move(char direction) {
		int newX = this.xCoord;
		int newY = this.yCoord;
		int cScore = this.score;
		int movement = direction;
		switch(movement) {
		//Direction = 'U'
		case 85:
			//x,y
			newY -= 1;
			//If out of bounds, reverse the change.
			if (newY <= -1 || newY >= 15) {
				newY += 1;
			}
			return(new Avatar(newX, newY, cScore));
		//Direction = 'D'
		case 68:
			//x,y
			newY += 1;
			if (newY <= -1 || newY >= 15) {
					newY -= 1;
			}
			return(new Avatar(newX, newY, cScore));
		//Direction = 'L'
		case 76:
			newX -= 1;
			if (newX <= -1) {
					newX = 9;
			}
			return(new Avatar(newX, newY, cScore));
		//Direction = 'R'
		case 82:
			newX += 1;
			if (newX >= 10) {
					newX = 0;
			}
			return(new Avatar(newX, newY, cScore));
		default:
			return(new Avatar(newX, newY, cScore));
		}
	}
	
	//getLocation() --> X and Y
	public int getXCoord() {
		return(xCoord);
	}
	
	public int getYCoord() {
		return(yCoord);
	}
	
	//Update Score: 'change' will have to be positive
	//when moving forwards, and negative when moving back.
	public void updateScore(int change) {
		score += change;
	}
	
	public int getScore() {
		return(score);
	}
	//Update Health: Setter method.
	public void updateHealth(int change) {
		health = change;
	}
	
	public int getHealth() {
		return(health);
	}
	
	//Resets position of the frog
	public Avatar resetPos() {
		final int originX = 5;
		final int originY = 14;
		int cScore = this.score;
		return(new Avatar(originX, originY, cScore));
	}
}
	