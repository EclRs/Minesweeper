
package minesweeper.form;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EnterYourName
{
    private Stage stage;
    private static final String TITLE = "Meilleur score!";
    private static final String MESSAGE = "Entrez votre nom :";
    private static final String BUTTON_MESSAGE = "Envoyer";

    public EnterYourName(final Label controlToAddName)
    {
	stage = new Stage();
	stage.setResizable(false);
	stage.initModality(Modality.WINDOW_MODAL);
	stage.setTitle(TITLE);
	Label lblMessage = new Label(MESSAGE);
	final TextField txtName = new TextField();
	Button send = new Button(BUTTON_MESSAGE);
	send.setOnMouseClicked(new EventHandler<MouseEvent>()
	{

	    @Override
	    public void handle(MouseEvent me)
	    {
		sendName(controlToAddName, txtName.getText());
		stage.close();
	    }
	});
	GridPane grid = new GridPane();
	grid.add(lblMessage, 0, 0);
	grid.add(txtName, 0, 1);
	grid.add(send, 0, 2);
	grid.setAlignment(Pos.CENTER);
	grid.setPadding(new Insets(10));
	Scene scene = new Scene(grid);

	stage.setScene(scene);
	stage.showAndWait();
    }

    private void sendName(Label controlToAddName, String name)
    {
	controlToAddName.setText(name);
    }
}
