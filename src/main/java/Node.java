
import java.util.Arrays;

/**
 * This class is used to store a state of a tic tac toe puzzle in the form of a string as well as a min/max value
 * Methods are included to set the min/max value depending on whose turn it is, X or O
 * @author Mark Hallenbeck
 *
 * Copyright© 2014, Mark Hallenbeck, All Rights Reservered.
 *
 */

public class Node {
	
	public String[] state;
	
	int minMaxValue;
	
	public int movedTo;
	
	Node(String[] stateOfPuzzle, int move)
	{
		state = stateOfPuzzle;

//		System.out.println(state);
		movedTo = move;
		
		minMaxValue = -1;
	}
	
	int getMovedTo()
	{
		return movedTo;
	}
	
	/**
	 * checks for all the ways that O can win and sets minmax to -10. If it is a draw, sets it to 0
	 */
	void setMinMax_for_O()
	{
//		System.out.println("state of puzzel o: ");
//		 System.out.println(Arrays.toString(state));

		if(checkForDraw())
			{
				minMaxValue = 0;
			}
		
		
		if(state[0].toUpperCase().equals("O") && state[1].toUpperCase().equals("O") && state[2].toUpperCase().equals("O")) //horizontal top
		{
			minMaxValue = -10;
		}
		
		if(state[3].toUpperCase().equals("O") && state[4].toUpperCase().equals("O") && state[5].toUpperCase().equals("O"))//horizontal middle
		{
			minMaxValue = -10;
		}

		if(state[6].toUpperCase().equals("O") && state[7].toUpperCase().equals("O") && state[8].toUpperCase().equals("O"))//horizontal bottom
		{
			minMaxValue = -10;
		}

		if(state[0].toUpperCase().equals("O") && state[3].toUpperCase().equals("O") && state[6].toUpperCase().equals("O"))//vert right
		{
			minMaxValue = -10;
		}

		if(state[1].toUpperCase().equals("O") && state[4].toUpperCase().equals("O") && state[7].toUpperCase().equals("O"))//vert middle
		{
			minMaxValue = -10;
		}

		if(state[2].toUpperCase().equals("O") && state[5].toUpperCase().equals("O") && state[8].toUpperCase().equals("O"))//vert left
		{
			minMaxValue = -10;
		}

		if(state[0].toUpperCase().equals("O") && state[4].toUpperCase().equals("O") && state[8].toUpperCase().equals("O"))// diag from top left
		{
			minMaxValue = -10;
		}

		if(state[2].toUpperCase().equals("O") && state[4].toUpperCase().equals("O") && state[6].toUpperCase().equals("O"))// diag from top right
		{
			minMaxValue = -10;
		}

	}
	
	/**
	 * checks for all the ways that X can win and sets minmax to 10. If a draw, sets minmax to 0
	 */
	void setMinMax_for_X()
	{
//		System.out.println("state of puzzel x: ");
//		 System.out.println(Arrays.toString(state));

		if(checkForDraw())
		{
			minMaxValue = 0;
		}
		
		
		if(state[0].toUpperCase().equals("X") && state[1].toUpperCase().equals("X") && state[2].toUpperCase().equals("X")) //horizontal top
		{
//			System.out.println("10 from here 1");
			minMaxValue = 10;
		}
		
		if(state[3].toUpperCase().equals("X") && state[4].toUpperCase().equals("X") && state[5].toUpperCase().equals("X"))//horizontal middle
		{
//			System.out.println("10 from here 2");
			minMaxValue = 10;
		}

		if(state[6].toUpperCase().equals("X") && state[7].toUpperCase().equals("X") && state[8].toUpperCase().equals("X"))//horizontal bottom
		{
//			System.out.println("10 from here 2");
			minMaxValue = 10;
		}

		if(state[0].toUpperCase().equals("X") && state[3].toUpperCase().equals("X") && state[6].toUpperCase().equals("X"))//vert right
		{
//			System.out.println("10 from here 3");
			minMaxValue = 10;
		}

		if(state[1].toUpperCase().equals("X") && state[4].toUpperCase().equals("X") && state[7].toUpperCase().equals("X"))//vert middle
		{
//			System.out.println("10 from here 4");
			minMaxValue = 10;
		}

		if(state[2].toUpperCase().equals("X") && state[5].toUpperCase().equals("X") && state[8].toUpperCase().equals("X"))//vert left
		{
//			System.out.println("10 from here 5");
			minMaxValue = 10;
		}

		if(state[0].toUpperCase().equals("X") && state[4].toUpperCase().equals("X") && state[8].toUpperCase().equals("X"))// diag from top left
		{
//			System.out.println("10 from here 6");
			minMaxValue = 10;
		}

		if(state[2].toUpperCase().equals("X") && state[4].toUpperCase().equals("X") && state[6].toUpperCase().equals("X"))// diag from top right
		{
//			System.out.println("10 from here 7");
			minMaxValue = 10;
		}
	}

	void setMinMax(int x)
	{
		minMaxValue = x;
	}
	
	/**
	 * check the state to see if it is a draw (no b's in the string only X and O)
	 * @return true if its a draw, false if not
	 */
	boolean checkForDraw()
	{
		for(int x = 0; x < state.length; x++)
		{
			if(state[x].equals("b"))
			{
				return false;
			}
		}
		
		return true;
	}
	int getMinMax()
	{
		return minMaxValue;
	}
	
	String[] getInitStateString()
	{
		return state;
	}

}
