import java.util.ArrayList;
import java.util.Arrays;

public class Board {
    private static MarbleNode n = null;
    private static MarbleNode g = new MarbleNode('G', 'R', true); // green
    private static MarbleNode r = new MarbleNode('R', 'G', true); // red
    private static MarbleNode b = new MarbleNode(' ', 'O', true); // blue
    private static MarbleNode o = new MarbleNode(' ', 'B', true); // orange
    private static MarbleNode p = new MarbleNode(' ', 'Y', true); // purple
    private static MarbleNode y = new MarbleNode(' ', 'P', true); // yellow
    private static MarbleNode e = new MarbleNode(' ', ' ', true); // empty
    private static MarbleNode d = new MarbleNode(); // '-' dash

    private static MarbleNode[][] InitBoard = { 
        {n, n, n, n, n, n, n, n, n, n, n, n, g.copy(), n, n, n, n, n, n, n, n, n, n, n, n}, 
        {n, n, n, n, n, n, n, n, n, n, n, g.copy(), d, g.copy(), n, n, n, n, n, n, n, n, n, n, n}, 
        {n, n, n, n, n, n, n, n, n, n, g.copy(), d, g.copy(), d, g.copy(), n, n, n, n, n, n, n, n, n, n}, 
        {n, n, n, n, n, n, n, n, n, g.copy(), d, g.copy(), d, g.copy(), d, g.copy(), n, n, n, n, n, n, n, n, n}, 
        {b.copy(), d, b.copy(), d, b.copy(), d, b.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, y.copy(), d, y.copy(), d, y.copy(), d, y.copy()}, 
        {n, b.copy(), d, b.copy(), d, b.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, y.copy(), d, y.copy(), d, y.copy(), n}, 
        {n, n, b.copy(), d, b.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, y.copy(), d, y.copy(), n, n}, 
        {n, n, n, b.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, y.copy(), n, n, n}, 
        {n, n, n, n, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), n, n, n, n}, 
        {n, n, n, p.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, o.copy(), n, n, n}, 
        {n, n, p.copy(), d, p.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, o.copy(), d, o.copy(), n, n}, 
        {n, p.copy(), d, p.copy(), d, p.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, o.copy(), d, o.copy(), d, o.copy(), n}, 
        {p.copy(), d, p.copy(), d, p.copy(), d, p.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, e.copy(), d, o.copy(), d, o.copy(), d, o.copy(), d, o.copy()}, 
        {n, n, n, n, n, n, n, n, n, r.copy(), d, r.copy(), d, r.copy(), d, r.copy(), n, n, n, n, n, n, n, n, n}, {n, n, n, n, n, n, n, n, n, n, r.copy(), d, r.copy(), d, r.copy(), n, n, n, n, n, n, n, n, n, n}, 
        {n, n, n, n, n, n, n, n, n, n, n, r.copy(), d, r.copy(), n, n, n, n, n, n, n, n, n, n, n}, {n, n, n, n, n, n, n, n, n, n, n, n, r.copy(), n, n, n, n, n, n, n, n, n, n, n, n}
    };

    public static int rows = 17;
    public static int cols = 25;

    public static ArrayList<Character> humanMarbles = new ArrayList<>(Arrays.asList('G', 'B', 'P'));
    public static ArrayList<Character> pcMarbles = new ArrayList<>(Arrays.asList('R', 'O', 'Y'));

    // -----------------------------------------------------------------------------------------------

    public ArrayList<Integer> greenMarbleCells;
    public ArrayList<Integer> redMarbleCells;

    public MarbleNode[][] board;

    Board(){
        greenMarbleCells = new ArrayList<>(Arrays.asList(12, 36, 38, 60, 62, 64, 84, 86, 88, 90));
        redMarbleCells = new ArrayList<>(Arrays.asList(412, 386, 388, 360, 362, 364, 334, 336, 338, 340));

        this.board = new MarbleNode[rows][cols];

        for(int row=0; row<rows; row++){
            for(int col=0; col<cols; col++){
                this.board[row][col] = InitBoard[row][col];
            }
        }
    }

    Board(MarbleNode[][] board){
        this.board = new MarbleNode[rows][cols];

        for(int row=0; row<rows; row++){
            for(int col=0; col<cols; col++){
                this.board[row][col] = board[row][col];
            }
        }
    }

    public Board copy(){
        MarbleNode[][] copiedNodes = new MarbleNode[board.length][board[0].length];

        for(int row=0; row<board.length; row++){
            for(int col=0; col<board[row].length; col++){
                MarbleNode node = board[row][col];

                if(node == null){
                    copiedNodes[row][col] = null;
                }else{
                    if(node.marble == '-'){
                        copiedNodes[row][col] = node;
                    }else{
                        copiedNodes[row][col] = node.copy();
                    }
                }
            }
        }

        Board CopiedBoard = new Board(copiedNodes);
        CopiedBoard.greenMarbleCells = new ArrayList<>(this.greenMarbleCells);
        CopiedBoard.redMarbleCells = new ArrayList<>(this.redMarbleCells);

        return CopiedBoard;
    }

    public void print(){
        System.out.println("Red Marbles Cells");
        System.out.println(this.redMarbleCells);
        System.out.println("Green Marbles Cells");
        System.out.println(this.greenMarbleCells);
        System.out.print("\n  ");

        for(int i = 0 ; i < board[0].length ; i++){
            System.out.print(" " + (i < 10 ? ("0" + i) : i));
        }

        System.out.println();
        
        for(int i = 0 ; i < board.length ; i++) {
            System.out.print(i < 10 ? ("0" + i + " ") : (i + " "));
			for(int j = 0 ; j < board[i].length ; j++) {
				MarbleNode cell = board[i][j];
                if(cell == null || cell.marble == '-'){
                    System.out.print("   ");
                }else if(cell.marble == ' '){
                    System.out.print("+  ");
                }else{
                    System.out.print(cell.marble + "  ");
                }
			}
			System.out.println();
		}

        System.out.println();
    }
}
