
package minesweeper.backend;

public class Box
{
    private boolean hasMine;
    private int numberAdjacentMine;
    private boolean revealed;
    private boolean flagged;
    private static final String PRINT_BOMB = "X";
    private static final String PRINT_NOT_BOMB = "O";

    public Box()
    {
	this.hasMine = false;
	this.flagged = false;
	this.revealed = false;
	this.numberAdjacentMine = 0;
    }

    public boolean SetHasMine(boolean hasMine)
    {
	if (hasMine == true)
	{
	    if (this.hasMine == true)
	    {
		return false;
	    }
	}
	this.hasMine = hasMine;
	return true;
    }

    public boolean ChangeFlag()
    {
	this.flagged = !flagged; // Set to the opposite state.
	return this.flagged;
    }

    public boolean Reveal()
    {
	if (!this.IsFlagged() && !this.IsRevealed())
	{
	    this.revealed = true;
	    return true;
	}
	else
	{
	    return false;
	}

    }

    public int GetNumberAdjacentMine()
    {
	return this.numberAdjacentMine;
    }

    public boolean SetNumberAdjacentMine(int numberMine)
    {
	if (numberMine < 0 || numberMine > 8)
	{
	    return false;
	}
	else
	{
	    this.numberAdjacentMine = numberMine;
	    return true;
	}
    }

    public String Print()
    {
	if (this.IsMine())
	{
	    return PRINT_BOMB;
	}
	return PRINT_NOT_BOMB;
    }

    public boolean IsMine()
    {
	return this.hasMine;
    }

    public boolean IsFlagged()
    {
	return this.flagged;
    }

    public boolean IsRevealed()
    {
	return this.revealed;
    }

    public void setRevealed(boolean revealed)
    {
	this.revealed = revealed;
    }

}
