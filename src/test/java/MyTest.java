import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MyTest {

	static HLThreading game;
	static MyCall game2;
	static AI_MinMax AI;
	
	ArrayList<Character> gameBoard;
	ArrayList<Character> gameBoard2 = new ArrayList<>();
	ArrayList<Character> gameBoard3  =  new ArrayList<Character>();


	Character turn;
	int level, level2, xcounter,ycounter;
	ArrayList<Integer> win = new ArrayList<Integer>();

	@BeforeEach
	public void Todo()
	{
		gameBoard = new ArrayList<>(Arrays.asList('-','-','-','-','-','-','-','-','-'));
		turn = 'x';
		level = 3;
		level2 = 1;
		xcounter = 1;
		ycounter = 1;
		game2 = new MyCall(gameBoard,turn,level,level2,xcounter,ycounter);
	}

	@Test
	//test if gameboard is the correct size 
 	public void testsize()
 	{
  		assertEquals(9,game.gameBoard1.size(), "Incorrect size of gameBoard");

 	}
	
	@Test
	//test if and index is returned 
 	public void testlevels()
 	{
		assertNotNull(game2.Level1(), "RandNum returns nulls");

 	}
	
	@Test
 	public void testlevel1()
 	{
		int index1 = game2.Level1();
		int index2 = game2.Level1();
		assertNotNull(index2, "RandNum returns nulls");
		assertNotNull(index1, "RandNum returns nulls");
		assertNotEquals(index1,index2, "returns repeating vlaues");

 	}
	
	@Test
 	public void testlevel2_3()
 	{
		int index1 = game2.level3();
		assertNotNull(index1, "RandNum returns nulls");

 	}
	
	@Test
 	public void testlevelAI()
 	{
		//the best move would be to place an X at postion 2 or 3 or 9
		AI = new AI_MinMax("o b b o x o x x b ",'x');
		win = AI.movesWin;
		assertEquals(true,win.contains(2), "movesWin returns incorrect vlaues");
 	}

	@Test
 	public void testlevelAI1()
 	{
		AI = new AI_MinMax("o b b o x o x x b ",'x');
		win = AI.movesWin;
		assertNotNull(win, "movesWin returns nulls");
 	}
	
	
	@Test
 	public void testlevelAI2()
 	{
		AI = new AI_MinMax("b b b b o x b b x",'o');
		win = AI.movesWin;
		assertNotNull(win, "movesWin returns nulls");
		assertEquals(true,win.contains(3), "movesWin returns incorrect vlaues");

 	}
	
	@Test
 	public void testlevelAI34()
 	{
		AI = new AI_MinMax("b b b b b b b b b ",'x');
		win = AI.movesDraw;
		assertEquals(true,win.contains(4), "movesWin returns incorrect vlaues");
 	}

	@Test
 	public void testlevelAI4()
 	{
		AI = new AI_MinMax("o b o b o x x b x ",'x');
		win = AI.movesWin;
		System.out.println("WIN:" + win);
		assertEquals(true,win.contains(8), "movesWin returns incorrect vlaues");

 	}
	
		@Test
 	public void testlevelAI21()
 	{
		AI = new AI_MinMax("o b o b o x x b x ",'x');
		win = AI.movesDraw;
		System.out.println("WIN:" + win);
		assertEquals(true,win.contains(2), "movesWin returns incorrect vlaues");

 	}

	@Test
 	public void testlevelAI33()
 	{
		AI = new AI_MinMax("x x b o x o b b o ",'x');
		win = AI.movesWin;
		assertEquals(true,win.contains(3), "movesWin returns incorrect vlaues");

 	}
	
	@Test
 	public void testlevelAI32()
 	{
		AI = new AI_MinMax("b b b b o b b b b  ",'o');
		win = AI.movesDraw;
		assertEquals(true,win.contains(7), "movesWin returns incorrect vlaues");

 	}
	
	@Test
 	public void stringBoard()
 	{
		String val = game2.getStringBoard(gameBoard);
		String board2 = "b b b b b b b b b ";

		assertEquals(val,board2, "board not converted properly");

 	}
	
	@Test
 	public void stringBoard2()
 	{
		String val = game2.getStringBoard(gameBoard);
		String board2 = "b b b b b b b b b ";
		assertNotNull(val, "stringboard returns nulls");

 	}
	

	
//	@Test
// 	public void SwitchBoard()
// 	{
//		gameBoard.set(6, 'x');
//		gameBoard.set(4, 'x');
//		gameBoard.set(4, 'o');
//		gameBoard3 = gameBoard;
//		gameBoard2 = game2.switchBoard(gameBoard);
//		assertNotEquals(gameBoard2,gameBoard3, "board not switched properly");
//
// 	}
//	
//	
	@Test
 	public void SwitchBoard2()
 	{
		gameBoard.set(6, 'x');
		gameBoard.set(4, 'x');
		gameBoard.set(3, 'o');
		gameBoard2 = game2.switchBoard(gameBoard);
		assertEquals('o',gameBoard2.get(6), "board not converted properly");
		assertEquals('o',gameBoard2.get(4), "board not converted properly");
		assertEquals('x',gameBoard2.get(3), "board not converted properly");

 	}
	
	@Test
 	public void SwitchBoard3()
 	{
		gameBoard.set(6, 'x');
		gameBoard.set(4, 'x');
		gameBoard.set(3, 'o');
		gameBoard2 = game2.switchBoard(gameBoard);
		assertNotNull(gameBoard2, "switch board returns nulls");

 	}
	

}
