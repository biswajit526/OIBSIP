import java.util.Random;
import java.util.Scanner;

public class NumberGuessing {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        int randomNumber = rand.nextInt(100) + 1;
        
        int count = 0;

        while (true) {
            System.out.print("Guess a number between 1 to 100: ");
            int num = sc.nextInt();
            count++;

            if (count > 10) {
                System.out.println("You exced the number of attempts.");
                break;
            }
            
            if (num == randomNumber) {
                System.out.println("You guessed the correct number.");
                System.out.println("The number of attempts : " + count);
                break;
            } else if (num < randomNumber) {
                System.out.println("Nope! number is higher.");
            } else {
                System.out.println("Nope! number is lesser.");
            }

            
        }
        sc.close();
    }
}
