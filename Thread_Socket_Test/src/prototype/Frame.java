package prototype;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Frame{
	private static Frame mFrame =null;
	public JFrame mJFrame = null;
	
	JTextArea txta1;
	public static Frame getInstance() {
		if(mFrame == null)
			mFrame = new Frame();
		return mFrame;
	}
	private Frame() {
		mJFrame = new JFrame();
		mJFrame.setTitle("ProtoType");
		mJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = mJFrame.getContentPane();
		contentPane.setLayout(null);
		
		txta1 = new JTextArea();
		JScrollPane scroll1 = new JScrollPane(txta1);
		scroll1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll1.setBounds(100, 60, 500, 450);
		contentPane.add(scroll1);

//		JButton btn_selP = new JButton("회원정보");
//		btn_selP.setLocation(40, 60);
//		btn_selP.setSize(120, 80);
//		contentPane.add(btn_selP);
//
//		btn_selP.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//
//				txta1.setText("");
//				// Frame에 싱글톤 변수 사용
//				SIngleTon_Test s = SIngleTon_Test.getInstance();
//				txta1.append(s.readData_singleTon + "\n");
//				
////				txta1.setCaretPosition(txta1.getDocument().getLength());
//				scroll1.getVerticalScrollBar().setValue(scroll1.getVerticalScrollBar().getMaximum());

				
//				Rasp_socket mP = new Rasp_socket();
//				mP.loadRasp_socket(txta1);
//			}
//		});

		JButton btn_clr = new JButton("값 지우기");
		btn_clr.setLocation(660, 60);
		btn_clr.setSize(100, 200);
		contentPane.add(btn_clr);
		btn_clr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				txta1.setText("");

			}
		});

		JButton btn_exit = new JButton("종료");
		btn_exit.setLocation(660, 300);
		btn_exit.setSize(100, 200);
		contentPane.add(btn_exit);
		btn_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		mJFrame.setSize(800, 600);
		mJFrame.setVisible(true);
	}
	

}
