
package minesweeper.backend.test;

import static org.junit.Assert.*;
import minesweeper.backend.Box;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Raphaël Sylvain
 * @author Élise Carbonneau-Leclerc
 * 
 */
public class BoxTest 
{

	@Test
	public void ConstructorTest() 
	{
		Box box = new Box();
		Assert.assertNotNull(box);
	}
	
	@Test
	public void SetHasMineTest() 
	{
		Box box = new Box();
		
		Assert.assertTrue(box.SetHasMine(true));
		Assert.assertFalse(box.SetHasMine(false));
	}
	
	@Test 
	public void IsMineTest()
	{
		Box box = new Box();
		
		Assert.assertFalse(box.IsMine());
		box.SetHasMine(true);
		Assert.assertTrue(box.IsMine());
		box.SetHasMine(false);
		Assert.assertFalse(box.IsMine());

	}
	
	@Test
	public void ChangeFlagTest() 
	{
		Box box = new Box();
		// From not flagged to flagged.
		Assert.assertTrue(box.ChangeFlag());
		// From flagged to not flagged.
		Assert.assertFalse(box.ChangeFlag());
		
	}
	
	@Test
	public void IsFlaggedTest()
	{
		Box box = new Box();
		
		Assert.assertFalse(box.IsFlagged());
		box.ChangeFlag();
		Assert.assertTrue(box.IsFlagged());
		box.ChangeFlag();
		Assert.assertFalse(box.IsFlagged());
	}
	
	@Test
	public void RevealedTest() 
	{
		Box flaggedBox = new Box();
		flaggedBox.ChangeFlag();
		Assert.assertFalse(flaggedBox.Reveal());
		Assert.assertFalse(flaggedBox.IsRevealed());
		
		Box minedBox = new Box();
		Assert.assertTrue(minedBox.SetHasMine(true));
		Assert.assertTrue(minedBox.Reveal());
		
		Box normalBox = new Box();
		Assert.assertTrue(normalBox.Reveal());
		Assert.assertFalse(normalBox.Reveal());
	}
	
	@Test
	public void IsRevealedTest()
	{
		Box box = new Box();
		
		Assert.assertFalse(box.IsRevealed());
		box.Reveal();
		Assert.assertTrue(box.IsRevealed());
	}
		
	
	@Test
	public void NumberAdjacentMineTest()
	{
		Box box = new Box();
	
		
		Assert.assertEquals(box.GetNumberAdjacentMine(), 0);
		
		Assert.assertTrue(box.SetNumberAdjacentMine(1));
		Assert.assertEquals(box.GetNumberAdjacentMine(), 1);
		
		Assert.assertTrue(box.SetNumberAdjacentMine(8));
		Assert.assertEquals(box.GetNumberAdjacentMine(), 8);
		
		Assert.assertFalse(box.SetNumberAdjacentMine(9));
		Assert.assertFalse(box.SetNumberAdjacentMine(-1));
	
	}
	
	@Test
	public void PrintTest()
	{
		Box box = new Box();
		Assert.assertEquals("O", box.Print());
		
		box.SetHasMine(true);
		Assert.assertEquals("X", box.Print());
	
	}
	

}
