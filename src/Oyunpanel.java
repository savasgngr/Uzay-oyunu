
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import jdk.nashorn.internal.ir.BreakNode;

class Ates{
    private  int x;
    private  int y;

    public Ates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}


public class Oyunpanel extends  JPanel implements KeyListener,ActionListener{
    private  int sure=0;
    private  int ates=0;
    Timer timer =new Timer(1/100, this);
    
    private BufferedImage image;
    private  ArrayList<Ates> atesler=new ArrayList<Ates>();
    
    private int atesY=1;//ateş her seferinde bir birim ilerlemesi
    private  int topX=0;//topu sıfır noktasında başlatma
    
    private  int topdirX=2;//her seferinde top 2 birim ilerlemesi
    
    private int xuzayGemisiX=0;//uzay gemisini sıfır noktasında başlatma
    
    private  int dirUzayX=20;//uzay gemisinin birim hareketi
    
    public boolean kontrolEt(){
        
        for (Ates ateskontrolAtes : atesler) {
            
            if (new Rectangle(ateskontrolAtes.getX(),ateskontrolAtes.getY(),10,20).intersects(new Rectangle(topX,0,20,20))) {
                
                return true;
                
            }
           
        }
        return  false;
        
    }
    

    public Oyunpanel() throws FileNotFoundException {
 
        try {
            image=ImageIO.read(new  FileInputStream(new File("uzaygemisi.png")));
        } catch (IOException ex) {
            Logger.getLogger(Oyunpanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        setBackground(Color.BLACK);
        
        timer.start();
       
        
        
    }

    @Override
    public void repaint() {
        super.repaint(); 
        
        
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); 
        sure+=5;    
        g.setColor(Color.red);
        g.fillOval(topX, 0, 20, 20);
         
        g.setColor(Color.GREEN);
        g.fillOval(topX, 100, 20, 20);
        g.setColor(Color.blue);
        g.fillOval(topX, 200, 20, 20);
      
        
        g.drawImage(image, xuzayGemisiX, 500, image.getWidth()/10, image.getHeight()/10, this);
        
        for (Ates ates : atesler) {
            
            if (ates.getY()<0) {
                
            atesler.remove(ates);
                
            }  
        }
        g.setColor(Color.YELLOW);
        for (Ates ates1 : atesler) {
            
            g.fillRect(ates1.getX(), ates1.getY(), 10, 20);
            
        }
        
        if (kontrolEt()) {
            
            
            timer.stop();
            String mesaj="kazandınız\n"
                    + "harcanan ateş:"+ates+
                    "geçen süre:"+sure/1000;
            
            JOptionPane.showMessageDialog(this, mesaj);
            System.exit(0);
        
        }
    }
      
    
    @Override
    public void keyTyped(KeyEvent e) {
    
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        int c=e.getKeyCode();
        
        if (c==KeyEvent.VK_LEFT) {
            
            if (xuzayGemisiX<=0) {
                
                xuzayGemisiX=0; 
            }else{
                
                xuzayGemisiX-=dirUzayX;
            }
            
            
        }else if (c==KeyEvent.VK_RIGHT) {
           
            if (xuzayGemisiX>=750) {
                
                xuzayGemisiX=750;
                
            }else{
                
                xuzayGemisiX+=dirUzayX;
            }
            
        }else if (c==KeyEvent.VK_CONTROL) {
            
            atesler.add(new Ates(xuzayGemisiX+15, 500));
            
            ates++;
            
        }
       
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    //ateş etme hareketi
        for (Ates ates1 : atesler) {
            
           ates1.setY(ates1.getY()-atesY);
            
        }
        
        
   //topun hareket etmesi 
        topX+=topdirX;
        
        if (topX>=770) {
            
            topdirX=-topdirX;
           
            
        }
        if (topX<=0) {
            
            topdirX=-topdirX; 
        }
        repaint();
    
    }
    
 
    
}
