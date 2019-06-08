import org.junit.*;
import static org.junit.Assert.*;


public class testPhysElement
{
    @Test
    public void testGetSet()
    {
        Position pos = new Position(1,2);
        Position pos2 = new Position(3,4);

        PhysElement pe = new PhysElement(pos);

        assertEquals(pe.getPosition(), pos);
        pe.setPosition(pos2);
        assertEquals(pe.getPosition(), pos2);
    }
}
