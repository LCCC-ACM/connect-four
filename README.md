connect-four
============

Note: This code is currently under development. We would like to have a more interactive UI. Also, I would like to implement dynamic class loading so people could set bots to play each other without modifiying the source code.

Cloning this project
-------------------
type `git clone https://www.github.com/lccc-acm/connect-four`

Building the project
--------------------

Using Ant

- from the command line type `ant run`

Using Eclipse

- Go to File / New Project and find 'Java Project from existing Ant Buildfile' and select `build.xml` in the root of this repository

Building a Player
-----------------
Create a new class in the competitors folder - make sure it is in the competitors package
Your class must implement the player interface.
To have your player join the game, see lines 15-16 of the `GameManager` class.
The game manager is reposible for newing up the players that will play against each other.

In your code, your class will need to know what color it is. Use this code:
`GameManager.getInstance().getPlayerColor(this)`

