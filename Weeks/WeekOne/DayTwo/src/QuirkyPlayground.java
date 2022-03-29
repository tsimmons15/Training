public class QuirkyPlayground {
    public static void main(String[] args) {
        Integer x = 500;
        Integer y = 500; // 500 is not a byte, so it's not in the shared pool

        System.out.println(x == y);

        /*while (false) {
            System.out.println("Never execute");
        }*/

        if (false) {
            System.out.println("Also never executed");
        }
    }
}
