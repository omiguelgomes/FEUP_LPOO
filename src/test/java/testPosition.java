import org.junit.*;
import static org.junit.Assert.*;

public class testPosition
{
    @Test
    public void testPositionGetsSets()
    {
        Position position = new Position(1,2);

        assertEquals(position.getX(), 1);
        assertEquals(position.getY(), 2);
    }

    @Test
    public void testEquals()
    {
        Position pos1 = new Position(1,2);
        Position pos2 = new Position(3,4);
        Position pos3 = new Position(3,4);

        assertTrue(pos2.equals(pos3));
        assertFalse(pos1.equals(pos2));
    }
}
