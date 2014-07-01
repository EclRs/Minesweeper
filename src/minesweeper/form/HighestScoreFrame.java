
package minesweeper.form;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class HighestScoreFrame
{
    private static final String LBLEXPERT_TEXT = "Expert : ";
    private static final String LBLINTERMEDIATE_TEXT = "Intermédiare : ";
    private static final String LBLBEGINNER_TEXT = "Débutant : ";
    private static final String BTNOK_TEXT = "Ok";
    private static final String TITLE = "Démineurs les plus rapides";
    private GridPane highScoreGrid;
    private BorderPane baseBorderPane;
    private Label lblScoreBeginner;
    private Label lblScoreIntermediate;
    private Label lblScoreExpert;
    private Stage stage;

    private static final String DEFAULT_TIME_STRING = "N/A secondes";
    private static final String DEFAULT_NAME_STRING = "N/A";
    private static final String BTNERASE_TEXT = "Effacer les scores";
    private Label lblNameBeginner;
    private Label lblNameIntermediate;
    private Label lblNameExpert;

    public HighestScoreFrame()
    {
	stage = new Stage();
	stage.initModality(Modality.WINDOW_MODAL);
	stage.setResizable(false);
	stage.setTitle(TITLE);

	highScoreGrid = new GridPane();
	baseBorderPane = new BorderPane();
	lblScoreBeginner = new Label(DEFAULT_TIME_STRING);
	lblScoreIntermediate = new Label(DEFAULT_TIME_STRING);
	lblScoreExpert = new Label(DEFAULT_TIME_STRING);
	lblNameExpert = new Label(DEFAULT_NAME_STRING);
	lblNameBeginner = new Label(DEFAULT_NAME_STRING);
	lblNameIntermediate = new Label(DEFAULT_NAME_STRING);

	Button btnErase = new Button(BTNERASE_TEXT);
	btnErase.setOnMouseClicked(new EventHandler<MouseEvent>()
	{

	    @Override
	    public void handle(MouseEvent me)
	    {
		lblScoreBeginner.setText(DEFAULT_TIME_STRING);
		lblScoreIntermediate.setText(DEFAULT_TIME_STRING);
		lblScoreExpert.setText(DEFAULT_TIME_STRING);
		lblNameBeginner.setText(DEFAULT_NAME_STRING);
		lblNameIntermediate.setText(DEFAULT_NAME_STRING);
		lblNameExpert.setText(DEFAULT_NAME_STRING);
		HighestScoreFrame.this.hide();
	    }
	});

	Button btnOk = new Button(BTNOK_TEXT);
	btnOk.setOnMouseClicked(new EventHandler<MouseEvent>()
	{

	    @Override
	    public void handle(MouseEvent me)
	    {
		HighestScoreFrame.this.hide();
	    }
	});

	Label lblBeginner = new Label(LBLBEGINNER_TEXT);

	Label lblIntermediate = new Label(LBLINTERMEDIATE_TEXT);

	Label lblExpert = new Label(LBLEXPERT_TEXT);

	highScoreGrid.add(lblBeginner, 0, 0);
	highScoreGrid.add(lblIntermediate, 0, 1);
	highScoreGrid.add(lblExpert, 0, 2);

	highScoreGrid.add(lblScoreBeginner, 1, 0);
	highScoreGrid.add(lblScoreIntermediate, 1, 1);
	highScoreGrid.add(lblScoreExpert, 1, 2);

	highScoreGrid.add(lblNameBeginner, 2, 0);
	highScoreGrid.add(lblNameIntermediate, 2, 1);
	highScoreGrid.add(lblNameExpert, 2, 2);

	for (Node node : highScoreGrid.getChildren())
	{
	    GridPane.setMargin(node, new Insets(5));
	}

	baseBorderPane.setCenter(highScoreGrid);
	GridPane buttonGrid = new GridPane();

	buttonGrid.add(btnErase, 0, 0);
	buttonGrid.add(btnOk, 1, 0);
	buttonGrid.setHgap(30);
	buttonGrid.setPadding(new Insets(20, 0, 0, 0));
	buttonGrid.setAlignment(Pos.CENTER);

	baseBorderPane.setBottom(buttonGrid);
	baseBorderPane.setPadding(new Insets(20));
	Scene scene = new Scene(baseBorderPane);
	stage.setScene(scene);
    }

    public void Show()
    {
	this.stage.show();
    }

    private void hide()
    {
	stage.hide();
    }

    public void SetTime(Label lblToSetTime, Label lblToAddName, int time)
    {
	new EnterYourName(lblToAddName);
	lblToSetTime.setText(Integer.toString(time) + " secondes");
    }

    public Label GetLblScoreBeginner()
    {
	return lblScoreBeginner;
    }

    public Label GetLblScoreInermediate()
    {
	return lblScoreIntermediate;
    }

    public Label GetLblScoreExpert()
    {
	return lblScoreExpert;
    }

    public Label GetLblNameBeginner()
    {
	return lblNameBeginner;
    }

    public Label GetLblNameInermediate()
    {
	return lblNameIntermediate;
    }

    public Label GetLblNameExpert()
    {
	return lblNameExpert;
    }

    public int GetTime(Label lblToGetTime)
    {
	String text = lblToGetTime.getText();
	if (text == DEFAULT_TIME_STRING)
	{
	    return Integer.MAX_VALUE;
	}
	else
	{
	    String textSplited[] = text.split(" ");
	    String time = textSplited[0];
	    return Integer.parseInt(time);
	}
    }
}
