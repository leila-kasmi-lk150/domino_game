package domino;
/**
 *
 * @author Leila
 */
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javax.swing.*;

public class start extends JFrame {
    ArrayList<String> dominoStone = new ArrayList<>();
    ArrayList<String> player1Stone = new ArrayList<>();
    ArrayList<String> player2Stone = new ArrayList<>();
    Random random = new Random();
    JPanel player1Panel = new JPanel();
    JPanel player2Panel = new JPanel();
    JPanel gamePanel = new JPanel();
    JPanel sidePanel = new JPanel();
    
    //CustomButton restart  = new CustomButton("Restart", 160, 60);
    JButton restart = new JButton("Restart");
    public start() {
        distribute();
        getContentPane().setBackground(new Color(255, 246, 224));
        setSize(1350, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        //player1Panel.setBackground(Color.BLUE);
        player1Panel.setLayout(new FlowLayout());
        
        //player2Panel.setBackground(Color.BLUE);
        player2Panel.setLayout(new FlowLayout());
        
        gamePanel.setLayout(null);
        gamePanel.setBackground(new Color(255, 246, 224));
        gamePanel.setSize(1000, 700);
        gamePanel.setLayout(new BorderLayout());
        gamePanel.add(player1Panel, BorderLayout.NORTH);
        gamePanel.add(player2Panel, BorderLayout.SOUTH);
        
        // Load images
        Image imgDomino = new ImageIcon(getClass().getResource("/img/turnOffTile.png")).getImage();
        
        // Create labels
        JLabel label1 = new JLabel(new ImageIcon(imgDomino));
        JLabel label2 = new JLabel(new ImageIcon(new ImageIcon(imgDomino).getImage().getScaledInstance(100, 200, Image.SCALE_SMOOTH)));

        // Rotate label2
        label2.setIcon(new ImageIcon(rotateImage(new ImageIcon(imgDomino).getImage(), 90)));

        // Set layout for central panel to hold the labels
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(new Color(255, 246, 224)); // Same as gamePanel background color

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(label1, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        centerPanel.add(label2, gbc);

        // Add center panel to game panel
        gamePanel.add(centerPanel, BorderLayout.CENTER);

        
        //sidePanel.setBackground(Color.RED);
        sidePanel.setSize(350, 700);
        sidePanel.add(restart);
        sidePanel.add(restart);
        //sidePanel.setLayout(new BorderLayout());
        
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(gamePanel, BorderLayout.CENTER);
        getContentPane().add(sidePanel, BorderLayout.WEST);
        
        setVisible(true);
        
        addDominoesToPanels();
    }

    public void distribute() {
        System.out.println("Domino Stone");
        for (int i = 0; i <= 6; i++) {
            for (int j = i; j <= 6; j++) {
                System.out.print("[" + i + "-" + j + "] ");
                dominoStone.add(i + "" + j);
            }
            System.out.println();
        }
        
        // Distribute the stones: 7 for each player, rest 14 for the pile
        for (int i = 0; i < 7; i++) {
            int r1 = random.nextInt(dominoStone.size());
            player1Stone.add(dominoStone.get(r1));
            dominoStone.remove(r1);
            
            int r2 = random.nextInt(dominoStone.size());
            player2Stone.add(dominoStone.get(r2));
            dominoStone.remove(r2);
        }
        Collections.shuffle(dominoStone);
        System.out.println("Player 1 Stones: " + player1Stone);
        System.out.println("Player 2 Stones: " + player2Stone);
        System.out.println("Rest Stones: " + dominoStone);
    }

    private void addDominoesToPanels() {
        try {
            // Add dominoes to player 1 panel
            Image imgDominoPlayer1 = new ImageIcon(getClass().getResource("/img/turnOffTile.png")).getImage();
            if (imgDominoPlayer1 == null) {
                System.out.println("Player 1 domino image not found!");
                return;
            }
            for (int i = 0; i < player1Stone.size(); i++) {
                JLabel label = new JLabel(new ImageIcon(imgDominoPlayer1));
                player1Panel.add(label);
            }

            // Add dominoes to player 2 panel
            for (int i = 0; i < player2Stone.size(); i++) {
                String x = player2Stone.get(i);
                Image imgDominoPlayer2 = new ImageIcon(getClass().getResource("/img/" + x + ".png")).getImage();
                if (imgDominoPlayer2 == null) {
                    System.out.println("Player 2 domino image not found: " + x);
                    continue;
                }
                JLabel label = new JLabel(new ImageIcon(imgDominoPlayer2));
                player2Panel.add(label);
            }

            player1Panel.revalidate();
            player2Panel.revalidate();
            player1Panel.repaint();
            player2Panel.repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // Method to rotate an image
    private static Image rotateImage(Image img, int angle) {
        int width = img.getWidth(null);
        int height = img.getHeight(null);
        BufferedImage rotated = new BufferedImage(height, width, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = rotated.createGraphics();
        g2d.rotate(Math.toRadians(angle), width / 2, height / 2);
        g2d.drawImage(img, 0, 0, null);
        g2d.dispose();
        return rotated;
    }

}
