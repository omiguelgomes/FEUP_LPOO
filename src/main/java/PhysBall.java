import java.util.*;

public class PhysBall extends PhysElement
{
    private int i;
    private int j;
    private int speed;
    private boolean idle;

    public PhysBall(Position position)
    {
        super(position);
        this.idle = true;
        this.i = 0;
        this.j = 0;
        this.speed = 1;
    }

    public int getSpeed() {
        return speed;
    }

    public boolean isIdle()
    {
        return idle;
    }

    public void switchIdle()
    {
        this.idle = false;
        this.j = -1;
    }

    public void processCollision(Position collision, boolean isSide)
    {
        if (isSide)
        {
            if (collision.getX() == position.getX() + 1)
            {
                this.i = -1;
            }
            else if (collision.getX() == position.getX() - 1)
            {
                this.i = 1;
            }
        }
        else
        {
            if (collision.getY() == position.getY() + 1) {
                this.j = -1;
            }
            else if (collision.getY() == position.getY() - 1) {
                this.j = 1;
            }
        }
    }

    public void processCollision(int ace, int posPlayer)
    {
        switch (ace)
        {
            case 0: {
                this.i = ace*this.i;
                break;
            }
            case 1:
            {
                this.i = -1;
                break;
            }
            case -1:
            {
                this.i = 1;
                break;
            }
        }

        this.j = -1;
    }

    public void processCollision(PhysBlock block)
    {
        String type = null;

        for (Position pos : block.adjecents().keySet())
        {
            if (position.equals(pos)) type = block.adjecents().get(pos);
        }

        Random random = new Random();
        int n = random.nextInt(100) + 1;

        switch (type)
        {
            case "upperLeftCorner":
            {
                if(n >= 90)
                {
                    this.i = -1;
                    this.j = -1;
                }
                else if (n >= 45)
                {
                    this.i = -1;
                    this.j = 1;
                }
                else {
                    this.i = 1;
                    this.j = -1;
                }
                break;
            }
            case "lowerLeftCorner":
            {
                if(n >= 90)
                {
                    this.i = -1;
                    this.j = 1;
                }
                else if (n >= 45)
                {
                    this.i = -1;
                    this.j = -1;
                }
                else {
                    this.i = 1;
                    this.j = 1;
                }
                break;
            }
            case "bot":
            {
                this.j = 1;
                break;
            }
            case "upperRightCorner":
            {
                if(n >= 85)
                {
                    this.i = 1;
                    this.j = -1;
                }
                else if (n >= 40)
                {
                    this.i = -1;
                    this.j = -1;
                }
                else {
                    this.i = 1;
                    this.j = 1;
                }


                break;
            }
            case "lowerRightCorner":
            {
                if(n >= 90)
                {
                    this.i = 1;
                    this.j = 1;
                }
                else if (n >= 45)
                {
                    this.i = 1;
                    this.j = -1;
                }
                else {
                    this.i = -1;
                    this.j = 1;
                }
                break;
            }
        }
    }

    public Position move()
    {
        return new Position(position.getX() + (1 * i), position.getY() + (1 * j));
    }

    public void reset(Position pos)
    {
        this.idle = true;
        this.position = pos;
        this.speed = 1;
        this.i = 0;
        this.j = 0;
    }
}
