package fruitshop.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fruitshop.dao.UserDao;
import fruitshop.dao.impl.UserDaoImpl;
import fruitshop.model.User;

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
		String email = req.getParameter("email");
		String password = req.getParameter("mat-khau");
		HttpSession session = req.getSession();
		if (userDao.tonTaiUser(email, password) == true) {
			User currentUser = userDao.getUserByEmail(email);
			// System.out.println("login controller line 38 " + currentUser.getAnh());
			session.setAttribute("currentUser", currentUser);
			session.setMaxInactiveInterval(3600 * 24);
			resp.sendRedirect("./home");
		}
		else {
			req.setAttribute("dangNhapKhongThanhCong", 1);
			req.getRequestDispatcher("./login.jsp").forward(req, resp);
		}
	}
}
