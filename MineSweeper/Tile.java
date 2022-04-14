public class Tile {
    private int x;
    private int y;
    private boolean isMine;
    private int borderingMines;
    /**
     * creates a new Tile
     * @param  y      y coordinate of tile
     * @param  x      x coordinate of tile
     * @param  isMine whether the tile is a mine
     */
    public Tile(int y, int x, boolean isMine) {
        this.y = y;
        this.x = x;
        this.isMine = isMine;
        this.borderingMines = -1;
    }

    /**
     * creates a new Tile that is not a mine
     * @param  y      y coordinate of tile
     * @param  x      x coordinate of tile
     */
    public Tile(int y, int x) {
        this(y, x, false);
    }

    /**
     * returns the tile's x coordinate
     * @return the x coordinate
     */
    public int getX() {
        return this.x;
    }

    /**
     * returns the tile's y coordinate
     * @return the y coordinate
     */
    public int getY() {
        return this.y;
    }

    /**
     * returns the tile's x and y coordinates as an int array
     * @return an array containg [x coordinate, y coordinate]
     */
    public int[] getPointLocation() {
        return new int[]{this.x, this.y};
    }

    /**
     * returns whether the tile is is a mine or not
     * @return true if the tile is a mine, false otherwise
     */
    public boolean isMine() {
        return this.isMine;
    }

    /**
     * setter for number of bordering mines
     * @param  borderingMines number of bordering mines
     */
    public void setBorderingMines(int borderingMines) {
        this.borderingMines = borderingMines;
    }

    /**
     * getter for number of bordering mines
     * @return number of bordering mines
     */
    public int getBorderingMines() {
        return this.borderingMines;
    }

}