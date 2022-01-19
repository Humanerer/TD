import java.util.LinkedList;

class SimpleTD {
    public static void main(String[] args) {
        LinkedList<CoordVector> path = new LinkedList<CoordVector>();
        for (int x = 0; x < 32; x++) {
            path.add(new CoordVector(x,4));
        }



        Board board = new Board(32,18, path);
        board.printBoard();
        System.out.println();
        board.placeTower(new Tower(),4,5);
        board.printBoard();
    }
}