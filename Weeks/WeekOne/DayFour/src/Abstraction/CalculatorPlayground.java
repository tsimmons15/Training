package Abstraction;

public class CalculatorPlayground {
    public static void main(String[] args) {
        Calculator c = new FourFunction();
        System.out.println(c.add(1,2));
        System.out.println(c.add(1,2,3,4,5,6,7,8,9,10));
        System.out.println(c.inverse(3));
        System.out.println(c.negate(9));
        System.out.println(c.multiply(c.add(1,2), c.inverse(3)));
    }
}

interface Scientific {
    double logBase2(double num);
    double squareRoot(double num);
}

interface Calculator {
    final static double pi = 3.14;

    double add(double a, double b);
    double add(double... nums);
    double subtract(double a, double b);
    double subtract(double... nums);
    double multiply(double a, double b);
    double multiply(double... nums);
    double divide(double a, double b);
    double divide(double... nums);
    default double negate(double a) {
        return subtract(0, a);
    }
    default double inverse(double a) {
        return divide(1, a);
    }
}

class FourFunction implements Calculator {

    @Override
    public double add(double a, double b) {
        return a+b;
    }

    @Override
    public double add(double... nums) {
        double total = 0;
        for (double d : nums) {
            total = add(total, d);
        }

        return total;
    }

    @Override
    public double subtract(double a, double b) {
        return a-b;
    }

    @Override
    public double subtract(double... nums) {
        double total = 0;
        for (double d : nums) {
            total = subtract(total, d);
        }

        return total;
    }

    @Override
    public double multiply(double a, double b) {
        return a*b;
    }

    @Override
    public double multiply(double... nums) {
        double total = 0;
        for (double d : nums) {
            total = multiply(total, d);
        }

        return total;
    }

    @Override
    public double divide(double a, double b) {
        return a/b;
    }

    @Override
    public double divide(double... nums) {
        double total = 0;
        for (double d : nums) {
            total = divide(total, d);
        }

        return total;
    }
}

class ScientificCalculator implements Calculator, Scientific {

    @Override
    public double logBase2(double num) {
        return Math.log(num) / Math.log(2);
    }

    @Override
    public double squareRoot(double num) {
        return Math.sqrt(num);
    }

    @Override
    public double add(double a, double b) {
        return a+b;
    }

    @Override
    public double add(double... nums) {
        double total = 0;
        for (double d : nums) {
            total = add(total, d);
        }

        return total;
    }

    @Override
    public double subtract(double a, double b) {
        return a-b;
    }

    @Override
    public double subtract(double... nums) {
        double total = 0;
        for (double d : nums) {
            total = subtract(total, d);
        }

        return total;
    }

    @Override
    public double multiply(double a, double b) {
        return a*b;
    }

    @Override
    public double multiply(double... nums) {
        double total = 0;
        for (double d : nums) {
            total = multiply(total, d);
        }

        return total;
    }

    @Override
    public double divide(double a, double b) {
        return a/b;
    }

    @Override
    public double divide(double... nums) {
        double total = 0;
        for (double d : nums) {
            total = divide(total, d);
        }

        return total;
    }
}