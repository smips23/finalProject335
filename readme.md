# CSc 335 Spring 2025 Final Project

## Team Members
- Zayyan
- Ryan
- Anthony
- Jack

## Project Overview

This is a card-matching game where players flip cards on a grid to find matching pairs. It offers four game modes: Standard, Timed (60-second countdown), Lives (limited mistakes allowed), and Crazy (timed and lives challenges). Players select from three difficulty levels (Easy, Normal, Hard), which determine grid size (4×4, 5×5, or 6×6). Special cards can appear that have abilities like revealing random pairs or highlighting potential matches. The game tracks whether you've seen card values before, and in Lives mode, mismatching previously seen cards costs you a life. Match all pairs to win!

## Design
This project follows the **Model-View-Controller (MVC)** 

## UML

## Design Patterns
- **Observer Pattern**: Used to update the GUI automatically whenever there are changes in the model.
- **MVC Pattern**

## Code Design

#### Model:

- Card.java: Base class for all cards with properties like flipped, locked, highlighted
- Grid.java: Creates the grid, based on difficulty. (Avoided Primitive Obsession: Used final constants for difficulty)
- SpecialCard.java: Extends Card with special abilities that affect gameplay (INHERITANCE)
- Game.java: Implements the core game logic and rules


#### View:

- UI.java: Main menu with game mode and difficulty selection
- GameView.java: Game board display with card buttons
- Main.java: startup view


#### Controller:

- Controller.java: Handles user input from the main menu
- Game.java: Implements ActionListener to process game actions


## Features: Special Cards

## How to run

- Clone the repository
- Open the project in your IDE
- Navigate to Main.java inside the View package and run the file
