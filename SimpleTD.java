import java.util.LinkedList;

class SimpleTD {

    final static int width = 32;
    final static int height = 18;

    public static void main(String[] args) {
        int lives = 10;
        final int frameDelayMs = 200;

        LinkedList<CoordVector> path = new LinkedList<CoordVector>();
        for (int x = 0; x < 32; x++) {
            path.add(new CoordVector(x,4));
        }

        GameFrame gameFrame = new GameFrame(width, height);

        Board board = new Board(width, height, path);
        board.printBoard();
        System.out.println();
        board.placeTower(new Tower(4,5),4,5);
//        board.spawn(new Enemy());

        try {
            while (lives > 0){
                board.updateTowers();
                lives -= board.updateEnemies();
                board.spawn(new Enemy());
                board.printBoard();
                System.out.println(lives);

                Thread.sleep(frameDelayMs);
            }
            System.out.println("<< GAME OVER >>");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }



    }
}