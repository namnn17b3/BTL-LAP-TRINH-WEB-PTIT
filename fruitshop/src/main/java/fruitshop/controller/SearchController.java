package fruitshop.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fruitshop.dao.SanPhamDao;
import fruitshop.dao.impl.SanPhamDaoImpl;
import fruitshop.model.SanPham;

@WebServlet("/search")
public class SearchController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final SanPhamDao sanPhamDao = new SanPhamDaoImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if (session.getAttribute("listSanPham") == null) {
			req.getRequestDispatcher("./khong_tim_thay_san_pham.jsp").forward(req, resp);
			return;
		}
		req.getRequestDispatcher("./ket_qua_tim_kiem.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String tenSanPham = req.getParameter("ten-san-pham");
		// System.out.println(tenSanPham);
		int page = 1;
		if (req.getParameter("page") != null) {
			try {
				page = Integer.parseInt(req.getParameter("page"));				
			} catch (Exception e) {
				req.getRequestDispatcher("./khong_tim_thay_san_pham.jsp").forward(req, resp);
				return;
			}
		}
		List<SanPham> listSanPham = sanPhamDao.searchSanPhamByName(tenSanPham, page);
		if (listSanPham.size() == 0) {
			req.getRequestDispatcher("./khong_tim_thay_san_pham.jsp").forward(req, resp);
			return;
		}
		HttpSession session = req.getSession();
		int soLuongSanPhamAll = sanPhamDao.getSoLuongSanPhamByName(tenSanPham);
		session.setAttribute("listSanPham", listSanPham);
		session.setAttribute("page", page);
		session.setAttribute("soLuongSanPhamAll", soLuongSanPhamAll);
		session.setAttribute("tenSanPhamTimKiem", tenSanPham);
		req.getRequestDispatcher("./ket_qua_tim_kiem.jsp").forward(req, resp);
	}
}
