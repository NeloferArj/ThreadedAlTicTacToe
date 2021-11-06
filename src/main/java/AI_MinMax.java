import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is used to read in a state of a tic tac toe board. It creates a MinMax object and passes the state to it. What returns is a list 
 * of possible moves for the player X that have been given min/max values by the method findMoves. The moves that can result in a win or a 
 * tie for X are printed out with the method printBestMoves()
 * 
 * @author Mark Hallenbeck
 *
 * Copyright© 2014, Mark Hallenbeck, All Rights Reservered.
 *
 */
public class AI_MinMax {
	
	private String[] init_board;
	ArrayList<Node> movesList;
	ArrayList<Integer> movesWin = new ArrayList<Integer>();
	ArrayList<Integer> movesDraw = new ArrayList<Integer>();

	Character move;

	
	AI_MinMax(String board,Character turn)
	{
		init_board = getBoard(board);
		System.out.println("board in Al_min_max: " + board);
		move = turn;
//		System.out.println("Move in AI: " + move);

		
		if(init_board.length != 9)
		{
			System.out.println("You have entered an invalid state for tic tac toe, exiting......");
			System.exit(-1);
		}
		
		MinMax sendIn_InitState = new MinMax(init_board);
		
		movesList = sendIn_InitState.findMoves();
		
		printBestMoves();
	}
	
	/**
	 * reads in a string from user and parses the individual letters into a string array
	 * @return String[]
	 */
	String[] getBoard(String board)
	{
			String puzzle;
			String[] puzzleParsed;
			String delim = "[ ]+";

			puzzle = board;
			puzzleParsed = puzzle.split(delim);			
			return puzzleParsed;
			
	}
	
	/**
	 * goes through a node list and prints out the moves with the best result for player X
	 * checks the min/max function of each state and only recomends a path that leads to a win or tie
	 */
	private void printBestMoves()
	{
		System.out.print("The moves list is: < ");
		
		for(int x = 0; x < movesList.size(); x++)
		{
			
			Node temp = movesList.get(x);
			
			if(temp.getMinMax() == 10)
			{
				System.out.print(temp.getMovedTo() + " ");
				movesWin.add(temp.getMovedTo());
			}
			else if(temp.getMinMax() == 0)
			{
				System.out.print(temp.getMovedTo() + " ");
				movesDraw.add(temp.getMovedTo());
			}
			
		}
		System.out.println(">\n");
	}
	

}
