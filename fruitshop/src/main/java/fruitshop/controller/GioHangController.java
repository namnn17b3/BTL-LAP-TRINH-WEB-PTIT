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
		
		Cookie[] cookies = req.getCookies();
		boolean clickCapNhatGioHang = false;
		for (Cookie c : cookies) {
			if (c.getName().equals("clickCapNhatGioHang")) {
				clickCapNhatGioHang = true;
				c.setMaxAge(0);
				c.setPath("/fruitshop/gio-hang");
				resp.addCookie(c);
				break;
			}
		}
		if (clickCapNhatGioHang == true) {
			for (Cookie c : cookies) {
				try {
					int idSanPham = Integer.parseInt(c.getName());
					int soLuong = Integer.parseInt(c.getValue());
					// System.out.println(c.getName());
					c.setMaxAge(0);
					c.setPath("/fruitshop/gio-hang");
					resp.addCookie(c);
					SanPhamTrongGioHang sanPhamTrongGioHang = sanPhamTrongGioHangDao.getSanPhamTrongGioHangByIdUserAndIdSanPham(currentUser.getId(), idSanPham);
					/*if (sanPhamTrongGioHang == null) {
						System.out.println("null line 53 gio hang controller");
					}*/
					sanPhamTrongGioHang.setSoLuong(soLuong);
					sanPhamTrongGioHang.setNgayThem(new Date());
					// System.out.println(sanPhamTrongGioHang.getIdSanPham() + " line 54 gio hang controller");
					if (soLuong > 0) {
						System.out.println("line 55 gio hang controller");
						sanPhamTrongGioHangDao.capNhatSanPhamTrongGioHang(sanPhamTrongGioHang);
					}
					else {
						sanPhamTrongGioHangDao.xoaSanPhamTrongGioHangByIdUserAndIdSanPham(currentUser.getId(), idSanPham);
						session.setAttribute("soLuongSanPhamTrongGioHang", (int) session.getAttribute("soLuongSanPhamTrongGioHang") - 1);
					}
					// System.out.println(sanPhamTrongGioHang.getIdSanPham() + " line 61 gio hang controller");
				} catch (Exception e) {
					continue;
				}
			}
		}
		else {
			for (Cookie c : cookies) {
				try {
					Integer.parseInt(c.getName());
					c.setMaxAge(0);
					c.setPath("/fruitshop/gio-hang");
					resp.addCookie(c);
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
