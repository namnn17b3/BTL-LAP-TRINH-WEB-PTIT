package fruitshop.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fruitshop.dao.DanhSachDonHangDao;
import fruitshop.dao.impl.DanhSachDonHangDaoImpl;
import fruitshop.model.DanhSachDonHang;
import fruitshop.model.User;

@WebServlet("/huy-don-hang")
public class HuyDonHangController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final DanhSachDonHangDao danhSachDonHangDao = new DanhSachDonHangDaoImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User currentUser = (User) session.getAttribute("currentUser");
		
		if (currentUser == null) {
			// System.out.println("line 38 huy don hang controller");
			req.getRequestDispatcher("./khong_tim_thay_san_pham").forward(req, resp);
			return;
		}
		double x = 0;
		double y = 0;
		int idDanhSachDonHang = 0;
		Cookie[] cookies = req.getCookies();
		for (Cookie c : cookies) {
			if (c.getName().equals("clickHuyDonHang")) {
				try {
					String[] data = c.getValue().split("_");
					x = Double.parseDouble(data[0].split("==")[1]);
					y = Double.parseDouble(data[1].split("==")[1]);
					idDanhSachDonHang = Integer.parseInt(data[2].split("==")[1]);
					c.setMaxAge(0);
					c.setPath("/fruitshop/huy-don-hang");
					resp.addCookie(c);
				}
				catch (Exception e) {
					// System.out.println("line 57 huy don hang controller");
					// e.printStackTrace();
					req.getRequestDispatcher("./khong_tim_thay_san_pham.jsp").forward(req, resp);
					return;
				}
			}
		}
		
		DanhSachDonHang danhSachDonHang = danhSachDonHangDao.getDanhSachDonHangById(idDanhSachDonHang);
		if (danhSachDonHang == null) {
			// System.out.println("line 67 huy don hang controller");
			req.getRequestDispatcher("./khong_tim_thay_san_pham.jsp").forward(req, resp);
			return;
		}
		
		if (danhSachDonHang.getNgayGui() == null && danhSachDonHang.getHuy() == 0) {
			danhSachDonHang.setHuy(1);
			danhSachDonHangDao.updateDanhSachDonHang(danhSachDonHang);
			session.setAttribute("huyDonHangStatus", 2);
		}
		else {
			session.setAttribute("huyDonHangStatus", 1);
		}
		session.setAttribute("x", x);
		session.setAttribute("y", y);
		session.setAttribute("idDanhSachDonHang", idDanhSachDonHang);
		resp.sendRedirect("./don-hang");
	}
}
