
package minesweeper.mainscene.layout;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import minesweeper.mainscene.MainScene;

public class MineCounter
{
    private int nbMine;
    BorderPane contener;
    MainScene scene;
    private Label lblMineNumber;

    public String identification()
    {
	return "Mine Counter";
    }

    public MineCounter(BorderPane contener, int mine, MainScene scene)
    {
	this.contener = contener;
	this.scene = scene;

	BorderPane mineCounter = new BorderPane();
	Label lblMineText = new Label("Mine : ");
	lblMineNumber = new Label(Integer.toString(mine));
	lblMineNumber.setId("mineNumber");

	setNbMine(mine);

	mineCounter.setLeft(lblMineText);
	mineCounter.setRight(lblMineNumber);

	mineCounter.setPadding(new Insets(5, 0, 0, 2));
	this.contener.setLeft(mineCounter);
    }

    public void AddMineCounter()
    {
	this.setNbMine(getNbMine() + 1);
	lblMineNumber.setText(Integer.toString(getNbMine()));

    }

    public void RemoveMineCounter()
    {
	this.setNbMine(getNbMine() - 1);
	lblMineNumber.setText(Integer.toString(getNbMine()));

    }

    public int GetNumberMine()
    {
	return (Integer.parseInt(this.lblMineNumber.getText()));
    }

    public int getNbMine()
    {
	return this.nbMine;
    }

    public void setNbMine(int nbMine)
    {
	this.nbMine = nbMine;
    }

}
