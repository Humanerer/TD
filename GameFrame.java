import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.WindowConstants;
import java.awt.Color;
import java.awt.Image;
import java.awt.Font;
import java.awt.Paint;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class GameFrame extends JFrame {

    private final int pixPerGrid = 40;
    private JButton jButton = new JButton();
    private int boardWidth;
    private int boardHeight;
    private String[][] pieceNames;

    GameFrame(int boardWidth, int boardHeight) throws IOException {
        pieceNames = new String[boardWidth][boardHeight];

        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        jButton.setVisible(true);
        jButton.setFocusPainted(false);
        jButton.setBorderPainted(false);

        updateDisplay(null);
        add(jButton);

        setSize(boardWidth * pixPerGrid, boardHeight * pixPerGrid);
        setUndecorated(true);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void updateDisplay(String s) throws IOException {
        // Random random = new Random();

        BufferedImage bufferedImage = new BufferedImage(boardWidth*pixPerGrid, boardHeight*pixPerGrid,BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = bufferedImage.createGraphics();

        boolean asciiMode = true;

        if (asciiMode && s != null){
            int startIndex = 0;
            int lineCount = -1;
            while (startIndex >= 0){
                startIndex = s.indexOf("\n", startIndex+1);
                lineCount++;
            }

            // System.out.println(boardHeight*pixPerGrid + lineCount);

            int fontSize = boardHeight*pixPerGrid/lineCount;

            graphics2D.setColor(new Color(167, 136, 69));
            graphics2D.setFont(new Font("Courier", Font.PLAIN, fontSize));
            int lineStart = 0;
            int lineEnd = s.indexOf("\n");
            int lineNo = 0;

            while (lineEnd >= 0){
                graphics2D.drawString(s.substring(lineStart, lineEnd), 0, boardHeight+lineNo*fontSize);
                lineStart = lineEnd;
                lineEnd = s.indexOf("\n",lineEnd+1);
                lineNo++;
            }
            
        } else {
            BufferedImage backGround = ImageIO.read(new File("images/background.jpg"));
            graphics2D.drawImage(backGround, 0, 0, boardWidth*pixPerGrid, boardHeight*pixPerGrid, null);

            for (int buttonY = 0; buttonY < boardHeight; buttonY++){
                for (int buttonX = 0; buttonX < boardWidth; buttonX++){
                    int y = (boardHeight-buttonY)*pixPerGrid;
                    int x = buttonX*pixPerGrid;
                    if (pieceNames[buttonX][buttonY] == null){
                    //    graphics2D.setColor(new Color(random.nextInt(100),random.nextInt(100),random.nextInt(100)));
                    //    graphics2D.fillRect(x, y, pixPerGrid, pixPerGrid);
                    } else {
                        BufferedImage pieceImage = ImageIO.read(new File("images/"+pieceNames[buttonX][buttonY]));
                        graphics2D.drawImage(pieceImage, x, y, pixPerGrid, pixPerGrid, null);
                    }
                }
            }
        }
        
        jButton.setIcon(new ImageIcon(bufferedImage));
    }

    public void drawPiece(int x, int y, String pieceName) throws IOException {
        pieceNames[x][y] = pieceName;
//        updateDisplay();
    }

    public JButton getButton(){
        return jButton;
    }

    public int getPixPerGrid(){
        return pixPerGrid;
    }
}
