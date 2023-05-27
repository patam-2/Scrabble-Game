# PATAMPROJECT

So.. About the Project:

We are creating a Java based desktop application of a Scrabble game.
The game is built in MVVM architecture (Model-View-View Model) allowing each game to have a local Host running the game,
and up to 3 additional clients connecting remotely through the Host. 
This game is built based on our first semester Advanced Software Development course project,
in which we built the foundations of this project creating a local server that runs and checks all of the game’s legalities.
This will allow us to have multiple games running at once all through this server. 
We have also created a facade to create more presentable and easier to use game functions.

This project is part of our second semester Advanced Software Development course and is designed and created in three major legs:

  1. First semester individual project- creating all of the game legalities and basic rules. This includes creating a server,
     dictionaries, bloom filter to find and predict the legality of certain words, and all logical decisions regarding the game.
  2. Second semester group project part 1- Designing the games architecture and creating the Model layer including complete functionality and testing.
  3. Second semester group project part 2- Creating the View and View-Model layers including complete functionality and testing.

Rules and the course of the game-
So for the purpose of the project, we will define a slightly simpler set of rules than the original game:
  1. Each player randomly draws a tile from the bag.
  2. The order of the players turns is determined by the order of the letters drawn (from smallest to largest).
     a. If an empty tile is drawn, we will return it to the bag and draw another one.
  3. All the tiles are returned to the bag.
  4. Each player randomly draws 7 tiles.
  5. The first player (the one who drew the smallest letter in the lottery) must form a legal word which passes through the central slot (the star) in the board.
     
     a. Only he gets a double score for that letter. 
    
     b. After laying down a legal word he needs to draw from the bag so that he has 7 tiles again.
     
  6. Gradually, each player, in turn, assembles a legal word using the tiles in his possession.
     a. As in a crossword puzzle, each word must rest on one of the tiles on the board.
     b. After writing the word, the player draws additional tiles from the bag, so he always has 7 tiles. 
     c. His score is accumulated according to all the words created on the board following the placing of the tiles.
     
         i. Tiles placed on double or triple letter squares will be doubled or tripled in value accordingly.
         
         ii. The word then receives the sum point score of all the tiles used to make the word.
         
         iii. This amount will be doubled or tripled for each doubling or tripling word slot that is one of the tiles are superimposed on it
              (that is, it is possible, for example, to multiply by 4 or 9 if the word took two double word or triple word slots respectively).
              
         iv. The above calculation is true for every new word created on the board following the placement in turn.
         
  7. A player who cannot form a valid word forfeits his turn.
  8. The game will end after N rounds.

A legal word must meet all the following conditions:

    •	Written from left to right or from top to bottom (and not in any other way).
    
    •	A word that appears in one of the books chosen for the game.
    
    •	Rests on one of the existing tiles on the board.
    
    •	Does not produce other illegal words on the board.
    
Thank you for reading:)
