package mazeGenerator;

import java.util.ArrayList;
import java.util.Random;

public class Maze {
	private Cell[][] maze;
	ArrayList<Cell> frontiers = new ArrayList<Cell>();
	Cell Start;
	int i;
	int j;
	
	public Cell getStart() {
		return Start;
	}

	public void setStart(Cell start) {
		Start = start;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public int getJ() {
		return j;
	}

	public void setJ(int j) {
		this.j = j;
	}

	
	
	
	public Cell[][] getMaze() {
		return maze;
	}

	public void setMaze(Cell[][] maze) {
		this.maze = maze;
	}

	public ArrayList<Cell> getFrontiers() {
		return frontiers;
	}

	public void setFrontiers(ArrayList<Cell> frontiers) {
		this.frontiers = frontiers;
	}

	

	


	public static boolean getRandomBoolean() {
		return Math.random() < 0.5;
	}

	public void generateMaze(int x, int y) {
		Random random = new Random();
		maze = new Cell[x][y];
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				maze[i][j] = new Cell(getRandomBoolean(), i, j, null);
			}
		}
		Start = new Cell(getRandomBoolean(), random.ints(0, x).findFirst().getAsInt(),
				random.ints(0, y).findFirst().getAsInt(), null);
		maze[Start.getX()][Start.getY()] = Start;
		Start.setStart(true);
		getNeighborCells(Start);
		Cell last = null;
		while (!frontiers.isEmpty()) {
			// pick a random cell from the frontiers(neighbors)
			Cell current = frontiers.remove((int) (Math.random() * frontiers.size()));
			Cell opposite = getOpposite(current);
			try {
				// if both node and its opposite are walls
				if (maze[current.getX()][current.getY()].isBlocked()
						&& maze[opposite.getX()][opposite.getY()].isBlocked()) {
					// open path between the nodes
					maze[current.getX()][current.getY()].setBlocked(false);
					maze[opposite.getX()][opposite.getY()].setBlocked(false);

					// generate pokemons randomly in unblocked cells
					maze[current.getX()][current.getY()].addPokemon(getRandomBoolean());
					maze[opposite.getX()][opposite.getY()].addPokemon(getRandomBoolean());

					// store last node in order to mark it later
					last = opposite;
					// iterate through direct neighbors of node, same as earlier
					getNeighborCells(opposite);
				}
			} catch (Exception e) { // ignore NullPointer and
				// ArrayIndexOutOfBounds
			}
			if (opposite == null) {
				last = current;
			}
			if (frontiers.isEmpty()) {
				maze[last.getX()][last.getY()].setEnd(true);
				maze[last.getX()][last.getY()].setBlocked(false);
			}
		}
		drawMaze();
	}

	public void getNeighborCells(Cell x) {
		for (int i = -1; i <= 1; i++)
			for (int j = -1; j <= 1; j++) {
				if (i == 0 && j == 0 || i != 0 && j != 0) // to make sure it's a
					// neighbor cell
					continue;
				try {
					if (!maze[x.getX() + i][x.getY() + j].isBlocked())
						continue;
				} catch (Exception e) { // ignore ArrayIndexOutOfBounds
					continue;
				}
				// Create new node for the frontiers and set their parents
				Cell frontierCell = new Cell(getRandomBoolean(), x.getX() + i, x.getY() + j, x);
				// add eligible points to frontier
				frontiers.add(frontierCell);
			}
	}

	// compute opposite node(cell) on the other direction of the parent cell
	public Cell getOpposite(Cell cell) {
		if (cell.getX().compareTo(cell.getParent().getX()) != 0) {
			return new Cell(getRandomBoolean(), cell.getX() + cell.getX().compareTo(cell.getParent().getX()),
					cell.getY(), cell);
		}
		if (cell.getY().compareTo(cell.getParent().getY()) != 0) {
			return new Cell(getRandomBoolean(), cell.getX(),
					cell.getY() + cell.getY().compareTo(cell.getParent().getY()), cell);
		}
		return null;
	}

	public void drawMaze() {
		System.out.print(" ");
		for (int i = 0; i < maze[0].length; i++) {
			System.out.print("_ ");
		}
		for (int i = 0; i < maze.length; i++) {
			System.out.println();
			for (int j = 0; j < maze[i].length; j++) {
				if (j == 0) {
					System.out.print("|");
				}
				if (maze[i][j].isStart()) {
					System.out.print("S ");
				} else {
					if (maze[i][j].isEnd()) {
						System.out.print("E ");
					} else {
						if (maze[i][j].isBlocked()) {
							System.out.print("* ");
						} else {
							if (maze[i][j].hasPokemon()) {
								System.out.print("P ");
							} else {
								System.out.print(". ");
							}
						}
					}
				}

			}
		}
		System.out.println();
		System.out.print(" ");
		for (int i = 0; i < maze[0].length; i++) {
			System.out.print("_ ");
		}
	}

	public void genMaze() {
		Random random = new Random();

		int x = random.ints(1, 10).findFirst().getAsInt();
		int y = random.ints(1, 10).findFirst().getAsInt();
		i = x;
		j = y;
		System.out.println(x + " " + y + " ");
		generateMaze(10, 10);
	}

	public static void main(String[] args) {
		Maze maze = new Maze();
		maze.genMaze();

	}

}
