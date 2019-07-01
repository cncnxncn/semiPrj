package panel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import sales.sales;

public class daySales extends Panel implements ActionListener{
	JTextField ynum,mnum,dnum,total;
	JButton addbu,finbu,debu;
	
	JTable table1;
	daySalesTable daySales;
	DBmodel model;
	panel textpan;
	
	String sum;
	int total1,price;
	
	
	public daySales() throws Exception {
			addLayout();
			connectdb();
			eventClick();
	}
	public void getprice(String price) {
		System.out.println(price);
		total = new JTextField(price);
		total.setText(price);
	}
	  private void connectdb() {
		   try {
			model=new DBmodel();
		} catch (Exception e) {
			System.out.println("Á¢¼Ó ½ÇÆÐ");
			e.printStackTrace();
		}
		   	
	}
	  public void eventClick() {
		  table1.addMouseListener(new MouseAdapter() {
			
			 
			@Override
			public void mouseClicked(MouseEvent e) {
				sales sa=new sales();
				int row=table1.getSelectedRow();
				int col=3;
				String data=(String) table1.getValueAt(row, col);
				int no=Integer.parseInt(data);
					try {
						sales sa1=model.selectLine(no);
						selectLine(sa1);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			
	 });
	  }
	private void addLayout() {
		ynum=new JTextField(10);
		mnum=new JTextField(10);
		dnum=new JTextField(10);
		total=new JTextField(10);
		addbu=new JButton("µî          ·Ï");
		addbu.addActionListener(this);
		finbu=new JButton("Á¶          È¸");
		finbu.addActionListener(this);
		debu=new JButton("»è          Á¦");
		debu.addActionListener(this);
		addbu.setPreferredSize(new Dimension(200, 60));
		finbu.setPreferredSize(new Dimension(200, 60));
		debu.setPreferredSize(new Dimension(200, 60));
		try {
			textpan=new panel();
		} catch (Exception e) {
			e.printStackTrace();
		}
		JPanel pan_w=new JPanel();
		JPanel pan_e=new JPanel();
		JPanel pan_s=new JPanel();
		JPanel west=new JPanel();
		JPanel east=new JPanel();
		JPanel south=new JPanel();
		
		JLabel yname=new JLabel("³â");
		yname.setFont(new Font("ÈÞ¸é¿¢½ºÆ÷", Font.BOLD, 30));
		JLabel mname=new JLabel("¿ù");
		mname.setFont(new Font("ÈÞ¸é¿¢½ºÆ÷", Font.BOLD, 30));
		JLabel dname=new JLabel("ÀÏ");
		dname.setFont(new Font("ÈÞ¸é¿¢½ºÆ÷", Font.BOLD, 30));
		JLabel toname=new JLabel("¸ÅÃâ");
		toname.setFont(new Font("ÈÞ¸é¿¢½ºÆ÷", Font.BOLD, 30));
		JLabel label=new JLabel("ÀúÀå¼Ò");
		label.setFont(new Font("ÈÞ¸é¿¢½ºÆ÷", Font.BOLD, 60));
		
		east.setLayout(new BorderLayout());
		west.setLayout(new BorderLayout());
		south.setLayout(new BorderLayout());
		daySales=new daySalesTable();
		table1=new JTable(daySales);
		table1.setModel(daySales);
		
		pan_w.add(yname);
		pan_w.add(ynum);
		pan_w.add(mname);
		pan_w.add(mnum);
		pan_w.add(dname);
		pan_w.add(dnum);
		pan_w.add(toname);
		pan_w.add(total);
		pan_w.add(label);
		west.add(pan_w,BorderLayout.WEST);
		
		pan_e.add(new JScrollPane(table1),BorderLayout.CENTER);
		east.add(pan_e,BorderLayout.EAST);
		
		pan_s.add(finbu);
		pan_s.add(addbu);
		pan_s.add(debu);
		south.add(pan_s,BorderLayout.SOUTH);
		pan_w.setLayout(new GridLayout(5, 0));
		yname.setHorizontalAlignment(JTextField.CENTER);
		ynum.setHorizontalAlignment(JTextField.RIGHT);
		ynum.setFont(new Font("ÈÞ¸Õ¿¢½ºÆ÷", Font.BOLD, 20));
		mname.setHorizontalAlignment(JTextField.CENTER);
		mnum.setHorizontalAlignment(JTextField.RIGHT);
		mnum.setFont(new Font("ÈÞ¸Õ¿¢½ºÆ÷", Font.BOLD, 20));
		dname.setHorizontalAlignment(JTextField.CENTER);
		dnum.setHorizontalAlignment(JTextField.RIGHT);
		dnum.setFont(new Font("ÈÞ¸Õ¿¢½ºÆ÷", Font.BOLD, 20));
		toname.setHorizontalAlignment(JTextField.CENTER);
		total.setHorizontalAlignment(JTextField.RIGHT);
		
		total.setFont(new Font("ÈÞ¸Õ¿¢½ºÆ÷", Font.BOLD, 20));
		label.setHorizontalAlignment(JTextField.CENTER);
		setLayout(new BorderLayout());
		add("West",west);
		add(east);
		add("South",south);
	}
	class daySalesTable extends AbstractTableModel{
		   ArrayList data1=new ArrayList();
		   String[] colName= {"³â","¿ù","ÀÏ","¸ÅÃâ"};
		   
		@Override
		public int getColumnCount() {
			return colName.length;
		}

		@Override
		public int getRowCount() {
			return data1.size();
		}

		@Override
		public Object getValueAt(int row, int col) {
			ArrayList temp=(ArrayList) data1.get(row);
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
		ArrayList data;
		if (evt==finbu) {
			searchDays();
		}else if (evt==addbu) {
			insertDatys();
			restart();
		}else if (evt==debu) {
				try {
					delete();
					restart();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
		}
	}
	private void restart() {
		ArrayList data;
		try {
			data = model.searchTotal();
			daySales.data1=data;
			daySales.fireTableDataChanged();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	void selectLine(sales sa) {
		ynum.setText(String.valueOf(sa.getYear()));
		mnum.setText(String.valueOf(sa.getMonth()));
		dnum.setText(String.valueOf(sa.getDays()));
		total.setText(String.valueOf(sa.getPrice()));
		
	}
	private void delete() {
		sales sa=new sales();
		
		sa.setYear(Integer.parseInt(ynum.getText()));
		sa.setMonth(Integer.parseInt(mnum.getText()));
		sa.setDays(Integer.parseInt(dnum.getText()));
		sa.setPrice(Integer.parseInt(total.getText()));
		
		try {
			model.deleteDays(sa);
			JOptionPane.showMessageDialog(null,"»èÁ¦¿Ï·á");
			ynum.setText("");
			mnum.setText("");
			dnum.setText("");
			total.setText("");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"»èÁ¦½ÇÆÐ");
			e.printStackTrace();
		}
	}
	void searchDays(){
		try {
			
			ArrayList data=model.searchTotal();
					daySales.data1=data;
					table1.setModel(daySales);
					daySales.fireTableDataChanged();
					ynum.setText("");
					mnum.setText("");
					dnum.setText("");
					total.setText("");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
private void insertDatys() {
		sales sa=new sales();
		sa.setYear(Integer.parseInt(ynum.getText()));
		sa.setMonth(Integer.parseInt(mnum.getText()));
		sa.setDays(Integer.parseInt(dnum.getText()));
		sa.setPrice(Integer.parseInt(total.getText()));
		
		try {
			model.insertTotal(sa);
			JOptionPane.showMessageDialog(null,"µî·Ï¼º°ø");
			ynum.setText("");
			mnum.setText("");
			dnum.setText("");
			total.setText("");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"µî·Ï½ÇÆÐ");
		e.printStackTrace();
		}
	}
}
