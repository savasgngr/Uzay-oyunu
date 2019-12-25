
import java.awt.HeadlessException;
import java.io.FileNotFoundException;
import javax.swing.JFrame;


public class Oyunekrani extends JFrame{
    
    public static void main(String[] args) throws FileNotFoundException {
        
        
        Oyunekrani ekran=new Oyunekrani("Uzay oyunu");
        
        ekran.setResizable(false);
        ekran.setFocusable(false);
        
        ekran.setSize(800,600);
        
        ekran.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Oyunpanel panel=new Oyunpanel();
        
        panel.requestFocus();
        
        panel.addKeyListener(panel);
        
        panel.setFocusable(true);
        panel.setFocusTraversalKeysEnabled(false);
        
        ekran.add(panel);
        ekran.setVisible(true);
        
        
        
        
    }

    public Oyunekrani(String title) throws HeadlessException {
        super(title);
    }
    
}
