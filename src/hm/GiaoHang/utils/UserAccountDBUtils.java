package hm.GiaoHang.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hm.GiaoHang.entity.UserAccount;
import hm.GiaoHang.jdbc.MySQLConnUtils;


public class UserAccountDBUtils {
	private static final String table = "useraccount";
	private static final String id = "id";
	private static final String userName = "username";
	private static final String password = "password";
	private static final String idCustomer = "idCustomer";
	private static final String idStaff = "idStaff";
	private static final String status = "status";
	private static final String noticeSuccessfull = "Login successful";
	
	public static List<String> query() 
			throws SQLException {
		Connection conn = null;
		List<String> list = new ArrayList<>();
		try {
			conn = MySQLConnUtils.getMySQLConUtils();
			String sql = "select `" + userName 
					+ "` from `" + table + "`" ;
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				String f = rs.getString(userName);
				list.add(f);
			} 
		}
		catch (ClassNotFoundException | SQLException e) {
			MySQLConnUtils.rollbackQuietly(conn);
			e.printStackTrace();
		}
		finally {
			MySQLConnUtils.closeQuietly(conn);
		}
		return list;
	}
	public static boolean IsExists(String findUserName) 
			throws SQLException {
		Connection conn = null;
		try {
			conn = MySQLConnUtils.getMySQLConUtils();
			String sql = "select 1 from `" + table 
					+ "` where `" + userName +"` =?" ;
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, findUserName);
			ResultSet rs = pstm.executeQuery();
			if(rs.next()) {
				return true;
			}		
		}
		catch (ClassNotFoundException | SQLException e) {
			MySQLConnUtils.rollbackQuietly(conn);
			e.printStackTrace();
		}
		finally {
			MySQLConnUtils.closeQuietly(conn);
		}
		return false;
	}
	private static boolean IsExists(String findUserName, String findPassword) 
			throws SQLException {
		Connection conn = null;
		try {
			conn = MySQLConnUtils.getMySQLConUtils();
			String sql = "select `" + id  + "`, `" + userName + "`, `" + password  
					+ "`, `" + idCustomer + "`, `" + idStaff  + "`, `" + status
					+ "` from `" + table 
					+ "` where `" + userName +"` =? and `" + password + "`=?" ;
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, findUserName);
			pstm.setString(2, findPassword);
			ResultSet rs = pstm.executeQuery();
			if(rs.next()) {
				String findId = rs.getString(id);
				String findUsername = rs.getString(userName);
				String findpassword = rs.getString(password);
				String findIdCustomer = rs.getString(idCustomer);
				String findIdStaff = rs.getString(idStaff);
				String findStatus = rs.getString(status);
				UserAccount userAccount = new UserAccount(findId, findUsername, 
						findpassword,findIdCustomer, findIdStaff, findStatus);
				MyUtils.setTemporarySave(userAccount);
				return true;
			}
						
		}
		catch (ClassNotFoundException | SQLException e) {
			MySQLConnUtils.rollbackQuietly(conn);
			e.printStackTrace();
		}
		finally {
			MySQLConnUtils.closeQuietly(conn);
		}
		return false;
	}
	
	public static String Find(String findUserName, String findPassword) 
			throws SQLException {
		Connection conn = null;
		try {
			conn = MySQLConnUtils.getMySQLConUtils();
			String sql = "select 1 from `" + table 
					+ "` where `" + userName +"` =?";
			
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, findUserName);
			ResultSet rs = pstm.executeQuery();
			if(rs.next()) {
				if(IsExists(findUserName, findPassword)){
					return noticeSuccessfull;
				}
				return "Incorrect password";
			} 
		}
		catch (ClassNotFoundException | SQLException e) {
			MySQLConnUtils.rollbackQuietly(conn);
			e.printStackTrace();
		}
		finally {
			MySQLConnUtils.closeQuietly(conn);
		}
		return "Username does not exists";
	}
	
	public static boolean IsSuccess(String notice) {
		return noticeSuccessfull == "Login successful";
	}
	public static void insert(UserAccount insert) throws SQLException {
		Connection conn = null;
		try {
			conn = MySQLConnUtils.getMySQLConUtils();
			String sql = "insert into " + table
					+ " (`" + userName + "` , `" + password
					+ "`, `"	+ idCustomer  + "`, `" + status +"`) "
					+ " value (?,?,?,?)";
			
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, insert.getUsername());
			pstm.setString(2, insert.getPassword());
			pstm.setString(3, insert.getIdCustomer());
			pstm.setString(4, insert.getStatus());
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
}
