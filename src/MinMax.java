import java.util.ArrayList;
import java.util.List;

class MoveInfo
{
    public int srcCell = -1;
    public int destCell = -1;
    public int val;

    @Override
    public String toString() {
        return "Source Cell: " + this.srcCell + "\n" +
            "Destination Cell: " + this.destCell + "\n" +
            "Evaluation Value: " + this.val;
    }
}

public class MinMax 
{
    private static MoveInfo utilityFun(Board board){
    	MoveInfo info = new MoveInfo();
    	float humanDistance = 0;
    	float compDistance = 0;

    	for(int i=0; i<board.greenMarbleCells.size(); i++)
    	{
    		int goalRow = Common.getCellRow(board, Board.greenGoalCells.get(i));
			int goalCol = Common.getCellCol(board, Board.greenGoalCells.get(i));
			int greenRow = Common.getCellRow(board, board.greenMarbleCells.get(i));
			int greenCol = Common.getCellCol(board, board.greenMarbleCells.get(i));
			humanDistance += Math.abs(greenCol - goalCol) + Math.abs(greenRow - goalRow) ;
    	}
       
    	for(int i=0; i<board.redMarbleCells.size(); i++)
    	{
    		int goalRow = Common.getCellRow(board, Board.redGoalCells.get(i));
			int goalCol = Common.getCellCol(board, Board.redGoalCells.get(i));
			int redRow = Common.getCellRow(board, board.redMarbleCells.get(i));
			int redCol = Common.getCellCol(board, board.redMarbleCells.get(i));
			compDistance += Math.abs(redCol - goalCol) + Math.abs(redRow - goalRow) ;
    	}

    	info.val = (int) -(compDistance-humanDistance);

        return info;
    }

    public static MoveInfo minMax(Common.Player player, int levelNum, Board CCBoard, boolean maximizing){
        if(levelNum <= 0){
            return utilityFun(CCBoard);
        }

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
