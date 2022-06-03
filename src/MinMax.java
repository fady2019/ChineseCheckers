import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class MoveInfo
{
    public int srcCell = -1;
    public int destCell = -1;
    public int val;
}

public class MinMax 
{
    public static float getDictanceBetween(int p1x,int p1y,int p2x, int p2y) {
        return (float) Math.sqrt(Math.pow(p1x - p2x, 2) + Math.pow(p1y - p2y, 2));
    }
    
    private static MoveInfo utilityFun(Board board){
    	MoveInfo info=new MoveInfo();
    	MarbleNode[][] CCBoard=board.board;
    	int humandistance=0;
    	int compDistance=0;
    	int CountRedIndex=board.redGoal.size()-1;
    	int CountGreenIndex=0;
    	Common common = new Common();
    	
    	for(int i=0;i<board.greenMarbleCells.size();i++)
    	{
    		int RX=common.getCellRow(board,board.redGoal.get(CountRedIndex));
			int RY=common.getCellCol(board,board.redGoal.get(CountRedIndex));
			int GX=common.getCellRow(board,board.greenMarbleCells.get(i));
			int GY=common.getCellCol(board,board.greenMarbleCells.get(i));
			CountRedIndex--;
			humandistance+=getDictanceBetween(GX,GY,RX,RY);
    	}
       
    	for(int i=0;i<board.redMarbleCells.size();i++)
    	{
    		int GX=common.getCellRow(board,board.greenGoal.get(CountGreenIndex));
			int GY=common.getCellCol(board,board.greenGoal.get(CountGreenIndex));
			int RX=common.getCellRow(board,board.redMarbleCells.get(i));
			int RY=common.getCellCol(board,board.redMarbleCells.get(i));
			CountGreenIndex++;
			compDistance+=getDictanceBetween(GX,GY,RX,RY);
    	}

    	info.val=compDistance-humandistance;
        return info;
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
