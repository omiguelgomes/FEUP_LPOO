import java.util.*;

public class PhysWall{

    private List<PhysBlock> blocks;
    private PhysPowerUp powerUp;
    private int width, lines, x, y;
    private int level;

    public PhysWall(int width, Position initPos, int playerLevel)
    {
        lines = 5;
        this.level = playerLevel;
        this.width = width;
        this.x = initPos.getX();
        this.y = initPos.getY();
        powerUp = new PhysNullPowerUp(new Position(0,0));

        generateBlocks();
    }

    public List<PhysBlock> getBlocks(){
        return this.blocks;
    }

    private void generateBlocks()
    {
        blocks = new ArrayList<>();

        for (int i = 0; i < lines; i++) {
            for (int j = 0; j < width; j++) {

               Random rand = new Random();
               int blockChooser = rand.nextInt(101) + this.level*6;

               if(blockChooser > 100){blockChooser = 100;}

                   if(blockChooser >= 0 && blockChooser < 60)
                       {
                       PhysBlock block = new PhysNormalBlock(new Position(x + j, y + i));
                       blocks.add(block);
                   }
                   else if(blockChooser >= 60 && blockChooser < 90)
                   {
                       PhysBlock block = new PhysSuperBlock(new Position(x + j, y + i));
                       blocks.add(block);
                   }
                   else if(blockChooser >= 90 && blockChooser <= 100)
                   {
                       PhysBlock block = new PhysMegaBlock(new Position(x + j, y + i));
                       blocks.add(block);
                   }
                }
            }
        }


    private PhysBlock getBlock (Position pos)
    {
        Iterator<PhysBlock> it = blocks.iterator();
        while (it.hasNext())
        {
            PhysBlock next = it.next();

            if (pos.equals(next.getPosition()))
                return next;
        }

        return null;
    }

    public void collide(Position pos)
    {
        PhysBlock block = getBlock(pos);

        int blockLevel = block.getLevel();

        blocks.remove(block);

        switch(blockLevel){
            case(1): {
                createPowerUp(pos);
                break;
            }
            case(2): {
                PhysBlock newBlock = new PhysNormalBlock(block.getPosition());
                newBlock.setScore(block.getScore() + newBlock.getScore());
                blocks.add(newBlock);
                break;
            }
            case(3): {
                PhysBlock newSuperBlock = new PhysSuperBlock(block.getPosition());
                newSuperBlock.setScore(block.getScore() + newSuperBlock.getScore());
                blocks.add(newSuperBlock);
                break;
            }
        }
    }

    private void createPowerUp(Position pos)
    {
        if(!powerUp.isNull())
        {
            Random rand = new Random();
            int powerUpOdd = rand.nextInt(100);

            if(powerUpOdd < 100){
                powerUp = new PhysPowerUpLevel(pos);
            }
        }
    }

    public PhysPowerUp getPowerUp(){
        return this.powerUp;
    }

}

