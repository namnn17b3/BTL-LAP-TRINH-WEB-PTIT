package fruitshop.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin/*")
public class AdminController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String[] functionStrings = {
		"main",
		"doanh-thu-theo-ngay",
		"doanh-thu-theo-thang",
		"quan-ly-don-hang",
		"quan-ly-san-pham",
		"quan-ly-tai-khoan",
		"top-10-san-pham",
		"top-5-khach-hang",
	};

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(req.getRequestURI());
		req.setAttribute("prefix", ".");
		for (String s : functionStrings) {
			if (req.getRequestURI().lastIndexOf("admin") != -1 && req.getRequestURI().lastIndexOf(s) != -1) {
				req.setAttribute("prefix", "..");
				break;
			}
		}
		System.out.println(req.getAttribute("prefix"));
		String chucNang = "";
		try {
			int chucNangInt = Integer.parseInt(req.getParameter("chuc-nang"));
			if (chucNangInt >= 0 && chucNangInt <= 8) {
				req.setAttribute("chucNang", chucNang);
			}
		} catch (Exception e) {
			// e.printStackTrace();
		}
		req.setAttribute("chucNang", chucNang);
		req.getRequestDispatcher("/admin_page.jsp").forward(req, resp);
	}
}
