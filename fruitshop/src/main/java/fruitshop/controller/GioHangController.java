package fruitshop.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fruitshop.dao.DonHangDao;
import fruitshop.dao.impl.DonHangDaoImpl;
import fruitshop.model.DonHang;
import fruitshop.model.User;

@WebServlet("/gio-hang")
public class GioHangController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final DonHangDao donHangDao = new DonHangDaoImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int page = 0;
		if (req.getParameter("page") == null) {
			page = 1;
		}
		else {
			page = Integer.parseInt(req.getParameter("page"));
		}
		HttpSession session = req.getSession();
		User currentUser = (User) session.getAttribute("currentUser");
		
		Cookie[] cookies = req.getCookies();
		for (Cookie c : cookies) {
			System.out.println(c.getName());
			try {
				int idSanPham = Integer.parseInt(c.getName());
				int soLuong = Integer.parseInt(c.getValue());
				c.setMaxAge(0);
				c.setPath("/fruitshop/gio-hang");
				resp.addCookie(c);
				DonHang donHang = donHangDao.getDonHangByIdUserAndIdSanPham(currentUser.getId(), idSanPham);
				donHang.setSoLuong(soLuong);
				donHang.setNgayXuat(new Date());
				if (soLuong > 0) {
					donHangDao.capNhatDonHang(donHang);
				}
				else {
					donHangDao.xoaDonHangByIdUserAndIdSanPham(currentUser.getId(), idSanPham);
				}
			} catch (Exception e) {
				continue;
			}
		}
		
		int tongTien = donHangDao.getTongTienByIdUser(currentUser.getId());
		List<DonHang> listSanPhamTrongGioHang = donHangDao.getSanPhamTrongGioHangByPage(currentUser.getId(), page);
		req.setAttribute("tongTien", tongTien);
		req.setAttribute("listSanPhamTrongGioHang", listSanPhamTrongGioHang);
		req.setAttribute("page", page);
		req.getRequestDispatcher("./gio_hang.jsp").forward(req, resp);
	}
}
