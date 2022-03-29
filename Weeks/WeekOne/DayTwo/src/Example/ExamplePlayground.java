package Example;

public class ExamplePlayground {
    public static void main(String[] args) {
        CreditCard c1 = new CreditCard(0, 1000);

        c1.debit(200);
        c1.debit(700);
        c1.debit(400);
        c1.credit(600);
        c1.showAccount();
    }
}