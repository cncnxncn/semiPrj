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
	 String[] mm={"후라이드치킨","양념치킨","뿌링클치킨","핫크리스피","간장치킨","불닭치킨","탕수육","과일탕수육","사천탕수육","감자튀김","양파튀김","야채튀김","새우튀김","치즈스틱","만두",
			 "처음처럼","참이슬","좋은데이","아사히","카스","칭타오","콜라","사이다","환타","핫식스","밀키스","몬스터"};
   
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
	obu=new JButton("주문");
      cbu=new JButton("초기화");
      pbu=new JButton("추가");
      
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
			c[i].setFont(new Font("궁서체", Font.BOLD, 15));
			c[i].setForeground(Color.blue);
		}
		for (int i = 15; i < 27; i++) {
//			c[i].setFont(new Font("궁서체", Font.BOLD, 15));
			c[i].setForeground(Color.MAGENTA);
		}
      west_n.setBorder(new TitledBorder(new LineBorder(Color.BLACK), "메뉴",0,0) );
      west_n.setFont(new Font("휴먼엑스포", Font.ITALIC, 20));
      
      west=new JPanel();
      west.setLayout(new BorderLayout());
      west.add(west_n,BorderLayout.CENTER);
      west.add(west_c,BorderLayout.NORTH);
      
      JLabel lala=new JLabel("어서오고");
      lala.setFont(new Font("휴먼엑스포",Font.BOLD , 50));
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
	   JLabel labe=new JLabel("총");
	   labe.setFont(new Font("휴먼엑스포", Font.BOLD, 18));
	   JLabel labe1=new JLabel("원");
	   labe1.setFont(new Font("휴먼엑스포", Font.BOLD, 18));
	   east_c_n.setLayout(new BorderLayout());
	   east_c_c.setLayout(new BorderLayout());
	   
	   
      east_c_n.add(new JScrollPane(table));
      east_c_n.setBorder(new TitledBorder("가격표"));
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
		System.out.println("접속");
	} catch (Exception e) {
		System.out.println("접속 실패");
		e.printStackTrace();
	}
	   	
}

class salesTableModel extends AbstractTableModel{
	   ArrayList data=new ArrayList();
	   String[] colName= {"품명","가격"};
	   
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
					JOptionPane.showMessageDialog(null,"주문 초기화");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}else if (c[i].isSelected()==true&&evt==this.obu) {
				try {
					daySales de = new daySales();
					de.getprice(msum.getText());
				} catch (Exception e1) {
					System.out.println("주문 실패");
				}
				JOptionPane.showMessageDialog(null,"주문 완료!! \n가격은 : "+total);
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
			JOptionPane.showMessageDialog(null,"주문추가 완료");
			
		}
	}
}
public int orderMenu(Object evt, int price, int i) {
	if (c[i].isSelected()==true&&evt==pbu&&mm[i].equals("후라이드치킨")) {
		price = 12000;
	} if (c[i].isSelected()==true&&evt==pbu&&mm[i].equals("양념치킨")) {
		price = 13000;
	} if (c[i].isSelected()==true&&evt==pbu&&mm[i].equals("뿌링클치킨")) {
		price = 15000;
	} if (c[i].isSelected()==true&&evt==pbu&&mm[i].equals("간장치킨")) {
		price = 13000;
	} if (c[i].isSelected()==true&&evt==pbu&&mm[i].equals("불닭치킨")) {
		price = 15000;
	} if (c[i].isSelected()==true&&evt==pbu&&mm[i].equals("탕수육")) {
		price = 15000;
	} if (c[i].isSelected()==true&&evt==pbu&&mm[i].equals("과일탕수육")) {
		price = 16000;
	} if (c[i].isSelected()==true&&evt==pbu&&mm[i].equals("사천탕수육")) {
		price = 17000;
	} if (c[i].isSelected()==true&&evt==pbu&&mm[i].equals("감자튀김")) {
		price = 6000;
	} if (c[i].isSelected()==true&&evt==pbu&&mm[i].equals("양파튀김")) {
		price = 8000;
	} if (c[i].isSelected()==true&&evt==pbu&&mm[i].equals("야채튀김")) {
		price= 6000;
	} if (c[i].isSelected()==true&&evt==pbu&&mm[i].equals("새우튀김")) {
		price = 8000;
	} if (c[i].isSelected()==true&&evt==pbu&&mm[i].equals("치즈스틱")) {
		price = 7000;
	} if (c[i].isSelected()==true&&evt==pbu&&mm[i].equals("만두")) {
		price = 5000;
	} if (c[i].isSelected()==true&&evt==pbu&&mm[i].equals("처음처럼")) {
		price = 3000;
	} if (c[i].isSelected()==true&&evt==pbu&&mm[i].equals("참이슬")) {
		price = 3000;
	} if (c[i].isSelected()==true&&evt==pbu&&mm[i].equals("좋은데이")) {
		price = 3000;
	} if (c[i].isSelected()==true&&evt==pbu&&mm[i].equals("아사히")) {
		price = 5000;
	} if (c[i].isSelected()==true&&evt==pbu&&mm[i].equals("카스")) {
		price = 4000;
	} if (c[i].isSelected()==true&&evt==pbu&&mm[i].equals("칭타오")) {
		price = 5000;
	} if (c[i].isSelected()==true&&evt==pbu&&mm[i].equals("콜라")) {
		price = 2000;
	} if (c[i].isSelected()==true&&evt==pbu&&mm[i].equals("사이다")) {
		price = 2000;
	}else if (c[i].isSelected()==true&&evt==pbu&&mm[i].equals("환타")) {
		price = 2000;
	}else if (c[i].isSelected()==true&&evt==pbu&&mm[i].equals("핫식스")) {
		price = 3000;
	}else if (c[i].isSelected()==true&&evt==pbu&&mm[i].equals("밀키스")) {
		price=2000;
	}else if (c[i].isSelected()==true&&evt==pbu&&mm[i].equals("몬스터")) {
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



