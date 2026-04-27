# Abalone — Junior Project

A desktop implementation of the strategy board game **Abalone**, built in Java Swing.
The game ships with a single-player mode against a heuristic AI opponent and a local
two-player hot-seat mode.

Submitted as the final project for the **Software Technician** track at **ORT
Hermelin College, Netanya** (תשע״ז / 2017). Built in Java with **NetBeans IDE 8.2**.
Mentors: Michael Chernobalsky and Zehava Yakobson.

> *"I wanted to learn how a computer 'thinks' and to test its skills under
> non-standard conditions. I liked that Abalone is played on a hexagonal grid that
> demands a different approach to direction calculations and a slightly inverted
> logic. Only after working on it did I understand how complex the game really is —
> and along the way, I learned to apply object-oriented programming principles to
> reach an efficient and robust product."*
> — From the original project paper (translated from Hebrew)

![Abalone](https://i.imgur.com/AKIj6js.png)

---

## Table of contents

- [About the game](#about-the-game)
- [Features](#features)
- [Game rules](#game-rules)
- [How to run](#how-to-run)
- [Project structure](#project-structure)
- [How the AI works](#how-the-ai-works)
- [Author](#author)
- [Original project paper](#original-project-paper)
- [License](#license)

---

## About the game

[Abalone](https://en.wikipedia.org/wiki/Abalone_(board_game)) is an abstract two-player
strategy game played on a hexagonal board. Each player owns 14 marbles. The first
player to push **6 of the opponent's marbles off the board** wins.

This project recreates the game as a Java Swing desktop application, complete with a
custom-painted hexagonal board, mouse-driven selection, directional move buttons, and
a computer opponent driven by a position-evaluation heuristic.

## Features

- Local two-player mode (Red vs. Black, hot-seat)
- Single-player mode against a heuristic AI
- Custom hexagonal board rendered with `java.awt.Graphics`
- Six directional move buttons (`↖ ↗ ← → ↙ ↘`)
- Push-out detection with side bars showing captured marbles
- Win detection (6 captures) with automatic new-game prompt
- In-game rules screen (`Help → About`)
- AI runs on its own thread so the UI stays responsive

## Game rules

A short summary (the same rules are shown in-game from the **Help** menu):

1. On your turn, select up to **3 of your own marbles** that lie in a straight line
   and adjacent to one another, then move them one step in any of the six directions.
2. **Inline pushes**: if your column of marbles meets a shorter column of opponent
   marbles directly ahead, you may push them — provided you outnumber them
   (3-vs-2, 3-vs-1, or 2-vs-1). Equal or larger opposing columns block the move.
3. **Side moves**: a row of marbles can also slide sideways into empty cells.
4. Marbles pushed **off the edge of the board are lost** for the rest of the game.
5. The first player to push **6 of the opponent's marbles** off the board wins.

Red moves first. White (black in this build) responds.

## How to run

The project is a NetBeans Ant project targeting Java SE 1.5+.

### Option 1 — Run the prebuilt JAR

```bash
cd dist
java -jar Abalone.jar
```

> The JAR loads its board and marble images from the **working directory**, so make
> sure you launch it from the project root (or from `dist/` after copying the PNGs in).
> If the board looks blank, check that `Board.JPG`, `Red.png`, `Black.png`, etc. live
> next to the JAR.

### Option 2 — Open in NetBeans

1. Clone the repository:
   ```bash
   git clone https://github.com/marikb/Abalone-Junior-Project.git
   ```
2. In NetBeans: **File → Open Project…** and pick the cloned folder.
3. Press **F6** (Run Project). The main class is `abalonegame.GameFrame`.

### Option 3 — Build with Ant from the command line

```bash
ant clean jar
java -jar dist/Abalone.jar
```

### Starting a game

From the menu bar:

- **Game → New → vs. Player** — local two-player mode.
- **Game → New → vs. Computer** — single-player mode against the AI.
- **Help → About** — opens the rules screen.

## Project structure

```
src/abalonegame/
├── GameFrame.java        Main JFrame: menus, direction buttons, repaint loop
├── AboutFrame.java       In-game rules / about dialog
├── Board.java            11×21 cell grid, move legality, pushes, win check
├── Cell.java             A single board cell (empty / red / black / selected)
├── Spot.java             A connected cluster of same-colour marbles, with a grade
├── Player.java           Abstract player; builds the spot list used for grading
├── HumanPlayer.java      No-op DoStep — the human moves via mouse + buttons
├── ComputerPlayer.java   AI player: enumerates moves, grades them, picks the best
├── Move.java             A virtual move on a copied board (used for AI lookahead)
└── AI.java               Thread wrapper so the AI move doesn't block the UI

build.xml, nbproject/      NetBeans Ant build files
dist/Abalone.jar           Pre-built runnable JAR
*.png, *.JPG               Board, marbles, direction-arrow icons
```

The board is stored as an **11×21 byte matrix** (`Board.InitBoard`). The wide column
count is a trick: every other column is a "spacer" so that the same matrix can
represent the staggered hexagonal layout. The six legal directions are encoded as
`(dx, dy)` pairs in `Board.Direction`, where horizontal moves step by ±2 columns and
diagonal moves step by ±1 column and ±1 row.

## How the AI works

The computer opponent uses a **one-ply heuristic search** with a position-evaluation
function — no full min-max tree, but the evaluator already accounts for the
opponent's reply implicitly by subtracting their score.

For every move available to the AI:

1. The current `Board` is copied (`Board.CopyBoard`) so the move can be played
   virtually without disturbing the real game state (`Move.DoMove` / `Move.UndoMove`).
2. The AI's marbles are partitioned into **spots** — connected clusters of same-colour
   marbles (`Player.createSpots`, recursive flood-fill in `Player.makeSpot`).
3. Each spot is graded by `Spot.setGrade()` based on two ideas:
   - **Compactness** — `cluster_size / max_distance_from_centroid`. Tight clusters
     score higher because they form unbreakable 3-in-a-row formations.
   - **Centrality** — `2 × cluster_size / distance_from_board_centre`. Marbles in
     the middle are harder to push off the edge.
4. The same calculation is run for the human's marbles to get their score.
5. The final grade is:
   ```
   grade = (humanSpotCount − aiSpotCount) × 4
         + aiSpotScore
         − humanSpotScore
   ```
   …plus a **+100 bonus** if the move pushes a human marble off the board, since
   captures directly progress toward the win condition.
6. The move with the highest grade is played for real.

The AI runs on a dedicated `Thread` (`AI.java`) with a 1-second sleep before moving
so the human has a chance to see the board state and the move feels less abrupt.

## Author

**Mariel Borodkin** — final project, Software Technician track,
ORT Hermelin College, Netanya (2017).
Mentored by Michael Chernobalsky and Zehava Yakobson.

The "Game creator: Mariel Borodkin" credit is also visible in-game on the
**Help → About** screen.

## Original project paper

The full project paper (in Hebrew) — covering motivation, design decisions,
class diagrams, the AI heuristic, and the complete annotated source — is
included in this repository:

- [docs/abalone-original-paper.pdf](docs/abalone-original-paper.pdf) (78 pages, Hebrew)

## License

This project is published as-is for educational and portfolio purposes. No formal
license is attached; please contact the author before reusing substantial parts of
the code.
