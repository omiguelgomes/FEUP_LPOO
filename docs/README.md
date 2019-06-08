# LPOO_18 Breaking Out

The basics of the 'Breaking Out' game are inspired on the classic arcade game 'Arkanoid', where the player must shoot a wall of bricks with a ball which can rebound from the borders of the play area and from the bricks themselves. If the ball passes the lower border, the player looses a life. If the player's live-count reaches zero, the player looses. If the player destroys all the bricks, the next level ensues.

## Implemented Features

-A game area with defined and impassable borders;

-A paddle, representing the player, capable of moving left and right;

-A a ball which ricochets from the walls and bricks, capable of transposing the lower border;

-A collision detection and processing engine;

-A set of Bricks, each one capable of assuming several attributes;

-Player lives and a method to decrease them, reset the positions when the player looses a life and performing the 'Game Over' action.

-A level system;

-A score system.

## Planned Features

-Swing GUI (ongoing development);

-Power-ups (ongoing development);

-More block types;


## Design

Thus far we have found use for three different Design Patters, DP for short. More may appear either upon close analysis of the code or if more issues are encountered while the continuation of this project. The description and implementation of the following DP's might change further in the development cycle.

### The Model-View-Controller

![alt text](https://github.com/FEUP-LPOO/projecto-lpoo-2019-lpoo_18/blob/master/docs/MVC.jpg "Model-View-Controller Pattern UML")

#### The Problem

Soon into the development of this project, we found the need/usefulness of separating the physical aspects/calculation of the program from the manner in which the program draws and updates the screen. This separation has already proved useful by allowing changes on one side with little to no changes on the other part.

#### The Pattern

To better streamline the production of code as well as maintain some form of organization, we found, after advice from our lab-classes' teacher, that implementing **MVC** was exactly what we needed.

In short, the Model View Controller (MVC) design pattern specifies that an application consist of a data model, presentation information, and control information. The pattern requires that each of these be separated into different objects. The model and view are not aware of each other and their 'interactions' are mediated by the controller.

#### Implementation

Implementing this will be complex, but overall, not that difficult. Further separation between the Playground and PhysPlayground will be needed and a suitable controller between these two classes must be created to mediate the relation between these two classes. This controller is the Game class, which retrieves from PhysPlayground all the information needed and proceeds to feed it to the View (Playground).

#### Consequences

This separation has allowed us to turn our program mostly independent from Lanterna. This means we can add more views - i.e. Swing - to our program with little changes to the physical aspect of it. In the same manner, the physical part of the program can be changed or incremented without ever having to change anything on the graphical part.

### The State Pattern

 #### The Problem

 When beginning to develop the concept of power-ups, i.e., objects in the game world which grant the player benefits of several forms, it became clear that, in some cases, the player's - or even the ball's - behaviour would have to change to reflect the activation of these power-ups. This led us, naturally, to the State Pattern.
 
 #### The Pattern

 The **State Design Pattern** is, in short, a behavioral pattern which allows an object to change its behaviour in run-time, instantaneously, by changing its internal state - making it seem the object changed class.
 
 #### Implementation

 In some cases, the State Pattern won't need to be used, such as in cases where the power-up only changes the value of a given attribute of an object, e.g. increasing/decreasing the ball's speed. In the other cases, several states will be created, one for each power-up, and these states dictate the behaviour of the object they're instantiated in. When the object is to display a certain behaviour, it will simply refer to the function in its current state to display it. States will be changed by checking the player for collisions with power-up objects and checking their type.
 
 #### Consequences

Due to time constraints, we won't be able to implement power-ups. However, we predict the main consequences of implementing this pattern would be to easily and in runtime, allow the player or the ball to behave differently depending on which power-up they collect. 

**Note**: since we haven't fully implemented the Power-ups, as stated above, we were also unable of providing an UML.
 
 ### The Composite Pattern
 
 ![alt text](https://github.com/FEUP-LPOO/projecto-lpoo-2019-lpoo_18/blob/master/docs/Composite.jpg "Composite UML")

#### The problem

The main goal of the game if to destroy blocks while preventing the ball from touching the bottom of the game area. The blocks can have diferent attributes but they all exist within a wall object. Therefore, we wanted to be able to interact with this wall object as if we were working with each block individually.

#### The pattern

The **Composite** pattern is a structural design pattern that lets you compose objects into structures and then work with these structures as if they were individual objects, which suits our case like a glove.

#### Implementation

A common abstract-class with default behaviour for all block types was created, with each block then extending it. Then a composition of these derived blocks was created in a class with methods to work with the individual blocks, so that externally to all this, we only need to address the wall object - the composition - and not the individual blocks themselves.

#### Consequences

By structuring the program like this allowed us to work with an otherwise complex structure rather easily, while also guaranteeing other parts of the problem don't need to concern themselves with or be dependent on the inner workings of the blocks classes.

### The Iterator Pattern

#### The Problem

During development, we found ourselves face-to-face with a Concurrent Modification Exception, due to the fact that we were adding and deleting blocks from the same List, at the same time. Part of the solution to this problem was, instead of using a normal for()-loop, we would traverse the List in question using and adequate iterator.

#### The Pattern

The Iterator pattern is a behavioural pattern used to get a way to access the elements of a collection object, in sequential manner without any need to know its underlying representation.

#### Implementation

Implementing this was very, very simple. We created an iterator with the class we desired in the template-class position. Then we would re-do the for-loop as a while-loop instead, with the exit condition being Iterator.hasNext().

#### Consequences

This, allied with the creation of a copy-list, allowed us to surpass this problem and better process the graphical-part of the block's collision.

### The Null-Object Pattern

![alt text](https://github.com/FEUP-LPOO/projecto-lpoo-2019-lpoo_18/blob/master/docs/NOP.jpg "Null-Object Pattern UML")

#### The Problem

While beginning the work on implementing the power-ups, we noticed that, at all times, the program must be looking out for power-ups which can change the behaviour of the player or the ball or other, and must be ready to draw them to the screen. However, there are moments where there are no power-ups active in the scene, a situation which can change at any moment. A simple solution would be creating a variety of guard-clauses to test wether the power-up is instantiated or is *null*. This, however is a problematic solution, inefficient and prone to errors and bugs. A workaround would be to instantiate a **Null-Object** and checking our actions towards it.  

#### The Pattern

The intent of a Null-Object is to encapsulate the absence of an object by providing a substitutable alternative that offers suitable default do-nothing behavior. In short, a design where "nothing will come of nothing", having created an object with neutral - or null - behaviour.

#### Implementation

The implementation of this pattern is very straightforward. We began by creating an abstract class the regulate the behaviour of all power-ups. Then, we create an abstract method isNull() which, in all concrete subclasses always returns *false*. We then create an additional class, which all atributes have a default value and the isNull() function returns *true*.

Now, instead of having the guard-clauses checking against the *null* value, they check against the return from the isNull() function.

#### Consequences

By having done this refactoring, we have guaranteed our program is less prone to errors and bugs, while also being more readable and clean.

## Known Code Smells and Refactoring Suggestions

### The Middle-Man Code Smell

This code smell is present when a function's only job is to call another function. 

By employing 'Remove Middle-Man' we can get rid of this code smell. On an interesting note, several cases od Middle-Man were 'accidentally' removed when refactoring the program to MVC. This is because, while doing this, we elevated the PhysPlayground (PP) class so that it would no longer the an attribute of Playground. With this, whenever the Game class had to use any PP feature, it could directly call it.

### Shotgun surgery

The Shotgun Surgery code smell is present whenever one finds himself having to perform several small changes in different classes or functions just because of changes to one of them. This code smell was present at the beginning of our development phase, before we began refactoring the program into MVC.

Refactoring the program to MVC allowed us to aggregate functions and classes with similar behaviours under the same roof, essencially ridding us of the problem. This was a pratical application of the *Mode Method* and *Move Field* techniques.

### Large class

During the beginning of our program development we had the physical and graphical aspects of our program consolidated under the same classes. This invariably led to both unwanted dependencies but also to large and overly-complex classes. By using techniques such as *Extract Class* and to a lesser extent *Extract Subclass* we managed to solve this issue and now have rather compact and well-specialized classes.

**Sidenote**: so far, all of our major code smells were resolved, some even unwittingly, simply by implementing the MVC pattern. Our theory is that this pattern resulted in such a major overhaul of the application's structure that many code smells, some of which we may not even have realized existed, were resolved as a consequence.

**Sidenote 2**: we have also found two other code smells (the next two sections), however, for reasons we will explain in their respective sections, we elected not to remove/refactor them.

### Switch Statements

Our program has a fair ammount of switch-case statements and if-elses. This is, indeed, a code smell, under the category of OOP-abusers, however, we found little need of refactor them simply because the contents of each clause are quite simple and performing any refactoring would add unnecessary complexity to the code.

### Data Class

A Data class is a class which only holds some values and a way of accessing them (Getters and/or Setters). This is the case of our Position class. Despite this, we decided we would not perform any refactoring because this class, as it is and despite not seeming so, is probably our most important class we have, because its through it we can move data which can be read by both the Model and the View.

Attempting a refactoring at this stage would be very complex and risky. Besides, this structure had, at least to us, its beneficts. So we chose not to change it.

## Testing Results

[Coverage report](index.html)

### Screenshots

Testing Results
 ![Unit Tests Results](https://github.com/FEUP-LPOO/projecto-lpoo-2019-lpoo_18/blob/master/docs/ut.png "Unit Tests Results")
 
 Coverage Report (in case the HTML doesn't work)
 ![alt text](https://github.com/FEUP-LPOO/projecto-lpoo-2019-lpoo_18/blob/master/docs/coverage.png "Coverage Report")
 
 **Sidenote**: regarding the Mutation test, an error ocurred related to Pitest which we were unable to understand/resolve in time.
 
 **Sidenote 2**: we avoided testing the classes with Lanterna/Swing methods both because we were unaware of how to do so but also at our teatcher's recommendation. Power-up related unit tests are not present because we do not consider the feature the be fully developed, as indicated by its present on the '**Planned Features**' section.

## Self-evaluation

In general we consider the project to be a success. We are both very glad with the end product and fill very fulfilled. 

There are, indeed, some shortcomings, such as our failure in fully implementing the Swing view or that we were unable/had no time to implement further block types or correclty implement the Power-ups, which would add a whole new layer of strategy and complexity to the game. The collision system of the blocks also isn't perfect, but it's, at least, free from bugs.

The Unit Testing component was also a bit handicapped. This is because there were many things we were uncertain of how to do and this task (testing) which should have been done parallel to the implementation of new features, was actually very neglected up until the end.

Even with all this in mind, we think we are worthy of a good grade. We are aiming, and believe it's fair, for a grade between 12 to 14 values. Of course, any grade higher is more than welcome.

In terms of contributions, both of us put a considerable amount of effort and we consider our participation and contribution to be largely equal, both in quantity and in quality.
