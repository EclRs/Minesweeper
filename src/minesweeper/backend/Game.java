
package minesweeper.backend;

public class Game
{
    private Box arrayBox[][];
    private int numberBoxX;
    private int numberBoxY;
    private int numberBoxRevealed;
    private int numberSafeBox;
    private int tempMineX;
    private int tempMineY;

    private final static int MINIMUM_MINE_NUMBER = 10;
    private final static int MINIMUM_GRID_WIDTH = 9;
    private final static int MINIMUM_GRID_HEIGHT = 9;
    private final static int MAXIMUM_GRID_WIDTH = 30;
    private final static int MAXIMUM_GRID_HEIGHT = 24;

    public Game(int numberX, int numberY, int numberMine)
    {

	if (numberMine < MINIMUM_MINE_NUMBER)
	{
	    numberMine = MINIMUM_MINE_NUMBER;
	}
	// Maximum mine number
	if (numberMine > numberY + numberX + 1)
	{
	    numberMine = numberY + numberX + 1;
	}

	if (numberX < MINIMUM_GRID_WIDTH)
	{
	    numberX = MINIMUM_GRID_WIDTH;
	}

	if (numberY < MINIMUM_GRID_HEIGHT)
	{
	    numberY = MINIMUM_GRID_HEIGHT;
	}

	if (numberX > MAXIMUM_GRID_WIDTH)
	{
	    numberX = MAXIMUM_GRID_WIDTH;
	}

	if (numberY > MAXIMUM_GRID_HEIGHT)
	{
	    numberY = MAXIMUM_GRID_HEIGHT;
	}

	this.numberBoxX = numberX;
	this.numberBoxY = numberY;
	this.numberSafeBox = (numberX * numberY) - numberMine;
	int numberMinePlaced = 0;

	this.arrayBox = new Box[numberX][numberY];
	for (int i = 0; i < numberX; i++)
	{
	    for (int j = 0; j < numberY; j++)
	    {
		arrayBox[i][j] = new Box();
	    }
	}

	while (numberMinePlaced != numberMine)
	{
	    int randomX = (int) (Math.random() * this.numberBoxX);
	    int randomY = (int) (Math.random() * this.numberBoxY);
	    if (this.arrayBox[randomX][randomY].SetHasMine(true))
	    {
		numberMinePlaced++;
	    }
	}

	// Temporary Mine
	do // We find a suitable place for the tempMine;
	{
	    setTempMineX((int) (Math.random() * this.numberBoxX));
	    setTempMineY((int) (Math.random() * this.numberBoxY));
	}
	while (!this.arrayBox[getTempMineX()][getTempMineY()].SetHasMine(true));

	// We don't want the tempmine to be considered as a mine at the beginning.
	this.arrayBox[getTempMineX()][getTempMineY()].SetHasMine(false);
    }

    public void SetNumberAdjacentMine()
    {
	for (int boxPositionX = 0; boxPositionX < this.numberBoxX; boxPositionX++)
	{
	    for (int boxPositionY = 0; boxPositionY < this.numberBoxY; boxPositionY++)
	    {
		if (arrayBox[boxPositionX][boxPositionY].IsMine())
		{
		    for (int translationX = -1; translationX <= 1; translationX++)
		    {
			for (int translationY = -1; translationY <= 1; translationY++)
			{
			    if (translationX != 0 || translationY != 0)
			    {
				int boxAdjacentMineX = translationX + boxPositionX;
				if (boxAdjacentMineX >= 0 && boxAdjacentMineX < this.numberBoxX)
				{
				    int boxAdjacentMineY = translationY + boxPositionY;
				    if (boxAdjacentMineY >= 0 && boxAdjacentMineY < this.numberBoxY)
				    {
					int currentAdjacentMine = arrayBox[boxAdjacentMineX][boxAdjacentMineY].GetNumberAdjacentMine();
					arrayBox[boxAdjacentMineX][boxAdjacentMineY].SetNumberAdjacentMine(currentAdjacentMine + 1);
				    }
				}
			    }

			}
		    }
		}
	    }
	}

    }

    public void firstAction(int firstBoxX, int firstBoxY)
    {
	if (this.arrayBox[firstBoxX][firstBoxY].IsMine()) // First click on a mine
	{
	    this.arrayBox[firstBoxX][firstBoxY].SetHasMine(false);
	    this.arrayBox[this.tempMineX][this.tempMineY].SetHasMine(true);
	}
	if (this.arrayBox[firstBoxX][firstBoxY].GetNumberAdjacentMine() != 0)
	{
	    this.RevealBox(firstBoxX, firstBoxY);
	}
	this.SetNumberAdjacentMine();
    }

    public int GetNumberSafeBox()
    {
	return this.numberSafeBox;
    }

    private int GetNumberBoxRevealed()
    {
	return this.numberBoxRevealed;
    }

    public Boolean GameCompleted()
    {
	if (GetNumberSafeBox() == GetNumberBoxRevealed())
	{
	    return true;
	}
	else
	{
	    return false;
	}
    }

    public Boolean RevealBox(int positionX, int positionY)
    {
	if (this.arrayBox[positionX][positionY].Reveal())
	{
	    this.numberBoxRevealed++;
	    return true;
	}
	else
	{
	    return false;
	}
    }

    public void Print()
    {
	for (int y = 0; y < numberBoxY; y++)
	{
	    for (int x = 0; x < numberBoxX; x++)
	    {
		System.out.print(arrayBox[x][y].Print());
	    }
	    System.out.println();
	}
    }

    public int GetNumberBoxX()
    {
	return this.numberBoxX;
    }

    public int GetNumberBoxY()
    {
	return this.numberBoxY;
    }

    public Box getBox(int positionX, int positionY)
    {
	return this.arrayBox[positionX][positionY];
    }

    private int getTempMineX()
    {
	return tempMineX;
    }

    private void setTempMineX(int tempMineX)
    {
	this.tempMineX = tempMineX;
    }

    private int getTempMineY()
    {
	return tempMineY;
    }

    private void setTempMineY(int tempMineY)
    {
	this.tempMineY = tempMineY;
    }
}