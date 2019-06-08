import java.util.*;

public class PhysBlock extends PhysElement
{

    protected Map<Position, String> adj;
    protected int score, blockLevel;
    protected String tag;

    public PhysBlock(Position position)
    {
        super(position);
        initAdj();
    }

    protected void initAdj()
    {
        List<Position> positions = new ArrayList<>();
        adj = new HashMap<>();


        int x = position.getX();
        int y = position.getY();

        for (int i = x - 1; i <= x + 1; i++)
        {
            for (int j = y - 1; j <= y + 1; j++)
            {
                positions.add(new Position(i,j));
            }
        }

        adj.put( positions.get(0),"upperLeftCorner");
        adj.put( positions.get(2), "lowerLeftCorner");
        adj.put( positions.get(5), "bot");
        adj.put( positions.get(6), "upperRightCorner");
        adj.put( positions.get(8), "lowerRightCorner");

    }

    public Map<Position, String> adjecents()
    {
        return adj;
    }

    public int getScore()
    {
        return this.score;
    }


    public void setScore (int score)
    {
        this.score = score;
    }

    public int getLevel()
    {
        return this.blockLevel;
    }

    public String getTag()
    {
        return tag;
    }
}
