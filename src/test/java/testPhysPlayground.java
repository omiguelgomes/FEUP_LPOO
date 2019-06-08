import org.junit.*;
import static org.junit.Assert.*;


public class testPhysPlayground
{
    @Test
    public void testConstr()
    {
        Position pos = new Position(15, 15);
        PhysPlayground physPlay = new PhysPlayground(10, 10, pos);

        assertEquals(physPlay.getPlayerLevel(), 1);
        assertEquals(physPlay.getPlayerLives(), 3);
        assertEquals(physPlay.getPlayerScore(), 0);
    }

    @Test
    public void testLoseLife(){
        Position pos = new Position(15, 15);
        PhysPlayground physPlay = new PhysPlayground(10, 10, pos);

        int livesBefore = physPlay.getPlayerLives();

        physPlay.loseLife();

        assertEquals(livesBefore - 1, physPlay.getPlayerLives());
    }

    @Test
    public void testGameOver(){
        Position pos = new Position(15, 15);
        PhysPlayground physPlay = new PhysPlayground(10, 10, pos);

        physPlay.gameOver();

        assertEquals(physPlay.getPlayerLevel(), 1);
        assertEquals(physPlay.getPlayerLives(), 3);
        assertEquals(physPlay.getPlayerScore(), 0);
    }

    @Test
    public void testNextLevel(){
        Position pos = new Position(15, 15);
        PhysPlayground physPlay = new PhysPlayground(10, 10, pos);

        int levelBefore = physPlay.getPlayerLevel();

        physPlay.nextLevel();

        assertEquals(levelBefore + 1, physPlay.getPlayerLevel());
    }


}
