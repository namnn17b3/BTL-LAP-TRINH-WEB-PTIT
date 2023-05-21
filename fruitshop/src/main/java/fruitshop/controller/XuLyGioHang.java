package fruitshop.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fruitshop.dao.DonHangDao;
import fruitshop.dao.SanPhamDao;
import fruitshop.dao.impl.DonHangDaoImpl;
import fruitshop.dao.impl.SanPhamDaoImpl;
import fruitshop.model.DonHang;
import fruitshop.model.SanPham;
import fruitshop.model.User;

@WebServlet("/xu-ly-gio-hang")
public class XuLyGioHang extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final SanPhamDao sanPhamDao = new SanPhamDaoImpl();
	private static final DonHangDao donHangDao = new DonHangDaoImpl(); 

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User currentUser = (User) session.getAttribute("currentUser");
		int idSanPham = (int) session.getAttribute("id");
		String url = (String) session.getAttribute("url");
		int soLuong = (int) session.getAttribute("soLuong");
		SanPham sanPham = sanPhamDao.getSanPhamById(idSanPham);
		System.out.println(idSanPham + " " + url + " " + soLuong);
		if (sanPham.getSoLuongNhap() - sanPham.getSoLuongBan() <= 0) {
			session.setAttribute("themGioHangStatus", 1);
			resp.sendRedirect(url);
			return;
		}
		session.setAttribute("tenSanPham", sanPham.getTen());
		session.setAttribute("themGioHangStatus", 2);
		// Cập nhật số lượng 1 sản phẩm cụ thể có trong giỏ hàng
		DonHang donHang = donHangDao.getDonHangByIdUserAndIdSanPham(currentUser.getId(), idSanPham);
		if (donHang == null) {
			donHang = new DonHang();
			donHang.setIdSanPham(idSanPham);
			donHang.setIdUser(currentUser.getId());
			donHang.setTenSanPham(sanPham.getTen());
			donHang.setTienTrenDonVi(sanPham.getTienTrenDonVi());
			donHang.setSoLuong(1);
			donHang.setNgayXuat(new Date());
			donHang.setTrangThai("giỏ hàng");
			donHangDao.themDonHang(donHang);
		}
		else {
			donHang.setNgayXuat(new Date());
			donHang.setTrangThai("giỏ hàng");
			donHang.setSoLuong(donHang.getSoLuong() + soLuong);
			donHangDao.capNhatDonHang(donHang);
		}
		resp.sendRedirect(url);
		return;
	}
}
