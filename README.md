# Minesweeper

## About
This project generates a text based map for the old game “minesweeper” (https://en.wikipedia.org/wiki/Minesweeper_(video_game)):

## What is Minesweeper?
There is a game area of certain size, with certain amount of mines in it. 
The idea of the game is to open all squares of the game without opening a square where a mine is located.
Each square is either totally empty, contains a mine or contains a number which tells how many mines are in total in all 8 diagonally adjacent squares around the number square.

## How does it work?
For now, the object of the project is only to generate the game map.

The generation method needs to take three parameters:
1. the width of the game area (amount of columns)
2. the height of the game area (amount of rows)
3. the amount of game objects

If you won't input these 3 parameters like program arguments, program will ask you to input them one by one.


Example run:

First approach
```
> java main.Main 6 5 2
111---
1x211-
112x1-
--111-
------
```
Second Approach
````
> java main.Main
Please enter 3 parameters next
Input amount of columns (width): 6
Input amount of rows (height): 5
Input mines amount: 2
------
111---
1x21--
12x1--
-111--
```
