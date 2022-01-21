import java.util.LinkedList;

public class Board {

    private int width;
    private int height;
    private BoardPiece[][] pieceBoard;

    LinkedList<CoordVector> path;

    /**
     * Makes a board with the given width and height, as well as a path for enemies to travel along
     * @param width of the board
     * @param height of the board
     * @param path to be taken by enemies
     */
    Board(int width, int height, LinkedList<CoordVector> path){
        this.width = width;
        this.height = height;
        this.path = path;
        pieceBoard = new BoardPiece[width][height];

        for (CoordVector pathPiece: path){
            final int x = pathPiece.getX();
            final int y = pathPiece.getY();
            pieceBoard[x][y] = new Path();
        }

    }

    /**
     * Updates the board
     */
    public void update(){
        /**
         * for each tower, try to attack
         * 
         * for each enemy, try to move
         */
    }

    /**
     * Prints the board using System.out, '#' is used to symbolise the border
     */
    public void printBoard(){
        for (int x = 0; x <= width; x++){
            System.out.print("##");
        }
        System.out.println();
        for (int y = height-1; y >= 0; y--){
            System.out.print('#');
            for (int x = 0; x < width; x++){
                System.out.print(" ");

                if (pieceBoard[x][y] == null){
                    System.out.print(" ");
                } else {
                    System.out.print(pieceBoard[x][y].asChar());
                }
            }
            System.out.println('#');
        }
        for (int x = 0; x <= width; x++){
            System.out.print("##");
        }
        System.out.println();
    }

    /**
     * Places tower at x y coords on the board, returns true if the position is empty and the tower was placed, else false
     * @param tower to be placed
     * @param x coord to be placed at
     * @param y coord to be placed at
     * @return whether the placment was successful
     */
    public boolean placeTower(Tower tower, int x, int y){
        if (pieceBoard[x][y] == null) {
            pieceBoard[x][y] = tower;
            return true;
        }

        return false;
    }
}
