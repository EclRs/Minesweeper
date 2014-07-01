
package minesweeper.form;

import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import minesweeper.mainscene.MainScene;

public class AppFrame extends Application
{
    public static final String TITLE = "Démineur par Élise C.-L. et Raphaël S.";
    public static final int DEFAULT_LOCATION_X = 100;
    public static final int DEFAULT_LOCATION_Y = 100;

    @Override
    public void start(Stage primaryStage)
    {
	primaryStage.setTitle(TITLE);
	primaryStage.setX(DEFAULT_LOCATION_X);
	primaryStage.setX(DEFAULT_LOCATION_Y);
	primaryStage.setResizable(false);

	if (MainScene.SetInstance(primaryStage, new BorderPane()))
	{
	    primaryStage.setScene(MainScene.GetInstance());
	}
	primaryStage.show();
    }
}
