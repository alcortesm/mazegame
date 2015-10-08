# MazeGame

## Introduction

MazeGame is a tiny roguelike game for teaching Java.

Its desing is strongly influenced by the current curriculum of the course I
have been teaching in the past 3 years at UC3M. This means you will find:

- Questionable desing decissions.
- Inefficiencies.
- Overengineering.
- Scarce use of the most Java interesting features.

Some of these are the result of me overlooking something, but must of them are
motivated by conscious decision to adapt the code to the course curriculum.

It should be simple to start the course using a simplified version of the code
and built from there. I have also tried to desing the program so it can be easily
extended.

## The Goal of the Game

The goal of the game is to control the movements of a *hero* inside a closed 2D
maze.

The hero will begin the game at a starting position and the game will end
when the hero gets to the *ending* position or dies.

The maze is a 2D rectangular array of *wall*s and *empty space*. Walls in the
maze prevents the player from taking a direct, straighforward route to the
ending position. Empty space can hold other objects, like the ending position,
the hero, monsters, traps...

## Overall desing

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

## The Client/Server Interface

As the course curriculum does not include networking or concurrency, the
client and the server will run synchronously and in the same device.

In the most simple case, there will be a single client for a single server.
Each client will create a server instance for its own internal use.

It is easy to extend a simple client to handle several servers (playing
simultaneously against several internal servers). But the opposite, connecting
with a client to an already running server, will require the modification of
the client/server interface as it has no callbacks from the client to the
server nor it has the concept of "connections" between client and server.

### Client to Server messages

The server initialization requires some data from the client:

- The desired strategy for generating the maze (i.e. choosing one among a set
  of predefined mazes or asking for a randomly generated maze) and its
  parameters (i.e.  the name of the pregenerated maze or the dimensions for a
  randomly generated maze).

This information is stored in objects implementing the `ServerSpec` interface.
There are implementations of this interface for every kind of server supported.

Once the server has been created, the client can send two types of messages to
the server:

- `movePlayer(Direction dir)`: to ask the server to move the player one step
  towards a certain direction.

- `getClientView()`: to ask the server for the current representation of the
  game state.

`Direction` is an enum with the compass directions (`NORTH`, `SOUTH`, `EAST` and
`WEST`).

Both messages return an object from the `ClientView` class. This class holds
all the information the client needs to render an accurate representation of
the game state to the user after sending the message to the server. The
information stored in a `ClientView` object is:

- `boolean isGameOver()`: to know if the game has finished.

- `boolean isHeroAlive()`: if the player is alive. At game over the
  player wins if the hero is alive, otherwise the player loses.

- `String lastMsgResult()`: a textual message to the user to let her know
  if the last movement was succesfully performed (the hero can not move into
  walls, for example).

- `Icon[][] getTopView()`: the set of objects in the maze and their
  positions. To keep things simple, its current implementation is a Java 2D
  rectangular array of Icons (another enum). This means there can only be one icon
  for each position in the maze, this is, the hero will obscure the floor under
  him or the starting and ending position. This makes sense for a textual
  interface, but will look ugly for a graphical one where transparencies could
  be used for a much more pleasant visual effect.

### Writing a Client

In summary, to write a client for the game, you will only need to handle the
following data types:

- The `Server` class: to create a server and send messages to it.

- The `ServerSpec` interface and its implementations: to configure the server:
  pre-generated map, randomly generated map...

- The `Direction` enum: to tell the server where the hero want to go.

- The `ClientView` class: all the information for an accurate rendering of the
  game state.

- The `Icon` enum: walls, hero, starting position, ending position or an empty
  floor; to render the maze to the user.

All these data types can be found in the `server` package.

The `Tui` class is a very simple implementation for a console based client. The
`Gui` class is simple SWING based implementation of a graphical client.

