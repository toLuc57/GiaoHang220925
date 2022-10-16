package hm.GiaoHang.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import hm.GiaoHang.entity.Customer;
import hm.GiaoHang.jdbc.MySQLConnUtils;

public class CustomerDBUtils {
	private static final String table = "customer";
	private static final String id = "idcustomer";
	private static final String name = "name";
	private static final String telephone = "telephone";
	private static final String address = "address";
	private static final String status = "status";
	
	public static void insert(Customer insert) throws SQLException {
		Connection conn = null;
		try {
			conn = MySQLConnUtils.getMySQLConUtils();
			String sql = "insert into " + table
					+ " (`" + name + "` , `" + telephone
					+ "`, `"	+ address + "`, `" + status +"`) "
					+ " value (?,?,?,?)";
			
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, insert.getName());
			pstm.setString(2, insert.getTelephone());
			pstm.setString(3, insert.getAddress());
			pstm.setInt(4, insert.getStatus());
			pstm.executeUpdate();
		}
		catch (ClassNotFoundException | SQLException e) {
			MySQLConnUtils.rollbackQuietly(conn);
			e.printStackTrace();
		}
		finally {
			MySQLConnUtils.closeQuietly(conn);
		}	
	}
	public static String findIdCustomer(Customer findRecords) 
			throws SQLException {
		Connection conn = null;
		try {
			conn = MySQLConnUtils.getMySQLConUtils();
			String sql = "select `" + id 
					+ "` from `" + table
					+ "` where `" + name + "` = ? and `" 
					+ telephone + "` = ? and `" 
					+ address + "` = ? and `"
					+ status  + "` = ?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, findRecords.getName());
			pstm.setString(2, findRecords.getTelephone());
			pstm.setString(3, findRecords.getAddress());
			pstm.setInt(4, findRecords.getStatus());
			ResultSet rs = pstm.executeQuery();
			if(rs.next()) {
				return rs.getString(id);
			} 
		}
		catch (ClassNotFoundException | SQLException e) {
			MySQLConnUtils.rollbackQuietly(conn);
			e.printStackTrace();
		}
		finally {
			MySQLConnUtils.closeQuietly(conn);
		}
		return null;
	}
}
