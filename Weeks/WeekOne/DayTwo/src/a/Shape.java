package a;

public class Shape {
    private static class Shape2 {
        static void privateClass() {
            System.out.println("privateClass");
        }
    }
    public static void main(String[] args) {
        pubHello();
        defaultHello();
        privHello();
        protHello();

        Shape2.privateClass();
    }

    public static void pubHello() {
        System.out.println("pubHello");
    }
    static void defaultHello() {
        System.out.println("defaultHello");
    }
    private static void privHello() {
        System.out.println("privHello");
    }
    protected static void protHello() {
        System.out.println("protHello");
    }
}
