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
	private static ArrayList<Integer> greenIndex;
    private static ArrayList<Integer> redIndex;

    MinMax(){
    	greenIndex = new ArrayList<>(Arrays.asList(12, 36, 38, 60, 62, 64, 84, 86, 88, 90));
    	redIndex = new ArrayList<>(Arrays.asList(412, 386, 388, 360, 362, 364, 334, 336, 338, 340));
    }
    
    private static int getCellRow(int cell,Board board){
        return cell / board.board[0].length;
    }

    private static int getCellColl(int cell,Board board){
        return cell % board.board[0].length;
    }
    
    public static float getDictanceBetween(int p1x,int p1y,int p2x, int p2y) {
        return (float) Math.sqrt(Math.pow(p1x - p2x, 2) + Math.pow(p1y - p2y, 2));
    }
    
    private static MoveInfo utilityFun(Board board){
    	MoveInfo info=new MoveInfo();
    	MarbleNode[][] CCBoard=board.board;
    	int humandistance=0;
    	int compDistance=0;
    	int CountRedIndex=redIndex.size()-1;
    	int CountGreenIndex=0;
    	for(int i = 0 ; i < CCBoard.length ; i++) {
    		for(int j = 0 ; j < CCBoard[i].length ; j++) {
    			if(CCBoard[i][j].marble=='G')
    			{
    				int RX=getCellRow(redIndex.get(CountRedIndex),board);
    				int RY=getCellColl(redIndex.get(CountRedIndex),board);
    				CountRedIndex--;
    				humandistance+=getDictanceBetween(i,j,RX,RY);
    			}
    			else if(CCBoard[i][j].marble=='R')
    			{
    				int GX=getCellRow(greenIndex.get(CountRedIndex),board);
    				int GY=getCellColl(greenIndex.get(CountRedIndex),board);
    				CountGreenIndex++;
    				compDistance+=getDictanceBetween(i,j,GX,GY);
    			}
    		}
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
