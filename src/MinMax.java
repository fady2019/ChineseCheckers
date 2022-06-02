import java.util.ArrayList;
import java.util.List;

public class MinMax {
    public static void minMax(Common.Player player, int levelNum, Board CCBoard){
        levelNum--;

        ArrayList<Integer> marblesCells;

        if(player == Common.Player.HUMAN){
            marblesCells = CCBoard.greenMarbleCells;
        }else{
            marblesCells = CCBoard.redMarbleCells;
        }

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
            }
        }
    }
}
