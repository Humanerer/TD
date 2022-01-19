import java.util.LinkedList;

public class Board {

    private int width;
    private int height;
    private BoardPiece[][] pieceBoard;

    LinkedList<CoordVector> path;

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

    public void place(int x, int y, BoardPiece bp){
        pieceBoard[x][y] = bp;
    }

    public void tick(){
        for (int x = 0; x < width; x++){
            for (int y = 0; y < height; y++){
                pieceBoard[x][y].tick();
            }
        }
    }

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

    public boolean placeTower(Tower tower, int x, int y){
        if (pieceBoard[x][y] == null) {
            pieceBoard[x][y] = tower;
            return true;
        }

        return false;
    }
}
