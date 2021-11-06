

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

import javax.xml.stream.events.Characters;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class MyController implements Initializable {
	
    @FXML
    private Button startGame;
    
    @FXML
    private Button b2;
    
    
    @FXML
    private Button b11;

    
    @FXML
    private TextArea textArea1;
    
    @FXML
    private MenuItem game1,game2,game3,game4,game5,game6,game7,game8,game9,game10,exit;

    @FXML
    private Label label,label2,label3;
    
    @FXML
    private Menu game, Level, Level2;
    
    
    @FXML
    private GridPane grid2,grid;
    
	@FXML
	private VBox root;
	
	@FXML
	private BorderPane root2;

	@FXML
	private BorderPane root3;
    
    
    @FXML
    private TextField textField,putText;
    

    
    @FXML
    private TextArea txt1, txt2, txt3, txt4, txt5, txt6,txt7,txt8,txt9;

    
    //static so each instance of controller can access to update 
    private static String textEntered = "";
    private static String textEntered1 = "";
    private static String box1 = "";
	static ArrayList<Character> boardList = new ArrayList<>(Arrays.asList('-','-','-','-','-','-','-','-','-'));

	static winnerGame obj = new winnerGame();


	static int numGames;
    static int level;
    static int level2;
    static int finalGames;
    int Winner = 0;
    int index;
    int turn = 1; //1 is or x, 2 is for o
    Character move;
	int j= 0;
	int done;
	int item = 1;

    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
        
	}
    
//methods to change elements of the GUI
	public void setTextArea()
	{
        textArea1.setText(textEntered);
//      System.out.println("hello from setText");
  }
	
    public void setText(){
        putText.setText(textEntered);
//        System.out.println("hello from setText");
    }
    
    public void setTextLabel(){
        label.setText(textEntered);
//        System.out.println("hello from label text");
    }
    
    public void setText2(){
        textField.setText(textEntered1);
//        System.out.println("hello from setText2");
    }
    
    public void disablelvl(){
        Level.setDisable(true);
//        System.out.println("hello from disable");
    }
    
    public void disablelvl2(){
        Level2.setDisable(true);
//        System.out.println("hello from disable");
    }
    
    public void disableNumGames(){
    	game.setDisable(true);
//        System.out.println("hello from disable");
    }
    
    
    public void disableResult(){
    	b11.setDisable(true);
//        System.out.println("hello from disable");
    }
    
    public void disablelvlfalse(){
        Level.setDisable(false);
//        System.out.println("hello from disable");
    }
    
    public void disablelvl2false(){
        Level2.setDisable(false);
//        System.out.println("hello from disable");
    }
    
    public void disableNumGamesfalse(){
    	game.setDisable(false);
//        System.out.println("hello from disable");
    }

 
    public void disablestrtGame(){
    	startGame.setDisable(true);
//        System.out.println("hello from disable");
    }
    
    
    public void disablestrtGameFalse(){
    	startGame.setDisable(false);
//        System.out.println("hello from disable start game to false");
    }
    
	//NOT USED
	public void helloMethod(ActionEvent e) throws IOException {
		
        textEntered = textField.getText(); //get text entered by user
        System.out.println(textEntered);
        
        //get instance of the loader class
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Myfxml2.fxml"));
        Parent root2 = loader.load(); //load view into parent
        MyController myctr = loader.getController();//get controller created by FXMLLoader
        myctr.setText();//use MyLoader class method for setText()
        
        root2.getStylesheets().add("/styles/style2.css");//set style
        
        root.getScene().setRoot(root2);//update scene graph
        

       	/* original way to load views...nothing shared across FXML files
         
         Parent root2 = FXMLLoader.load(getClass()
                 .getResource("/FXML/Myfxml2.fxml"));
        root2.getStylesheets().add("/styles/style2.css");
		 
		 root.getScene().setRoot(root2);
       */
	}
	
	//NOT USED
	public void b1Method(ActionEvent e) throws IOException{
		
        textEntered = putText.getText();
        System.out.println(textEntered);
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Myfxml.fxml"));
        Parent root = loader.load();
        MyController myctr = loader.getController();
        myctr.setText2();
        root2.getScene().setRoot(root);
        
        /*
		Parent root = FXMLLoader.load(getClass()
                .getResource("/FXML/Myfxml.fxml"));
		 
		 root2.getScene().setRoot(root);
         */
	}
	
	public void exitMethod(ActionEvent e) throws IOException{
		 Platform.exit();
         System.exit(0);
	}
	
	//View results
	public void resultMethod(ActionEvent e) throws IOException {
	    FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/file3.fxml"));
	    Parent root3 = loader.load();
	    MyController myctr = loader.getController();
        root3.getStylesheets().add("/styles/style2.css");//set style
	    root2.getScene().setRoot(root3);
	    		
		textEntered = obj.winners.get(0);
        myctr.setTextArea();

        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/FXML/Myfxml2.fxml"));
        Parent root2 = loader1.load();
        MyController myctr2 = loader1.getController();

      PauseTransition pause = new PauseTransition(Duration.seconds(10));
      pause.setOnFinished(event->{
          root3.getScene().setRoot(root2);

      });
      pause.play();
    
      numGames = numGames-1; 
      
      System.out.println("games left: " +  Integer.toString(numGames));
      System.out.println("games chosen: " +  Integer.toString(finalGames));

      	if(numGames != finalGames && numGames != 0)
      	{
      		myctr2.disablelvl();
      		myctr2.disablelvl2();
          	myctr2.disableNumGames();
          	myctr2.disableResult();
          	textEntered1 = "You have " + Integer.toString(numGames) + " game(s) left! press 'Start game!' to start a new game!";
            myctr2.setText2();
            obj.actualWinner = 0;
            
          	textEntered = "";
            myctr2.setTextLabel();
      	}
      	else if(numGames == 0)
      	{
      		myctr2.disablelvlfalse();
      		myctr2.disablelvl2false();
          	myctr2.disableNumGamesfalse();
          	myctr2.disablestrtGame();
          	myctr2.disableResult();

          	textEntered1 = "You have no games left please choose the level and number of games to restart.";
            myctr2.setText2();
          	textEntered = "";
            myctr2.setTextLabel();
            obj.clear();
      		
      	}

}

	//NOT USED
	public void b2Method(ActionEvent e) throws IOException{
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Myfxml2.fxml"));
        Parent root2 = loader.load();
        MyController myctr = loader.getController();
        root3.getScene().setRoot(root2);
        
        /*
		Parent root = FXMLLoader.load(getClass()
                .getResource("/FXML/Myfxml.fxml"));
		 
		 root2.getScene().setRoot(root);
         */
	}
	
	//start game
	public void startGameMethod(ActionEvent e) throws IOException {
            label.setText("INSTRUCTIONS: PLEASE WAIT! for the makers to placed on the board.");
            textField.setText("Once board is filled, press 'View Results' to see winner info!");
            
			// doesnt happen till the END 
			startGame.setDisable(true);
			Level.setDisable(true);
			Level2.setDisable(true);
			game.setDisable(true);
			b11.setDisable(false);
	
			
			runGame();
	}//start BUTTON

	
	public void runGame()
	{		
		boardList = reset(boardList);

		ExecutorService ex = Executors.newFixedThreadPool(1); //create a thread pool of 5 threads
		done = 0;
		for(int i=0; i<9; i++)
		{
			if(obj.actualWinner == 1 || obj.actualWinner == 2 )
			{
				System.out.println("1st winner: " + Integer.toString(obj.actualWinner));
				break;
			}
			
			j++;
			if(i == 0 || i==2|| i==4|| i==6|| i==8)
			{
				move  = 'x';
			}
			else
			{
				move  = 'o';
			}
			
			ArrayList<Character> newboard = new ArrayList<>(Arrays.asList('-','-','-','-','-','-','-','-','-'));
	
			newboard = (ArrayList<Character>) boardList.clone();
			
			HLThreading thread = new HLThreading(data -> {
					boardList = (ArrayList<Character>) data;
						Platform.runLater(()->{
						try {
							printOnBoard((ArrayList<Character>) data,j);
	
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block 
							e.printStackTrace();
						}
						});
				System.out.println("After update in MyController: " + boardList);
				},level,level2,newboard,move,done);		
			
			//evaluate board in the next round, game is done after next round
			if(i == 7)
			{
				done = 1;
			}
			if(i == 8)
			{
				System.out.println("///////////////////////////END///////////////////////");
				
			}

		}//for

	}
	
	//reset board
	public static ArrayList<Character> reset(ArrayList<Character>  val)
	{
		  val.clear();
		  for (int i = 0; i < 9; i++)
		  {
			  val.add('-');  
		  }
		  return val;
	}
	
	//print arraylist on to GUI
	public void printOnBoard( ArrayList<Character> boardList, int i) throws InterruptedException{
		System.out.println("Updating board move: "+ Integer.toString(item));
		item++;
		PauseTransition pause = new PauseTransition(Duration.seconds(i));
		Thread.sleep(1000);
		pause.setOnFinished(event->{
		 for (int counter = 0; counter < boardList.size(); counter++) { 	

			  if(boardList.get(counter) != '-')
			  {
				 if(counter == 0)
				 {
					 Label l7 = new Label("          " + boardList.get(counter).toString());
					 l7.setFont(new Font("Arial", 35));
					grid.add(l7,0,0);
				 }
				 else if(counter == 1)
				 {
					 Label l7 = new Label("          " + boardList.get(counter).toString());
					 l7.setFont(new Font("Arial", 35));
					grid.add(l7,1,0);

				 }
				 else if(counter == 2)
				 {
					 Label l7 = new Label("          " + boardList.get(counter).toString());
					 l7.setFont(new Font("Arial", 35));
					grid.add(l7,2,0);

				 }
				 else if(counter == 3)
				 {
					 Label l7 = new Label("          " + boardList.get(counter).toString());
					 l7.setFont(new Font("Arial", 35));
					grid.add(l7,0,1);

				 }
				 else if(counter == 4)
				 {
					 Label l7 = new Label("          " + boardList.get(counter).toString());
					 l7.setFont(new Font("Arial", 35));
					grid.add(l7,1,1);

				 }
				 else if(counter == 5)
				 {
					 Label l7 = new Label("          " + boardList.get(counter).toString());
					 l7.setFont(new Font("Arial", 35));
					grid.add(l7,2,1);

				 }
				 else if(counter == 6)
				 {
					 Label l7 = new Label("          " + boardList.get(counter).toString());
					 l7.setFont(new Font("Arial", 35));
					grid.add(l7,0,2);

				 }
				 else if(counter == 7)
				 {
					 Label l7 = new Label("          " + boardList.get(counter).toString());
					 l7.setFont(new Font("Arial", 35));
					grid.add(l7,1,2);

				 }
				 else// if(counter == 8)
				 {
					 Label l7 = new Label("          " + boardList.get(counter).toString());
					 l7.setFont(new Font("Arial", 35));
					grid.add(l7,2,2);

				 }
			 } //if
	      } 
			
		});	
		pause.play();
	}//printBoard
	
		//coverts arraylist to string
		static String[] getStringBoard(ArrayList<Character> list)
		{    
			String[] s = new String[list.size()];
			for(int i = 0; i<list.size(); i++ )
		    {
		    	s[i] = list.get(i).toString().toUpperCase();
		    }
		    return s;
		}
		
		//Computer 1 levels
		public void Novice(ActionEvent e) throws IOException {
			level = 1;
			System.out.println("Comp1: Novice");

			textEntered = "INSTRUCTIONS: Please select the number of games under 'Games'";
			label.setText(textEntered);	
			Level.setDisable(true);
			b11.setDisable(true);
		}
		public void Advanced(ActionEvent e) throws IOException {
			level = 2;
			System.out.println("Comp1: Advanced");
			textEntered = "INSTRUCTIONS: Please select the number of games under 'Games'";
			label.setText(textEntered);	
			Level.setDisable(true);
		}
		public void Expert(ActionEvent e) throws IOException {
			level = 3;
			System.out.println("Comp1: Expert");
			textEntered = "INSTRUCTIONS: Please select the number of games under 'Games'";
			label.setText(textEntered);	
			Level.setDisable(true);
		}
		
		//Computer 2 levels
		public void Novice2(ActionEvent e) throws IOException {
			level2 = 1;
			System.out.println("Comp2: Novice");
			textEntered = "INSTRUCTIONS: Please select the number of games under 'Games'";
			label.setText(textEntered);	
			Level2.setDisable(true);
			b11.setDisable(true);
		}
		public void Advanced2(ActionEvent e) throws IOException {
			level2 = 2;
			System.out.println("Comp2: Advanced");
			textEntered = "INSTRUCTIONS: Please select the number of games under 'Games'";
			label.setText(textEntered);	
			Level2.setDisable(true);
		}
		public void Expert2(ActionEvent e) throws IOException {
			level2 = 3;
			System.out.println("Comp2: Expert");
			textEntered = "INSTRUCTIONS: Please select the number of games under 'Games'";
			label.setText(textEntered);	
			Level2.setDisable(true);
		}
		
		// number of games 1-10
		public void game1(ActionEvent e) throws IOException {
			startGame.setDisable(false);
			box1 = game1.getText();
			numGames = Integer.parseInt(box1);
			System.out.println("picked: " + Integer.toString(numGames));
			textEntered = "INSTRUCTIONS: Press 'Start Game!' and wait for game to finish running.";
			label.setText(textEntered);	
			finalGames = numGames;
			game.setDisable(true);

		
		}
		
		public void game2(ActionEvent e) throws IOException {
			startGame.setDisable(false);
			box1 = game2.getText();
			numGames = Integer.parseInt(box1);
			System.out.println("picked: " + Integer.toString(numGames));
			textEntered = "INSTRUCTIONS: Press 'Start Game!' and wait for game to finish running.";
			label.setText(textEntered);	
			finalGames = numGames;
			game.setDisable(true);


		}
		
		public void game3(ActionEvent e) throws IOException {
			startGame.setDisable(false);
			box1 = game3.getText();
			numGames = Integer.parseInt(box1);
			System.out.println("picked: " + Integer.toString(numGames));
			textEntered = "INSTRUCTIONS: Press 'Start Game!' and wait for game to finish running.";
			label.setText(textEntered);	
			finalGames = numGames;
			game.setDisable(true);


		}
		
		public void game4(ActionEvent e) throws IOException {
			startGame.setDisable(false);
			box1 = game4.getText();
			numGames = Integer.parseInt(box1);
			System.out.println("picked: " + Integer.toString(numGames));
			textEntered = "INSTRUCTIONS: Press 'Start Game!' and wait for game to finish running.";
			label.setText(textEntered);	
			finalGames = numGames;
			game.setDisable(true);

		}
		
		public void game5(ActionEvent e) throws IOException {
			startGame.setDisable(false);
			box1 = game5.getText();
			numGames = Integer.parseInt(box1);
			System.out.println("picked: " + Integer.toString(numGames));
			textEntered = "INSTRUCTIONS: Press 'Start Game!' and wait for game to finish running.";
			label.setText(textEntered);	
			finalGames = numGames;
			game.setDisable(true);

		}
		
		public void game6(ActionEvent e) throws IOException {
			startGame.setDisable(false);
			box1 = game6.getText();
			numGames = Integer.parseInt(box1);
			System.out.println("picked: " + Integer.toString(numGames));
			textEntered = "INSTRUCTIONS: Press 'Start Game!' and wait for game to finish running.";
			label.setText(textEntered);	
			finalGames = numGames;
			game.setDisable(true);

		}
		
		public void game7(ActionEvent e) throws IOException {
			startGame.setDisable(false);
			box1 = game7.getText();
			numGames = Integer.parseInt(box1);
			System.out.println("picked: " + Integer.toString(numGames));
			textEntered = "INSTRUCTIONS: Press 'Start Game!' and wait for game to finish running.";
			label.setText(textEntered);	
			finalGames = numGames;
			game.setDisable(true);

		}
		
		public void game8(ActionEvent e) throws IOException {
			startGame.setDisable(false);
			box1 = game8.getText();
			numGames = Integer.parseInt(box1);
			System.out.println("picked: " + Integer.toString(numGames));
			textEntered = "INSTRUCTIONS: Press 'Start Game!' and wait for game to finish running.";
			label.setText(textEntered);	
			finalGames = numGames;
			game.setDisable(true);

		}
		
		public void game9(ActionEvent e) throws IOException {
			startGame.setDisable(false);
			box1 = game9.getText();
			numGames = Integer.parseInt(box1);
			System.out.println("picked: " + Integer.toString(numGames));
			textEntered = "INSTRUCTIONS: Press 'Start Game!' and wait for game to finish running.";
			label.setText(textEntered);	
			finalGames = numGames;
			game.setDisable(true);

		}
		
		public void game10(ActionEvent e) throws IOException {
			startGame.setDisable(false);
			box1 = game10.getText();
			numGames = Integer.parseInt(box1);
			System.out.println("picked: " + Integer.toString(numGames));
			textEntered = "INSTRUCTIONS: Press 'Start Game!' and wait for game to finish running.";
			label.setText(textEntered);	
			finalGames = numGames;
			game.setDisable(true);

		}
} //controller calss
