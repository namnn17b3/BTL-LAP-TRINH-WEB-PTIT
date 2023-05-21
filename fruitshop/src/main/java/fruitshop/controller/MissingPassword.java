package fruitshop.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fruitshop.dao.UserDao;
import fruitshop.dao.impl.UserDaoImpl;
import fruitshop.model.User;
import fruitshop.utils.Email;
import fruitshop.utils.RanDomCode;
import fruitshop.utils.Sha1;

@WebServlet("/missing-password")
public class MissingPassword extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final UserDao userDao = new UserDaoImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("thongBaoDaGuiMatKhauMoi", 0);
		req.getRequestDispatcher("./missing_password.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		if (email == null) {
			req.setAttribute("chuaDienEmail", 1);
			req.getRequestDispatcher("./missing_password.jsp").forward(req, resp);
			return;
		}
		if (userDao.tonTaiUser(email) == false) {
			req.setAttribute("emailKhongTonTai", 1);
			req.getRequestDispatcher("./missing_password.jsp").forward(req, resp);
			return;
		}
		String matKhauMoi = Sha1.encryptThisString(RanDomCode.randomCode());
		// Email.sendMail(email, matKhauMoi);
		System.out.println(matKhauMoi);
		User user = userDao.getUserByEmail(email);
		user.setMatKhau(matKhauMoi);
		userDao.upDateUserByEmail(user);
		req.setAttribute("thongBaoDaGuiMatKhauMoi", 1);
		req.getRequestDispatcher("./missing_password.jsp").forward(req, resp);
	}
}
