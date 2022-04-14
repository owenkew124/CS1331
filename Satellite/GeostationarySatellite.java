/**
 * Represents a Geostationary Satellite which orbits once per day
 * and has all the other instance variables of the Satellite class.
 * A subclass of the Satellite class.
 *
 *@author okew3
 *@version 11.0.9.1
 *
 */
public class GeostationarySatellite extends Satellite {
    /**
     * Creates a Satellite object with a mean motion of 1 orbit per day.
     * @param name the name of the satellite
     * @param catalogNumber the catalog number of the satellite
     * @param launchYear the year the satellite was launched
     * @param launchDay the day the satellite was launched
     * @param inclination the angle of the orbit of the satellite
     *                    in relation to the Earth's equator
     */
    public GeostationarySatellite(String name, int catalogNumber, int launchYear, int launchDay, double inclination) {
        super(name, catalogNumber, launchYear, launchDay, inclination, 1.0);
    }
}