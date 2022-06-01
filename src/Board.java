public class Board {
    public MarbleNode[][] board;

    Board(int rows, int cols, MarbleNode[][] board){
        this.board = new MarbleNode[rows][cols];

        for(int row=0; row<board.length; row++){
            for(int col=0; col<board[row].length; col++){
                this.board[row][col] = board[row][col];
            }
        }
    }

    public MarbleNode[][] copy(){
        MarbleNode[][] copiedBoard = new MarbleNode[board.length][board[0].length];

        for(int row=0; row<board.length; row++){
            for(int col=0; col<board[row].length; col++){
                MarbleNode node = board[row][col];

                if(node == null){
                    copiedBoard[row][col] = null;
                }else{
                    if(node.marble == '-'){
                        copiedBoard[row][col] = node;
                    }else{
                        copiedBoard[row][col] = node.copy();
                    }
                }
            }
        }

        return copiedBoard;
    }

    public void print(){
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
}
