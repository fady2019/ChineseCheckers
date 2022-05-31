package ChineseCheckers;

public class ChineseCheckersBoard {
    private static MarbleNode n = null;

    private static MarbleNode g = new MarbleNode('G', 'R', true); // green
    private static MarbleNode r = new MarbleNode('R', 'G', true); // red
    private static MarbleNode b = new MarbleNode('B', 'O', true); // blue
    private static MarbleNode o = new MarbleNode('O', 'B', true); // orange
    private static MarbleNode p = new MarbleNode('P', 'Y', true); // purple
    private static MarbleNode y = new MarbleNode('Y', 'P', true); // yellow

    private static MarbleNode e = new MarbleNode(' ', ' ', true); // empty
    private static MarbleNode d = new MarbleNode(); // '-' dash



    private static MarbleNode[][] board = {
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
        {n, n, n, n, n, n, n, n, n, r.copy(), d, r.copy(), d, r.copy(), d, r.copy(), n, n, n, n, n, n, n, n, n},
        {n, n, n, n, n, n, n, n, n, n, r.copy(), d, r.copy(), d, r.copy(), n, n, n, n, n, n, n, n, n, n},
        {n, n, n, n, n, n, n, n, n, n, n, r.copy(), d, r.copy(), n, n, n, n, n, n, n, n, n, n, n},
        {n, n, n, n, n, n, n, n, n, n, n, n, r.copy(), n, n, n, n, n, n, n, n, n, n, n, n}
    };

    public static void printBoard(){
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
