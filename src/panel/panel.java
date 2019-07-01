package panel;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicBorders.RadioButtonBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import sales.sales;

public class panel extends Panel implements ActionListener{
   JPanel menu,order,textf,imgf,east,east_s,east_n,west,west_s,west_n;
   JButton obu,cbu,pbu;
   JTextField mname,mprice,mnum,msum;
   JRadioButton[] c=new JRadioButton[27]; 
	 String[] mm={"�Ķ��̵�ġŲ","���ġŲ","�Ѹ�ŬġŲ","��ũ������","����ġŲ","�Ҵ�ġŲ","������","����������","��õ������","����Ƣ��","����Ƣ��","��äƢ��","����Ƣ��","ġ�ƽ","����",
			 "ó��ó��","���̽�","��������","�ƻ���","ī��","ĪŸ��","�ݶ�","���̴�","ȯŸ","�ֽĽ�","��Ű��","����"};
   
	 int total;
	 int price ;
   JLabel la;
   JTable table;
   ButtonGroup meme;
   ButtonGroup mememe;
   
   DBmodel model;
   salesTableModel salModel;
   ImageIcon icon,icon2;
   daySales days;
   int sum;
   String str;
   public panel() throws Exception {
	   connectdb();
      menuFrame();
  		searchMenu();
   }
private void menuFrame() {
	obu=new JButton("�ֹ�");
      cbu=new JButton("�ʱ�ȭ");
      pbu=new JButton("�߰�");
      
      msum=new JTextField();
     
      for (int i = 0; i < c.length; i++) {
    	  c[i]=new JRadioButton(mm[i]);
	}
      
      meme=new ButtonGroup();
      for (int i = 0; i < c.length; i++) {
		meme.add(c[i]);
	}
      
for (int i = 0; i < c.length; i++) {
	c[i].addActionListener(this);
}      
      obu.addActionListener(this);
      cbu.addActionListener(this);
      pbu.addActionListener(this);
	
      JPanel west_c=new JPanel();
      west_n=new JPanel();
      west_n.setLayout(new GridLayout(8, 3));
      for (int i = 0; i < c.length; i++) {
    	  west_n.add(c[i]);
      }
		for (int i = 0; i < 15; i++) {
			c[i].setFont(new Font("�ü�ü", Font.BOLD, 15));
			c[i].setForeground(Color.blue);
		}
		for (int i = 15; i < 27; i++) {
//			c[i].setFont(new Font("�ü�ü", Font.BOLD, 15));
			c[i].setForeground(Color.MAGENTA);
		}
      west_n.setBorder(new TitledBorder(new LineBorder(Color.BLACK), "�޴�",0,0) );
      west_n.setFont(new Font("�޸տ�����", Font.ITALIC, 20));
      
      west=new JPanel();
      west.setLayout(new BorderLayout());
      west.add(west_n,BorderLayout.CENTER);
      west.add(west_c,BorderLayout.NORTH);
      
      JLabel lala=new JLabel("�����");
      lala.setFont(new Font("�޸տ�����",Font.BOLD , 50));
      west_s=new JPanel();
      west_s.add(lala);
      west.add(west_s,BorderLayout.SOUTH);
      
      east=new JPanel();
      east.setLayout(new BorderLayout());
      east_s=new JPanel();
      east_s.setLayout(new BorderLayout());
      JPanel east_s_s=new JPanel();
      
      JPanel east_c_s=new JPanel();
      JPanel east_c=new JPanel();
      east_c.setLayout(new BorderLayout());

      east_s_s.add(obu);
      east_s_s.add(cbu);
      east_s_s.add(pbu);
      
      east_n=new JPanel();
      east_n.setLayout(new BorderLayout());
      la=new JLabel(icon);
	  east_n.add(la);
	   
	   msum=new JTextField(20);
	   salModel=new salesTableModel();
	   table=new JTable(salModel);
	   table.setModel(salModel);
	   JPanel east_c_n=new JPanel();
	   JPanel east_c_c=new JPanel();
	   JLabel labe=new JLabel("��");
	   labe.setFont(new Font("�޸տ�����", Font.BOLD, 18));
	   JLabel labe1=new JLabel("��");
	   labe1.setFont(new Font("�޸տ�����", Font.BOLD, 18));
	   east_c_n.setLayout(new BorderLayout());
	   east_c_c.setLayout(new BorderLayout());
	   
	   
      east_c_n.add(new JScrollPane(table));
      east_c_n.setBorder(new TitledBorder("����ǥ"));
      east_c_s.add(labe);
      east_c_s.add(msum);
      east_c_s.add(labe1);
      
      east_c.add(east_c_n,BorderLayout.CENTER);
      east_c.add(east_c_s,BorderLayout.SOUTH);
      east_s.add(east_s_s,BorderLayout.SOUTH);
      
      east.add(east_c,BorderLayout.CENTER);
      east.add(east_s,BorderLayout.SOUTH);
      east.add(east_n,BorderLayout.NORTH);
      msum.setHorizontalAlignment(JTextField.RIGHT);
//      eventPro();
      
      setLayout(new GridLayout(1, 2));
      salModel.fireTableDataChanged();
      
      add(west);
      add(east);
}

private void connectdb() {
	   try {
		model=new DBmodel();
		System.out.println("����");
	} catch (Exception e) {
		System.out.println("���� ����");
		e.printStackTrace();
	}
	   	
}

class salesTableModel extends AbstractTableModel{
	   ArrayList data=new ArrayList();
	   String[] colName= {"ǰ��","����"};
	   
	@Override
	public int getColumnCount() {
		return colName.length;
	}

	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		ArrayList temp=(ArrayList) data.get(row);
		return temp.get(col);
	}
	@Override
	public String getColumnName(int col) {
		return colName[col];
	}
	   
   }
   

@Override
public void actionPerformed(ActionEvent e) {
	Object evt=e.getSource();
	Object evc=e.getActionCommand();
		for (int i = 0; i < c.length; i++) {
			if (c[i].isSelected()==true&&evt==pbu) {
					this.total = orderMenu(evt, price, i);
					msum.setText(""+this.total);
						try {
							insertMenu();
							restart();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
			}else if (c[i].isSelected()==true&&evt==this.cbu) {
				sum=0;
				msum.setText(""+sum);
				try {
					model.deleteMenu();
					restart();
					JOptionPane.showMessageDialog(null,"�ֹ� �ʱ�ȭ");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}else if (c[i].isSelected()==true&&evt==this.obu) {
				try {
					daySales de = new daySales();
					de.getprice(msum.getText());
				} catch (Exception e1) {
					System.out.println("�ֹ� ����");
				}
				JOptionPane.showMessageDialog(null,"�ֹ� �Ϸ�!! \n������ : "+total);
			}
		}
		imagess(e);
}
private void restart() {
	try {
		ArrayList data=model.searchMenu();
		salModel.data=data;
		salModel.fireTableDataChanged();
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
}
private void insertMenu() throws Exception {
	for (int i = 0; i < c.length; i++) {
		if (c[i].isSelected()==true) {
			model.insertMenu(mm[i]);
			JOptionPane.showMessageDialog(null,"�ֹ��߰� �Ϸ�");
			
		}
	}
}
public int orderMenu(Object evt, int price, int i) {
	if (c[i].isSelected()==true&&evt==pbu&&mm[i].equals("�Ķ��̵�ġŲ")) {
		price = 12000;
	} if (c[i].isSelected()==true&&evt==pbu&&mm[i].equals("���ġŲ")) {
		price = 13000;
	} if (c[i].isSelected()==true&&evt==pbu&&mm[i].equals("�Ѹ�ŬġŲ")) {
		price = 15000;
	} if (c[i].isSelected()==true&&evt==pbu&&mm[i].equals("����ġŲ")) {
		price = 13000;
	} if (c[i].isSelected()==true&&evt==pbu&&mm[i].equals("�Ҵ�ġŲ")) {
		price = 15000;
	} if (c[i].isSelected()==true&&evt==pbu&&mm[i].equals("������")) {
		price = 15000;
	} if (c[i].isSelected()==true&&evt==pbu&&mm[i].equals("����������")) {
		price = 16000;
	} if (c[i].isSelected()==true&&evt==pbu&&mm[i].equals("��õ������")) {
		price = 17000;
	} if (c[i].isSelected()==true&&evt==pbu&&mm[i].equals("����Ƣ��")) {
		price = 6000;
	} if (c[i].isSelected()==true&&evt==pbu&&mm[i].equals("����Ƣ��")) {
		price = 8000;
	} if (c[i].isSelected()==true&&evt==pbu&&mm[i].equals("��äƢ��")) {
		price= 6000;
	} if (c[i].isSelected()==true&&evt==pbu&&mm[i].equals("����Ƣ��")) {
		price = 8000;
	} if (c[i].isSelected()==true&&evt==pbu&&mm[i].equals("ġ�ƽ")) {
		price = 7000;
	} if (c[i].isSelected()==true&&evt==pbu&&mm[i].equals("����")) {
		price = 5000;
	} if (c[i].isSelected()==true&&evt==pbu&&mm[i].equals("ó��ó��")) {
		price = 3000;
	} if (c[i].isSelected()==true&&evt==pbu&&mm[i].equals("���̽�")) {
		price = 3000;
	} if (c[i].isSelected()==true&&evt==pbu&&mm[i].equals("��������")) {
		price = 3000;
	} if (c[i].isSelected()==true&&evt==pbu&&mm[i].equals("�ƻ���")) {
		price = 5000;
	} if (c[i].isSelected()==true&&evt==pbu&&mm[i].equals("ī��")) {
		price = 4000;
	} if (c[i].isSelected()==true&&evt==pbu&&mm[i].equals("ĪŸ��")) {
		price = 5000;
	} if (c[i].isSelected()==true&&evt==pbu&&mm[i].equals("�ݶ�")) {
		price = 2000;
	} if (c[i].isSelected()==true&&evt==pbu&&mm[i].equals("���̴�")) {
		price = 2000;
	}else if (c[i].isSelected()==true&&evt==pbu&&mm[i].equals("ȯŸ")) {
		price = 2000;
	}else if (c[i].isSelected()==true&&evt==pbu&&mm[i].equals("�ֽĽ�")) {
		price = 3000;
	}else if (c[i].isSelected()==true&&evt==pbu&&mm[i].equals("��Ű��")) {
		price=2000;
	}else if (c[i].isSelected()==true&&evt==pbu&&mm[i].equals("����")) {
		price=3000;
	}
	
	return  sum+=price;
}

private void searchMenu() throws Exception {
//	int m = mm.length;
	ArrayList data=model.searchMenu();
	salModel.data=data;
	table.setModel(salModel);
	salModel.fireTableDataChanged();
}

private void imagess(ActionEvent e) {
	for (int i = 0; i < c.length; i++) {
		if (e.getSource()==c[0]) {
			icon=new ImageIcon("src/pictures/chi.png");
			la.setIcon(icon);
		}else if (e.getSource()==c[1]) {
			icon=new ImageIcon("src/pictures/c1.png");
			la.setIcon(icon);
		}else if (e.getSource()==c[2]) {
			icon=new ImageIcon("src/pictures/c3.png");
			la.setIcon(icon);
		}else if (e.getSource()==c[3]) {
			icon=new ImageIcon("src/pictures/c5.png");
			la.setIcon(icon);
		}else if (e.getSource()==c[4]) {
			icon=new ImageIcon("src/pictures/c2.png");
			la.setIcon(icon);
		}else if (e.getSource()==c[5]) {
			icon=new ImageIcon("src/pictures/c4.png");
			la.setIcon(icon);
		}else if (e.getSource()==c[6]) {
			icon=new ImageIcon("src/pictures/t1.png");
			la.setIcon(icon);
		}else if (e.getSource()==c[7]) {
			icon=new ImageIcon("src/pictures/t.png");
			la.setIcon(icon);
		}else if (e.getSource()==c[8]) {
			icon=new ImageIcon("src/pictures/t2.png");
			la.setIcon(icon);
		}else if (e.getSource()==c[9]) {
			icon=new ImageIcon("src/pictures/f.png");
			la.setIcon(icon);
		}else if (e.getSource()==c[10]) {
			icon=new ImageIcon("src/pictures/f1.png");
			la.setIcon(icon);
		}else if (e.getSource()==c[11]) {
			icon=new ImageIcon("src/pictures/f5.png");
			la.setIcon(icon);
		}else if (e.getSource()==c[12]) {
			icon=new ImageIcon("src/pictures/f3.png");
			la.setIcon(icon);
		}else if (e.getSource()==c[13]) {
			icon=new ImageIcon("src/pictures/f2.png");
			la.setIcon(icon);
		}else if (e.getSource()==c[14]) {
			icon=new ImageIcon("src/pictures/f4.png");
			la.setIcon(icon);
		}else if (e.getSource()==c[15]) {
				icon=new ImageIcon("src/pictures/s.png");
				la.setIcon(icon);
			}else if (e.getSource()==c[16]) {
				icon=new ImageIcon("src/pictures/s1.png");
				la.setIcon(icon);
			}else if (e.getSource()==c[17]) {
				icon=new ImageIcon("src/pictures/s2.png");
				la.setIcon(icon);
			}else if (e.getSource()==c[18]) {
				icon=new ImageIcon("src/pictures/s3.png");
				la.setIcon(icon);
			}else if (e.getSource()==c[19]) {
				icon=new ImageIcon("src/pictures/s4.png");
				la.setIcon(icon);
			}else if (e.getSource()==c[20]) {
				icon=new ImageIcon("src/pictures/s5.png");
				la.setIcon(icon);
			}else if (e.getSource()==c[21]) {
				icon=new ImageIcon("src/pictures/b.png");
				la.setIcon(icon);
			}else if (e.getSource()==c[22]) {
				icon=new ImageIcon("src/pictures/b1.png");
				la.setIcon(icon);
			}else if (e.getSource()==c[23]) {
				icon=new ImageIcon("src/pictures/b2.png");
				la.setIcon(icon);
			}else if (e.getSource()==c[24]) {
				icon=new ImageIcon("src/pictures/b3.png");
				la.setIcon(icon);
			}else if (e.getSource()==c[25]) {
				icon=new ImageIcon("src/pictures/b4.png");
				la.setIcon(icon);
			}else if (e.getSource()==c[26]) {
				icon=new ImageIcon("src/pictures/b5.png");
				la.setIcon(icon);
			}
	}
	
}

}



