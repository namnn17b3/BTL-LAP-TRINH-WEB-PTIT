package fruitshop.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fruitshop.dao.DonHangDao;
import fruitshop.dao.UserDao;
import fruitshop.dao.impl.DonHangDaoImpl;
import fruitshop.dao.impl.UserDaoImpl;
import fruitshop.model.User;

@WebServlet("/login")
public class LoginController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final UserDao userDao = new UserDaoImpl();
	private static final DonHangDao donHangDao = new DonHangDaoImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("dangNhapKhongThanhCong", 0);
		req.getRequestDispatcher("./login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if (session.getAttribute("currentUser") != null) {
			req.getRequestDispatcher("./khong_tim_thay_san_pham.jsp").forward(req, resp);
			return;
		}
		String email = req.getParameter("email");
		String password = req.getParameter("mat-khau");
		if (userDao.tonTaiUser(email, password) == true) {
			User currentUser = userDao.getUserByEmail(email);
			// System.out.println("login controller line 38 " + currentUser.getAnh());
			session.setAttribute("currentUser", currentUser);
			// session.setAttribute("gioHang", donHangDao.getGioHangByIdUser(currentUser.getId()));
			session.setMaxInactiveInterval(3600 * 24);
			Integer clickThemVaoGioHang = (Integer) session.getAttribute("clickThemVaoGioHang");
			Integer clickGioHang = (Integer) session.getAttribute("clickGioHang");
			if (clickThemVaoGioHang != null && clickThemVaoGioHang == 1) {
				System.out.println("line 52 login controller");
				resp.sendRedirect("./xu-ly-gio-hang");
				return;
			}
			if (clickGioHang != null && clickGioHang == 1) {
				resp.sendRedirect("./gio-hang");
				return;
			}
			System.out.println("line 60 login controller");
			resp.sendRedirect("./home");
		}
		else {
			req.setAttribute("dangNhapKhongThanhCong", 1);
			req.getRequestDispatcher("./login.jsp").forward(req, resp);
		}
	}
}
