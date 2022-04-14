/**
 * Represents a Monster with a name and a spookiness rating.
 * Superclass of other types of Monsters
 *
 *@author okew3
 *@version 11.0.9.1
 */
public class Monster implements Comparable<Monster> {
    private String name;
    private int spookiness;
    /**
     * Creates a Monster will all necessary instance variables.
     * @param name The name of the Monster
     * @param spookiness the spookiness rating of the Monster
     */
    public Monster(String name, int spookiness) {
        this.name = name;
        this.spookiness = spookiness;
    }
    /**
     * Compares two Monsters. Overrides the compareTo method.
     * @param otherMonster Another Monster to compare this Monster to
     * @return returns whether the Monster is greater to, less than, or equal to
     *         the other Monster.
     */
    @Override
    public int compareTo(Monster otherMonster) {
        if (this.spookiness < otherMonster.getSpookiness()) {
            return -1;
        } else if (this.spookiness > otherMonster.getSpookiness()) {
            return 1;
        } else {
            return 0;
        }
    }
    @Override
    /**
     *
     * @return A String representation of the Monster
     */
    public String toString() {
        return String.format("%s has a spook rating of %d", name, spookiness);
    }
    /**
     * A Getter for the name of the Monster.
     * @return the name of the Monster
     */
    public String getName() {
        return name;
    }
    /**
     * A Getter for the spookiness of the Monster.
     * @return the spookiness of the Monster
     */
    public int getSpookiness() {
        return spookiness;
    }

    /**
     * Setter for the name of the Monster.
     * @param name a name which needs to be set as the name of the Monster
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter for the spookiness of the Monster.
     * @param spookiness a spookiness rating which needs to be set as
     *                   the spookiness of the Monster
     */
    public void setSpookiness(int spookiness) {
        this.spookiness = spookiness;
    }
}
