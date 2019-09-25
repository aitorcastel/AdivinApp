package dad.javafx;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;


public class AdivinApp extends Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	
	private Label instruccionesText;
	private TextField introText;
	private Button checkButton;
	
	private int 	intentos=0,
					random = (int) (Math.random() * 100);
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {

		instruccionesText = new Label("Introduce un número del 1 al 100");
		
		introText = new TextField();
		introText.setPromptText("");
		introText.setMaxWidth(150);
		
		checkButton = new Button("Comprobar");
		checkButton.setDefaultButton(true);
		checkButton.setOnAction(e -> oncheckButtonAction(e));
		
		VBox root = new VBox();
		root.setSpacing(5);
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(instruccionesText, introText, checkButton);
		
		Scene scene = new Scene(root, 320, 200);
		primaryStage.setTitle("AdivinApp");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}


	private void oncheckButtonAction(ActionEvent e) {

		try {
			
			int 	num_usuario = Integer.parseInt(introText.getText());
			boolean check = (random == num_usuario);
			
			if(check) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setHeaderText("¡Has ganado!");
				alert.setContentText("Sólo has necesitado "+ intentos +" intentos.\n"
						+ "Vuelve a jugar y hazlo mejor.");
				alert.showAndWait();
			}
			
			else if(num_usuario > 100 || num_usuario < 0) {
				
				intentos +=2;
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("Error entre la pantalla y la silla");
				alert.setContentText("Te has saltado los límites!\nSe te han añadido 2 intentos.");
				alert.showAndWait();
				
			}
			else {
				
				if(random >= 50) {
					intentos++;
					Alert alert = new Alert(AlertType.WARNING);
					alert.setHeaderText("¡Has fallado!");
					alert.setContentText("El número a adivinar es mayor a 50");
					alert.showAndWait();
				}
				else {
					intentos++;
					Alert alert = new Alert(AlertType.WARNING);
					alert.setHeaderText("¡Has fallado!");
					alert.setContentText("El número a adivinar es menor a 50");
					alert.showAndWait();
				}
				
			}
			
		}catch(Exception x) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Error entre la pantalla y la silla");
			alert.setContentText("¿Qué clase de número es ese?");
			alert.showAndWait();

		}

		
		
	}

}
