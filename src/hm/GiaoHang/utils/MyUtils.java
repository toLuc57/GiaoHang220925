package hm.GiaoHang.utils;

import javax.servlet.http.HttpSession;

public class MyUtils {
	public static void storeLoginedUser(HttpSession session, String customer) {
		session.setAttribute("loginedUser", customer);
	}
	public static String getLoginedUser(HttpSession session) {
		return (String) session.getAttribute("loginedUser");
	}
	public static void deleteLoginedUser(HttpSession session) {
		session.setAttribute("loginedUser", null);
	}
}
