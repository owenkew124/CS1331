import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
/**
 * An object that has a method that creates an array of Satellite objects and
 * creates a csv file containing the information of each Satellite in the array.
 *
 *@author okew3
 *@version 11.0.9.1
 *
 */
class SatelliteDataConverter {
    /**
     * Creates an array of Satellite Objects that are in a folder.
     * @param f a folder with Satellite objects
     * @return an array of Satellite Objects
     */
    private static Satellite[] createSatelliteArray(File f) {
        Satellite[] emptySatelliteArray = {};
        try {
            int count = 0;
            String[] files = f.list();
            for (String fileName : files) {
                count += 1;
            }
            Satellite[] satelliteArray = new Satellite[count];
            int fileIndex = 0;
            for (String fileName : files) {
                File myFile = new File(f.getPath() + "/" + fileName);
                Scanner scan = new Scanner(myFile);
                String object = "";
                while (scan.hasNextLine()) {
                    object = object + scan.nextLine() + "\n";
                }
                satelliteArray[fileIndex] = SatelliteTLEParser.parseData(object);
                fileIndex += 1;
            }
            return satelliteArray;
        } catch (FileNotFoundException fnfe) {
            System.out.println(fnfe.getMessage());
            return emptySatelliteArray;
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
            return emptySatelliteArray;
        }
    }

    /**
     * Creates a csv file that contains all the information of the
     * satellites in a folder.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        try {
            File folder = new File(args[0]);
            Satellite[] satelliteArray = createSatelliteArray(folder);
            FileWriter fw = new FileWriter("satellite_output.csv");
            for (Satellite s : satelliteArray) {
                fw.write(s.encodeCSV());
            }
            fw.close();
        } catch (FileNotFoundException fnfe) {
            System.out.println(fnfe.getMessage());
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }
}