import org.junit.*;
import static org.junit.Assert.*;


public class testBlocks
{
    @Test
    public void testLevels()
    {
        Position pos = new Position(12, 12);
        PhysBlock normalBlock = new PhysNormalBlock(pos);
        PhysBlock superBlock = new PhysSuperBlock(pos);
        PhysBlock superBlock2 = new PhysSuperBlock(pos);
        PhysBlock megaBlock = new PhysMegaBlock(pos);

        assertEquals(normalBlock.getLevel(), 1);
        assertEquals(superBlock.getLevel(), 2);
        assertEquals(megaBlock.getLevel(), 3);

        assertEquals(superBlock.getLevel(), superBlock2.getLevel());

    }

    @Test
    public void testScores(){
        Position pos = new Position(12, 12);
        PhysBlock normalBlock = new PhysNormalBlock(pos);
        PhysBlock normalBlock2 = new PhysNormalBlock(pos);
        PhysBlock superBlock = new PhysSuperBlock(pos);
        PhysBlock megaBlock = new PhysMegaBlock(pos);

        assertEquals(normalBlock.getScore(), 5);
        assertEquals(superBlock.getScore(), 10);
        assertEquals(megaBlock.getScore(), 15);

        assertEquals(normalBlock.getScore(), normalBlock2.getScore());

    }
}
