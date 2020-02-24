
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class AnimationApp {
	private int CurrentX = 5;
	private int CurrentY = 14;
	private Avatar avatar = new Avatar(CurrentX, CurrentY);
	private ArrayList<Collectible> collectibles = new ArrayList<Collectible>();
	private ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
	private int[][] board = new int[15][10];
	private boolean frogObtained = false;
	
	public AnimationApp() {
		//14,13 Empty
		//7-12 Cars
		//6 Empty
		//Obstacles section
		//1-5 Logs
		//0 Empty (Fly)
		for(int i = 0; i < board.length; i++)
		{
			if(i != 14 && i != 13 &&  i != 6 && i != 1 && i != 0)
			{
				for(int x = 0; x < board[i].length; x++)
				{
					if(x != 0 && x != 4 && x != 5 && x != 9)
					{
						obstacles.add(new Obstacle(x,i));
					}
				}
			}
		}
		
		for(Obstacle i : obstacles)
		{
			board[i.getY()][i.getX()] = 2;
		}
		
		board[CurrentY][CurrentX] = 1;
		collectibles.add(new Collectible(5,6,300, 3)); //Frog
		collectibles.add(new Collectible(5,0,600, 4)); //Fly 'Ending'
		board[collectibles.get(0).getY()][collectibles.get(0).getX()] = collectibles.get(0).getName();
		board[collectibles.get(1).getY()][collectibles.get(1).getX()] = collectibles.get(1).getName();
		
	}
	
	//Prints out board as it is
	private void printCurrentState() 
	{
		for(int i = 0; i < board.length; i++)
		{
			for(int x = 0; x < board[i].length; x++)
			{
				board[i][x] = 0;
			}
		}
		for(Obstacle i : obstacles)
		{
			board[i.getY()][i.getX()] = 2;
		}
		board[CurrentY][CurrentX] = 1;
		if(collectibles.get(0).overlapsWith(avatar) != true)
			{
			board[collectibles.get(0).getY()][collectibles.get(0).getX()] = collectibles.get(0).getName();
			}
		board[collectibles.get(1).getY()][collectibles.get(1).getX()] = collectibles.get(1).getName();
		 for (int[] row : board) 
		 { 
	            System.out.println(Arrays.toString(row));
		 }
	}
	
	private int[][] processAvatarMove(String movement) {
		int newPoints = 0;
		int[][] newBoard = this.board;
		
		try {
			char direction = movement.charAt(0);
			avatar = avatar.move(direction);
			
			newBoard[CurrentY][CurrentX] = 0;
			
			//Maybe add a conditional here to see if the space is empty (0) before moving.
			//If the space isn't and has an obstacle(2), kill the frog and then check the coordinates
			//using getYCoord and getXCoord on the board to see what's there -- If the space is (1),
			//Then move on. If the space is (2), it means the frog died and we should run something like
			//The isDeadly() code below.
			if (collectibles.get(0).overlapsWith(avatar)) {
			   frogObtained = true;
			   collectibles.get(0).updateName(0);
			}
			
			if (collectibles.get(1).overlapsWith(avatar)) {
				newPoints += collectibles.get(1).getValue();
				avatar = avatar.resetPos();
				CurrentX = avatar.getXCoord();
				CurrentY = avatar.getYCoord();
				newBoard[CurrentY][CurrentX] = 1;
				newBoard[collectibles.get(1).getY()][collectibles.get(1).getX()] = collectibles.get(1).getName();
				if (frogObtained) {
					newPoints += collectibles.get(0).getValue();
					newBoard[collectibles.get(0).getY()][collectibles.get(0).getX()] = collectibles.get(0).getName();
				}
				avatar.updateScore(newPoints);
				collectibles.get(0).updateName(3);
				return(newBoard);
			}
			
			//THIS IS WHERE I WANT ANOTHER overlapsWith() BUT WITH OBSTACLES
			for(Obstacle i : obstacles)
			{
				if(i.overlapsWith(avatar))
				{
					avatar = avatar.resetPos();
					CurrentX = avatar.getXCoord();
					CurrentY = avatar.getYCoord();
					System.out.println("You got hit!");
					frogObtained = false;
					collectibles.get(0).updateName(3);
				}
			}
			
			newBoard[avatar.getYCoord()][avatar.getXCoord()] = 1;
			//if (overlapsWith(avatar)) {
				//newBoard[avatar.getYCoord()][avatar.getXCoord()] = 2; -- "Obstacle" takes priority over frog
				//If the conditional above already does this, then just remove the line.
				//avatar = avatar.resetPos();  -- Reset object to starting pt.
				//CurrentX = avatar.getXCoord(); -- Setters for private variables back to starting pt
				//CurrentY = avatar.getYCoord();
			//} else {  -- If the square isn't deadly and frog still exists, just take new coordinates.
			CurrentX = avatar.getXCoord();
			CurrentY = avatar.getYCoord();
			return(newBoard);
			//}
		} catch (Exception e) {
			System.out.println("No movement: No Input");
			return(newBoard);
		}
	}
	
	//processAvatarMove() : Go through all the objects in the Obstacle ArrayList.
	//For Even Rows: We want to call Obstacle's moveL -- Obstacle tempO = moveL(ArrayList.get(I))
	//For Odd Rows: We want to call Obstacle's moveR
	//Once you call moveL or R, we want to set ArrayList.get(i) = tempO (something like this, we want to change the object to Tempo)
	//Once the x values reach their respective ends: (Odds - 14, Evens - 0), Set that value at that point to..
	//The value at the opposite end, in the imaginary plane (Odds - -1, Even 15), so that it gets pushed into the array on the next round.
	
	private int[][] processObstacleMove() {
		int[][] newBoard = this.board;
		for(Obstacle i : obstacles)
		{
			if(i.getY() % 2 == 0)
			{
				i.moveL();
				board[i.getY()][i.getX()] = 2;
			}
			else
			{
				i.moveR();
				board[i.getY()][i.getX()] = 2;
			}
		}
		
		
		return(newBoard);
	}
	
	public void initialize() {
		Scanner keyboard = new Scanner(System.in);
		String uInput = "";
		
		System.out.println("CURRENT SCORE: " + avatar.getScore());
		System.out.print("Please enter a direction(U,D,L,R): ");
		uInput = keyboard.nextLine();
		
		//this.board = processObstacleMove();
		this.board = processAvatarMove(uInput);
		printCurrentState();
	}
	
	//Not final main
	public static void main(String[] args) {
		AnimationApp Start = new AnimationApp();
		boolean run = true;
		
		//This applies to collectible one (the frog (0) collectible that moves with the frog)
		
		Start.printCurrentState();
		while(run) {
			Start.initialize();
		}
	}
}