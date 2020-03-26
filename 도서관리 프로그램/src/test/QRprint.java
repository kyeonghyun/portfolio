package test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.awt.Image.*;
import javax.imageio.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;




public class QRprint extends JFrame{


   
public QRprint(int i) {
   

   ImageIcon []img= { new ImageIcon("images/1.jpg"), 
             new  ImageIcon("images/2.jpg"),
             new  ImageIcon("images/3.jpg"),
             new  ImageIcon("images/4.jpg"),
             new  ImageIcon("images/5.jpg"),
             new  ImageIcon("images/6.jpg"),
             new  ImageIcon("images/7.jpg"),
             new  ImageIcon("images/8.jpg"),
             new  ImageIcon("images/9.jpg"),
             new  ImageIcon("images/10.jpg"), 
             new  ImageIcon("images/11.jpg"), 
             new  ImageIcon("images/12.jpg"), 
             new  ImageIcon("images/13.jpg"), 
             new  ImageIcon("images/14.jpg"), 
             new  ImageIcon("images/15.jpg"),
             new  ImageIcon("images/16.jpg"),
             new  ImageIcon("images/17.jpg"),
             new  ImageIcon("images/18.jpg"),
             new  ImageIcon("images/19.jpg"),
             new  ImageIcon("images/20.jpg"),
             new  ImageIcon("images/21.jpg"),
             new  ImageIcon("images/22.jpg"),
             new  ImageIcon("images/23.jpg"),
             new  ImageIcon("images/24.jpg"),  
             new  ImageIcon("images/25.jpg"),
             new  ImageIcon("images/26.jpg"),
             new  ImageIcon("images/27.jpg"),
             new  ImageIcon("images/28.jpg"),
             new  ImageIcon("images/29.jpg"),
             new  ImageIcon("images/30.jpg")
};
   
   Dimension dim = new Dimension(img[i].getIconWidth()+20, img[i].getIconHeight()+35);                                       
    
   JFrame frame=new JFrame("QR코드 정보");
   frame.setLocation(300, 10);
   frame.setPreferredSize(dim);
   frame.pack();
   frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
   frame.setLocationRelativeTo(null);
   
      JLabel label=new JLabel(img[i]);
      label.setIcon(img[i]);
      label.setSize(img[i].getIconWidth(), img[i].getIconHeight());
      frame.add(label);      
      frame.setVisible(true);
      
     
}

}






