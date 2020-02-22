import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class AnimationApp {
	private int CurrentX = 6;
	private int CurrentY = 14;
	private Avatar avatar = new Avatar(CurrentX, CurrentY);
	//Not yet implemented
	//private ArrayList<Collectible> collectibles = new ArrayList<Collectible>
	//private ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>
	private int[][] board = new int[15][10];
	
	public AnimationApp() {
		board[CurrentY][CurrentX] = 1;
	}
	
	//Prints out board as it is
	private void printCurrentState() {
		 for (int[] row : board) { 
	            System.out.println(Arrays.toString(row));
		 }
	}
	
	private int[][] processAvatarMove(String movement) {
		int[][] newBoard = this.board;
		char direction = movement.charAt(0);
		avatar = avatar.move(direction);
		
		newBoard[CurrentY][CurrentX] = 0;
		
		//Maybe add a conditional here to see if the space is empty (0) before moving.
		//If the space isn't and has an obstacle(2), kill the frog and then check the coordinates
		//using getYCoord and getXCoord on the board to see what's there -- If the space is (1),
		//Then move on. If the space is (2), it means the frog died and we should run something like
		//The isDeadly() code below.
		
		newBoard[avatar.getYCoord()][avatar.getXCoord()] = 1;
		//if (isDeadly()) {
			//newBoard[avatar.getYCoord()][avatar.getXCoord()] = 2; -- "Obstacle" takes priority over frog
			//If the conditional above already does this, then just remove the line.
		
			//avatar = avatar.resetPos();  -- Reset object to starting pt.
			//CurrentX = avatar.getXCoord(); -- Setters for private variables back to starting pt
			//CurrentY = avatar.getYCoord();
		//} else {  -- If the square isn't deadly and frog still exists, just take new coordinates.
		CurrentX = avatar.getXCoord();
		CurrentY = avatar.getYCoord();
		//}
		
		return(newBoard);
	}
	
	public void initialize() {
		
		Scanner keyboard = new Scanner(System.in);
		String uInput = "";
		
		System.out.print("Please enter a direction(U,D,L,R): ");
		uInput = keyboard.nextLine();
		
		this.board = processAvatarMove(uInput);
		printCurrentState();
	}
	
	//Not final main
	public static void main(String[] args) {
		AnimationApp Start = new AnimationApp();
		boolean run = true;
		
		Start.printCurrentState();
		while(run) {
			Start.initialize();
		}
	}
}

