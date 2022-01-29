import java.util.LinkedList;
import java.lang.Math;

public class Board {

    private int width;
    private int height;
    private BoardPiece[][] pieceBoard;

    private LinkedList<CoordVector> path;
    private LinkedList<Tower> towers = new LinkedList<>();
    private LinkedList<Enemy> enemies = new LinkedList<>();

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
     * @return The number of lives lost by the player
     */
    public int updateEnemies(){
        int livesLost = 0;
        for (int i = 0; i < enemies.size(); i++){
            Enemy enemy = enemies.get(i);
            if (enemy.isDead()){
                enemies.remove(i);
                i--;
            } else {
                enemy.move();
                if (enemy.getPathIndex() >= path.size()){
                    livesLost += enemy.getHealth();
                    enemies.remove(i);
                    i--;
                }
            }
        }
        return livesLost;
    }

    /**
     * Reduces the coolDown for each tower by 1 and makes then attempt to attack the youngest enemy
     * within their attack radius, by Manhattan distance
     */
    public void updateTowers(){
        for (Tower tower : towers){
            tower.reduceCoolDown();
            CoordVector towerCoord = tower.getCoord();
            int attackRadius = tower.getAttackRadius();
            for (Enemy enemy : enemies){
                CoordVector enemyCoord = path.get(enemy.getPathIndex());
                if (getManhattanDist(towerCoord.getX(), towerCoord.getY(), enemyCoord.getX(), enemyCoord.getY()) <= attackRadius){
                    enemy.damage(tower.attack());
                    break;
                }
            }
        }
    }

    /**
     * Helper method which returns the manhattan distance between two x,y pairs
     * @param x1 first x coordinate
     * @param y1 first y coordinate
     * @param x2 second x coordinate
     * @param y2 second y coordinate
     * @return the Manhattan distance between x1,y1 and x2,y2
     */
    private int getManhattanDist(int x1, int y1, int x2, int y2){
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
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
                } else if (enemyAtCoord(x,y)){
                    System.out.print("E");

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

    private boolean enemyAtCoord(int x, int y){
        for (Enemy enemy : enemies){
            CoordVector enemyCoord = path.get(enemy.getPathIndex());
            if (enemyCoord.getX() == x && enemyCoord.getY() == y){
                return true;
            }
        }
        return false;
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
            towers.add(tower);
            return true;
        }

        return false;
    }

    /**
     * Adds an enemy to the start of the path
     * @param enemy to be added
     */
    public void spawn(Enemy enemy){
        enemies.add(enemy);
    }

    /**
     * Indicates whether there are enemies on the path
     * @return true if there are enemies on the path, else false
     */
    public boolean pathEmpty(){
        return enemies.isEmpty();
    }
}
