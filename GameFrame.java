import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Image;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

public class GameFrame extends JFrame {

    final int pixPerGrid = 20;
    int boardWidth;
    int boardHeight;

    GameFrame(int boardWidth, int boardHeight){
        JButton jButton = new JButton();
        jButton.setVisible(true);

        init(jButton);
        add(jButton);
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;

        setSize(boardWidth * pixPerGrid, boardHeight * pixPerGrid);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public void init(JButton jButton){
        jButton.setFocusPainted(false);


        Random random = new Random();

        BufferedImage bufferedImage = new BufferedImage(pixPerGrid,pixPerGrid,BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = bufferedImage.createGraphics();

        for (int buttonX = 0; buttonX < boardWidth; buttonX++){
            for (int buttonY = 0; buttonY < boardHeight; buttonY++){

                graphics2D.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
                graphics2D.fillRect(buttonX*pixPerGrid, buttonY*pixPerGrid, pixPerGrid, pixPerGrid);

            }
        }

        jButton.setIcon(new ImageIcon(bufferedImage));
    }
}
