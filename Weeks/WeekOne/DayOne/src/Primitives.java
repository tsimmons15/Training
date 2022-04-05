public class Primitives {

    public static void main(String[] args) {
        byte y = 0;
        short s = 0;
        char c = 10000;
        int i = 0;
        float f = 2.1f;
        double d = 2.1;
        long l = 500;
        boolean b = false;

        System.out.println(c);
        System.out.println(i);
        references(i);
        System.out.println(i);
        references2(i);
        System.out.println(i);
        Integer newInt = new Integer(5);
        references(newInt);
        System.out.println(newInt);
    }

    public static void references(int i) {
        i = 5;
    }

    public static void references2(Integer i) {
        i = new Integer(4);
    }
}
