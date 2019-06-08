import org.junit.*;
import static org.junit.Assert.*;


public class testPhysBall
{
    @Test
    public void testConst()
    {
        Position pos = new Position(14, 15);
        PhysBall ball = new PhysBall(pos);

        assertEquals(ball.getSpeed(), 1);
        assertEquals(ball.isIdle(), true);

    }

    @Test
    public void testIdle()
    {
        Position pos = new Position(14, 15);
        PhysBall ball = new PhysBall(pos);

        ball.switchIdle();

        assertEquals(ball.isIdle(), false);
    }

    @Test
    public void testReset()
    {
        Position pos = new Position(14, 15);
        PhysBall ball = new PhysBall(pos);

        ball.switchIdle();
        ball.setPosition(ball.move());
        ball.reset(pos);

        assertEquals(ball.getPosition(), pos);
        assertEquals(ball.isIdle(), true);
        assertEquals(ball.getSpeed(), 1);
    }

    @Test
    public void testMove()
    {
        Position pos = new Position(14, 15);
        PhysBall ball = new PhysBall(pos);

        ball.switchIdle();
        ball.setPosition(ball.move());

        assertNotEquals(pos, ball.getPosition());
    }
}
