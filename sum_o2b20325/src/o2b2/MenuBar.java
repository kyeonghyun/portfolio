package o2b2;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.event.ActionEvent;

import javax.imageio.ImageIO;
import javax.swing.*;

//import AssistantsGUI.MenuBarAction.myActionListener;


public class MenuBar extends JFrame{
   
   BufferedImage img = null; // �̹��� �ִ°� �߰��ߴ���
   static private MenuBar menubar = null;
   
   ///
    JScrollPane scrollPane;
    ImageIcon icon;
    ///

   private MenuBar() {
      
   }
   
   public static MenuBar getIntance() {
      if(menubar == null)
         menubar = new MenuBar();
      return menubar;
   }

   void MenuBar() {
      setTitle("Study Assistants ������ ������");
      createMenu(); // �޴� ����, �����ӿ� ����
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      setSize(1200,800);
      setLocation(350, 100);
      setVisible(true);
      
   }
   
   JMenuBar MenuBar1 = new JMenuBar();   // �޴����� ����
   
   JMenu screenMenu1 = new JMenu("File"); 
   JMenu screenMenu2 = new JMenu("ȸ������");
   JMenu screenMenu3 = new JMenu("�����");
   JMenu screenMenu4 = new JMenu("������");
   

 
   void createMenu() {

      JMenuItem[] mListItem1 = new JMenuItem[3];  // �޴��� ���� �迭��
      JMenuItem[] mListItem2 = new JMenuItem[2];
      JMenuItem[] mListItem3 = new JMenuItem[2];
      JMenuItem[] mListItem4 = new JMenuItem[2];

      mListItem1[0] = new JMenuItem("SOCKET ON");
      mListItem1[0].setName("SOCKET ON");
      mListItem1[1] = new JMenuItem("Home");
      mListItem1[1].setName("Home");
      mListItem1[2] = new JMenuItem("Exit");
      mListItem1[2].setName("Exit");
      
      mListItem2[0] = new JMenuItem("����");
      mListItem2[0].setName("����");
      mListItem2[1] = new JMenuItem("������");
      mListItem2[1].setName("������");

      mListItem3[0] = new JMenuItem("��ȹ �н��ð�");
      mListItem3[0].setName("��ȹ �н��ð�");
      mListItem3[1] = new JMenuItem("���� �н��ð�");
      mListItem3[1].setName("���� �н��ð�");
      
      mListItem4[0] = new JMenuItem("�׷���");
      mListItem4[0].setName("�׷���");
      mListItem4[1] = new JMenuItem("��");
      mListItem4[1].setName("��");
      
      //MenuBarAction mb = new MenuBarAction();
      
      for(int i=0; i< mListItem1.length; i++) {
         screenMenu1.add(mListItem1[i]);
         mListItem1[i].addActionListener(new myActionListener());
      }
      
      for(int i=0; i< mListItem2.length; i++) {
         screenMenu2.add(mListItem2[i]);
         mListItem2[i].addActionListener(new myActionListener());
      }
      
      for(int i=0; i< mListItem3.length; i++) {
         screenMenu3.add(mListItem3[i]);
         mListItem3[i].addActionListener(new myActionListener());
      }
      
      for(int i=0; i< mListItem4.length; i++) {
         screenMenu4.add(mListItem4[i]);
         mListItem4[i].addActionListener(new myActionListener());
      }
      

      
      setJMenuBar(MenuBar1);  // menubar ���̱�
      
      MenuBar1.add(screenMenu1);
      MenuBar1.add(screenMenu2);
      MenuBar1.add(screenMenu3);
      MenuBar1.add(screenMenu4);
      ///
      MenuBar1.setVisible(false);
      
      
      homemain jp6 = new homemain(null);
      ///////////////////////////////////////////////////////
      
      
      
      ///////////////////////////////////////////////////////
      
      
      
      jp6.setBackground(new Color(230, 64, 74));
      getContentPane().removeAll();
      getContentPane().add(jp6);
      revalidate();
      repaint();
      
      
   }
   /////////////////////////////////////////////////////
   class jp6 extends JPanel {
      public void paint(Graphics g) {
         try {
            img = ImageIO.read(new File("image/book3.png")); 
         }catch (Exception e) {
            // TODO: handle exception
         }
         g.drawImage(img,0,0,null);
      }
   }
   ////////////////////////////////////////////////////
   void setVisible() {
      MenuBar1.setVisible(true);
   }
   class myActionListener implements ActionListener{  // �޴��ٿ��� �޴��� ���� �׼�

      
      
      @Override
      public void actionPerformed(ActionEvent e) {
         JMenuItem m = (JMenuItem)e.getSource();         

         //////////////////////// file �޴��� /////////////////////////
         if(m.getName().equals("SOCKET ON"))
         {
            JPanel01 jp1 = new JPanel01(null);  // �߿�
      
                  getContentPane().removeAll();
                  getContentPane().add(jp1);
                  jp1.setBackground(new Color(50, 133, 187));
              
                  revalidate();
                  repaint();
         }
         
         if(m.getName().equals("Exit"))
         {
               System.exit(0);
         }

         if(m.getName().equals("����"))
         {
               JPanel02 jp2 = new JPanel02(null);
               jp2.setBackground(new Color(50, 133, 187));
                  getContentPane().removeAll();
                  getContentPane().add(jp2);
                  revalidate();
                  repaint();
         }
         
         /////////////////////////////// ����� �޴��� ///////////////////////////////
         if(m.getName().equals("��ȹ �н��ð�"))
         {
               JPanel03 jp3 = new JPanel03(null);
               jp3.setBackground(new Color(50, 133, 187));
                  getContentPane().removeAll();
                  getContentPane().add(jp3);
                  revalidate();
                  repaint();
         }
         
         if(m.getName().equals("���� �н��ð�"))
         {
               JPanel04 jp4 = new JPanel04(null);
               jp4.setBackground(new Color(50, 133, 187));
                  getContentPane().removeAll();
                  getContentPane().add(jp4);
                  revalidate();
                  repaint();
         }
         
         if(m.getName().equals("�׷���"))
         {
            Select_RealStudyTime mR = new Select_RealStudyTime();
            mR.loadRealStudyTime();
            Select_ScheduleStudyTime mS = new Select_ScheduleStudyTime();
            mS.loadScheduleStudytime();
            ResultPanel jp5 = new ResultPanel();
               jp5.setBackground(new Color(50, 133, 187));
                  getContentPane().removeAll();
                  getContentPane().add(jp5);
                  revalidate();
                  repaint();
         }
         
         if(m.getName().equals("Home"))
         {
               homemain jp6 = new homemain(null);
               jp6.setBackground(new Color(230, 64, 74));
               getContentPane().removeAll();
               getContentPane().add(jp6);
               revalidate();
               repaint();
         }
         
//         if(m.getName().equals("������"))
//         {
//               JPanel07 jp7 = new JPanel07(null);
//               jp7.setBackground(new Color(50, 133, 187));
//               getContentPane().removeAll();
//               getContentPane().add(jp7);
//               revalidate();
//               repaint();
//         }
               
      }
   }      
}
