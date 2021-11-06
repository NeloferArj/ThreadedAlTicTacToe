import java.util.ArrayList;

public class winnerGame{

	static String gameWinner;
	static int[] score = new int[]{0,0,0};
	static ArrayList<String> winners = new ArrayList<String>();
	static int level;
	static int numGames;
	static int actualWinner = 0;
	
	static int xcount = 0;
	static int ocount = 0;
	

	public void clear()
	{
		level = 0;
		numGames = 0;
		winners.clear();
		gameWinner = "";
		actualWinner = 0;
		xcount = 0;
		ocount = 0;
		for(int j= 0; j<score.length; j++)
		{
			score[j] = 0;

		}
		
	}

	public void setscoreX(int val)
	{
		score[0] = val;
	}
	
	public void setscoreO(int val)
	{
		score[1] = val;
	}
	
	public void setscoreDraw(int val)
	{
		 score[2] = val;
	}
	
	public int getX()
	{
		return score[0];
	}
	
	public int getO()
	{
		return score[1];
	}
	
	public int getDraw()
	{
		return score[2];
	}

	
	public void setWinner(String val)
	{
		gameWinner = val;
	}
	public String getWinner()
	{
		return gameWinner;
	}
}
