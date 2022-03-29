import java.util.*;
import java.io.*;
import java.math.*;

public class Codingame {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int a = in.nextInt();
        int b = in.nextInt();
        int c = in.nextInt();

        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(a);
        list.add(b);
        list.add(c);

        Collections.sort(list);
        int length = list.size();
        int max = list.get(length-2) * list.get(length-1);
        int min = list.get(0) * list.get(1);

        max = (max < min) ? min : max;

        System.out.println(max);
    }


}
