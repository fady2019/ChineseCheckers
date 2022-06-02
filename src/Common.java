public class Common {
    enum Player {HUMAN, PC};

    public static boolean isCellInBoardRange(Board CCBoard,  int row, int col){
        return row >= 0 && row < CCBoard.board.length && col >=0 && col < CCBoard.board[0].length;
    }

    public static int getCellNumber(Board CCBoard,  int row, int col){
        return CCBoard.board[0].length*row + col;
    }

    public static int getCellRow(Board CCBoard, int cell){
        return cell / CCBoard.board[0].length;
    }

    public static int getCellCol(Board CCBoard, int cell){
        return cell % CCBoard.board[0].length;
    }
}
