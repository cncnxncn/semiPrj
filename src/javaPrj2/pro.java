package javaPrj2;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import panel.DBmodel;
import panel.daySales;
import panel.panel;

public class pro extends JFrame {
	panel pan;
	daySales dsa;
	
	public pro() throws Exception {
		
		pan=new panel();
		dsa=new daySales();
		JTabbedPane pane=new JTabbedPane();
		pane.add("메뉴 및 주문", pan);
		pane.add("매출관리",dsa);
		pane.setSelectedIndex(0);
		
		add("Center",pane);
		
		setSize(1000, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					DBmodel mo = new DBmodel();
					mo.deleteMenu();
				} catch (Exception e1) {
				}
			}
		});
		
	}
	public static void main(String[] args) throws Exception {
		new pro();
	}
	
}
//int money = 0
//if(combox.equls(후라읻,))
//	money += 18000;