
package minesweeper.mainscene.layout;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import minesweeper.mainscene.MainScene;

public class Grid extends GridPane
{
    Tile[][] tileArray;

    public String identification()
    {
	return "Game Grid";
    }

    public Grid(BorderPane contener, int numberX, int numberY, MainScene scene)
    {
	super();
	this.tileArray = new Tile[numberX][numberY];

	this.setStyle("-fx-background-color: lightgrey;");

	for (int y = 0; y < numberY; y++)
	{
	    for (int x = 0; x < numberX; x++)
	    {
		this.SetTile(x, y, new Tile(this, x, y, scene));

	    }
	}
	this.setAlignment(Pos.CENTER);
	this.setPadding(new Insets(10, 0, 0, 0));
	contener.setCenter(this);
    }

    public void SetTile(int posX, int posY, Tile newTile)
    {
	this.tileArray[posX][posY] = newTile;

    }

    public Tile GetTile(int posX, int posY)
    {
	return this.tileArray[posX][posY];
    }

}
