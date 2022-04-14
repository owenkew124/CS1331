/**
 * Represent a FordGT which is a subclass of
 * the Car class. It has the instance variables
 * of a Car along with a weight and a driverName.
 *
 *@author okew3
 *@version 11.0.9.1
 *
 */
public class FordGT extends Car {
    private double weight;
    private String driverName;
    /**
     * Creates a FordGT with all required parameters.
     *
     * @param name the name of the FordGT
     * @param make the make of the FordGT
     * @param fuelLevel the fuel level of the FordGT
     * @param horsepower the horsepower of the FordGT
     * @param previousUpgrade the upgrade status of the FordGT
     * @param weight the weight of the FordGT
     * @param driverName the name of the driver of the FordGT
     */
    public FordGT(String name, String make, double fuelLevel, int horsepower,
                  boolean previousUpgrade, double weight, String driverName) {
        super(name, make, fuelLevel, horsepower, previousUpgrade);
        this.weight = weight;
        this.driverName = driverName;
    }
    /**
     * Creates a FordGT with the name Pony, make Ford, fuelLevel 100,
     * 450 horsepower, no upgrade, a weight of 3300, a driver named
     * Ken Miles.
     */
    public FordGT() {
        this("Pony", "Ford", 100, 450, false, 3300, "Ken Miles");
    }
    /**
     * @return a string representation of this FordGT
     */
    @Override
    public String toString() {
        String carString = super.toString();
        return (carString + " Weight: " + this.weight + " Driver: " + this.driverName);
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
        if (!(c instanceof FordGT)) {
            return false;
        }
        FordGT car = (FordGT) c;
        return (equalCar && this.weight == car.weight);

    }
    /**
     * Checks if the FordGT has had an upgrade and if it
     * hasn't upgrades it by increasing the horsepower and
     * fuel level of the car. Overrides the Car class's
     * upgrade method.
     */
    @Override
    public void upgrade() {
        if (!(super.getPreviousUpgrade())) {
            if (super.getHorsepower() < 200) {
                super.setHorsepower(super.getHorsepower() + 100);
            } else if (super.getHorsepower() >= 200) {
                super.setHorsepower(super.getHorsepower() + 50);
            }
            if (super.getFuelLevel() >= MAX_FUEL_LEVEL - 50) {
                super.setFuelLevel(MAX_FUEL_LEVEL);
            } else {
                super.setFuelLevel(super.getFuelLevel() + 50);
            }
            super.setPreviousUpgrade(true);
        }
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
            FordGT c = (FordGT) car;
            super.setFuelLevel(super.getFuelLevel() - 25);
            c.setFuelLevel(c.getFuelLevel() - 25);
            if (super.getHorsepower() > c.getHorsepower()) {
                System.out.println(super.getName() + " with " + this.driverName + "as the driver won against "
                                   + c.getName() + " with " + c.driverName + " as the driver");
            } else if (c.getHorsepower() > super.getHorsepower()) {
                System.out.println(c.getName() + " with " + c.driverName + "as the driver won against "
                                   + super.getName() + " with " + this.driverName + " as the driver");
            } else if (this.weight > c.weight) {
                System.out.println(super.getName() + " with " + this.driverName + "as the driver won against "
                                   + c.getName() + " with " + c.driverName + " as the driver");
            } else if (c.weight > this.weight) {
                System.out.println(c.getName() + " with " + c.driverName + "as the driver won against "
                                   + super.getName() + " with " + this.driverName + " as the driver");
            } else {
                System.out.println(c.getName() + " tied with " + super.getName());
            }
        } else {
            System.out.println(car.getName() + " could not race " + super.getName());
        }
    }
}