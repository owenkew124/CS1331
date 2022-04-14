/**
 * Represents a Car with a name, make, fuelLevel, horsepower
 * upgrade status, and max fuel level. Parent abstract class of
 * other car types.
 *
 *@author okew3
 *@version 11.0.9.1
 *
 */
public abstract class Car {
    private String name;
    private String make;
    private double fuelLevel;
    private int horsepower;
    private boolean previousUpgrade;
    protected static final double MAX_FUEL_LEVEL = 100;
    /**
     * Creates a Car with all required parameters.
     *
     * @param name the name of the car
     * @param make the make of the car
     * @param fuelLevel the fuel level of the car
     * @param horsepower the horsepower of the car
     * @param previousUpgrade the upgrade status of the car
     */
    public Car(String name, String make, double fuelLevel, int horsepower, boolean previousUpgrade) {
        this.name = name;
        this.make = make;
        if (fuelLevel >= MAX_FUEL_LEVEL) {
            this.fuelLevel = MAX_FUEL_LEVEL;
        } else {
            this.fuelLevel = fuelLevel;
        }
        this.horsepower = horsepower;
        this.previousUpgrade = previousUpgrade;
    }
    /**
     * @return a string representation of this Car
     */
    @Override
    public String toString() {
        return String.format("Car named: %s, Make: %s, Fuel Amount: %.1f, HP: %d", name, make, fuelLevel, horsepower);
    }
    /**
     * Compares two cars to see if they are the same.
     *
     * @param c a car to compare this car to
     * @return a boolean of whether the cars are equal
     */
    @Override
    public boolean equals(Object c) {
        if (!(c instanceof Car)) {
            return false;
        }
        Car car = (Car) c;
        return (name == car.name) && (make == car.make) && (horsepower == car.horsepower);

    }

    /**
     * Checks if the car has had an upgrade and if it
     * hasn't upgrades it by increasing the horsepower
     * of the car.
     */
    public void upgrade() {
        if (!(this.previousUpgrade)) {
            if (this.horsepower < 200) {
                this.horsepower += 100;
                this.previousUpgrade = true;
            } else if (this.horsepower >= 200) {
                this.horsepower += 50;
                this.previousUpgrade = true;
            }
        }
    }

    /**
     * An abstract method to be implemented in subclasses.
     * @param c a car to race against
     */
    public abstract void race(Car c);
    /**
     * Sets the name of this Car.
     *
     *@param name the name of the car
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Sets the make of this Car.
     *
     *@param make the make of the car
     */
    public void setMake(String make) {
        this.make = make;
    }
    /**
     * Sets the fuel level of this Car.
     *
     *@param fuelLevel the fuel level of the car
     */
    public void setFuelLevel(double fuelLevel) {
        this.fuelLevel = fuelLevel;
    }
    /**
     * Sets the horsepower of this Car.
     *
     *@param horsepower the horsepower of the car
     */
    public void setHorsepower(int horsepower) {
        this.horsepower = horsepower;
    }
    /**
     * Sets the upgrade status of this Car.
     *
     *@param previousUpgrade the upgrade status of the car
     */
    public void setPreviousUpgrade(boolean previousUpgrade) {
        this.previousUpgrade = previousUpgrade;
    }
    /**
     * @return the name of this Car
     */
    public String getName() {
        return name;
    }
    /**
     * @return the make of this Car
     */
    public String getMake() {
        return make;
    }
    /**
     * @return the fuel level of this Car
     */
    public double getFuelLevel() {
        return fuelLevel;
    }
    /**
     * @return the horsepower of this Car
     */
    public int getHorsepower() {
        return horsepower;
    }
    /**
     * @return the upgrade status of this Car
     */
    public boolean getPreviousUpgrade() {
        return previousUpgrade;
    }
}