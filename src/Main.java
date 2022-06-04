import java.util.Scanner;

public class Main {
    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        ChineseCheckersGame.printBoard();

        int diffLevel = 1;

        System.out.println("Enter Difficulty Level");
        System.out.println("1- Easy");
        System.out.println("2- Medium");
        System.out.println("3- Hard");

        diffLevelChoice: 
        while(true){
            int userChoice = scan.nextInt();

            switch(userChoice){
                case 1:
                    diffLevel = 1;
                    break diffLevelChoice; 
                case 2:
                    diffLevel = 3;
                    break diffLevelChoice;
                case 3:
                    diffLevel = 5;
                    break diffLevelChoice;  
                default:
                    System.out.println("Invalid Choice try again!");
            }
        }

        int state=0;        
        while(state == 0){
            System.out.println("Enter Source Cell (row col): ");
            int srcRow = scan.nextInt();
            int srcCol = scan.nextInt();
            System.out.println("Enter Destination Cell (row col): ");
            int destRow = scan.nextInt();
            int destCol = scan.nextInt();

            try{
                ChineseCheckersGame.humanMove(srcRow, srcCol, destRow, destCol);
                state = ChineseCheckersGame.getState();
                ChineseCheckersGame.printBoard();

                if(state == 1){
                    System.out.println("Human won!");
                    break;
                }

                ChineseCheckersGame.pcMove(diffLevel);
                state = ChineseCheckersGame.getState();
                ChineseCheckersGame.printBoard();

                if(state == -1){
                    System.out.println("PC won!");
                    break;
                }
            }catch(Exception ex){
                System.out.println(ex.getMessage());
            }
        }
    }
}
