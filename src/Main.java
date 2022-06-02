import java.util.Scanner;

public class Main {
    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        ChineseCheckersGame.printBoard();
        
        while(true){
            System.out.println("Enter Source Cell (row col): ");
            int srcRow = scan.nextInt();
            int srcCol = scan.nextInt();
            System.out.println("Enter Destination Cell (row col): ");
            int destRow = scan.nextInt();
            int destCol = scan.nextInt();

            try{
                ChineseCheckersGame.humanMove(srcRow, srcCol, destRow, destCol);
                ChineseCheckersGame.getState();
                ChineseCheckersGame.printBoard();
            }catch(Exception ex){
                System.out.println(ex.getMessage());
            }
        }
    }
}
