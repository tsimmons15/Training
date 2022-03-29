public class VarArgsPlayground {
    public static void main(String[] args) {
        double[] ray = {23.4, 53.3, 100.1};
        System.out.println(Calculator.sum(0,ray));
        System.out.println(Calculator.sum(0,12, 4, 4.5));
    }
}

class Calculator {
    /*static double sum(double [] nums) {
        double result = 0;
        for(double d : nums) {
            result += d;
        }

        return result;
    }*/

    static double sum(int i, double... nums) {
        double result = 0;
        for (double d : nums) {
            result += d;
        }

        return result;
    }
}