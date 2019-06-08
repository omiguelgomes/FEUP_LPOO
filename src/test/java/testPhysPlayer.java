import org.junit.*;
import static org.junit.Assert.*;

public class testPhysPlayer
{
    @Test
    public void testMoveLeft()
    {
        PhysPlayer player = new PhysPlayer(new Position(1,1));
        Position pos = new Position(0,1);

        assertEquals(player.moveLeft().getX(), pos.getX());
    }

    @Test
    public void testMoveRight()
    {
        PhysPlayer player = new PhysPlayer(new Position(1,1));
        Position pos = new Position(2,1);

        assertEquals(player.moveRight().getX(), pos.getX());
    }

    @Test
    public void testGetSet()
    {
        PhysPlayer player = new PhysPlayer(new Position(1,1));

        int l = player.getLength();
        assertEquals(l, 2);

        Position centre = new Position(2,0);

        Position test = player.getCenteredPosition();
        assertTrue(test.equals(centre));

        player.setPosition(centre);
        assertEquals(player.getPosition(), centre);

        Position ace = new Position(3,3);
        assertEquals(player.getAcceleration(), -1);
    }
}
