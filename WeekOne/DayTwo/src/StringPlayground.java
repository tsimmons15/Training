public class StringPlayground {
    public static void main(String[] args) {
        String name = "Adam";
        System.out.println(name);

        System.out.println(name.toLowerCase());
        System.out.println(name);
        System.out.println(name.toUpperCase());
        System.out.println(name.contains("d"));

        System.out.println(name);

        Car c1 = new Car();
        Car c2 = new Car();
        System.out.println(c1 == c2);
        System.out.println("Adam" == name);

        String name2 = new String("Adam");
        System.out.println(name == name2);
        System.out.println(name.equals(name2));
    }
}
