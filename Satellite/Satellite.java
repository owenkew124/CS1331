/**
 * Represents a Satellite with a name, catalog number, launch year,
 * launch day, inclination, and number of orbits per day. Parent class of
 * other satellite types.
 *
 *@author okew3
 *@version 11.0.9.1
 *
 */
public class Satellite {
    private String name;
    private int catalogNumber;
    private int launchYear;
    private int launchDay;
    private double inclination;
    private double meanMotion;

    /**
     * Creates a Satellite object with all required parameters.
     * @param name the name of the satellite
     * @param catalogNumber the catalog number of the satellite
     * @param launchYear the year the satellite was launched
     * @param launchDay the day the satellite was launched
     * @param inclination the angle of the orbit of the satellite
     *                    in relation to the Earth's equator
     * @param meanMotion the number of orbits the satellite goes through
     *                   per day
     */
    public Satellite(String name, int catalogNumber, int launchYear, int launchDay,
               double inclination, double meanMotion) {
        this.name = name;
        this.catalogNumber = catalogNumber;
        this.launchYear = launchYear;
        this.launchDay = launchDay;
        this.inclination = inclination;
        this.meanMotion = meanMotion;
    }

    /**
     * Calculates the time it takes for this satellite to orbit the Earth.
     * @return the time it takes for this satellite to orbit the Earth
     *         in minutes
     */
    public double orbitTime() {
        double time;
        time = (24 * 60) / this.meanMotion;
        return time;
    }

    /**
     * @return a String representation of this satellite
     */
    @Override
    public String toString() {
        return String.format("%s: #%d. Launched on day %d of %d. Inclination: %f degrees. %f orbits per day - "
                             + "%f minutes per orbit.\n", name, catalogNumber, launchDay, launchYear, inclination,
                             meanMotion, orbitTime());
    }

    /**
     * @return a String represention in the form of a CSV
     */
    public String encodeCSV() {
        return String.format("%s, %d, %d, %d, %f, %f, %f\n",
                             name, catalogNumber, launchYear, launchDay, inclination, meanMotion, orbitTime());
    }
}