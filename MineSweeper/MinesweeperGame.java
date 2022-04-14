import java.util.Random;
import java.util.HashSet;
public class MinesweeperGame {
	private final boolean TESTING_MODE = false;
	private Tile[][] minesweeperBoard;
    private boolean[][] isVisible;
	private int numMines;
	private boolean firstCheck;
	private boolean isWon;
	private boolean isLost;
    private Difficulty gameDifficulty;
	private int xDimension;
	private int yDimension;

	public MinesweeperGame(Difficulty gameDifficulty) {
		this.minesweeperBoard = new Tile[15][15];
		switch (gameDifficulty) {
			case EASY:
				this.numMines = 10;
				break;
			case MEDIUM:
				this.numMines = 50;
                break;
			case HARD:
				this.numMines = 99;
                break;
		}
		this.firstCheck = false;
		this.isWon = false;
		this.isLost = false;
		this.xDimension = 15;
		this.yDimension = 15;
        this.gameDifficulty = gameDifficulty;
        this.isVisible = new boolean[15][15];
        if (TESTING_MODE) {
            this.numMines = 15;
        }
	}

    /**
     * checks the specified location. If it is the first check, it will initialize the board with bombs.
     * If the location is already revealed returns null.
     * @param  y y coordinate of where to check for bomb
     * @param  x x coordinate of where to check for bomb
     * @return   an array of tiles. If the location was a mine, the tile will have isMine = true. Otherwise, returns all tiles that were revealed by the check.
     */
	public Tile[] check(int y, int x) {
        // if this is our first check, initialize the board.
		if (this.firstCheck == false) {
			this.firstCheck = true;
			this.generateBoard(y, x, numMines);
		}
        if (this.isVisible[y][x]) {
            return null;
        }
        if (this.minesweeperBoard[y][x].isMine()) {
            Tile[] returnArray = new Tile[1];
            returnArray[0] = this.minesweeperBoard[y][x];
            this.isLost = true;
            return returnArray;
        }

        HashSet<Tile> toReveal = new HashSet<Tile>();
        this.computeFreeSpace(y, x, toReveal);
        Tile[] returnArray = toReveal.toArray(new Tile[toReveal.size()]);
        this.checkDone();
        return returnArray;

	}

    /**
     * returns whether this game is won
     * @return whether or not the game is won
     */
    public boolean isWon() {
        return this.isWon;
    }

    /**
     * return whether this game is lost
     * @return whether or not the game is lost
     */
    public boolean isLost() {
        return this.isLost;
    }

    public boolean[][] getIsVisible() {
        return this.isVisible;
    }

    /**
     * helper method to print board - prints A in null elements, O in non-mine tiles, X in mine tiles
     */
    public void printBoard() {
        for (int yIndex = 0; yIndex < yDimension; yIndex++) {
            for (int xIndex = 0; xIndex < xDimension; xIndex++) {
                if (this.minesweeperBoard[yIndex][xIndex] == null) {
                    System.out.print("A ");
                } else if (this.minesweeperBoard[yIndex][xIndex].isMine() == false) {
                    System.out.print("O ");
                } else {
                    System.out.print("X ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * helper method to see what is visible on board - prints the number of each revealed location and ? in unrevealed locations
     */
    public void printBoardVisible() {
        for (int yIndex = 0; yIndex < yDimension; yIndex++) {
            for (int xIndex = 0; xIndex < xDimension; xIndex++) {
                if ((!this.minesweeperBoard[yIndex][xIndex].isMine() && isVisible[yIndex][xIndex])) {
                    System.out.print(this.minesweeperBoard[yIndex][xIndex].getBorderingMines() + " ");
                } else {
                    System.out.print("? ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

	/**
	 * places tiles on the board - algorithm inspiration from https://www.geeksforgeeks.org/cpp-implementation-minesweeper-game/
     * 1. initialize empty tiles around first check, so they do not instantly hit a bomb. Also create a small pocket for playability.
     * 2. place bombs randomly in remaining space.
     * 3. fill non-bomb areas with non-bomb tiles
     * 4. loop through the entire board and calculate how many bombs the non-bomb tiles are adjacent to.
     *
	 * @param y        the y location of the first tile clicked
	 * @param x        the x location of the first tile clicked
	 * @param numMines number of mines on the board
	 */
	private void generateBoard(int y, int x, int numMines) {

        // place non-bomb tiles where the user first clicked and in its adjacent squares
        this.minesweeperBoard[y][x] = new Tile(y, x, false);
        int initial = this.placeInitial(y, x);
        // generate a test board - bombs along main diagonal unless you click there to init
        if (this.TESTING_MODE == true) {
            for (int i = 0; i < xDimension; i++) {
                // if they checked on the diagonal at first, don't place bombs there
                if (this.minesweeperBoard[i][i] != null) {
                    this.numMines--;
                } else {
                    this.minesweeperBoard[i][i] = new Tile(i, i, true);
                }
            }
        } else {
            // randomly place bombs anywhere except where the initial click is
            Random myRand = new Random();
            for (int i = 0; i < numMines; i++) {
                placeBombRandomly(myRand, i, initial);
            }
        }
        // places non-bomb tiles at each empty location.
		for (int yIndex = 0; yIndex < yDimension; yIndex++) {
            for (int xIndex = 0; xIndex < xDimension; xIndex++) {
                if (this.minesweeperBoard[yIndex][xIndex] == null) {
                    this.minesweeperBoard[yIndex][xIndex] = new Tile(yIndex, xIndex, false);
                }
            }
        }
        // figure out how many adjacent bombs each non-mine tile has and set the corresponding value.
        for (int yIndex = 0; yIndex < yDimension; yIndex++) {
            for (int xIndex = 0; xIndex < xDimension; xIndex++) {
                if (this.minesweeperBoard[yIndex][xIndex].isMine() == false) {
                    this.minesweeperBoard[yIndex][xIndex].setBorderingMines(this.calculateBorderingMines(yIndex, xIndex));
                }
            }
        }
	}

    // places a bomb at a random free location on the board.
	private void placeBombRandomly(Random myRand, int currBombNum, int initial) {
        // calculate how much free space is left on the board. Generate a random number in this free space.
        // we cannot place bombs where there are already bombs OR where the user initially checked/in a small area around it.
        // (total space - number of bombs down - number of space reserved for free tiles from the original click)
        int remainingSlots = this.xDimension * this.yDimension - currBombNum - initial;
		int newLocation = myRand.nextInt(remainingSlots);

        // loop through all the tiles on the board
        // if the current tile has no bomb or is not reserved by the initial click of the user, incrememnt the amount of free space we've seen by 1
        // once we find the actual location, place a bomb there and return
		int traversedFreeTiles = 0;
        int actualLocation = 0;
		for (int yIndex = 0; yIndex < yDimension; yIndex++) {
			for (int xIndex = 0; xIndex < xDimension; xIndex++) {
				// reached bomb location
				if (traversedFreeTiles == newLocation && this.minesweeperBoard[yIndex][xIndex] == null) {
					int newY = actualLocation / xDimension;
					int newX = actualLocation % xDimension;
					minesweeperBoard[newY][newX] = new Tile(newY, newX, true);
                    return;
				} else if (this.minesweeperBoard[yIndex][xIndex] == null) { // current spot is free
					traversedFreeTiles++;
					actualLocation++;
				} else { //current spot is taken (reserved by click or already has a bomb), so do not increment how much free space we've seen
					actualLocation++;
				}
			}
		}
	}

    /**
     * counts how many bombs are adjacent to a given tile location
     * @param  y y coordinate of the tile location
     * @param  x x coordinate of the tile loation
     * @return   number of bombs adjacent to the tile location
     */
    private int calculateBorderingMines(int y, int x) {
        int totalBordering = 0;

        boolean topOfBoard = false;
        boolean bottomOfBoard = false;
        boolean leftOfBoard = false;
        boolean rightOfBoard = false;
        if (y == 0) {
            topOfBoard = true;
        }
        if (x == 0) {
            leftOfBoard = true;
        }
        if (y == this.yDimension - 1) {
            bottomOfBoard = true;
        }
        if (x == this.xDimension - 1) {
            rightOfBoard = true;
        }
        // check up
        if (!topOfBoard) {
            if (this.minesweeperBoard[y - 1][x].isMine()) {
                totalBordering++;
            }
        }
        // check left
        if (!leftOfBoard) {
            if (this.minesweeperBoard[y][x - 1].isMine()) {
                totalBordering++;
            }
        }
        // check down
        if(!bottomOfBoard) {
            if (this.minesweeperBoard[y + 1][x].isMine()) {
                totalBordering++;
            }
        }
        // check right
        if (!rightOfBoard) {
            if (this.minesweeperBoard[y][x + 1].isMine()) {
                totalBordering++;
            }
        }
        // check top left
        if (!(leftOfBoard || topOfBoard)) {
            if (this.minesweeperBoard[y - 1][x - 1].isMine()) {
                totalBordering++;
            }
        }
        // check bottom left
        if (!(leftOfBoard || bottomOfBoard)) {
            if (this.minesweeperBoard[y + 1][x - 1].isMine()) {
                totalBordering++;
            }
        }
        // check bottom right
        if (!(rightOfBoard || bottomOfBoard)) {
            if (this.minesweeperBoard[y + 1][x + 1].isMine()) {
                totalBordering++;
            }
        }
        // check top right
        if (!(rightOfBoard || topOfBoard)) {
            if (this.minesweeperBoard[y - 1][x + 1].isMine()) {
                totalBordering++;
            }
        }

        return totalBordering;

    }

    /**
     * Given a tile that was checked, recursively figures out what tiles to reveal.
     * @param y        y coordinate of the tile location
     * @param x        x coordinate of the tile location
     * @param toReveal a HashSet of all tiles revealed
     */
    private void computeFreeSpace(int y, int x, HashSet<Tile> toReveal) {
        // if it is a mine, don't add anything
        if (this.minesweeperBoard[y][x].isMine()) {
            return;
        }
        // if it is not a mine and is bordering negative mines, something bad happened D:
        if (this.minesweeperBoard[y][x].getBorderingMines() < 0) {
            System.out.println("ERROR - contact TA");
            return;
        }
        // if it is bordering mines, it should be revealed but not its neighbors
        if (this.minesweeperBoard[y][x].getBorderingMines() > 0) {
            this.isVisible[y][x] = true;
            toReveal.add(this.minesweeperBoard[y][x]);
            return;
        }
        // if it does not border mines, it should be revealed and all its adjacent tiles as well.
        // Recursively call this method on adjacent locations.
        this.isVisible[y][x] = true;
        toReveal.add(this.minesweeperBoard[y][x]);
        boolean topOfBoard = false;
        boolean bottomOfBoard = false;
        boolean leftOfBoard = false;
        boolean rightOfBoard = false;
        if (y == 0) {
            topOfBoard = true;
        }
        if (x == 0) {
            leftOfBoard = true;
        }
        if (y == this.yDimension - 1) {
            bottomOfBoard = true;
        }
        if (x == this.xDimension - 1) {
            rightOfBoard = true;
        }
        // check up
        if (!topOfBoard && this.isVisible[y - 1][x] == false) {
            computeFreeSpace(y - 1, x, toReveal);
        }
        // check left
        if (!leftOfBoard && this.isVisible[y][x - 1] == false) {
            computeFreeSpace(y, x - 1, toReveal);
        }
        // check down
        if(!bottomOfBoard && this.isVisible[y + 1][x] == false) {
            computeFreeSpace(y + 1, x, toReveal);
        }
        // check right
        if (!rightOfBoard && this.isVisible[y][x + 1] == false) {
            computeFreeSpace(y, x + 1, toReveal);
        }
        // check top left
        if (!(leftOfBoard || topOfBoard) && this.isVisible[y - 1][x - 1] == false) {
            computeFreeSpace(y - 1, x - 1, toReveal);
        }
        // check bottom left
        if (!(leftOfBoard || bottomOfBoard) && this.isVisible[y + 1][x - 1] == false) {
            computeFreeSpace(y + 1, x - 1, toReveal);
        }
        // check bottom right
        if (!(rightOfBoard || bottomOfBoard) && this.isVisible[y + 1][x + 1] == false) {
            computeFreeSpace(y + 1, x + 1, toReveal);
        }
        // check top right
        if (!(rightOfBoard || topOfBoard) && this.isVisible[y - 1][x + 1] == false) {
            computeFreeSpace(y - 1, x + 1, toReveal);
        }

    }

    /**
     * checks whether, based on the isVisible 2d array, if all non-bomb locations are visible. If so, sets isWon to true.
     */
    private void checkDone() {
        for (int yIndex = 0; yIndex < yDimension; yIndex++) {
            for (int xIndex = 0; xIndex < xDimension; xIndex++) {
                if (!this.minesweeperBoard[yIndex][xIndex].isMine() && !isVisible[yIndex][xIndex]) {
                    return;
                }
            }
        }
        this.isWon = true;
    }


    /**
     * places an initial non-bomb tile on the location specified, as well in the adjacent tiles.
     * This is so users do not click a new grid and hit  bomb.
     * @param  y y coordinate of tile - where the first check happens
     * @param  x x coordinate of tile - where the first check happens
     * @return   number of initial tiles added (might be less than 9 if in a corner)
     */
    private int placeInitial(int y, int x) {
        int initial = 1;
        boolean topOfBoard = false;
        boolean bottomOfBoard = false;
        boolean leftOfBoard = false;
        boolean rightOfBoard = false;
        if (y == 0) {
            topOfBoard = true;
        }
        if (x == 0) {
            leftOfBoard = true;
        }
        if (y == this.yDimension - 1) {
            bottomOfBoard = true;
        }
        if (x == this.xDimension - 1) {
            rightOfBoard = true;
        }

        if (!topOfBoard) {
            this.minesweeperBoard[y - 1][x] = new Tile(y - 1, x, false);
            initial++;
        }
        // check left
        if (!leftOfBoard) {
            this.minesweeperBoard[y][x - 1] = new Tile(y, x - 1, false);
            initial++;
        }
        // check down
        if(!bottomOfBoard) {
            this.minesweeperBoard[y + 1][x] = new Tile(y + 1, x, false);
            initial++;
        }
        // check right
        if (!rightOfBoard) {
            this.minesweeperBoard[y][x + 1] = new Tile(y, x + 1, false);
            initial++;

        }
        // check top left
        if (!(leftOfBoard || topOfBoard)) {
            this.minesweeperBoard[y - 1][x - 1] = new Tile(y - 1, x - 1, false);
            initial++;
        }
        // check bottom left
        if (!(leftOfBoard || bottomOfBoard)) {
            this.minesweeperBoard[y + 1][x - 1] = new Tile(y + 1, x - 1, false);
            initial++;
        }
        // check bottom right
        if (!(rightOfBoard || bottomOfBoard)) {
            this.minesweeperBoard[y + 1][x + 1] = new Tile(y + 1, x + 1, false);
            initial++;
        }
        // check top right
        if (!(rightOfBoard || topOfBoard)) {
            this.minesweeperBoard[y - 1][x + 1] = new Tile(y - 1, x + 1, false);
            initial++;
        }

        return initial;

    }
}