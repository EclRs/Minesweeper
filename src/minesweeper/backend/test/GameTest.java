/**
 * 
 */
package minesweeper.backend.test;

import static org.junit.Assert.*;
import minesweeper.backend.Game;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Raphaël Sylvain
 * @author Élise Carbonneau-Leclerc
 * 
 */
public class GameTest 
{

	@Test
	public void ConstructorTest() 
	{
		Game validGame = new Game(9,9,10);
		Assert.assertTrue(validGame.GetNumberBoxX() == 9);
		Assert.assertTrue(validGame.GetNumberBoxY() == 9);
		Assert.assertTrue(validGame.GetNumberSafeBox() == (9*9) - 10);
		
		
		Game invalidGame = new Game(4,4,4);
		Assert.assertFalse(invalidGame.GetNumberBoxX() == 4);
		Assert.assertTrue(invalidGame.GetNumberBoxX() == 9);
		Assert.assertFalse(invalidGame.GetNumberBoxY() == 4);
		Assert.assertTrue(invalidGame.GetNumberBoxX() == 9);
		Assert.assertFalse(invalidGame.GetNumberSafeBox() == (4*4) - 4);
		Assert.assertTrue(invalidGame.GetNumberSafeBox() == (9*9) - 10);
		
		Game invalidGame2 = new Game(40,40,100);
		Assert.assertFalse(invalidGame2.GetNumberBoxX() == 40);
		Assert.assertTrue(invalidGame2.GetNumberBoxX() == 30);
		Assert.assertFalse(invalidGame2.GetNumberBoxY() == 40);
		Assert.assertTrue(invalidGame2.GetNumberBoxX() == 24);
		Assert.assertFalse(invalidGame2.GetNumberSafeBox() == (40*40) - 100);
		Assert.assertTrue(invalidGame2.GetNumberSafeBox() == (30*24) - 667);

	}
	
	
	@Test
	public void GameCompletedTest() 
	{
		Game game = new Game(9,9,10);
		Assert.assertFalse(game.GameCompleted());
		Assert.fail("Not yet implemented");
	}
	
	
	@Test
	public void RevealBoxTest() 
	{
		Assert.fail("Not yet implemented");
	}
	
	@Test
	public void PrintTest() 
	{
		Assert.fail("Not yet implemented");
	}

}
