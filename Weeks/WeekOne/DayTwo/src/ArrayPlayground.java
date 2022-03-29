import java.util.Arrays;
import java.util.Iterator;

public class ArrayPlayground {
    public static void main(String[] args) {
        int[] nums = {10, 20, 30, 40, 50};

        for (int i = 0; i < nums.length; i++) {
            System.out.println("nums[" + i + "] => " + nums[i]);
        }
        Iterator<Integer> iterator = Arrays.stream(nums).iterator();
        //System.out.println(nums[0]);

        for (int n : nums) {
            System.out.println(n);
        }

        while (iterator.hasNext()) {
            int i = iterator.next();
            System.out.println(i);
        }

        char [] chars = {'A', 'd', 'a', 'm'};
        System.out.println(chars);
        char [] char2 = new char[4];
        char2[0] = 'A';
        char2[1] = 'd';
        char2[2] = 'a';
        char2[3] = 'm';
        System.out.println(char2);

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] >= 'a') {
                chars[i] = (char)(chars[i] - 'a' + 'A');
            }
        }
        System.out.println(chars);

        char[] fname = {'T', 'o', 'm'};
        char[] lname = {'S','m','i','t','h'};

        char[] fullName = new char[fname.length + lname.length + 1];
        for (int i = 0; i < fullName.length;i++) {
            if (i < fname.length) {
                fullName[i] = fname[i];
            }
            else if (i == fname.length) {
                fullName[i] = ' ';
            }
            else {
                fullName[i] = lname[i - fname.length - 1];
            }
        }
        System.out.println(fullName);

        double [] dubs = new double[5];
        dubs[2] = 100.3;
        dubs[3] = 88.4;
        //dubs[100] = 9.4;
        System.out.println(dubs);

        for (double d : dubs){
            System.out.println(d);
        }
    }
}
