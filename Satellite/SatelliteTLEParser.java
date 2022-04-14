import java.util.Scanner;

public class SatelliteTLEParser {
    public static Satellite parseData(String data) {
        Scanner sc = new Scanner(data);
        String name = sc.nextLine().trim();

        // skip line number
        sc.next();

        String logNumberAndClassification = sc.next();
        int logNumber =
            Integer.parseInt(logNumberAndClassification.substring(0, 5));
        boolean unclassified =
            logNumberAndClassification.substring(5, 6).equals("U") ? true : false;

        // skip international classification
        sc.next();

        String yearAndDay = sc.next();
        int year = Integer.parseInt(yearAndDay.substring(0, 2));
        int day = (int)
            Double.parseDouble(yearAndDay.substring(2, yearAndDay.length()));
        if (year > 56) {
            year = 1900 + year;
        } else {
            year = 2000 + year;
        }

        // discard rest of line
        sc.nextLine();
        // discard line number and catalog number
        sc.next();
        sc.next();

        double inclination = sc.nextDouble();

        // ignore fourth, fifth, sixth tokens
        sc.next();
        sc.next();
        sc.next();
        sc.next();

        String meanMotionAndOther = sc.next();
        double meanMotion =
            Double.parseDouble(meanMotionAndOther.substring(0, 11));

        boolean geostationary = (inclination > -3 && inclination < 3)
                                    && (meanMotion < 1.01 && meanMotion > 0.99);
        if (!unclassified) {
            return new ClassifiedSatellite(name, logNumber, year, day, inclination, meanMotion);
        } else if (geostationary) {
            return new GeostationarySatellite(name, logNumber, year, day, inclination);
        } else {
            return new Satellite(name, logNumber, year, day, inclination, meanMotion);
        }
    }
}
