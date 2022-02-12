import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.WindowConstants;
import java.awt.Color;
import java.awt.Image;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

public class GameFrame extends JFrame {

    final int pixPerGrid = 40;
    private JButton jButton = new JButton();
    int boardWidth;
    int boardHeight;

    GameFrame(int boardWidth, int boardHeight){
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        jButton.setVisible(true);
        jButton.setFocusPainted(false);
        jButton.setBorderPainted(false);

        init();
        add(jButton);

        setSize(boardWidth * pixPerGrid, boardHeight * pixPerGrid);
        setUndecorated(true);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void init(){
        Random random = new Random();

        BufferedImage bufferedImage = new BufferedImage(boardWidth*pixPerGrid, boardHeight*pixPerGrid,BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = bufferedImage.createGraphics();

        for (int buttonY = 0; buttonY < boardHeight; buttonY++){
            for (int buttonX = 0; buttonX < boardWidth; buttonX++){
                graphics2D.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
                graphics2D.fillRect(buttonX*pixPerGrid, buttonY*pixPerGrid, pixPerGrid, pixPerGrid);
            }
        }

        jButton.setIcon(new ImageIcon(bufferedImage));
    }
}
