package fruitshop.api.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import fruitshop.dao.UserDao;
import fruitshop.dao.impl.UserDaoImpl;
import fruitshop.model.User;

@WebServlet("/api/user/login")
public class LoginAPI extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final UserDao userDao = new UserDaoImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		PrintWriter writer = resp.getWriter();
		resp.setContentType("application/json");
		
		Gson gon = new Gson();
		
		if (session.getAttribute("currentUser") != null) {
			writer.println(gon.toJson("not allowed"));
			return;
		}
		String email = req.getParameter("email");
		String password = req.getParameter("mat-khau");
		if (userDao.tonTaiUser(email, password) == true) {
			User currentUser = userDao.getUserByEmail(email);
			if (currentUser.getTrangThai() == 1) {
				writer.println(gon.toJson("Tài khoản đang được đăng nhập ở nơi khác"));
				return;
			}
			currentUser.setTrangThai(1);
			userDao.upDateUserByEmail(currentUser);
			session.setAttribute("currentUser", currentUser);
			session.setMaxInactiveInterval(3600 * 24);
			Integer clickThemVaoGioHang = (Integer) session.getAttribute("clickThemVaoGioHang");
			Integer clickGioHang = (Integer) session.getAttribute("clickGioHang");
			Integer clickMuaNgay = (Integer) session.getAttribute("clickMuaNgay");
			Integer clickTienHanhThanhToan = (Integer) session.getAttribute("clickTienHanhThanhToan");
			if (clickThemVaoGioHang != null && clickThemVaoGioHang == 1) {
				writer.println(gon.toJson("./fruitshop/xu-ly-gio-hang"));
				return;
			}
			if (clickGioHang != null && clickGioHang == 1) {
				writer.println(gon.toJson("./fruitshop/gio-hang"));
				return;
			}
			if (clickMuaNgay != null && clickMuaNgay == 1) {
				writer.println(gon.toJson("./fruitshop/thanh-toan"));
				return;
			}
			if (clickTienHanhThanhToan != null && clickTienHanhThanhToan == 1) {
				writer.println(gon.toJson("./fruitshop/thanh-toan"));
				return;
			}
			writer.println(gon.toJson("./fruitshop/home"));
			return;
		}
		else {
			writer.println(gon.toJson("Tài khoản hoặc mật khẩu không đúng"));
			return;
		}
	}
}
