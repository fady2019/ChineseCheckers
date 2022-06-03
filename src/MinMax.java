import java.util.ArrayList;
import java.util.List;

class MoveInfo{
    public int srcCell = -1;
    public int destCell = -1;
    public int val;
}

public class MinMax {
    private static MoveInfo utilityFun(Board board){
        return null;
    }

    public static MoveInfo minMax(Common.Player player, int levelNum, Board CCBoard, boolean maximizing){
        if(levelNum == 0){
            return utilityFun(CCBoard);
        }

        levelNum--;

        ArrayList<Integer> marblesCells;

        if(player == Common.Player.HUMAN){
            marblesCells = CCBoard.greenMarbleCells;
        }else{
            marblesCells = CCBoard.redMarbleCells;
        }

        MoveInfo bestMove = new MoveInfo();
        bestMove.val = maximizing? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for(int srcCell: marblesCells){
            List<Integer> possibleMoves = new ArrayList<>();
            // getting all possible moves for the current cell
            Move.getAllPossibleMoves(
                CCBoard, 
                Common.getCellRow(CCBoard, srcCell), 
                Common.getCellCol(CCBoard, srcCell), 
                true, 
                new ArrayList<>(), 
                possibleMoves
            );

            for(int destCell: possibleMoves){
                Board childBoard = CCBoard.copy();
                Move.moveMarble(player, childBoard, srcCell, destCell);
                Common.Player nextPlayer = player == Common.Player.HUMAN? Common.Player.PC : Common.Player.HUMAN;
                
                MoveInfo moveInfo = minMax(nextPlayer, levelNum-1, childBoard, !maximizing);
                moveInfo.srcCell = srcCell;
                moveInfo.destCell = destCell;
                
                if((maximizing && moveInfo.val > bestMove.val) || (!maximizing && moveInfo.val < bestMove.val)){
                    bestMove = moveInfo;
                }
            }
        }

        return bestMove;
    }
}
