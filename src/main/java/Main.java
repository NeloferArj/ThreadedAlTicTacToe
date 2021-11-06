import java.io.IOException;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	 Parent root;

	 
	@Override
	public void start(Stage primaryStage) {
		 try {
	            // Read file fxml and draw interface.
			 	root = FXMLLoader.load(getClass()
	                    .getResource("/FXML/Myfxml.fxml"));
	 
	            primaryStage.setTitle("My Application");
	             Scene s1 = new Scene(root, 700,700);
	            s1.getStylesheets().add("/styles/style1.css");
	            primaryStage.setScene(s1);
	            primaryStage.show();
	            
	            
	            PauseTransition delay = new PauseTransition(Duration.seconds(3));
	            delay.setOnFinished( event ->{	
					try {
						
						primaryStage.close();
						root = FXMLLoader.load(getClass().getResource("/FXML/Myfxml2.fxml"));
					   Scene s2 = new Scene(root, 700,700);
					    s2.getStylesheets().add("/styles/style2.css");
						primaryStage.setScene(s2);
						primaryStage.show();
					
					
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            		
	            });
	            delay.play();

	        } catch(Exception e) {
	            e.printStackTrace();
	            System.exit(1);
	        }
		 
		 
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
