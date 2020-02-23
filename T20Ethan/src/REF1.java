import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class REF1 {
	
	public static void drawArray(int[][] toDraw) {
		 for (int[] row : toDraw) 
			  
	            System.out.println(Arrays.toString(row));  
	}
	public static ArrayList<Integer> movement(char move) {
		ArrayList<Integer> Command = new ArrayList<Integer>();
		int movement = move;
		switch(move) {
		case 85:
			//x,y
			Command.add(0);
			Command.add(-1);
			return(Command);
		case 68:
			//x,y
			Command.add(0);
			Command.add(1);
			return(Command);
		case 76:
			Command.add(-1);
			Command.add(0);
			return(Command);
		case 82:
			Command.add(1);
			Command.add(0);
			return(Command);
		default:
			Command.add(0);
			Command.add(0);
			return(Command);
		}
	}
	
	public static void main(String[] args) {
		boolean loop = true;
		String move;
		char moveC;
		ArrayList<Integer> areaToM = new ArrayList<Integer>();
		int startX = 5;
		int startY = 14;
		int[][] board = new int[15][10];
		Scanner keyboard = new Scanner(System.in);
		int player = 1;
		
		for (int i = 0; i < board.length; i++) {
			for (int x = 0; x < board[i].length; x++) {
				board[i][x] = 0;
			}
		}
		
		board[startY][startX] = player;
		
		while (loop) {
			move = keyboard.nextLine();
			moveC = move.charAt(0);
			areaToM = movement(moveC);
			
			//Get new X,Y
			int newX = startX + areaToM.get(0);
			int newY = startY + areaToM.get(1);
			
			drawArray(board);
			System.out.println(newX);
			System.out.println(newY);
			
			board[startY][startX] = 0;
			board[newY][newX] = 1;
			
			System.out.println(board[startY][startX]);
			System.out.println(board[newY][newX]);
			startX = newX;
			startY = newY;
			
		}

	}

}