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

import fruitshop.dao.SanPhamTrongGioHangDao;
import fruitshop.dao.impl.SanPhamTrongGioHangDaoImpl;
import fruitshop.model.SanPhamTrongGioHang;
import fruitshop.model.User;

@WebServlet("/gio-hang")
public class GioHangController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final SanPhamTrongGioHangDao sanPhamTrongGioHangDao = new SanPhamTrongGioHangDaoImpl();
	
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
		
		if (req.getParameter("cap-nhat-gio-hang") != null) {
			Cookie[] cookies = req.getCookies();
			for (Cookie c : cookies) {
				System.out.println(c.getName());
				try {
					int idSanPham = Integer.parseInt(c.getName());
					int soLuong = Integer.parseInt(c.getValue());
					c.setMaxAge(0);
					c.setPath("/fruitshop/gio-hang");
					resp.addCookie(c);
					SanPhamTrongGioHang sanPhamTrongGioHang = sanPhamTrongGioHangDao.getSanPhamTrongGioHangByIdUserAndIdSanPham(currentUser.getId(), idSanPham);
					sanPhamTrongGioHang.setSoLuong(soLuong);
					sanPhamTrongGioHang.setNgayThem(new Date());
					if (soLuong > 0) {
						sanPhamTrongGioHangDao.capNhatSanPhamTrongGioHang(sanPhamTrongGioHang);
					}
					else {
						sanPhamTrongGioHangDao.xoaSanPhamTrongGioHangByIdUserAndIdSanPham(currentUser.getId(), idSanPham);
						session.setAttribute("soLuongSanPhamTrongGioHang", (int) session.getAttribute("soLuongSanPhamTrongGioHang") - 1);
					}
				} catch (Exception e) {
					continue;
				}
			}
		}
		
		int tongTien = sanPhamTrongGioHangDao.getTongTienByIdUser(currentUser.getId());
		List<SanPhamTrongGioHang> listSanPhamTrongGioHang = sanPhamTrongGioHangDao.getSanPhamTrongGioHangByPage(currentUser.getId(), page);
		req.setAttribute("tongTien", tongTien);
		req.setAttribute("listSanPhamTrongGioHang", listSanPhamTrongGioHang);
		req.setAttribute("page", page);
		req.getRequestDispatcher("./gio_hang.jsp").forward(req, resp);
	}
}
