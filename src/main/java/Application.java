public class Application {

    public static void main(String[] args)
    {
        Game game;

        if(args.length == 0)
            game = new Game("");
        else
            game = new Game(args[0]);

        game.run();
    }
}
