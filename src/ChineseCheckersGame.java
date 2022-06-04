import java.util.ArrayList;
import java.util.List;

public class ChineseCheckersGame {
    private static int humanCounter = 0;
    private static int pcCounter = 0;

    private static Board CCBoard = new Board();

    // -----------------------------------------------------

    public static void printBoard(){
        CCBoard.print();
    }

    public static void doMove(Common.Player player, MarbleNode src, MarbleNode dest, int srcRow, int srcCol, int destRow, int destCol){
        if(src.marble != src.getEnemyMarble() && dest.getEnemyMarble() == src.marble){
            if(player == Common.Player.HUMAN){
                humanCounter++;
            }else if(player == Common.Player.PC){
                pcCounter++;
            }
        }else if(src.marble == src.getEnemyMarble() && dest.getEnemyMarble() != src.marble){
            if(player == Common.Player.HUMAN){
                humanCounter--;
            }else if(player == Common.Player.PC){
                pcCounter--;
            }
        }

        Move.moveMarble(player, CCBoard, srcRow, srcCol, destRow, destCol);
    }

    public static void humanMove(int srcRow, int srcCol, int destRow, int destCol) throws Exception{
        if(srcRow == destRow && srcCol == destCol ){
            throw new Exception("Source and destination can't be the same cell!");
        }

        if(!Common.isCellInBoardRange(CCBoard, srcRow, srcCol)){
            throw new Exception("Invalid source row or col!");
        }

        if(!Common.isCellInBoardRange(CCBoard, destRow, destCol)){
            throw new Exception("Invalid destination row or col!");
        }

        MarbleNode src = CCBoard.board[srcRow][srcCol];

        if(!Board.humanMarbles.contains(src.marble)){
            throw new Exception("Can't move this marble! (You're trying to move a PC marble)");
        }

        if(src == null || !src.getAvailability() || src.marble == ' '){
            throw new Exception("Invalid source!");
        }

        MarbleNode dest = CCBoard.board[destRow][destCol];

        if(dest == null || !dest.getAvailability() || dest.marble != ' '){
            throw new Exception("Invalid destination!");
        }

        List<Integer> possibleMoves = new ArrayList<>();

        Move.getAllPossibleMoves(CCBoard, srcRow, srcCol, true, new ArrayList<>(), possibleMoves);

        int destCell = Common.getCellNumber(CCBoard, destRow, destCol);

        if(possibleMoves.contains(destCell)){
            doMove(Common.Player.HUMAN, src, dest, srcRow, srcCol, destRow, destCol);
        }else{
            throw new Exception("Can't do this movie!");
        }     
    }

    public static void pcMove(int diffLevel){
        MoveInfo bestMove = MinMax.minMax(Common.Player.PC, diffLevel, CCBoard, true);
        int srcRow = Common.getCellRow(CCBoard, bestMove.srcCell);
        int srcCol = Common.getCellCol(CCBoard, bestMove.srcCell);
        int destRow = Common.getCellRow(CCBoard, bestMove.destCell);
        int destCol = Common.getCellCol(CCBoard, bestMove.destCell);

        System.out.println("PC Source Move (row col): " + srcRow + " " + srcCol);
        System.out.println("PC Destination Move (row col): " + destRow + " " + destCol);

        MarbleNode src = CCBoard.board[srcRow][srcCol];
        MarbleNode dest = CCBoard.board[destRow][destCol];

        doMove(Common.Player.PC, src, dest, srcRow, srcCol, destRow, destCol);
    }

    public static int getState(){
        return humanCounter == 10 ? 1 : pcCounter == 10 ? -1 : 0;
    }
}