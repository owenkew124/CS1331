/**
 * Represents a Monster who can dance with the instance variables
 * of its parent class and implements the Danceable interface. A subclass
 * of the Monster class.
 *
 *@author okew3
 *@version 11.0.9.1
 */
public class DancingMonster extends Monster implements Danceable {
    private String danceMove;
    private int dancesWon;
    /**
     * Creates a Dancing Monster with a name, spookiness rating and dance move with zero
     * dances won.
     * @param name the name of the Dancing Monster
     * @param spookiness the spookiness rating of the Dancing Monster
     * @param danceMove the dance move of the Dancing Monster
     */
    public DancingMonster(String name, int spookiness, String danceMove) {
        super(name, spookiness);
        this.danceMove = danceMove;
        this.dancesWon = 0;
    }
    @Override
    /**
     * Represents a Dancing Monster Dancing. Implements the dance method
     * in the Danceable interface.
     * @return a string representation of the Dancing Monster dancing
     */
    public int dance() {
        System.out.printf("%s does the %s\n", this.getName(), danceMove);
        int rand = (int) (Math.random() * (this.getSpookiness() + 1));
        return rand;
    }
    @Override
    /**
     * Increments the dances won by the Dancing Monster. Implements the wonDance method
     * of the Danceable method.
     */
    public void wonDance() {
        dancesWon += 1;
    }
    @Override
    /**
     * @return a String representation of the Dancing Monster
     */
    public String toString() {
        return String.format("%s has a spook rating of %d and has won %d dances thus far",
                             this.getName(), this.getSpookiness(), dancesWon);
    }
    /**
     * A Getter for the dances won by the Dancing Monster.
     * @return the number of dances won by the Dancing Monster
     */
    public int getDancesWon() {
        return dancesWon;
    }
    /**
     * A Getter for the dance of the Dancing Monster.
     * @return the dance move of the Dancing Monster
     */
    public String getDanceMove() {
        return danceMove;
    }
    /**
     * Setter for the dance move of the Dancing Monster.
     * @param danceMove a dance move which needs to be set as
     *                   the dance move of the Dancing Monster
     */
    public void setDanceMove(String danceMove) {
        this.danceMove = danceMove;
    }
    /**
     * Setter for the dances won by the Dancing Monster.
     * @param dancesWon a number of dances won which needs to be set as
     *                   the dances won by the Dancing Monster
     */
    public void setDancesWon(int dancesWon) {
        this.dancesWon = dancesWon;
    }
}