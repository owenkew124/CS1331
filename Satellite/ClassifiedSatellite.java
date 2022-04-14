import java.util.Scanner;
/**
 * Represents a Classified Satellite which has all the instance variables
 * of the Satellite class. A subclass of the Satellite class.
 *
 *@author okew3
 *@version 11.0.9.1
 *
 */
public class ClassifiedSatellite extends Satellite {
    /**
     * Creates a Classified Satellite object with all required parameters.
     * @param name the name of the satellite
     * @param catalogNumber the catalog number of the satellite
     * @param launchYear the year the satellite was launched
     * @param launchDay the day the satellite was launched
     * @param inclination the angle of the orbit of the satellite
     *                    in relation to the Earth's equator
     * @param meanMotion the number of orbits the satellite goes through
     *                   per day
     */
    public ClassifiedSatellite(String name, int catalogNumber, int launchYear, int launchDay,
                        double inclination, double meanMotion) {
        super(name, catalogNumber, launchYear, launchDay, inclination, meanMotion);
    }
    /**
     * @return a String representation of this satellite protected by a password
     */
    public String toString() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Password:");
        String pass = scan.nextLine();
        if (pass.equals("f8ee89496da476b3849f4de45a4528b4")) {
            return super.toString();
        } else {
            String incorrect = "INCORRECT PASSWORD";
            return incorrect;
        }
    }
    /**
     * @return a String represention in the form of a CSV
     *         protected by a password
     */
    public String encodeCSV() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Password:");
        String pass = scan.nextLine();
        if (pass.equals("f8ee89496da476b3849f4de45a4528b4")) {
            return super.encodeCSV();
        } else {
            String incorrect = "INCORRECT PASSWORD";
            return incorrect;
        }
    }
}