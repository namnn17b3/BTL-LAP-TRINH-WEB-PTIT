package fruitshop.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin")
public class AdminController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String chucNang = "";
		try {
			int chucNangInt = Integer.parseInt(req.getParameter("chuc-nang"));
			if (chucNangInt >= 0 && chucNangInt <= 8) {
				req.setAttribute("chucNang", chucNang);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		req.setAttribute("chucNang", chucNang);
		req.getRequestDispatcher("./admin_page.jsp").forward(req, resp);
	}
}
