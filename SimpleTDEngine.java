import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.LinkedList;

class SimpleTDEngine {

    final static int width = 32;
    final static int height = 18;

    public SimpleTDEngine () {
        int lives = 10;
        final int frameDelayMs = 200;

        GameFrame gameFrame = null;
        try {
            gameFrame = new GameFrame(width, height);
        } catch (IOException e) {
            e.printStackTrace();
        }

        LinkedList<CoordVector> path = new LinkedList<>();
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
        // board.printBoard();
        // System.out.println();
        Tower tower = new Tower(4,5);

        try {
            if (board.placeTower(tower,4,5)){
                gameFrame.drawPiece(4,5, tower.getImageDir());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        board.spawn(new Enemy());

        GameFrame finalGameFrame = gameFrame;
        gameFrame.getButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Point xy = finalGameFrame.getButton().getMousePosition();
                int x = width-xy.x/finalGameFrame.getPixPerGrid()+1;
                int y = height-xy.y/finalGameFrame.getPixPerGrid()+1;
                System.out.println(x+" "+y);
                board.placeTower(new Tower(x,y),x,y);
            }
        });



        try {
            while (lives > 0){
                board.updateTowers();
                lives -= board.updateEnemies();
                board.spawn(new Enemy());
                // board.printBoard();
                // System.out.println(board.asString());
                System.out.println(lives);
                gameFrame.updateDisplay(board.asString());

                Thread.sleep(frameDelayMs);
            }
            System.out.println("<< GAME OVER >>");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }


        gameFrame.dispose();
    }
}