# Threaded Tic Tac Toe Game (Java)

This repository contains the code for a Threaded Tic Tac Toe game implemented in Java. The game allows two players to play Tic Tac Toe against each other using a client-server architecture with multithreading.

## Features

- Multiplayer functionality: Allows two players to connect and play Tic Tac Toe against each other.
- Real-time updates: Updates game state and displays the board in real-time.
- Threaded architecture: Utilizes multithreading to handle multiple client connections simultaneously.
- Interactive user interface: Provides a user-friendly console-based interface for playing the game.

## Prerequisites

- Java Development Kit (JDK) 8 or higher

## Getting Started

1. Clone the repository:

```shell
git clone https://github.com/NeloferArj/ThreadedAlTicTacToe.git
cd ThreadedAlTicTacToe
```

2. Compile the server code:

```shell
cd Server
javac Server.java
```

3. Start the server:

```shell
java Server
```

4. Open two separate terminal windows for the clients.

5. Compile the client code in one terminal:

```shell
cd ../Client
javac Client.java
```

6. Start the first client in the same terminal:

```shell
java Client
```

7. Compile the client code in the other terminal:

```shell
cd ../Client
javac Client.java
```

8. Start the second client in the second terminal:

```shell
java Client
```

9. The clients will connect to the server, and you can start playing the Threaded Tic Tac Toe game.

## Folder Structure

- `Server`: Contains the server-side codebase written in Java.
- `Client`: Contains the client-side codebase written in Java.

## Acknowledgments

- The Threaded Tic Tac Toe game is inspired by the classic Tic Tac Toe game.
- The multithreading concept in Java is used to handle multiple client connections and enable simultaneous gameplay.
