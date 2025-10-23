# PathMaster3000

A Java **Swing** grid-puzzle game. Move from **Start** to **End** across a grid of numbered cells, collecting the highest total while avoiding revisiting cells. Includes a resizable board, timer, score, hint system, and color customization.

## Features
- Resizable grid (e.g., 3x3, 5x5, …)
- Live **score**, **sum**, **path length**, and **timer**
- **Hint** button (suggests the best neighboring move)
- Appearance options: change **path** and **game** colors
- Simple, modular design: `PathMaster3000` (main), `GameState`, `GameGrid`, `GameStats`, `GameMenu`

## Requirements
- **JDK 17+** (OpenJDK works)
- Any OS with a terminal (or IntelliJ/VS Code)

## Run (terminal)
From the project root (where `src/` is):

```bash
# compile all sources into out/
javac -d out src/*.java

# run the app (main class has no package)
java -cp out PathMaster3000
