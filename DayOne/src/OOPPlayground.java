public class OOPPlayground {
    public static void main(String[] args)
    {
        System.out.println("Hello the application started");

        Language greeting = new English();
        greeting.hello();

        greeting = new German();
        greeting.hello();
    }
}

abstract class Language {
    public abstract void hello();
}

class English extends Language {
    public void hello() {
        System.out.println("Hello");
    }
}

class German extends Language {
    public void hello() {
        System.out.println("Guten Tag");
    }
}