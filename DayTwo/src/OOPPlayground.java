

public class OOPPlayground {
    public static void main(String[] args) {
        /*Car c = new Car();
        c.make = "Subaru";
        c.model = "Crosstrek";
        c.mileage = 48000;
        */
        Car c = new Car(48000, "Subaru", "Crosstrek");

        c.describeSelf();

        Car empty = new Car();
        empty.describeSelf();

        //System.out.println(Car.desc);
        //System.out.println(c.desc);

        System.out.println(Car.milesToKilometers(c.mileage));
        System.out.println(Car.milesToKilometers(c));

    }
}

class Car {
    static String desc = "A car is a vehicle with 4 wheels";
    int mileage;
    String make;
    String model;

    public int getMileage() {
        return this.mileage;
    }
    public void setMileage(int mileage) {
        if (mileage >= 0)
            this.mileage = mileage;
    }

    public String getMake() {
        return this.make;
    }
    public void setMake(String make) {
        if (make != null)
            this.make = make;
    }

    public String getModel() {
        return this.model;
    }
    public void setModel(String model) {
        if (model != null)
            this.model = model;
    }

    Car () {
        this.mileage = 0;
        this.make = "Unknown";
        this.model = "Unknown";
    }

    Car (int mileage, String make, String model) {
        setMileage(mileage);
        setMake(make);
        setModel(model);
    }

    public static double milesToKilometers(double miles) {
        double conversionRate = 1.6;
        return conversionRate * miles;
    }

    public static double milesToKilometers(Car c) {
        return milesToKilometers(c.mileage);
    }

    void describeSelf() {
        System.out.println("Make: " + this.make + " Model: " + this.model + " Mileage: " + this.mileage);
    }

}