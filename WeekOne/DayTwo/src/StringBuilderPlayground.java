public class StringBuilderPlayground {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("Hello");
        StringBuilder sb2 = new StringBuilder("Hello");
        System.out.println(sb == sb2);
        sb.reverse();
        System.out.println(sb);

        String lengthy = "";

        long time = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            lengthy += 'a';
        }
        time = System.nanoTime() - time;
        System.out.println(lengthy);
        System.out.println(time);

        StringBuilder lengthy2 = new StringBuilder();
        time = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            lengthy2.append('a');
        }
        time = System.nanoTime() - time;
        System.out.println(lengthy2);
        System.out.println(time);

        StringBuffer sbf = new StringBuffer("Hello");
    }
}
