
package minesweeper.form;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class HelpFrame
{
    private static final String TITLE = "Aide";
    private static final String RULE1 = "1. Cliquer sur une case pour débuter la partie.";
    private static final String FONT = "Consolas";
    private static final String RULE_HEADER = "Règles de jeu : ";
    private static final String RULE2 = "2. Le nombre dans une case indique le nombre de mine adjacentes à cette case.";
    private static final String RULE3 = "3. Si vous découvrez une mine, la partie se termine.";
    private static final String RULE4 = "4. Pour gagner une partie, vous devez découvrir toutes les cases ne contenant pas de mines.";
    private static final String RULE5 = "5. Vous pouvez utilser le clique droit pour marquer une case comme une mine. Un fanion remplacera cette case.";

    public HelpFrame()
    {
	Stage stage = new Stage();

	GridPane grid = new GridPane();

	Label titleRules = new Label(RULE_HEADER);
	titleRules.setFont(new Font(FONT, 15));

	Label rule1 = new Label(RULE1);
	rule1.setFont(new Font(FONT, 10));

	Label rule2 = new Label(RULE2);
	rule2.setFont(new Font(FONT, 10));

	Label rule3 = new Label(RULE3);
	rule3.setFont(new Font(FONT, 10));

	Label rule4 = new Label(RULE4);
	rule4.setFont(new Font(FONT, 10));

	Label rule5 = new Label(RULE5);
	rule5.setFont(new Font(FONT, 10));

	grid.add(titleRules, 1, 0);
	grid.add(rule1, 1, 1);
	grid.add(rule2, 1, 2);
	grid.add(rule3, 1, 3);
	grid.add(rule4, 1, 4);
	grid.add(rule5, 1, 5);

	Scene scene = new Scene(grid);

	stage.setScene(scene);
	stage.setResizable(false);
	stage.setTitle(TITLE);

	stage.showAndWait();

    }
}
