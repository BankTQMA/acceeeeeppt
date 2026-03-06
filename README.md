# Acceeeeeppt

## Credit

The core idea and inspiration for this project come from the game “Agreeee” (利用規約に同意したい) created by the Japanese developer べすとまん (Bestman).

The original game presents a humorous and frustrating experience based on interacting with misleading user interface elements, particularly the act of agreeing to terms and conditions. This project draws inspiration from that concept and adapts it into a new implementation developed independently using Java.

We would like to acknowledge and give credit to the original creator for the creative idea that inspired the theme and gameplay direction of this project.

## Description

**Acceeeeeppt** is a small Java-based game developed as part of an Object-Oriented Programming (OOP) project. The game focuses on humor and frustration-based gameplay, where players must interact with various misleading or intentionally confusing interface elements in order to progress.

The main concept of the game is inspired by the common experience of clicking **“Accept”** on terms and conditions without reading them. In this game, players are challenged through a series of mini-levels that simulate different types of annoying or deceptive user interface interactions.

Each level presents a unique mechanic designed to test the player's attention, patience, and reaction. Examples of gameplay elements include:

- Buttons that change position unexpectedly
- Misleading interface highlights
- Hidden requirements before accepting terms
- Puzzle-like button interactions
- Idle-style resource generation
- Reaction-based challenges

The game consists of several scenes such as the **main menu**, **tutorial**, and multiple **levels**, each introducing a different gameplay mechanic.

The project is implemented using **Java with AWT/Swing for the graphical interface**, and the codebase is organized following object-oriented design principles. The architecture separates responsibilities into packages such as core systems, scene management, user interface components, and utility classes.

Overall, _Acceeeeeppt_ is designed as an experimental and entertaining project that combines creative game mechanics with practical programming experience.

## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

## Directory Tree

```txt
.
├── .vscode
│   └── VS Code workspace configuration files
│
├── src
│   └── com/lnw/acceeeeeppt
│       ├── core
│       │   └── Core game systems such as game loop, scene manager, and global game control
│       │
│       ├── main
│       │   └── Program entry point and game startup logic (e.g., Main class)
│       │
│       ├── model
│       │   └── Data models and game state objects such as player data, scores, and level data
│       │
│       ├── scene
│       │   ├── level
│       │   │   └── Gameplay levels and stage implementations
│       │   │
│       │   ├── menu
│       │   │   └── Main menu, options menu, and other menu-related scenes
│       │   │
│       │   └── tutorial
│       │       └── Tutorial scenes that introduce game mechanics to the player
│       │
│       ├── system
│       │   └── Technical subsystems such as input handling, audio system, save/load system
│       │
│       ├── ui
│       │   └── Reusable user interface components such as buttons, labels, and dialogs
│       │
│       └── util
│           └── Helper and utility classes used throughout the project
│
├── bin
│   └── Compiled Java bytecode (.class files) generated during compilation
│
├── lib
│   └── External libraries (.jar files) used by the project
│
└── resource
    ├── fonts
    │   └── Custom fonts used in the game UI
    │
    ├── images
    │   └── Sprites, textures, icons, and other graphical assets
    │
    └── sounds
        └── Sound effects and background music used by the game
```

## Summary

| Folder     | Purpose                                        |
| ---------- | ---------------------------------------------- |
| `.vscode`  | IDE configuration for Visual Studio Code       |
| `src`      | All Java source code organized by package      |
| `bin`      | Compiled `.class` files generated during build |
| `lib`      | External `.jar` libraries                      |
| `resource` | Game assets such as images, sounds, and fonts  |

This structure separates **source code, compiled files, external libraries, and game assets**, making the project easier to maintain and expand.
