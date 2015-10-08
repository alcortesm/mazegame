# MazeGame

## Introduction

MazeGame is a tiny roguelike game for teaching Java at UC3M.

As far as I know, the original idea of using a maze game to teach Java is from
"Maria Carmen Fernandez Panadero" <mcfp@gmail.com>. I just wrote a reference
implementation to check that the game can be coded using only the course
curriculum and fits well on the course schedule.

This project is not yet finished, but most of the work is done, so you can 
start using it in your courses.

## Contact Information

You can reach me by email at "Alberto Cortés" <alcortesm@gmail.com>.

## The Goal of the Game

The goal of the game is to control the movements of a *hero* (visually, the
hero is more like a worm, really) inside a closed 2D randomly generated maze.

The hero will begin the game at a starting position and the game will end
when the hero gets to the *ending* position or dies.

The maze is a 2D rectangular array of *wall*s and *empty space*. Walls in the
maze prevents the player from taking a direct, straightforward route to the
ending position. Empty space can hold other objects, like the ending position,
the hero, monsters, traps...

### Screenshots

TUI version:

![Tui screenshot](https://cloud.githubusercontent.com/assets/9169414/10363281/f415d84c-6db3-11e5-8c0a-174439c9840d.PNG)

GUI version:

![Gui screenshot](https://cloud.githubusercontent.com/assets/9169414/10363390/97d37124-6db4-11e5-8c42-9a0f68d3ff02.PNG)

## Overall design

The design of the game is strongly influenced by the current curriculum of the
course. It has elements matching the following course topics:

- Lots of array manipulations (2D matrices mostly).

- Non-trivial, but simple inheritance and interfaces.

- A simple TUI client that can be used in the first month of the course, when 
  the students have not yet learn about SWING.

- A more advanced SWING GUI client is included. As our students do not really
  learn about canvas painting, the GUI is built from standard SWING widgets,
  like labels; this is a questionable decision and a bad design, but it suits
  our needs, you should be able to adapt your GUI version to a more sane 
  implementation in no time, if you need it.  

- Collections: all collections used in the game are in-house implementations of 
  the ones taught in the course curriculum.

- A simple queue is used for sending updates to the GUI, but it is not needed
  in the TUI.

- A more sophisticated queue (`Trail.java`) is used for the worm tail. A fake 
  trail is provided for trails of length 0, to use at the beginning of the 
  course. 

- A random set (`OpaqueSack.java`) is used for the random maze generators. It 
  is implemented as a random list as our students do not learn about sets.

- Lists (`ListArray.java`) are used for the graph generation of the random 
  mazes algorithms.  

- Stacks (`StackArray.java`) are used for the undo functionality.

- Recursion: one of the algorithms to build random mazes is recursive.

- A simple grid graph is used for generating random mazes.

- Trees are used for the cheat-mode (showing the path to the exit form the hero 
  position).

It should be simple to start the course using a simplified version of the code
and built from there. I have also tried to design the program so it can be easily
extended and it is composed of interchangeable parts.

The program is divided in two parts:

- The user interface (UI), called the `Client`.
- The game engine, called the `Server`.

The client (UI) task is to present the state of the game to the user and to
collect the user inputs and pass them to the server.

The server (engine) will process the user inputs, modify the internal state of
the game accordingly and provide and accurate representation of the state of
the game to the client. 

It should be easy to ask your students to code a client (console based or
graphical) for an already working server or the other way around.

## Extensions

It should be easy to ask your students to modify the program for course 
projects. Some examples:

- Turn this into a single player tron-like game: using empty mazes, adding some 
  coins (that will make the hero's trail to grow) and making the hero's trail 
  lethal to the hero. 
