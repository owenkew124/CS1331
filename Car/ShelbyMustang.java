/**
 * Represent a Shelby Mustang which is a subclass of
 * the Car class. It has the instance variables
 * of a Car along with races won.
 *
 *@author okew3
 *@version 11.0.9.1
 *
 */
public class ShelbyMustang extends Car {
    private int racesWon;
    /**
     * Creates a Shelby Mustang with all required parameters.
     *
     * @param name the name of the Shelby Mustang
     * @param make the make of the Shelby Mustang
     * @param fuelLevel the fuel level of the Shelby Mustang
     * @param horsepower the horsepower of the Shelby Mustang
     * @param previousUpgrade the upgrade status of the Shelby Mustang
     * @param racesWon the races won by this Shelby Mustang
     */
    public ShelbyMustang(String name, String make, double fuelLevel, int horsepower,
                         boolean previousUpgrade, int racesWon) {
        super(name, make, fuelLevel, horsepower, previousUpgrade);
        this.racesWon = racesWon;
    }
    /**
     * Creates a Shelby Mustang with the name Carroll, make Shelby Automotives,
     * fuelLevel 100, 350 horsepower, no upgrade, and 0 races won.
     */
    public ShelbyMustang() {
        this("Carroll", "Shelby Automotives", 100, 350, false, 0);
    }
    /**
     * @return a string representation of this Car
     */
    @Override
    public String toString() {
        String carString = super.toString();
        return carString + " Races Won: " + this.racesWon;
    }
    /**
     * Compares two cars to see if they are the same.
     *
     * @param c a car to compare this car to
     * @return a boolean of whether the cars are equal
     */
    @Override
    public boolean equals(Object c) {
        boolean equalCar = super.equals(c);
        if (!(c instanceof ShelbyMustang)) {
            return false;
        }
        ShelbyMustang car = (ShelbyMustang) c;
        return (equalCar && racesWon == car.racesWon);
    }
    /**
     * Takes in another car for this FordGT to race.
     * If they are low on fuel or are not both FordGTs
     * then they can't race. The FordGT with more horsepower
     * and, if that is equal weight, wins the race.
     * Overrides the Car class's race abstract method.
     *
     * @param car another car for the FordGT to race
     */
    @Override
    public void race(Car car) {
        if (this.getClass() == car.getClass() && super.getFuelLevel() > 50 && car.getFuelLevel() > 50) {
            ShelbyMustang c = (ShelbyMustang) car;
            super.setFuelLevel(super.getFuelLevel() - 25);
            c.setFuelLevel(c.getFuelLevel() - 25);
            if (super.getHorsepower() > c.getHorsepower()) {
                System.out.println(super.getName() + " won against " + c.getName());
                this.racesWon += 1;
            } else if (c.getHorsepower() > super.getHorsepower()) {
                System.out.println(c.getName() + " won against " + super.getName());
                c.racesWon += 1;
            } else if (this.racesWon > c.racesWon) {
                System.out.println(super.getName() + " won against " + c.getName());
                this.racesWon += 1;
            } else if (c.racesWon > this.racesWon) {
                System.out.println(c.getName() + " won against " + super.getName());
                c.racesWon += 1;
            } else {
                System.out.println(c.getName() + " tied with " + super.getName());
            }
        } else {
            System.out.println(car.getName() + " could not race " + super.getName());
        }
    }
}