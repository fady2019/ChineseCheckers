import java.util.Scanner;

public class Main {
    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        ChineseCheckersBoard.printBoard();
        
        while(true){
            System.out.println("Enter Source Cell (row col): ");
            int srcRow = scan.nextInt();
            int srcCol = scan.nextInt();
            System.out.println("Enter Destination Cell (row col): ");
            int destRow = scan.nextInt();
            int destCol = scan.nextInt();

            try{
                ChineseCheckersBoard.move(srcRow, srcCol, destRow, destCol);
                ChineseCheckersBoard.printBoard();
            }catch(Exception ex){
                System.out.println(ex.getMessage());
            }
        }
    }
}
