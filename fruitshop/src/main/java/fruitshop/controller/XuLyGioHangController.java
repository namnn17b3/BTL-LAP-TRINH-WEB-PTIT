package fruitshop.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fruitshop.dao.SanPhamDao;
import fruitshop.dao.SanPhamTrongGioHangDao;
import fruitshop.dao.impl.SanPhamDaoImpl;
import fruitshop.dao.impl.SanPhamTrongGioHangDaoImpl;
import fruitshop.model.SanPham;
import fruitshop.model.SanPhamTrongGioHang;
import fruitshop.model.User;

@WebServlet("/xu-ly-gio-hang")
public class XuLyGioHangController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final SanPhamDao sanPhamDao = new SanPhamDaoImpl();
	private static final SanPhamTrongGioHangDao sanPhamTrongGioHangDao = new SanPhamTrongGioHangDaoImpl(); 

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User currentUser = (User) session.getAttribute("currentUser");
		int idSanPham = (int) session.getAttribute("id");
		String url = (String) session.getAttribute("url");
		int soLuong = (int) session.getAttribute("soLuong");
		SanPham sanPham = sanPhamDao.getSanPhamById(idSanPham);
		System.out.println(idSanPham + " " + url + " " + soLuong);
		if (sanPham.getSoLuongNhap() - sanPham.getSoLuongBan() == 0) {
			session.setAttribute("themGioHangStatus", 1);
			resp.sendRedirect(url);
			return;
		}
		
		if (sanPham.getSoLuongNhap() - sanPham.getSoLuongBan() - soLuong < 0) {
			session.setAttribute("themGioHangStatus", 2);
			resp.sendRedirect(url);
			return;
		}
		session.setAttribute("tenSanPham", sanPham.getTen());
		session.setAttribute("themGioHangStatus", 3);
		// Cập nhật số lượng 1 sản phẩm cụ thể có trong giỏ hàng
		SanPhamTrongGioHang sanPhamTrongGioHang = sanPhamTrongGioHangDao.getSanPhamTrongGioHangByIdUserAndIdSanPham(currentUser.getId(), idSanPham);
		if (sanPhamTrongGioHang == null) {
			sanPhamTrongGioHang = new SanPhamTrongGioHang();
			sanPhamTrongGioHang.setIdSanPham(idSanPham);
			sanPhamTrongGioHang.setIdUser(currentUser.getId());
			sanPhamTrongGioHang.setTenSanPham(sanPham.getTen());
			sanPhamTrongGioHang.setTienTrenDonVi(sanPham.getTienTrenDonVi());
			sanPhamTrongGioHang.setSoLuong(soLuong);
			sanPhamTrongGioHang.setNgayThem(new Date());
			sanPhamTrongGioHangDao.themSanPhamTrongGioHang(sanPhamTrongGioHang);
		}
		else {
			sanPhamTrongGioHang.setNgayThem(new Date());
			sanPhamTrongGioHang.setSoLuong(sanPhamTrongGioHang.getSoLuong() + soLuong);
			sanPhamTrongGioHangDao.capNhatSanPhamTrongGioHang(sanPhamTrongGioHang);
		}
		resp.sendRedirect(url);
		return;
	}
}
