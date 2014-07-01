
package minesweeper.mainscene;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import minesweeper.backend.Game;
import minesweeper.form.AboutFrame;
import minesweeper.form.HelpFrame;
import minesweeper.form.HighestScoreFrame;
import minesweeper.interfaces.Identifier;
import minesweeper.mainscene.layout.Grid;
import minesweeper.mainscene.layout.HeadUpDisplay;

/**
 * La classe MainScene est une classe utilisée pour créer le menu et mettre en commun les autres classes.
 * 
 * @author Raphaël Sylvain
 * @author Élise Carbonneau-Leclerc
 * @since 1.0
 * @see Game
 * @see HeadUpDisplay
 * @see HighestScoreFrame
 * @see HelpFrame
 * @see AboutFrame
 * @see Grid
 * 
 */
public class MainScene extends Scene implements Identifier
{

    private static final String HELP_MENU_TITLE = "?";
    private static final String HELP_MENU_ABOUT_ITEM = "À Propos...";
    private static final String HELP_MENU_HELP_ITEM = "Aide";

    private static final String NEW_GAME_SHORTCUT = "F2";

    private static final String GAME_MENU_TITLE = "Partie";
    private static final String GAME_MENU_NEW_GAME_ITEM = "Nouveau";
    private static final String GAME_MENU_BEGINNER_ITEM = "Débutant";
    private static final String GAME_MENU_INTEMEDIATE_ITEM = "Intermédiare";
    private static final String GAME_MENU_EXPERT_ITEM = "Avancé";
    private static final String GAME_MENU_CHEAT_ITEM = "Tricher";
    private static final String GAME_MENU_HIGHSCORE_ITEM = "Meilleurs temps...";

    private static final int NUMBER_TILE_X_BEGINNER = 9;
    private static final int NUMBER_TILE_Y_BEGINNER = 9;
    private static final int NUMBER_MINE_BEGINNER = 10;

    private static final int NUMBER_TILE_X_INTERMED = 16;
    private static final int NUMBER_TILE_Y_INTERMED = 16;
    private static final int NUMBER_MINE_INTERMED = 40;

    private static final int NUMBER_TILE_X_EXPERT = 30;
    private static final int NUMBER_TILE_Y_EXPERT = 16;
    private static final int NUMBER_MINE_EXPERT = 99;

    private static final String DIFFICULTY_1 = "Beginner";
    private static final String DIFFICULTY_2 = "Intermediate";

    private Game game;
    Stage primaryStage;
    private BorderPane root;
    private MenuBar menuBar;

    private static MainScene instance = null;
    private BorderPane gameZone;
    private HeadUpDisplay headUpDisplay;
    private Grid grid;
    private boolean hasBeenClicked = false;
    private int gameNumberTileY;
    private int gameNumberTileX;
    private int gameNumberMine;
    private RadioMenuItem rdbCheat;

    private String difficulty = DIFFICULTY_1;
    private HighestScoreFrame highestScoreFrame;

    /**
     * La méthode qui permet d'instancier Mainscene.
     * 
     * @param Stage
     *            le «Stage» principal de la page de l'application
     * @param BorderPane
     *            le «BorderPane» principal de la page de l'application
     * @return boolean «False» si l'instance était déjà faite, «True» si l'on a effectué une nouvelle instance.
     * @see MainScene(Stage, BorderPane)
     */
    public static boolean SetInstance(Stage primaryStage, BorderPane root)
    {
	if (MainScene.instance == null)
	{
	    MainScene.instance = new MainScene(primaryStage, root);
	    return true;
	}
	return false;
    }

    /**
     * La méthode qui permet de créer une nouvelle partie lorsque nécessaire.
     * 
     * @see MainScene#doGameZone(int, int, int)
     */
    public void restartScene()
    {
	this.doGameZone(this.gameNumberTileX, this.gameNumberTileY, this.gameNumberMine);

    }

    /**
     * Le constructeur de la classe MainScene. Crée le menu et une partie de niveau débutant.
     * 
     * @param Stage
     *            le «Stage» principal de la page de l'application
     * @param BorderPane
     *            le «BorderPane» principal de la page de l'application
     * @see MainScene#doGameZone(int, int, int)
     * @see MainScene#BuildMenu()
     */
    private MainScene(Stage primaryStage, BorderPane root)
    {

	super(root);
	this.root = root;
	this.primaryStage = primaryStage;
	BuildMenu();
	BorderPane topBorderPane = new BorderPane();
	topBorderPane.setTop(menuBar);
	this.root.setTop(topBorderPane);

	primaryStage.setResizable(false);

	this.doGameZone(NUMBER_TILE_X_BEGINNER, NUMBER_TILE_Y_BEGINNER, NUMBER_MINE_BEGINNER);

	highestScoreFrame = new HighestScoreFrame();

    }

    /**
     * La méthode qui permet de créer une nouvelle partie selon les informations en paramètre.
     * 
     * @param int Nombre de case sur l'axe des X
     * @param int Nombre de case sur l'axe des y
     * @param int Nombre de mine de la partie
     * @see Game#Game(int, int, int)
     * @see HeadUpDisplay#HeadUpDisplay(BorderPane, MainScene, int)
     * @see Grid#Grid(BorderPane, int, int, MainScene)
     */
    private void doGameZone(int numberTileX, int numberTileY, int numberMine)
    {

	this.gameNumberTileX = numberTileX;
	this.gameNumberTileY = numberTileY;
	this.gameNumberMine = numberMine;
	game = new Game(numberTileX, numberTileY, numberMine);
	game.Print();
	this.root.setCenter(null);
	gameZone = new BorderPane();
	gameZone.setStyle("-fx-background-color: lightgrey;");
	gameZone.setPadding(new Insets(10));

	setHeadUpDisplay(new HeadUpDisplay(gameZone, this, gameNumberMine));
	setGrid(new Grid(gameZone, numberTileX, numberTileY, this));
	this.root.setCenter(gameZone);
	this.primaryStage.sizeToScene();
	this.setHasBeenClicked(false);
	this.GetHeadUpDisplay().GetMineCounter().setNbMine(numberMine);
	rdbCheat.setSelected(false);
    }

    /**
     * La méthode pour obtenir l'instance de MainScene
     * 
     * @return MainScene L'instance de MainScene
     */
    public static MainScene GetInstance()
    {
	return MainScene.instance;
    }

    /**
     * La méthode pour indentifier MainScene
     * 
     * @see Identifier
     * @return String le nom représentant MainScene
     */
    public String identification()
    {
	return "MainScene";
    }

    /**
     * La méthode qui crée le menu «Partie» et «?» ainsi que les événements des options.
     * 
     * @see MainScene#doGameZone(int, int, int)
     * @see MainScene#showMine()
     * @see AboutFrame#AboutFrame()
     * @see HelpFrame#HelpFrame()
     * @see HighestScoreFrame#HighestScoreFrame()
     */
    private void BuildMenu()
    {
	Menu menuPartie = new Menu(GAME_MENU_TITLE);
	ToggleGroup toggleGroup = new ToggleGroup();

	final RadioMenuItem rdbIntermed = new RadioMenuItem(GAME_MENU_INTEMEDIATE_ITEM);
	final RadioMenuItem rdbExpert = new RadioMenuItem(GAME_MENU_EXPERT_ITEM);

	MenuItem newGame = new MenuItem(GAME_MENU_NEW_GAME_ITEM);
	newGame.setOnAction(new EventHandler<ActionEvent>()
	{
	    @Override
	    public void handle(ActionEvent arg0)
	    {
		if (rdbIntermed.isSelected() == true)
		{
		    MainScene.this.doGameZone(NUMBER_TILE_X_INTERMED, NUMBER_TILE_Y_INTERMED, NUMBER_MINE_INTERMED);
		}
		else if (rdbExpert.isSelected() == true)
		{
		    MainScene.this.doGameZone(NUMBER_TILE_X_EXPERT, NUMBER_TILE_Y_EXPERT, NUMBER_MINE_EXPERT);
		}
		else
		{
		    MainScene.this.doGameZone(NUMBER_TILE_X_BEGINNER, NUMBER_TILE_Y_BEGINNER, NUMBER_MINE_BEGINNER);
		}
	    }
	});
	newGame.setAccelerator(KeyCombination.keyCombination(NEW_GAME_SHORTCUT));

	RadioMenuItem rdbBeginner = new RadioMenuItem(GAME_MENU_BEGINNER_ITEM);
	rdbBeginner.setOnAction(new EventHandler<ActionEvent>()
	{
	    @Override
	    public void handle(ActionEvent arg0)
	    {
		MainScene.this.doGameZone(NUMBER_TILE_X_BEGINNER, NUMBER_TILE_Y_BEGINNER, NUMBER_MINE_BEGINNER);
	    }
	});
	rdbBeginner.setToggleGroup(toggleGroup);

	rdbIntermed.setOnAction(new EventHandler<ActionEvent>()
	{
	    @Override
	    public void handle(ActionEvent arg0)
	    {
		MainScene.this.doGameZone(NUMBER_TILE_X_INTERMED, NUMBER_TILE_Y_INTERMED, NUMBER_MINE_INTERMED);
	    }
	});
	rdbIntermed.setToggleGroup(toggleGroup);

	rdbExpert.setOnAction(new EventHandler<ActionEvent>()
	{
	    @Override
	    public void handle(ActionEvent arg0)
	    {
		MainScene.this.doGameZone(NUMBER_TILE_X_EXPERT, NUMBER_TILE_Y_EXPERT, NUMBER_MINE_EXPERT);
	    }
	});
	rdbExpert.setToggleGroup(toggleGroup);

	rdbCheat = new RadioMenuItem(GAME_MENU_CHEAT_ITEM);
	rdbCheat.setOnAction(new EventHandler<ActionEvent>()
	{
	    @Override
	    public void handle(ActionEvent arg0)
	    {
		MainScene.this.rdbCheat.setSelected(MainScene.this.rdbCheat.isSelected());

		if (MainScene.this.rdbCheat.isSelected() == true)
		    MainScene.this.showMine();

		else
		{
		    for (int x = 0; x < MainScene.this.gameNumberTileX; x++)
		    {
			for (int y = 0; y < MainScene.this.gameNumberTileY; y++)
			{
			    if (MainScene.this.game.getBox(x, y).IsMine())
			    {
				if (MainScene.this.game.getBox(x, y).IsRevealed() == false)
				{
				    MainScene.this.GetGrid().GetTile(x, y).changeImageBackFromCheat();
				}
			    }
			}
		    }
		}
	    }
	});

	MenuItem highScores = new MenuItem(GAME_MENU_HIGHSCORE_ITEM);
	highScores.setOnAction(new EventHandler<ActionEvent>()
	{
	    @Override
	    public void handle(ActionEvent arg0)
	    {
		highestScoreFrame.Show();
	    }
	});

	menuPartie.getItems().addAll(newGame, new SeparatorMenuItem(), rdbBeginner, rdbIntermed, rdbExpert, new SeparatorMenuItem(), rdbCheat, new SeparatorMenuItem(), highScores);

	Menu menuQuestionMark = new Menu(HELP_MENU_TITLE);

	MenuItem about = new MenuItem(HELP_MENU_ABOUT_ITEM);
	about.setOnAction(new EventHandler<ActionEvent>()
	{
	    @Override
	    public void handle(ActionEvent arg0)
	    {
		new AboutFrame();
	    }
	});
	MenuItem help = new MenuItem(HELP_MENU_HELP_ITEM);
	help.setOnAction(new EventHandler<ActionEvent>()
	{

	    @Override
	    public void handle(ActionEvent arg0)
	    {
		new HelpFrame();
	    }
	});
	menuQuestionMark.getItems().addAll(about, help);

	this.menuBar = new MenuBar();
	this.menuBar.getMenus().addAll(menuPartie, menuQuestionMark);
    }

    /**
     * La méthode pour obtenir le «root», le «BorderPane» principal de la page
     * 
     * @return BorderPane le «BorderPane» principal de la page, root
     */
    public BorderPane GetRoot()
    {
	return this.root;
    }

    /**
     * La méthode pour obtenir le «BorderPane» de la zone de jeu
     * 
     * @return BorderPane le «BorderPane» de la zone de jeu
     */
    public BorderPane GetGameZone()
    {
	return this.gameZone;
    }

    /**
     * La méthode pour obtenir le «HeadUpDisplay», le menu, du jeu courant.
     * 
     * @return HeadUpDisplay
     */
    public HeadUpDisplay GetHeadUpDisplay()
    {
	return headUpDisplay;
    }

    /**
     * La méthode pour obtenir le «Stage» principal de l'application
     * 
     * @return Stage le «Stage» principal de l'application
     */
    public Stage GetStage()
    {
	return this.primaryStage;
    }

    /**
     * La méthode pour modifier le «HeadUpDisplay» de la fenêtre
     * 
     * @param HeadUpDisplay
     *            Le nouveau «HeadUpDisplay»
     */
    private void setHeadUpDisplay(HeadUpDisplay headUpDisplay)
    {
	this.headUpDisplay = headUpDisplay;
    }

    /**
     * La méthode pour obtenir le grille de jeu
     * 
     * @return Grid la grille de jeu
     */
    public Grid GetGrid()
    {
	return grid;
    }

    /**
     * La méthode pour modifier la grille de jeu
     * 
     * @param Grid
     *            la nouvelle grille de jeu
     */
    private void setGrid(Grid grid)
    {
	this.grid = grid;
    }

    /**
     * La méthode qui permet de révéler une case du jeu. Elle permet également de gérer si la partie est terminée.
     * 
     * @param int position sur l'axe des X où la case se situe
     * @param int position sur l'axe des Y où la case se situe
     * @return boolean «True» si la case a pu être révélée, «False» sinon.
     */
    public boolean RevealTile(int positionX, int positionY)
    {
	boolean TileRevealed = this.game.RevealBox(positionX, positionY);
	if (this.game.GameCompleted() == true)
	{
	    this.GetHeadUpDisplay().GetYellowGuy().changeImageWin();
	    this.showFlag();
	    this.headUpDisplay.GetTimer().StopTimer();
	    this.grid.setDisable(true);
	    int time = this.headUpDisplay.GetTimer().GetTime();
	    int bestTime;
	    Label labelScoreToCheck;
	    Label labelNameToCheck;
	    if (this.difficulty == DIFFICULTY_1)
	    {

		labelScoreToCheck = highestScoreFrame.GetLblScoreBeginner();
		labelNameToCheck = highestScoreFrame.GetLblNameBeginner();
	    }
	    else if (this.difficulty == DIFFICULTY_2)
	    {
		labelScoreToCheck = highestScoreFrame.GetLblScoreInermediate();
		labelNameToCheck = highestScoreFrame.GetLblNameInermediate();
	    }
	    else
	    {
		labelScoreToCheck = highestScoreFrame.GetLblScoreExpert();
		labelNameToCheck = highestScoreFrame.GetLblNameExpert();
	    }
	    bestTime = highestScoreFrame.GetTime(labelScoreToCheck);

	    if (time < bestTime)
	    {
		highestScoreFrame.SetTime(labelScoreToCheck, labelNameToCheck, time);
		highestScoreFrame.Show();
	    }

	}
	return TileRevealed;
    }

    /**
     * La méthode pour obtenir la partie courante.
     * 
     * @return Game la partie courante
     */
    public Game getGame()
    {
	return this.game;
    }

    /**
     * La méthode pour obtenir l'information si l'utilisateur a effectué le premier «click» du jeu.
     * 
     * @return boolean «True» si le premier «click» a été fait, «False» sinon.
     */
    public boolean getHasBeenClicked()
    {
	return hasBeenClicked;
    }

    /**
     * La méthode pour modifier l'information si l'utilisateur a effectué le premier «click» du jeu.
     * 
     * @param boolean «True» si le premier «click» a été fait, «False» sinon.
     */
    public void setHasBeenClicked(boolean hasBeenClicked)
    {
	this.hasBeenClicked = hasBeenClicked;
    }

    /**
     * La méthode qui permet d'afficher toutes les mines du jeu.
     */
    private void showMine()
    {
	for (int x = 0; x < MainScene.this.gameNumberTileX; x++)
	{
	    for (int y = 0; y < MainScene.this.gameNumberTileY; y++)
	    {
		if (MainScene.this.game.getBox(x, y).IsMine() && !MainScene.this.game.getBox(x, y).IsRevealed())
		{
		    MainScene.this.GetGrid().GetTile(x, y).changeImageForCheat();
		}
		else if (MainScene.this.game.getBox(x, y).IsFlagged() && !MainScene.this.game.getBox(x, y).IsRevealed())
		{
		    MainScene.this.GetGrid().GetTile(x, y).changeImageForCheat();
		}

	    }
	}
    }

    /**
     * La méthode qui gère quand une partie est perdue.
     */
    public void EndGame()
    {
	this.showMine();
	this.headUpDisplay.GetYellowGuy().changeImageLoss();
	this.headUpDisplay.GetTimer().StopTimer();
	this.grid.setDisable(true);
    }

    /**
     * La méthode qui permet d'afficher toutes les emplacements de toute les mines du jeu par des fanions.
     */
    private void showFlag()
    {
	for (int x = 0; x < MainScene.this.gameNumberTileX; x++)
	{
	    for (int y = 0; y < MainScene.this.gameNumberTileY; y++)
	    {
		if (MainScene.this.game.getBox(x, y).IsMine() && !MainScene.this.game.getBox(x, y).IsRevealed())
		{
		    MainScene.this.GetGrid().GetTile(x, y).changeImageForWin();
		}
	    }
	}
    }
}