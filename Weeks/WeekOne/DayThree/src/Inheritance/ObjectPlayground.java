package Inheritance;

public class ObjectPlayground {
    public static void main(String[] args) {
        Object tc = new TravelCard(0, 1000, new AccountHolder("A", "Person"));
        Object obj = new Object();
        System.out.println(obj.toString());
        System.out.println(tc.toString());

        TravelCard t = (TravelCard)tc;

        System.out.println(t.equals(tc));
        System.out.println(tc.equals(t));
    }
}
