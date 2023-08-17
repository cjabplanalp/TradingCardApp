# CardTrader

This project was developed independently with two group members during our time in CS400 - Programming III at UW-Madison. Our original project proposal is contained in `proposal.pdf`. In the proposal, our group was blue team (it should be noted that blue team should have been a group of four, but a member of our group dropped the class before this project, leaving us with three members).

The team (and roles they assumed) that worked on this project:

* DataWrangler: Dhruv Arora
* AlgorithmEngineer: ----
* BackendDeveloper: Masa Abboud
* FrontendDeveloper: Cameron Abplanalp

In this project, our group aimed to build a simple Trading Card storage service which could take in arbitrary information about a "trading card", as well as load information from files. It should be noted that a requirement of this project (dictated by course staff) was that we utilized a Red-Black Tree data structure for its efficiency in insertion/lookup operations. Here is a brief description of the application, taken from the proposal:

Our application stores trading card data, which consists of a unique identifier and a monetary value. This project will utilize a red-black tree effectively because a feature will be getting and iterating cards based on the value of cards from highest to lowest, while still being able to continuously insert and maintain a high efficiency, a task that can be implemented with a red-black binary search tree. This ensures a greater level of efficiency than a sorted array, because an array would not be as efficient when inserting/removing new cards, after the initial data has been loaded. This application will support insertion by loading a file or adding in a frontend interface, inserting single cards, high/low statistics, and printing a summary of your stored deck.

# Usage

To run the application, run

```
make run
```
on command line.

You will be then prompted to by the menu to press a selection of keys based on what is to be accomplished. 

* Use the command `make clean` to remove all Java `.class` files as needed.

### Loading Data
To load a file into the CardTrader storage system, press [L] and follow prompts to enter a desired `.csv` file. An example file included in this repository is `PokemonData.csv`, which contains a sample dataset on Pokemon cards and their card values, but notably does not include headers. Here is what that file would look like with headers for visual clarity:

| NAME  | VALUE |
| ----- | ----- |
| Name1 | 100.0 |
| Name2 | 200.75|

* Note that the VALUE column can only contain ints, due to the code computing statistics based on these values when the user presses [P].

# Testing
As a requirement of the project, our group ran extensive testing on the application to ensure it's functionality and the proper implementation of a RBT. Included in this repository are the tests we ran on each of our role's code, which can be ran by running:

```
make run{RoleTitle}Tests
```
where `RoleTitle` can be one of these values:
['DataWrangler',
 'AlgorithmEngineer',
 'BackendDeveloper',
 'FrontendDeveloper'].
