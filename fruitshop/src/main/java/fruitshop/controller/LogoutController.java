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

@WebServlet("/logout")
public class LogoutController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final UserDao userDao = new UserDaoImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if (session.getAttribute("currentUser") != null) {
			User currentUser = (User) session.getAttribute("currentUser");
			currentUser.setTrangThai(0);
			userDao.upDateUserByEmail(currentUser);
			session.invalidate();
			resp.sendRedirect("./home");
		}
		else {
			req.getRequestDispatcher("./khong_tim_thay_san_pham.jsp").forward(req, resp);
		}
	}
}
