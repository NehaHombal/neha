import java.util.Scanner;

public class MiniProject {
    public static void main(String[] args) {
        int myNumber = (int)(Math.random()*100);
        Scanner sc = new Scanner(System.in);
        int userNum;
        do{
            System.out.println("Guess a number between 1 to 100:");
            userNum = sc.nextInt();
            if (userNum==myNumber){
                System.out.println("Good job correct number");
                break;
            }
            else if (userNum<myNumber){
                System.out.println("number too small");
            }
            else{
                System.out.println("NUMBER TOO BIG");

            }
        }while(userNum>=0);
        
        System.out.println("the number is "+ myNumber);

    }
}
