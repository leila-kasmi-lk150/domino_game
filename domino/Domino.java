package domino;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Leila
 */
public class Domino extends JFrame{

    JButton start;
    public Domino() {
        getContentPane().setBackground(new Color(255, 246, 224));
        setSize(1350, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        start = new JButton("START");
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new start();
            }

        });
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(start, BorderLayout.CENTER);
        setVisible(true);
    }

   
    
    
    public static void main(String[] args) {
        new Domino();
    }
    
}
