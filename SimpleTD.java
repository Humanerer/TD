import java.util.LinkedList;

class SimpleTD {
    public static void main(String[] args) {
        int lives = 10;

        LinkedList<CoordVector> path = new LinkedList<CoordVector>();
        for (int x = 0; x < 32; x++) {
            path.add(new CoordVector(x,4));
        }



        Board board = new Board(32,18, path);
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

                Thread.sleep(1000);
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }



    }
}