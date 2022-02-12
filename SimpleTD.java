import java.io.IOException;
import java.util.LinkedList;

class SimpleTD {

    final static int width = 32;
    final static int height = 18;

    public static void main(String[] args) {
        int lives = 10;
        final int frameDelayMs = 200;

        GameFrame gameFrame = null;
        try {
            gameFrame = new GameFrame(width, height);
        } catch (IOException e) {
            e.printStackTrace();
        }

        LinkedList<CoordVector> path = new LinkedList<CoordVector>();
        for (int x = 0; x < width; x++) {
            path.add(new CoordVector(x,4));
            try {
                Path p = new Path();
                gameFrame.drawPiece(x,4, p.getImageDir());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



        Board board = new Board(width, height, path);
        board.printBoard();
        System.out.println();
        Tower tower = new Tower(4,5);
        board.placeTower(tower,4,5);
        try {
            gameFrame.drawPiece(4,5, tower.getImageDir());
        } catch (IOException e) {
            e.printStackTrace();
        }
//        board.spawn(new Enemy());

        try {
            while (lives > 0){
                board.updateTowers();
                lives -= board.updateEnemies();
                board.spawn(new Enemy());
                board.printBoard();
                System.out.println(lives);
                gameFrame.updateDisplay();

                Thread.sleep(frameDelayMs);
            }
            System.out.println("<< GAME OVER >>");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }


        gameFrame.dispose();
    }
}