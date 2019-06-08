# LPOO_18 Breaking Out

The basics of the 'Breaking Out' game are inspired on the classic arcade game 'Arkanoid', where the player must shoot a wall of bricks with a ball which can rebound from the borders of the play area and from the bricks themselves. If the ball passes the lower border, the player looses a life. If the player's live-count reaches zero, the player looses. If the player destroys all the bricks, the next level ensues.

This game is being developed by Eduardo Campos and Miguel Gomes.

## Screenshots

![alt text](https://github.com/FEUP-LPOO/projecto-lpoo-2019-lpoo_18/blob/master/docs/Screenshot_1.png)

![alt text](https://github.com/FEUP-LPOO/projecto-lpoo-2019-lpoo_18/blob/master/docs/Screenshot_2.png)

![alt text](https://github.com/FEUP-LPOO/projecto-lpoo-2019-lpoo_18/blob/master/docs/Screenshot_3.png)

## Install Instructions

To run the game, on git bash, use the command **./gradlew run**. The default run configuration runs with Lanterna as the GUI. However, if you use the command **./gradlew run --args=swing** you will run the game with Swing as the GUI. You can also type **./gradlew run --args=lanterna** to run the game in Lanterna, though.

The game is only built with these two configurations in mind, therefore typing **./gradlew run** with anything else will lead to a command line error and typing **./gradlew run --args=** with anything other than "lanterna" or "swing" will result in the program assuming default behaviour, displaying an error message in the console stating the same.
