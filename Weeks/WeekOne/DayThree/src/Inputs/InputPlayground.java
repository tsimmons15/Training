package Inputs;

import java.util.Scanner;

public class InputPlayground {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Please enter your name: ");
        String name = scan.nextLine();
        System.out.println("Welcome " + name);

        System.out.print("Number 1: ");
        int x = scan.nextInt();
        System.out.print("Number 2: ");
        int y = scan.nextInt();
        System.out.println(x + " * " + y + " = " + (x*y));

        scan.close();
    }
}
