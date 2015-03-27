# Project SNAKE :snake:

**Project SNAKE is a desktop application for an electronic version of the regular *SNAKE* game. This application will follow all the rules of the *SNAKE* game that was popularly known as a built-in game in the famous monochrome phone, Nokia 3310.**

**The *SNAKE* is an electronic or video game originated in an arcade classic called the *Blockade*, a black and white maze game developed and published by Gremlin in October 1976.  The simple game is designed to use four (4) directional buttons, wherein the player moves the snake character in a straight line turning at 90 degree angle.  The objective of the game is too last long through avoiding barriers or even its own body.**

**For this version, the Project Snake will be limited to a single player design in one phone starting with a dot as the preliminary length of the snake.**

######Purpose

  * This Software Requirement Specification (SRS) is intended to list the particular use cases in developing the Project SNAKE application.

######Scope

  * The SRS is limited within the desktop ecosystem, relying on Java as the language for developing the game.  With this document, it is expected that a functioning desktop game application will be produced, and to be aptly named as Project SNAKE.  The application will be designed as a one player game, and will follow the rules of the popular Snake game in the Nokia 3310 handset.

######Definitions, Acronyms, and Abbreviations

  * *Snake*: Snake is one of the most popular arcade game since it was preloaded on Nokia mobile phones.  The player controls either a dot or line on a plane, thus resembling like a snake.  As the game progresses, the snake continues to get longer as it moves and captures its food.  The player loses when it runs into a barrier or obstacle.
  * *Barrier*: An obstacle to be avoided by the player.
  * *Food*: An object, specifically an apple, that makes the snake’s body grow longer.
  * *Score*: A numerical representation of the snake’s length.


######Main Use Cases
  * Establish a displayInGameScore for players to keep track of their score and to easily know the game winner
  *	Designate a moveAccelerate function for the snake’s move or animation
  *	Develop a locateFakeApple function as one of the game’s twist or challenge
  *	Include a gameRestart for players can reset the game if they wish to
  *	Write a simple guide on how to play a game through showHelp function


######Product Perspective and Functions

Project SNAKE is reimagined as a version of the regular Snake game in the desktop computers. It is intended to replicate the traditional Snake experience in desktop computers.

######User Characteristics

Users of Project SNAKE are avid players of the Snake game or any maze game.  It is assumed that they know the basic rules of the Snake game.

######General Constraints

Constraints in the development process include limiting the game scope to particular pre-defined settings such as a single joint as the starting body length of the snake.  In addition, this game is intended to be played only in desktops.

######Assumptions and Dependencies

As stated, it is assumed that users are aware of the basic rules of the game.  In effect, developers are also assumed to understand the fundamental principles behind the game.  If not, the developers are expected to understand the game by any means (such as playing the actual game beforehand).
