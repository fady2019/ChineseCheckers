import java.util.ArrayList;
import java.util.List;

public class ChineseCheckersBoard {
    private static MarbleNode n = null;

    private static MarbleNode g = new MarbleNode('G', 'R', true); // green
    private static MarbleNode r = new MarbleNode('R', 'G', true); // red
    private static MarbleNode b = new MarbleNode('B', 'O', true); // blue
    private static MarbleNode o = new MarbleNode('O', 'B', true); // orange
    private static MarbleNode p = new MarbleNode('P', 'Y', true); // purple
    private static MarbleNode y = new MarbleNode('Y', 'P', true); // yellow

    private static MarbleNode e = new MarbleNode(' ', ' ', true); // empty
    private static MarbleNode d = new MarbleNode(); // '-' dash


    private static MarbleNode[][] board = {
        {n, n, n, n, n, n, n, n, n, n, n, n, g.copy(), n, n, n, n, n, n, n, n, n, n, n, n},
        {n, n, n, n, n, n, n, n, n, n, n, g.copy(), d, g.copy(), n, n, n, n, n, n, n, n, n, n, n},
        {n, n, n, n, n, n, n, n, n, n, g.copy(), d, g.copy(), d, g.copy(), n, n, n, n, n, n, n, n, n, n},
        {n, n, n, n, n, n, n, n, n, g.copy(), d, g.copy(), d, g.copy(), d, g.copy(), n, n, n, n, n, n, n, n, n},
        {b.copy(), d, b.copy(), d, b.copy(), d, b.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, y.copy(), d, y.copy(), d, y.copy(), d, y.copy()},
        {n, b.copy(), d, b.copy(), d, b.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, y.copy(), d, y.copy(), d, y.copy(), n},
        {n, n, b.copy(), d, b.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, y.copy(), d, y.copy(), n, n},
        {n, n, n, b.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, y.copy(), n, n, n},
        {n, n, n, n, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), n, n, n, n},
        {n, n, n, p.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, o.copy(), n, n, n},
        {n, n, p.copy(), d, p.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, o.copy(), d, o.copy(), n, n},
        {n, p.copy(), d, p.copy(), d, p.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, o.copy(), d, o.copy(), d, o.copy(), n},
        {p.copy(), d, p.copy(), d, p.copy(), d, p.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, o.copy(), d, o.copy(), d, o.copy(), d, o.copy()},
        {n, n, n, n, n, n, n, n, n, r.copy(), d, r.copy(), d, r.copy(), d, r.copy(), n, n, n, n, n, n, n, n, n},
        {n, n, n, n, n, n, n, n, n, n, r.copy(), d, r.copy(), d, r.copy(), n, n, n, n, n, n, n, n, n, n},
        {n, n, n, n, n, n, n, n, n, n, n, r.copy(), d, r.copy(), n, n, n, n, n, n, n, n, n, n, n},
        {n, n, n, n, n, n, n, n, n, n, n, n, r.copy(), n, n, n, n, n, n, n, n, n, n, n, n}
    };

    public static void printBoard(){
        System.out.print("\n  ");

        for(int i = 0 ; i < board[0].length ; i++){
            System.out.print(" " + (i < 10 ? ("0" + i) : i));
        }

        System.out.println();
        
        for(int i = 0 ; i < board.length ; i++) {
            System.out.print(i < 10 ? ("0" + i + " ") : (i + " "));
			for(int j = 0 ; j < board[i].length ; j++) {
				MarbleNode cell = board[i][j];
                if(cell == null){
                    System.out.print("   ");
                }else{
                    System.out.print(cell.marble + "  ");
                }
			}
			System.out.println();
		}

        System.out.println();
    }

    private static boolean isCellInBoardRange(int row, int col){
        return row >= 0 && row < board.length && col >=0 && col < board[0].length;
    }

    private static int getCellNumber(int row, int col){
        return board[0].length*row + col;
    }

    private static int getCellRow(int cell){
        return cell / board[0].length;
    }

    private static int getCellColl(int cell){
        return cell % board[0].length;
    }

    public static void getAllPossibleMoves(int row, int col, boolean flag, List<Integer> visitedCells, List<Integer> possibleMoves){
        int cell = getCellNumber(row, col);
        
        if(visitedCells.contains(cell) || board[row][col] == null || board[row][col].marble == '-'){
            return;
        }
        
        visitedCells.add(cell);

        // Left Moves
        MarbleNode left;
        if(isCellInBoardRange(row, col-2)){
            left = board[row][col-2];

            // if left cell is available and empty
            if(left != null && left.marble == ' ' && flag){
                cell = getCellNumber(row, col-2);
                possibleMoves.add(cell);
            }
            // if left cell is available but not empty
            else if(left != null && left.marble != ' '){
                if(isCellInBoardRange(row, col-4)){
                    left = board[row][col-4];

                    if(left != null && left.marble == ' '){
                        cell = getCellNumber(row, col-4);
                        possibleMoves.add(cell);
                        getAllPossibleMoves(row, col-4, false, visitedCells, possibleMoves);
                    }
                }
            }
        }
        
        // Right Moves
        MarbleNode right;
        if(isCellInBoardRange(row, col+2)){
            right = board[row][col+2];

            // if right cell is available and empty
            if(right != null && right.marble == ' ' && flag){
                cell = getCellNumber(row, col+2);
                possibleMoves.add(cell);
            }
            // if right cell is available but not empty
            else if(right != null && right.marble != ' '){
                if(isCellInBoardRange(row, col+4)){
                    right = board[row][col+4];

                    if(right != null && right.marble == ' '){
                        cell = getCellNumber(row, col+4);
                        possibleMoves.add(cell);
                        getAllPossibleMoves(row, col+4, false, visitedCells, possibleMoves);
                    }
                }
            }
        }
        
        // Top Left Moves
        MarbleNode topLeft;
        if(isCellInBoardRange(row-1, col-1)){
            topLeft = board[row-1][col-1];

            // if top left cell is available and empty
            if(topLeft != null && topLeft.marble == ' ' && flag){
                cell = getCellNumber(row-1, col-1);
                possibleMoves.add(cell);
            }
            // if top left cell is available but not empty
            else if(topLeft != null && topLeft.marble != ' '){
                if(isCellInBoardRange(row-2, col-2)){
                    topLeft = board[row-2][col-2];

                    if(topLeft != null && topLeft.marble == ' '){
                        cell = getCellNumber(row-2, col-2);
                        possibleMoves.add(cell);
                        getAllPossibleMoves(row-2, col-2, false, visitedCells, possibleMoves);
                    }
                }
            }
        }
        
        // Top Right Moves
        MarbleNode topRight;
        if(isCellInBoardRange(row-1, col+1)){
        topRight = board[row-1][col+1];

            // if top right cell is available and empty
            if(topRight != null && topRight.marble == ' ' && flag){
                cell = getCellNumber(row-1, col+1);
                possibleMoves.add(cell);
            }
            // if top right cell is available but not empty
            else if(topRight != null && topRight.marble != ' '){
                if(isCellInBoardRange(row-2, col+2)){
                    topRight = board[row-2][col+2];

                    if(topRight != null && topRight.marble == ' '){
                        cell = getCellNumber(row-2, col+2);
                        possibleMoves.add(cell);
                        getAllPossibleMoves(row-2, col+2, false, visitedCells, possibleMoves);
                    }
                }
            }
        }
         
        // Bottom Left Moves
        MarbleNode bottomLeft;
        if(isCellInBoardRange(row+1, col-1)){
            bottomLeft = board[row+1][col-1];

            // if bottom left cell is available and empty
            if(bottomLeft != null && bottomLeft.marble == ' ' && flag){
                cell = getCellNumber(row+1, col-1);
                possibleMoves.add(cell);
            }
            // if bottom left cell is available but not empty
            else if(bottomLeft != null && bottomLeft.marble != ' '){
                if(isCellInBoardRange(row+2, col-2)){
                    bottomLeft = board[row+2][col-2];

                    if(bottomLeft != null && bottomLeft.marble == ' '){
                        cell = getCellNumber(row+2, col-2);
                        possibleMoves.add(cell);
                        getAllPossibleMoves(row+2, col-2, false, visitedCells, possibleMoves);
                    }
                }
            }
        }

        // Bottom Right Moves
        MarbleNode bottomRight;
        if(isCellInBoardRange(row+1, col+1)){
            bottomRight = board[row+1][col+1];

            // if bottom right cell is available and empty
            if(bottomRight != null && bottomRight.marble == ' ' && flag){
                cell = getCellNumber(row+1, col+1);
                possibleMoves.add(cell);
            }
            // if bottom right cell is available but not empty
            else if(bottomRight != null && bottomRight.marble != ' '){
                if(isCellInBoardRange(row+2, col+2)){
                    bottomRight = board[row+2][col+2];

                    if(bottomRight != null && bottomRight.marble == ' '){
                        cell = getCellNumber(row+2, col+2);
                        possibleMoves.add(cell);
                        getAllPossibleMoves(row+2, col+2, false, visitedCells, possibleMoves);
                    }
                }
            }
        }
     
    }

    public static void doMove(MarbleNode src, MarbleNode dest){
        dest.marble = src.marble;
        src.marble = ' ';
    }

    public static void move(int srcRow, int srcCol, int destRow, int destCol) throws Exception{
        if(srcRow == destRow && srcCol == destCol ){
            throw new Exception("Source and destination can't be the same cell!");
        }

        if(!isCellInBoardRange(srcRow, srcCol)){
            throw new Exception("Invalid source row or col!");
        }

        if(!isCellInBoardRange(destRow, destCol)){
            throw new Exception("Invalid destination row or col!");
        }

        MarbleNode src = board[srcRow][srcCol];

        if(src == null || !src.getAvailability() || src.marble == ' '){
            throw new Exception("Invalid source!");
        }

        MarbleNode dest = board[destRow][destCol];

        if(
            dest == null || !dest.getAvailability() || dest.marble != ' ' || 
            (
                dest.enemyMarble != ' ' && dest.enemyMarble != dest.marble && 
                dest.getFriendMarble() != src.marble && dest.enemyMarble != src.marble
            )
        ){
            throw new Exception("Invalid destination!");
        }

        List<Integer> possibleMoves = new ArrayList<>();

        getAllPossibleMoves(srcRow, srcCol, true, new ArrayList<>(), possibleMoves);

        int destCell = getCellNumber(destRow, destCol);

        if(possibleMoves.contains(destCell)){
            doMove(src, dest);
        }else{
            throw new Exception("Can't do this movie!");
        }     
    }
}