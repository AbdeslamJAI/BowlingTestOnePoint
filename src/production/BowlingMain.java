package production;

import java.util.Scanner;

public class BowlingMain {
    public BowlingMain() {
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        BowlingApp bApp = new BowlingApp("OnePointGamer");
        System.out.println("Welcome " + bApp.returnName() + " to bowling!");
        System.out.println("How many pins you want to knock down");

        while(bApp.returnFrame() != 12 || bApp.returnRoll() != 2) {
            int a = in.nextInt();
            bApp.shot(a);
            System.out.println("");
            System.out.println("");
            System.out.println("");
            bApp.updateInfo();
        }

        System.out.println("Thank you for playing");
    }
}
