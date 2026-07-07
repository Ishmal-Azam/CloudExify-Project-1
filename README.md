# CloudExify-Project-1

# Number Guessing Game (Java Swing)

## Project Overview

The **Number Guessing Game** is a desktop application developed in **Java** using the **Swing GUI framework**. The objective of the game is to guess a randomly generated number within a limited number of attempts. The project provides a modern graphical interface, multiple difficulty levels, score tracking, and interactive hints to enhance the user experience.

This project was developed as part of my **Java Internship Project** to demonstrate object-oriented programming concepts, event-driven programming, GUI development, and file handling in Java.

# Objectives

- Develop a fully functional Java Swing application.
- Implement event-driven programming using ActionListener.
- Apply object-oriented programming concepts.
- Practice GUI design using Java Swing components.
- Implement file handling to store the best score.
- Improve user experience with modern dialogs and hint systems.

# Technologies Used

- Java
- Java Swing
- AWT
- Object-Oriented Programming (OOP)
- File Handling
- VS Code

# Features

### Gameplay

- Random number generation
- Easy Mode (1–50)
- Hard Mode (1–200)
- Maximum 10 attempts
- Too High / Too Low hints
- Warmer / Colder hints
- Input validation
- New Game option
- Automatic reset when difficulty changes

### User Interface

- Modern blue & white theme
- Rounded panels and buttons
- Professional typography
- Responsive layout
- Attractive hint cards
- Best Score panel
- Attempts counter

### Winning Screen

- Custom Congratulations dialog
- Trophy icon
- Attempts used
- Best score display
- Play Again button
- Modern UI design

### Game Over Screen

- Custom Game Over dialog
- Correct number display
- Attempts summary
- Motivational quote
- Play Again button
- Exit button

### File Handling

The application stores the player's best score in:

bestscore.txt

When the game starts, the previous best score is automatically loaded.

# Game Logic

1. Select a difficulty level.
2. Generate a random secret number.
3. Enter a guess.
4. Validate user input.
5. Compare the guess with the secret number.
6. Display:
   - Too High
   - Too Low
   - Warmer
   - Colder
7. Increase attempt counter.
8. If guessed correctly:
   - Show Congratulations dialog.
   - Update Best Score.
9. If attempts reach the limit:
   - Show Game Over dialog.
10. Player can start a new game anytime.

# Project Structure

```
NumberGuessingGame/
│
├── NumberGuessingGame.java
├── bestscore.txt
├── README.md
└── trophy.png (optional)
```

# Java Concepts Used

- Classes & Objects
- Constructors
- Methods
- Conditional Statements
- Loops
- Exception Handling
- Random Number Generation
- Event Handling
- Swing Components
- Layout Managers
- File Input/Output

# Swing Components Used

- JFrame
- JPanel
- JLabel
- JButton
- JTextField
- JComboBox
- JDialog
- BoxLayout
- BorderLayout
- Graphics2D

# Learning Outcomes

During this project, I learned how to:

- Design modern Java Swing interfaces.
- Build event-driven desktop applications.
- Implement game logic using Java.
- Handle user input safely.
- Save and retrieve data using files.
- Organize code into reusable methods.
- Create custom dialogs for a better user experience.

# Future Improvements

- Add sound effects.
- Add timer mode.
- Add player name.
- Add leaderboard.
- Add dark mode.
- Add animations.
- Add multiple themes.
- Add statistics dashboard.

# Conclusion

The **Number Guessing Game** successfully demonstrates the practical implementation of Java programming concepts, including GUI development, event handling, object-oriented programming, and file management. The project provides an engaging user experience with modern interface elements, custom dialogs, difficulty levels, and score tracking.

This project strengthened my understanding of Java application development and enhanced my problem-solving and software design skills. It also reflects my ability to develop complete desktop applications using Java Swing.

## Developed By

**Name:** Ishmal Azam

**Language:** Java

**Framework:** Java Swing

**IDE:** Visual Studio Code

**Year:** 2026
