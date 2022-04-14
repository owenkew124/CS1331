/**
 * An Interface that has a dance method, and a wonDance method.
 *
 *@author okew3
 *@version 11.0.9.1
 */
public interface Danceable {
    /**
     * A method that makes a monster dance. This method needs
     * to be implemented in another class.
     * @return An int to be determined in another class
     */
    int dance();
    /**
     * A method which indicates that a monster won a dance battle.
     * This method needs to be implemented in another class
     */
    void wonDance();
}