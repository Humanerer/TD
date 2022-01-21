import java.util.LinkedList;

public class Board {

    private int width;
    private int height;
    private BoardPiece[][] pieceBoard;

    private LinkedList<CoordVector> path;
    private LinkedList<Tower> towers;
    private LinkedList<Enemy> enemies;

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
     * Removes all dead enemies, moves all living enemies along the path, removes enemies which have completed the path
     */
    public void updateEnemies(){
        for (int i = 0; i < enemies.size(); i++){
            Enemy enemy = enemies.get(i);
            if (enemy.isDead()){
                enemies.remove(i);
            } else {
                enemy.move();
                if (enemy.getPathIndex() > path.size()){
                    enemies.remove(i);
                    //TODO: lives--
                }
            }
        }
    }

    /**
     * Updates all towers
     */
    private void updateTowers(){
        for (Tower tower : towers){

        }
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
     * @return whether the placement was successful
     */
    public boolean placeTower(Tower tower, int x, int y){
        if (pieceBoard[x][y] == null) {
            pieceBoard[x][y] = tower;
            return true;
        }

        return false;
    }

    /**
     * adds an enemy to the start of the path
     * @param enemy to be added
     */
    public void spawn(Enemy enemy){
        enemies.add(enemy);
    }
}
