package fruitshop.controller;

import java.io.IOException;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fruitshop.dao.UserDao;
import fruitshop.dao.impl.UserDaoImpl;
import fruitshop.model.User;
import fruitshop.utils.Sha256;

@WebServlet("/login")
public class LoginController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final UserDao userDao = new UserDaoImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("dangNhapKhongThanhCong", 0);
		req.getRequestDispatcher("./login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String email = req.getParameter("email");
		String password = req.getParameter("mat-khau");
		if (userDao.tonTaiUser(email, password) == true) {
			User currentUser = userDao.getUserByEmail(email);
			if (currentUser.getTrangThai() == 1) {
				req.setAttribute("dangNhapKhongThanhCong", 1);
				req.setAttribute("email", email);
				req.getRequestDispatcher("./login.jsp").forward(req, resp);
				return;
			}
			currentUser.setTrangThai(1);
			currentUser.setMatKhau(password);
			userDao.upDateUserByEmail(currentUser);
			// System.out.println("login controller line 38 " + currentUser.getAnh());
			session.setAttribute("currentUser", currentUser);
			// session.setAttribute("gioHang", donHangDao.getGioHangByIdUser(currentUser.getId()));
			session.setMaxInactiveInterval(86400); // 1 ngày
			
			String jsessionIDEncodedBase64 = Base64.getEncoder().encodeToString(session.getId().getBytes("UTF-8"));
			String currentUserIDEncodedBase64 = Base64.getEncoder().encodeToString(String.valueOf(currentUser.getId()).getBytes("UTF-8"));
			String salt = (String) this.getServletContext().getAttribute("salt");
			String signature = Sha256.sha256(jsessionIDEncodedBase64 + "." + currentUserIDEncodedBase64 + salt);
			String csrfToken = jsessionIDEncodedBase64 + "." + currentUserIDEncodedBase64 + "." + signature;
			session.setAttribute("csrfToken", csrfToken); // client đăng nhập, server render 1 csrf token và trả lại cho client
			session.setAttribute("sendCSRFTokenToClient", 1);
			
			Integer clickThemVaoGioHang = (Integer) session.getAttribute("clickThemVaoGioHang");
			Integer clickGioHang = (Integer) session.getAttribute("clickGioHang");
			Integer clickMuaNgay = (Integer) session.getAttribute("clickMuaNgay");
			Integer clickTienHanhThanhToan = (Integer) session.getAttribute("clickTienHanhThanhToan");
			if (clickThemVaoGioHang != null && clickThemVaoGioHang == 1) {
				System.out.println("line 52 login controller");
				resp.sendRedirect("./xu-ly-gio-hang");
				return;
			}
			if (clickGioHang != null && clickGioHang == 1) {
				resp.sendRedirect("./gio-hang");
				return;
			}
			if (clickMuaNgay != null && clickMuaNgay == 1) {
				System.out.println("line 67 login controller");
				resp.sendRedirect("./thanh-toan");
				return;
			}
			if (clickTienHanhThanhToan != null && clickTienHanhThanhToan == 1) {
				resp.sendRedirect("./thanh-toan");
				return;
			}
			System.out.println("line 60 login controller");
			resp.sendRedirect("./home");
		}
		else {
			req.setAttribute("dangNhapKhongThanhCong", 2);
			req.setAttribute("email", email);
			req.getRequestDispatcher("./login.jsp").forward(req, resp);
		}
	}
}
