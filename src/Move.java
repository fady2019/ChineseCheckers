import java.util.List;

public class Move {
    private static void getPossibleMovesInDir(Board CCBoard, int row1, int col1, int row2, int col2, boolean allowAdj, List<Integer> visitedCells, List<Integer> possibleMoves){
        MarbleNode node;
        int cell;
        if(Common.isCellInBoardRange(CCBoard, row1, col1)){
            node = CCBoard.board[row1][col1];

            // if cell is available and empty
            if(node != null && node.marble == ' ' && allowAdj){
                cell = Common.getCellNumber(CCBoard, row1, col1);
                possibleMoves.add(cell);
            }
            // if cell is available but not empty
            else if(node != null && node.marble != ' '){
                if(Common.isCellInBoardRange(CCBoard, row2, col2)){
                    node = CCBoard.board[row2][col2];

                    if(node != null && node.marble == ' '){
                        cell = Common.getCellNumber(CCBoard, row2, col2);
                        possibleMoves.add(cell);
                        getAllPossibleMoves(CCBoard, row2, col2, false, visitedCells, possibleMoves);
                    }
                }
            }
        }
    }

    public static void getAllPossibleMoves(Board CCBoard, int row, int col, boolean allowAdj, List<Integer> visitedCells, List<Integer> possibleMoves){
        int cell = Common.getCellNumber(CCBoard, row, col);
        
        if(visitedCells.contains(cell) || CCBoard.board[row][col] == null || CCBoard.board[row][col].marble == '-'){
            return;
        }
        
        visitedCells.add(cell);

        // Moves in left dir
        getPossibleMovesInDir(CCBoard, row, col-2, row, col-4, allowAdj, visitedCells, possibleMoves);

        // Moves in right dir
        getPossibleMovesInDir(CCBoard, row, col+2, row, col+4, allowAdj, visitedCells, possibleMoves);

        // Moves in top left dir
        getPossibleMovesInDir(CCBoard, row-1, col-1, row-2, col-2, allowAdj, visitedCells, possibleMoves);

        // Moves in top right dir
        getPossibleMovesInDir(CCBoard, row-1, col+1, row-2, col+2, allowAdj, visitedCells, possibleMoves);
        
        // Moves in bottom left dir
        getPossibleMovesInDir(CCBoard, row+1, col-1, row+2, col-2, allowAdj, visitedCells, possibleMoves);
        
        // Moves in bottom right dir
        getPossibleMovesInDir(CCBoard, row+1, col+1, row+2, col+2, allowAdj, visitedCells, possibleMoves);
    }

    public static void moveMarble(Common.Player player, Board CCBoard, int srcCell, int destCell){
        moveMarble(
            player,
            CCBoard, 
            Common.getCellRow(CCBoard, srcCell),
            Common.getCellCol(CCBoard, srcCell),
            Common.getCellRow(CCBoard, destCell),
            Common.getCellCol(CCBoard, destCell)
        );
    }

    public static void moveMarble(Common.Player player, Board CCBoard, int srcRow, int srcCol, int destRow, int destCol){
        CCBoard.board[destRow][destCol].marble = CCBoard.board[srcRow][srcCol].marble;
        CCBoard.board[srcRow][srcCol].marble = ' ';

        if(player == Common.Player.HUMAN){
            int oldCellIdx = CCBoard.greenMarbleCells.indexOf(Common.getCellNumber(CCBoard, srcRow, srcCol));
            CCBoard.greenMarbleCells.set(oldCellIdx, Common.getCellNumber(CCBoard, destRow, destCol));
        }else if(player == Common.Player.PC){
            int oldCellIdx = CCBoard.redMarbleCells.indexOf(Common.getCellNumber(CCBoard, srcRow, srcCol));
            CCBoard.redMarbleCells.set(oldCellIdx, Common.getCellNumber(CCBoard, destRow, destCol));
        }
    }
}
