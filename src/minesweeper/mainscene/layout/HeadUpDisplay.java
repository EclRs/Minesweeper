
package minesweeper.mainscene.layout;

import javafx.scene.layout.BorderPane;
import minesweeper.mainscene.MainScene;

public class HeadUpDisplay
{

    private TimerGame timer;
    private MineCounter mineCounter;
    private YellowGuy yellowGuy;

    public String identification()
    {
	return "Head Up Display";
    }

    public HeadUpDisplay(BorderPane contener, MainScene mainScene, int numberMine)
    {
	BorderPane headUpDisplay = new BorderPane();
	headUpDisplay.setStyle("-fx-background-color: lightgrey;");
	setTimer(new TimerGame(headUpDisplay, mainScene.GetStage()));

	mineCounter = new MineCounter(headUpDisplay, numberMine, mainScene);

	SetYellowGuy(new YellowGuy(headUpDisplay, mainScene));

	contener.setTop(headUpDisplay);
    }

    public YellowGuy GetYellowGuy()
    {
	return yellowGuy;
    }

    public void SetYellowGuy(YellowGuy yellowGuy)
    {
	this.yellowGuy = yellowGuy;
    }

    public void SetNumberMineControl(MineCounter mineCounter)
    {
	this.mineCounter = mineCounter;
    }

    public int GetNumberMine()
    {
	return this.mineCounter.GetNumberMine();
    }

    public MineCounter GetMineCounter()
    {
	return this.mineCounter;
    }

    public TimerGame GetTimer()
    {
	return timer;
    }

    private void setTimer(TimerGame timer)
    {
	this.timer = timer;
    }

}
