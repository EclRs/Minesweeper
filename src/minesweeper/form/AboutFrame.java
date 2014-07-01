
package minesweeper.form;

import java.io.InputStream;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AboutFrame
{
    private static final String DESCRIPTION = "Application développé dans le cadre d'un projet scolaire.";
    private static final String REALISATION_DATE = "Réalisé en novembre 2013";
    private static final String REALISED_BY = "Par : ";
    private static final String NAME1 = "Élise Carbonneau-Leclerc";
    private static final String NAME2 = "Raphaël Sylvain";
    private static final String RESSOURCE_IMAGE = "../ressources/aboutImage.jpg";
    private static final String TITLE = "About...";

    public AboutFrame()
    {
	Label lblDescription = new Label(DESCRIPTION);
	Label lblRealisationDate = new Label(REALISATION_DATE);
	Label lblBy = new Label(REALISED_BY);
	Label lblCoderName1 = new Label(NAME1);
	Label lblCoderName2 = new Label(NAME2);

	InputStream s = getClass().getResourceAsStream(RESSOURCE_IMAGE);
	Image image = new Image(s);

	ImageView imageView = new ImageView(image);

	GridPane grid = new GridPane();

	grid.add(lblDescription, 0, 0);
	grid.add(lblRealisationDate, 0, 1);
	grid.add(lblBy, 0, 2);
	grid.add(lblCoderName1, 0, 3);
	grid.add(lblCoderName2, 0, 4);
	grid.add(imageView, 0, 5);

	Stage stage = new Stage();
	stage.setTitle(TITLE);
	stage.setResizable(false);
	Scene scene = new Scene(grid);

	stage.setScene(scene);

	stage.showAndWait();

    }
}
