
package minesweeper.mainscene.layout;

import java.io.InputStream;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import minesweeper.mainscene.MainScene;

public class YellowGuy extends ImageView
{

    private ImageView btnYellowGuy;
    private MainScene scene;

    private static final String DEFAULT_IMAGE_RESSOURCE_PATH = "../ressources/yellow_guy_smile.gif";
    private static final String PRESSED_IMAGE_RESSOURCE_PATH = "../ressources/yellow_guy_smile_pressed.gif";
    private static final String WORRY_IMAGE_RESSOURCE_PATH = "../ressources/yellow_guy_worry.gif";
    private static final String LOSS_IMAGE_RESSOURCE_PATH = "../ressources/yellow_guy_dead.gif";
    private static final String WIN_IMAGE_RESSOURCE_PATH = "../ressources/yellow_guy_win.gif";

    public String identification()
    {
	return "Yellow Guy";
    }

    public YellowGuy(BorderPane contener, final MainScene mainScene)
    {
	this.scene = mainScene;

	InputStream image = getClass().getResourceAsStream(DEFAULT_IMAGE_RESSOURCE_PATH);
	Image yellowGuy = new Image(image);

	btnYellowGuy = new ImageView();
	btnYellowGuy.setImage(yellowGuy);
	btnYellowGuy.setOnMousePressed(new EventHandler<MouseEvent>()
	{
	    @Override
	    public void handle(MouseEvent me)
	    {
		YellowGuy.this.changeImage(PRESSED_IMAGE_RESSOURCE_PATH);
	    }
	});

	btnYellowGuy.setOnMouseReleased(new EventHandler<MouseEvent>()
	{
	    @Override
	    public void handle(MouseEvent me)
	    {
		YellowGuy.this.changeImage(DEFAULT_IMAGE_RESSOURCE_PATH);
	    }
	});

	btnYellowGuy.setOnMouseClicked(new EventHandler<MouseEvent>()
	{
	    @Override
	    public void handle(MouseEvent me)
	    {
		YellowGuy.this.changeImage(DEFAULT_IMAGE_RESSOURCE_PATH);
		mainScene.restartScene();
	    }
	});
	YellowGuy.this.scene.GetRoot().setOnMousePressed(new EventHandler<MouseEvent>()
	{
	    @Override
	    public void handle(MouseEvent me)
	    {
		if (me.getTarget() != btnYellowGuy)
		{
		    YellowGuy.this.changeImage(WORRY_IMAGE_RESSOURCE_PATH);
		}
	    }
	});

	YellowGuy.this.scene.GetRoot().setOnMouseReleased(new EventHandler<MouseEvent>()
	{
	    @Override
	    public void handle(MouseEvent arg0)
	    {
		YellowGuy.this.changeImage(DEFAULT_IMAGE_RESSOURCE_PATH);
	    }
	});

	contener.setCenter(btnYellowGuy);

    }

    public void changeImage(String ressource)
    {
	InputStream image = getClass().getResourceAsStream(ressource);
	Image yellowGuy = new Image(image);
	this.btnYellowGuy.setImage(yellowGuy);
	this.scene.GetHeadUpDisplay().SetYellowGuy(YellowGuy.this);
    }

    public void changeImageLoss()
    {
	this.changeImage(LOSS_IMAGE_RESSOURCE_PATH);
    }

    public void changeImageWin()
    {
	this.changeImage(WIN_IMAGE_RESSOURCE_PATH);
    }

}
