connect-four
============

Cloning this project
type 'git clone https://www.github.com/lccc-acm/connect-four'

Building the project

Using Ant

from the command line type ant run

Using Eclipse

Set the workspace to this repository folder, 'connect-four'
Go to File / New Project and find 'Java Project from existing Ant Buildfile'
Select the build.xml in the root of this repository

Building a Player
Create a new class in the competitors folder - make sure it is in the competitors package
Your class must extend the abstract Player class.
To have your player join the game, see lines 15-16 of the Game Manager class.
The game manager is reposible for newing up the players that will play against each other.

In your code, your class will need to know what color it is. Use this code:
GameManager.getInstance().getPlayerColor(this)

