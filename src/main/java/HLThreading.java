
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.function.Consumer;

public class HLThreading {
	static String board;
	static ArrayList<Character> gameBoard1 = new ArrayList<>(Arrays.asList('-','-','-','-','-','-','-','-','-'));
	static Consumer<Serializable> callback;
	static int numGames;
	static int level,level2;
	static String winner = ""; // 1 if x won, 2 if o won and 3 if it is a draw
	static Character turn;
	static int xcounter = 1;
	static int ocounter = 2;

	static int gameDone = 0;

	
	public HLThreading(Consumer<Serializable> call,int lvl, int lvl2,ArrayList<Character> board,Character move, int done) {
		turn = move;
		gameBoard1 = board;
		callback = call;
		level = lvl;
		level2 = lvl2;
		gameDone = done;
		methodstart();
		// TODO Auto-generated constructor stub
	}
	public static void methodstart() {
		ExecutorService ex = Executors.newFixedThreadPool(5); //create a thread pool of 5 threads

				Future<Integer> future = ex.submit(new MyCall(gameBoard1, turn,level,level2,xcounter,ocounter)); //return a result for the worker thread
				//give worker thread a callable
				try {
					Integer index = future.get();
					gameBoard1.set(index, turn);

					callback.accept(gameBoard1); 
//					Thread.sleep(1000);  NEEDED?

					System.out.println(gameBoard1.get(0)+ " "+gameBoard1.get(1)+" "+gameBoard1.get(2));
					System.out.println(gameBoard1.get(3)+ " "+gameBoard1.get(4)+" "+gameBoard1.get(5));
					System.out.println(gameBoard1.get(6)+ " "+gameBoard1.get(7)+" "+gameBoard1.get(8));
				}catch(Exception e){System.out.println(e.getMessage());}
				
				
				 Node result = new Node(MyController.getStringBoard(gameBoard1),1);
				 result.setMinMax_for_O();
				 int result_o = result.minMaxValue;
				 System.out.println("Score of o: " + Integer.toString(result_o));
				 result.setMinMax_for_X();
				 int result_x = result.minMaxValue;
				 System.out.println("Score of x: " + Integer.toString(result_x));
				 winnerGame obj = new winnerGame();
				
				if(result_o == -10 ||result_x == 10)
				 {
					 gameDone = 1;
					 if(result_o == -10)
						 obj.actualWinner = 2;
					 else
						 obj.actualWinner = 1;
					 
				 }

				if(gameDone == 1)
				{
					System.out.println("---------------------------------------------------------------");

					 if(result_o == -10 && result_x == 10 || result_o == 0 && result_x == 0 )
					 {
						 winner = "DRAW";
						 obj.score[2] = obj.score[2]+1;
					 }
					 if(result_o == -10 && result_x==0)
					 {
						 winner = "O";
						 obj.score[1] = obj.score[1]+1;
					 }
					 if(result_x == 10 && result_o == 0)
					 {
						 winner = "X";
						 obj.score[0] = obj.score[0]+1;
					 }
					 System.out.println("Winner in thread: " + winner);
					 obj.setWinner(winner);
					 obj.winners.clear();
				 
				System.out.println("Games Won: ");

				obj.winners.add("Winner this round: " + winner + "\n" + System.lineSeparator() + "Games so far: " + System.lineSeparator() +
				"	 Draw Won: " + Integer.toString(obj.score[2])  + System.lineSeparator() + "	 (COMP1) X Won: " 
				+ Integer.toString(obj.score[0]) +
			    System.lineSeparator() + "	 (COMP2) O Won: " + Integer.toString(obj.score[1]));
						
				for(int j= 0; j<obj.score.length; j++)
				{
					if(j == 0)
					{
						System.out.println("X: " + obj.score[j]);
					}
					if(j == 1)
					{
						System.out.println("O: " + obj.score[j]);
					}
					else if(j == 2)
					{
						System.out.println("Draw: " + obj.score[j]);
					}
					
				}
					
				}//if done
		
			ex.shutdown();
			

	}
	

}//class HLT

class MyCall implements Callable<Integer>{ //returns a result, runnable have to define a run method
	ArrayList<Character> board = new ArrayList<Character>();
	Character move;
//	private Consumer<Serializable> callback;
	private ArrayList<Integer> listMovesWin;
	private ArrayList<Integer> listMovesDraw;

	ArrayList<Character> switched = new  ArrayList<>();
	int gameLevel, gameLevel2;
	int index;
	AI_MinMax startThis;
	int turn;

	winnerGame turnTracker = new winnerGame();
	int xcount;
	int ocount;
	
	MyCall(ArrayList<Character> game, Character turn,int level,int level2,int xcounter,int ocounter){
		board = game;
		move = turn;
		gameLevel = level;
		gameLevel2 = level2;
		xcount = xcounter;
		ocount = ocounter;
		
		System.out.println("-------------------------- NEW ROUND --------------------------");
		System.out.println("Move: "+ move);


		System.out.println("COMP 1 X level: " + Integer.toString(gameLevel));
		System.out.println("COMP 2 O level: " + Integer.toString(gameLevel2));
		
		if(gameLevel2 == 3 && move == 'o'|| gameLevel2 == 2 && move == 'o' && turnTracker.ocount==1)
		{
				System.out.println("board " + getStringBoard(board));

				board = switchBoard(board);
				System.out.println("swtiched board " + getStringBoard(board));
				startThis = new AI_MinMax(getStringBoard(board),move);
				listMovesWin = startThis.movesWin;
				listMovesDraw = startThis.movesDraw;
		}
		else if(gameLevel == 3 && move == 'x' || gameLevel == 2 && move == 'x' && turnTracker.xcount==1)
		{
			startThis = new AI_MinMax(getStringBoard(board),move);
			listMovesWin = startThis.movesWin;
			listMovesDraw = startThis.movesDraw;	
		}	

	}

	@Override
	public Integer call() throws Exception {
		// TODO Auto-generated method stub
		
		
		if(gameLevel == 2 && move =='x')
		{
			if(turnTracker.xcount != 1)
			{
//				System.out.println("LEVEL 1 X");
				index = Level1();
				turnTracker.xcount = 1; //next time chose list
			}
			else if(turnTracker.xcount == 1)
			{
//				System.out.println("LEVEL 3 X");
				index = level3();
				turnTracker.xcount = 2; //next time chose random 
			}
			
		}
		
		if(gameLevel2 == 2 && move =='o')
		{
			if(turnTracker.ocount != 1)
			{
//				System.out.println("LEVEL 1 O");
				index = Level1();
				turnTracker.ocount = 1; //next time chose list
			}
			else if(turnTracker.ocount == 1)
			{
//				System.out.println("LEVEL 3 O");
				index = level3();
				turnTracker.ocount = 2; //next time chose random 
			}
			
		}
	
		if(gameLevel == 1 && move =='x')
		{
//			System.out.println("LEVEL 1 X");
			index = Level1();
		}
		
		if(gameLevel2 == 1 && move == 'o')
		{
//			System.out.println("LEVEL 1 O");
			index = Level1();
		}	
				
		if(gameLevel == 3 && move == 'x')
		{
//			System.out.println("LEVEL 3 X");
			index = level3();
		}
		if(gameLevel2 == 3 && move == 'o')
		{
//			System.out.println("LEVEL 3 O");

			index = level3();
		}

		return index;
	}//call
	
	
	public int level3()
	{
		int index; 
		boolean bool = true;
		Integer val = 0;
		
		while(bool) {
			
		Random r = new Random();
		if(listMovesWin.size() != 0)
		{
//			System.out.println("list size in win: " + Integer.toString(listMovesWin.size()));
			val = listMovesWin.get(r.nextInt(listMovesWin.size()));
			val = val-1;
		}
		else if(listMovesDraw.size() != 0)
		{
//			System.out.println("list size in draw: " + Integer.toString(listMovesDraw.size()));
			val = listMovesDraw.get(r.nextInt(listMovesDraw.size()));
			val = val-1;

		}
		else
		{
			val = r.nextInt(9);
		}
		
		System.out.println("val picked: " + Integer.toString(val));
		
		if(board.get(val) == 'x' || board.get(val) == 'o') 
		{bool = true;}
		else {bool = false;}	
		}
		
		System.out.println("player: " + move + " chooses index: "+val);
		index= val;
		
		if(move == 'o')
		{
			board = switchBoard(board);
//			System.out.println("swtiched at the end of run: " + getStringBoard(board));
			
		}
		return index;
	}
	
	public int Level1()
	{ 
		int index;
		boolean bool = true;
		Integer val = 0;
		
		while(bool) {
			Random r = new Random();
			val = r.nextInt(9);
			
			if(board.get(val) == 'x' || board.get(val) == 'o') 
			{bool = true;}
			else {bool = false;}	
		}
		
		System.out.println("player: " + move + " chooses index: "+val);
		index= val;
		return index;
	}
	
	//board as arraylist
	public ArrayList<Character> switchBoard(ArrayList<Character>  val)
	{
		  for (int i = 0; i < val.size(); i++)
		  {
			  if(val.get(i) == 'x')
			  {
				  val.set(i, 'o'); 
			  }
			  else if(val.get(i) == 'o')
			  {
				  val.set(i, 'x');
			  }
		  }
		  return val;
	}
	
	String getStringBoard(ArrayList<Character> list)
	{    
	    StringBuilder newBoard = new StringBuilder(list.size());
	    for(Character ch: list)
	    {
	    	if(ch == '-')
	    	{
	    		ch = 'b';
	    	}
	    	newBoard.append(ch + " ");
	    }
	    return newBoard.toString();
	}
} //MyCall
	