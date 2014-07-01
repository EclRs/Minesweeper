
package minesweeper.mainscene.layout;

import java.io.InputStream;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import minesweeper.backend.Box;
import minesweeper.mainscene.MainScene;

public class Tile extends ImageView
{
    private static final String TILE_DEFAULT_BOMB = "../ressources/bomb.gif";
    private static final String TILE_RED_BOMB = "../ressources/bombTrapped.gif";
    private static final String TILE_QUESTION_IMAGE = "../ressources/question.gif";
    private static final String TILE_FLAG_IMAGE = "../ressources/flagged.gif";
    private ImageView tileImage;
    private MainScene scene;
    private int posX;
    private int posY;
    private final String TILE_DEFAULT_IMAGE = "../ressources/emptyUp.gif";
    private final String TILE_DOWN_IMAGE = "../ressources/emptyDown.gif";

    public Tile(GridPane contener, int positionX, int positionY, final MainScene scene)
    {
	this.scene = scene;
	this.posX = positionX;
	this.posY = positionY;

	InputStream s = getClass().getResourceAsStream(TILE_DEFAULT_IMAGE);
	Image image = new Image(s);
	tileImage = new ImageView();
	tileImage.setImage(image);

	tileImage.setOnMousePressed(new EventHandler<MouseEvent>()
	{

	    @Override
	    public void handle(MouseEvent arg0)
	    {
		if (Tile.this.scene.getGame().getBox(Tile.this.posX, Tile.this.posY).IsRevealed() == false && Tile.this.scene.getGame().getBox(Tile.this.posX, Tile.this.posY).IsFlagged() == false)
		{
		    Tile.this.changeImage(TILE_DOWN_IMAGE);
		}

	    }
	});
	tileImage.setOnMouseReleased(new EventHandler<MouseEvent>()
	{

	    @Override
	    public void handle(MouseEvent arg0)
	    {
		if (Tile.this.scene.getGame().getBox(Tile.this.posX, Tile.this.posY).IsRevealed() == false && Tile.this.scene.getGame().getBox(Tile.this.posX, Tile.this.posY).IsFlagged() == false)
		{
		    Tile.this.changeImage(TILE_DEFAULT_IMAGE);
		}

	    }
	});

	tileImage.setOnMouseClicked(new EventHandler<MouseEvent>()
	{
	    private boolean questionMark;

	    @Override
	    public void handle(MouseEvent arg0)
	    {
		if (Tile.this.scene.getGame().getBox(Tile.this.posX, Tile.this.posY).IsRevealed() == false)
		{
		    if (arg0.getButton() == MouseButton.SECONDARY)
		    {

			if (Tile.this.scene.getGame().getBox(Tile.this.posX, Tile.this.posY).IsFlagged() == false && questionMark == false)
			{
			    String ressource = TILE_FLAG_IMAGE;
			    Tile.this.changeImage(ressource);
			    Tile.this.scene.getGame().getBox(Tile.this.posX, Tile.this.posY).ChangeFlag();
			    Tile.this.scene.GetHeadUpDisplay().GetMineCounter().RemoveMineCounter();

			}
			else if (Tile.this.scene.getGame().getBox(Tile.this.posX, Tile.this.posY).IsFlagged() == true)
			{
			    String ressource = TILE_QUESTION_IMAGE;
			    Tile.this.changeImage(ressource);
			    Tile.this.scene.getGame().getBox(Tile.this.posX, Tile.this.posY).ChangeFlag();
			    questionMark = true;
			    Tile.this.scene.GetHeadUpDisplay().GetMineCounter().AddMineCounter();
			}
			else if (Tile.this.scene.getGame().getBox(Tile.this.posX, Tile.this.posY).IsFlagged() == false && questionMark == true)
			{
			    Tile.this.changeImage(TILE_DEFAULT_IMAGE);
			    questionMark = false;
			}
		    }

		    else
		    {
			if (Tile.this.scene.getGame().getBox(Tile.this.posX, Tile.this.posY).IsFlagged() == false)
			{
			    if (Tile.this.scene.getHasBeenClicked() == false) // first Click
			    {
				Tile.this.scene.getGame().firstAction(Tile.this.posX, Tile.this.posY);
				Tile.this.scene.setHasBeenClicked(true);
			    }
			    if (Tile.this.scene.getGame().getBox(Tile.this.posX, Tile.this.posY).IsMine() == false)
			    {
				int numberMineAdjacent = scene.getGame().getBox(Tile.this.posX, Tile.this.posY).GetNumberAdjacentMine();

				if (numberMineAdjacent == 0)
				{
				    emptyFlat(Tile.this.scene.getGame().getBox(Tile.this.posX, Tile.this.posY), Tile.this.posX, Tile.this.posY);

				}
				else
				{
				    String ressource = "../ressources/" + numberMineAdjacent + ".gif";
				    Tile.this.changeImage(ressource);
				    scene.RevealTile(Tile.this.posX, Tile.this.posY);
				}
			    }
			    else
			    {
				String ressource = TILE_RED_BOMB;
				Tile.this.changeImage(ressource);
				scene.RevealTile(Tile.this.posX, Tile.this.posY);
				Tile.this.scene.EndGame();

			    }
			}
			Tile.this.scene.getGame().Print();
		    }
		}
	    }

	});

	contener.add(tileImage, posX, posY);

    }

    private void emptyFlat(Box tileToVerify, int positionX, int positionY)
    {

	if (tileToVerify.IsMine() == false)
	{

	    int numberMineAdjacent = tileToVerify.GetNumberAdjacentMine();
	    if (numberMineAdjacent == 0)
	    {
		if (this.scene.GetGrid().GetTile(positionX, positionY).scene.getGame().getBox(positionX, positionY).IsRevealed() == false)
		{
		    String ressource = "../ressources/emptyFlat.gif";

		    this.scene.GetGrid().GetTile(positionX, positionY).changeImage(ressource);

		    this.scene.GetGrid().GetTile(positionX, positionY).scene.getGame().RevealBox(positionX, positionY);

		    if (positionY - 1 >= 0)
		    {
			emptyFlat(Tile.this.scene.getGame().getBox(positionX, positionY - 1), positionX, positionY - 1);
		    }

		    if (positionY - 1 >= 0 && positionX - 1 >= 0)
		    {
			emptyFlat(Tile.this.scene.getGame().getBox(positionX - 1, positionY - 1), positionX - 1, positionY - 1);
		    }

		    if (positionY - 1 >= 0 && positionX + 1 < Tile.this.scene.getGame().GetNumberBoxX())
		    {
			emptyFlat(Tile.this.scene.getGame().getBox(positionX + 1, positionY - 1), positionX + 1, positionY - 1);
		    }

		    if (positionX - 1 >= 0 && positionY + 1 < Tile.this.scene.getGame().GetNumberBoxY())
		    {
			emptyFlat(Tile.this.scene.getGame().getBox(positionX - 1, positionY + 1), positionX - 1, positionY + 1);
		    }

		    if (positionX + 1 < Tile.this.scene.getGame().GetNumberBoxX() && positionY + 1 < Tile.this.scene.getGame().GetNumberBoxY())
		    {
			emptyFlat(Tile.this.scene.getGame().getBox(positionX + 1, positionY + 1), positionX + 1, positionY + 1);
		    }

		    if (positionX - 1 >= 0)
		    {
			emptyFlat(Tile.this.scene.getGame().getBox(positionX - 1, positionY), positionX - 1, positionY);
		    }

		    if (positionX + 1 < Tile.this.scene.getGame().GetNumberBoxX())
		    {
			emptyFlat(Tile.this.scene.getGame().getBox(positionX + 1, positionY), positionX + 1, positionY);
		    }

		    if (positionY + 1 < Tile.this.scene.getGame().GetNumberBoxY())
		    {
			emptyFlat(Tile.this.scene.getGame().getBox(positionX, positionY + 1), positionX, positionY + 1);
		    }

		}
	    }
	    else
	    {
		String ressource = "../ressources/" + numberMineAdjacent + ".gif";
		this.scene.GetGrid().GetTile(positionX, positionY).changeImage(ressource);
		this.scene.GetGrid().GetTile(positionX, positionY).scene.getGame().RevealBox(positionX, positionY);
	    }
	}

    }

    public void changeImageForCheat()
    {
	this.changeImage(TILE_DEFAULT_BOMB);
    }

    private void changeImage(String ressource)
    {
	InputStream s = getClass().getResourceAsStream(ressource);
	Image image = new Image(s);

	this.tileImage.setImage(image);

	this.scene.GetGrid().SetTile(posX, posY, this);
    }

    public void changeImageBackFromCheat()
    {
	this.changeImage(TILE_DEFAULT_IMAGE);
    }

    public void changeImageForWin()
    {
	this.changeImage(TILE_FLAG_IMAGE);
    }
}
