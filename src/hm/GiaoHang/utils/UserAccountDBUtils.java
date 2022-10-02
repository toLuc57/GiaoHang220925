package hm.GiaoHang.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import hm.GiaoHang.entity.UserAccount;
import hm.GiaoHang.jdbc.MySQLConnUtils;


public class UserAccountDBUtils {
	private static final String table = "useraccount";
	private static final String userName = "username";
	private static final String password = "password";
	private static final String idCustomer = "idCustomer";
	private static final String idStaff = "idStaff";
	private static final String status = "status";
	private static final String noticeSuccessfull = "Login successful";
	
	private static boolean IsExists(String findUserName, String findPassword) 
			throws SQLException {
		Connection conn = null;
		try {
			conn = MySQLConnUtils.getMySQLConUtils();
			String sql = "select `" + userName + "`, `" + password  
					+ "`, `" + idCustomer + "`, `" + idStaff  + "`, `" + status
					+ "` from `" + table 
					+ "` where `" + userName +"` =? and `" + password + "`=?" ;
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, findUserName);
			pstm.setString(2, findPassword);
			ResultSet rs = pstm.executeQuery();
			if(rs.next()) {
				String findUsername = rs.getString(userName);
				String findpassword = rs.getString(password);
				String findIdCustomer = rs.getString(idCustomer);
				String findIdStaff = rs.getString(idStaff);
				String findStatus = rs.getString(status);
				UserAccount userAccount = new UserAccount(findUsername, 
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
			String sql = "select `" + password  
					+ "`, `" + idCustomer + "`, `" + idStaff 
					+ "` from `" + table 
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
}
