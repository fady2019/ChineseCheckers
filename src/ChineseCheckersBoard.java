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

    private static MarbleNode[][] boardNodes = { {n, n, n, n, n, n, n, n, n, n, n, n, g.copy(), n, n, n, n, n, n, n, n, n, n, n, n}, {n, n, n, n, n, n, n, n, n, n, n, g.copy(), d, g.copy(), n, n, n, n, n, n, n, n, n, n, n}, {n, n, n, n, n, n, n, n, n, n, g.copy(), d, g.copy(), d, g.copy(), n, n, n, n, n, n, n, n, n, n}, {n, n, n, n, n, n, n, n, n, g.copy(), d, g.copy(), d, g.copy(), d, g.copy(), n, n, n, n, n, n, n, n, n}, {b.copy(), d, b.copy(), d, b.copy(), d, b.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, y.copy(), d, y.copy(), d, y.copy(), d, y.copy()}, {n, b.copy(), d, b.copy(), d, b.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, y.copy(), d, y.copy(), d, y.copy(), n}, {n, n, b.copy(), d, b.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, y.copy(), d, y.copy(), n, n}, {n, n, n, b.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, y.copy(), n, n, n}, {n, n, n, n, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), n, n, n, n}, {n, n, n, p.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, o.copy(), n, n, n}, {n, n, p.copy(), d, p.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, o.copy(), d, o.copy(), n, n}, {n, p.copy(), d, p.copy(), d, p.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, o.copy(), d, o.copy(), d, o.copy(), n}, {p.copy(), d, p.copy(), d, p.copy(), d, p.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, o.copy(), d, o.copy(), d, o.copy(), d, o.copy()}, {n, n, n, n, n, n, n, n, n, r.copy(), d, r.copy(), d, r.copy(), d, r.copy(), n, n, n, n, n, n, n, n, n}, {n, n, n, n, n, n, n, n, n, n, r.copy(), d, r.copy(), d, r.copy(), n, n, n, n, n, n, n, n, n, n}, {n, n, n, n, n, n, n, n, n, n, n, r.copy(), d, r.copy(), n, n, n, n, n, n, n, n, n, n, n}, {n, n, n, n, n, n, n, n, n, n, n, n, r.copy(), n, n, n, n, n, n, n, n, n, n, n, n}};

    private static Board CCBoard = new Board(boardNodes.length, boardNodes[0].length, boardNodes);
    
    // -----------------------------------------------------

    public static void printBoard(){
        CCBoard.print();
    }

    private static boolean isCellInBoardRange(int row, int col){
        return row >= 0 && row < CCBoard.board.length && col >=0 && col < CCBoard.board[0].length;
    }

    private static int getCellNumber(int row, int col){
        return CCBoard.board[0].length*row + col;
    }

    /*
    private static int getCellRow(int cell){
        return cell / CCBoard.board[0].length;
    }

    private static int getCellColl(int cell){
        return cell % CCBoard.board[0].length;
    }
    */

    private static void getPossibleMovesInDir(int row1, int col1, int row2, int col2, boolean allowAdj, List<Integer> visitedCells, List<Integer> possibleMoves){
        MarbleNode node;
        int cell;
        if(isCellInBoardRange(row1, col1)){
            node = CCBoard.board[row1][col1];

            // if cell is available and empty
            if(node != null && node.marble == ' ' && allowAdj){
                cell = getCellNumber(row1, col1);
                possibleMoves.add(cell);
            }
            // if cell is available but not empty
            else if(node != null && node.marble != ' '){
                if(isCellInBoardRange(row2, col2)){
                    node = CCBoard.board[row2][col2];

                    if(node != null && node.marble == ' '){
                        cell = getCellNumber(row2, col2);
                        possibleMoves.add(cell);
                        getAllPossibleMoves(row2, col2, false, visitedCells, possibleMoves);
                    }
                }
            }
        }
    }

    public static void getAllPossibleMoves(int row, int col, boolean allowAdj, List<Integer> visitedCells, List<Integer> possibleMoves){
        int cell = getCellNumber(row, col);
        
        if(visitedCells.contains(cell) || CCBoard.board[row][col] == null || CCBoard.board[row][col].marble == '-'){
            return;
        }
        
        visitedCells.add(cell);

        // Moves in left dir
        getPossibleMovesInDir(row, col-2, row, col-4, allowAdj, visitedCells, possibleMoves);

        // Moves in right dir
        getPossibleMovesInDir(row, col+2, row, col+4, allowAdj, visitedCells, possibleMoves);

        // Moves in top left dir
        getPossibleMovesInDir(row-1, col-1, row-2, col-2, allowAdj, visitedCells, possibleMoves);

        // Moves in top right dir
        getPossibleMovesInDir(row-1, col+1, row-2, col+2, allowAdj, visitedCells, possibleMoves);
        
        // Moves in bottom left dir
        getPossibleMovesInDir(row+1, col-1, row+2, col-2, allowAdj, visitedCells, possibleMoves);
        
        // Moves in bottom right dir
        getPossibleMovesInDir(row+1, col+1, row+2, col+2, allowAdj, visitedCells, possibleMoves);
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

        MarbleNode src = CCBoard.board[srcRow][srcCol];

        if(src == null || !src.getAvailability() || src.marble == ' '){
            throw new Exception("Invalid source!");
        }

        MarbleNode dest = CCBoard.board[destRow][destCol];

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