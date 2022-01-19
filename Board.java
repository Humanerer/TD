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
}
