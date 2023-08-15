# CardTrader

This project was developed independently with two group members during our time in CS400 - Programming III at UW-Madison. Our original project proposal is contained in `proposal.pdf`. 

In this project, our group aimed to build a simple Trading Card storage service which could take in arbitrary information about a "trading card", as well as load information from files. It should be noted that a requirement of this project (dictated by course staff) was that we utilized a Red-Black Tree data structure for its efficiency in insertion/lookup operations. Here is a brief description of the application, taken from the proposal:

Our application stores trading card data, which consists of a unique identifier and a monetary value. This project will utilize a red-black tree effectively because a feature will be getting and iterating cards based on the value of cards from highest to lowest, while still being able to continuously insert and remove values and maintain a high efficiency, a task that can be implemented with a red-black binary search tree. This ensures a greater level of efficiency than a sorted array, because an array would not be as efficient when inserting/removing new cards, after the initial data has been loaded. This application will support
insertion by loading a file or adding in a frontend interface, inserting/removing single cards, high/low statistics, and printing a summary of your stored deck.
