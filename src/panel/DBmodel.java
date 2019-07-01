package panel;

import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import panel.panel.salesTableModel;
import sales.sales;

public class DBmodel {
	Connection con;
	
	public DBmodel() throws Exception {
		con=Dbsal.getConnection();
	}

	public ArrayList  searchMenu() throws Exception  {
		// 검색기능
		ArrayList data=new ArrayList();
		
			String sql = "SELECT* from MENUBILL";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
//			ps.setInt(1, m);
			while (rs.next()) {
				ArrayList temp=new ArrayList();
				temp.add(rs.getString("NAME"));
				temp.add(rs.getString("PRICE"));
				data.add(temp);
			}
			
			ps.close();
			rs.close();
		return data;
	}
	public ArrayList searchTotal() throws Exception{
		
		ArrayList data=new ArrayList();
		
		String sql="SELECT* FROM DAYSALES";
		PreparedStatement ps=con.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		while (rs.next()) {
			ArrayList temp=new ArrayList();
			temp.add(rs.getString("YEAR"));
			temp.add(rs.getString("MONTH"));
			temp.add(rs.getString("DAYS"));
			temp.add(rs.getString("SALES"));
			data.add(temp);
		}
		ps.close();
		rs.close();
		return data;
	}
	public void insertMenu(String mm) throws Exception{
		
		String sql="INSERT INTO MENUBILL VALUES((SELECT NAME FROM SALES WHERE NAME=?), "
				+ "(SELECT PRICE FROM SALES WHERE NAME=?))";
			PreparedStatement ps=con.prepareStatement(sql);
				ps.setString(1, mm);
				ps.setString(2, mm);
				
			
			ps.executeUpdate();

			ps.close();
		}
	public void insertTotal(sales sa) throws Exception{
		panel pan=new panel();
		String sql="INSERT INTO DAYSALES(YEAR,MONTH,DAYS,SALES) "
				+ "VALUES (?,?,?,?)";
		PreparedStatement ps=con.prepareStatement(sql);
		
		ps.setInt(1, sa.getYear());
		ps.setInt(2, sa.getMonth());
		ps.setInt(3, sa.getDays());
		ps.setInt(4,sa.getPrice());
		
		ps.executeUpdate();
		
		ps.close();
	}
		public void deleteMenu() throws Exception{
			String sql="delete from menubill";
			
			PreparedStatement ps=con.prepareStatement(sql);
			ps.executeUpdate();
			
			ps.close();
		}
		public void deleteDays(sales sa) throws Exception{
			con.setAutoCommit(false);
			String sql1="delete from daysales where days=? and sales=?";
			PreparedStatement ps1=con.prepareStatement(sql1);
			ps1.setInt(1, sa.getDays());
			ps1.setInt(2, sa.getPrice());
			
			
			int r1=ps1.executeUpdate();
			
			
			ps1.close();
			con.setAutoCommit(true);
		}
		  public sales selectLine(int vo) throws Exception {
		     
		      sales sa=new sales();
		      con.setAutoCommit(false);
		     
		      String sql="SELECT*FROM DAYSALES WHERE sales="+vo;
		      System.out.println(vo);
		      PreparedStatement ps=con.prepareStatement(sql);
		      ResultSet rs=ps.executeQuery();
		      while (rs.next()) {
		         sa.setYear(Integer.parseInt(rs.getString("YEAR")));
		         sa.setMonth(Integer.parseInt(rs.getString("MONTH")));
		         sa.setDays(Integer.parseInt(rs.getString("DAYS")));
		         sa.setPrice(Integer.parseInt(rs.getString("SALES")));
		      }
		      rs.close();
		      ps.close();
		      con.setAutoCommit(true);
		      
		      return sa;
		   }
}
