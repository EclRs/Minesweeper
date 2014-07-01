
package minesweeper.mainscene.layout;

import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class TimerGame
{
    private static final int MAXIMUM_TIMER_TICK = 999;
    private static final String TIMER_PREFIX = "Time : ";
    Stage stage;
    private int times;
    private Timer timerGameMine;

    public String identification()
    {
	return "Timer";
    }

    public TimerGame(BorderPane contener, Stage primaryStage)
    {
	this.stage = primaryStage;

	BorderPane timer = new BorderPane();
	Label lblTimerText = new Label(TIMER_PREFIX);
	final Label lblTimerNumber = new Label("0");
	timerGameMine = new Timer();
	timer.setPadding(new Insets(5, 2, 0, 0));
	this.times = 0;
	TimerTask addTime = new TimerTask()
	{

	    @Override
	    public void run()
	    {
		Platform.runLater(new Runnable()
		{
		    public void run()
		    {
			if (TimerGame.this.stage.isFocused())
			{

			    if (times > MAXIMUM_TIMER_TICK)
			    {
				TimerGame.this.StopTimer();
			    }
			    else
			    {
				lblTimerNumber.setText(String.valueOf(times));
			    }
			    times++;
			}
		    }
		});
	    }
	};
	timerGameMine.schedule(addTime, 0, 1 * 1000);
	timer.setLeft(lblTimerText);
	timer.setRight(lblTimerNumber);
	contener.setRight(timer);
    }

    public void StopTimer()
    {
	timerGameMine.cancel();
    }

    public int GetTime()
    {
	return this.times;
    }
}