package fruitshop.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import fruitshop.dao.DanhSachChuyenKhoanDao;
import fruitshop.dao.DonHangDao;
import fruitshop.dao.impl.DanhSachChuyenKhoanDaoImpl;
import fruitshop.dao.impl.DonHangDaoImpl;
import fruitshop.model.DanhSachChuyenKhoan;
import fruitshop.model.DanhSachDonHang;
import fruitshop.model.DonHang;
import fruitshop.model.User;

@WebServlet("/xuat-don-hang")
public class XuatFileExcelDonHang extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final DonHangDao donHangDao = new DonHangDaoImpl();
	private static final DanhSachChuyenKhoanDao danhSachChuyenKhoanDao = new DanhSachChuyenKhoanDaoImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User currentUser = (User) session.getAttribute("currentUser");
		DanhSachDonHang danhSachDonHang = (DanhSachDonHang) session.getAttribute("danhSachDonHang");
		DanhSachChuyenKhoan danhSachChuyenKhoan = danhSachChuyenKhoanDao.getDanhSachChuyenKhoanByIdDanhSachDonHang(danhSachDonHang.getId());
		if (currentUser == null || danhSachDonHang == null) {
			req.getRequestDispatcher("./khong_tim_thay_san_pham.jsp").forward(req, resp);
			return;
		}
		
		String trangThaiDonHang = "";
		if (danhSachDonHang.getHuy() == 1) {
			trangThaiDonHang = "Đã hủy đơn hàng";
		}
		else if (danhSachDonHang.getHuy() == 0 && danhSachDonHang.getNgayGui() == null) {
			trangThaiDonHang = "Đang chờ nhân viên xử lý";
		}
		else if (danhSachDonHang.getHuy() == 0 && danhSachDonHang.getNgayGui() != null && danhSachDonHang.getNgayNhan() == null) {
			trangThaiDonHang = "Đang giao hàng";
		}
		else if (danhSachDonHang.getHuy() == 0 && danhSachDonHang.getNgayNhan() != null) {
			trangThaiDonHang = "Đã giao hàng thành công";
		}
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("DonHangFSDH" + danhSachDonHang.getId());
		XSSFRow row = null;
		Cell cell = null;
		
		row = sheet.createRow(0);
		cell = row.createCell(0);
		cell.setCellValue("Tổng quan");
		
		row = sheet.createRow(1);
		cell = row.createCell(0);
		cell.setCellValue("Mã đơn hàng");
		cell = row.createCell(1);
		cell.setCellValue("FSDH" + danhSachDonHang.getId());
		
		row = sheet.createRow(2);
		cell = row.createCell(0);
		cell.setCellValue("Tên người nhận");
		cell = row.createCell(1);
		cell.setCellValue(danhSachDonHang.getTenNguoiNhan());
		
		row = sheet.createRow(3);
		cell = row.createCell(0);
		cell.setCellValue("Địa chỉ người nhận");
		cell = row.createCell(1);
		cell.setCellValue(danhSachDonHang.getDiaChiNguoiNhan());
		
		row = sheet.createRow(4);
		cell = row.createCell(0);
		cell.setCellValue("Số điện thoại người nhận");
		cell = row.createCell(1);
		cell.setCellValue(danhSachDonHang.getSoDienThoaiNguoiNhan());
		
		row = sheet.createRow(5);
		cell = row.createCell(0);
		cell.setCellValue("Email");
		cell = row.createCell(1);
		cell.setCellValue(currentUser.getEmail());
		
		row = sheet.createRow(6);
		cell = row.createCell(0);
		cell.setCellValue("Số lượng sản phẩm");
		cell = row.createCell(1);
		cell.setCellValue(danhSachDonHang.getSoLuongSanPham());
		
		row = sheet.createRow(7);
		cell = row.createCell(0);
		cell.setCellValue("Hình thức thanh toán");
		cell = row.createCell(1);
		cell.setCellValue(danhSachDonHang.getThanhToan());
		
		row = sheet.createRow(8);
		cell = row.createCell(0);
		cell.setCellValue("Số tài khoản người chuyển");
		cell = row.createCell(1);
		cell.setCellValue(danhSachChuyenKhoan.getSoTaiKhoanNguoiChuyen());
		
		row = sheet.createRow(9);
		cell = row.createCell(0);
		cell.setCellValue("Ngân hàng người chuyển");
		cell = row.createCell(1);
		cell.setCellValue(danhSachChuyenKhoan.getTenNganHangNguoiChuyen());
		
		row = sheet.createRow(10);
		cell = row.createCell(0);
		cell.setCellValue("Ngày chuyển khoản");
		cell = row.createCell(1);
		cell.setCellValue(danhSachChuyenKhoan.getNgayChuyenKhoan() == null ? "Invalid" : format.format(danhSachChuyenKhoan.getNgayChuyenKhoan()));
		
		row = sheet.createRow(11);
		cell = row.createCell(0);
		cell.setCellValue("Ngày đặt");
		cell = row.createCell(1);
		cell.setCellValue(danhSachDonHang.getNgayXuat() == null ? "Invalid" : format.format(danhSachDonHang.getNgayXuat()));
		
		row = sheet.createRow(12);
		cell = row.createCell(0);
		cell.setCellValue("Ngày gửi");
		cell = row.createCell(1);
		cell.setCellValue(danhSachDonHang.getNgayGui() == null ? "Invalid" : format.format(danhSachDonHang.getNgayGui()));
		
		row = sheet.createRow(13);
		cell = row.createCell(0);
		cell.setCellValue("Ngày nhận");
		cell = row.createCell(1);
		cell.setCellValue(danhSachDonHang.getNgayNhan() == null ? "Invalid" : format.format(danhSachDonHang.getNgayNhan()));
		
		row = sheet.createRow(14);
		cell = row.createCell(0);
		cell.setCellValue("Tổng tiền");
		cell = row.createCell(1);
		cell.setCellValue(danhSachDonHang.getTongTien());
		
		row = sheet.createRow(15);
		cell = row.createCell(0);
		cell.setCellValue("Trạng thái");
		cell = row.createCell(1);
		cell.setCellValue(trangThaiDonHang);
		
		row = sheet.createRow(16);
		row = sheet.createRow(17);
		
		row = sheet.createRow(18);
		cell = row.createCell(0);
		cell.setCellValue("Chi tiết đơn hàng FSDH"+danhSachDonHang.getId());
		
		row = sheet.createRow(19);
		cell = row.createCell(0);
		cell.setCellValue("Tên sản phẩm");
		cell = row.createCell(1);
		cell.setCellValue("Đơn giá");
		cell = row.createCell(2);
		cell.setCellValue("Đơn vị");
		cell = row.createCell(3);
		cell.setCellValue("Số lượng");
		cell = row.createCell(4);
		cell.setCellValue("Tổng tiền");
		
		List<DonHang> listDonHang = donHangDao.getAllDonHangByIdDanhSachDonHang(danhSachDonHang.getId());
		
		for (int i = 0; i < listDonHang.size(); i++) {
			DonHang donHang = listDonHang.get(i);
			row = sheet.createRow(20 + i);
			cell = row.createCell(0);
			cell.setCellValue(donHang.getTenSanPham());
			cell = row.createCell(1);
			cell.setCellValue(donHang.getDonGia());
			cell = row.createCell(2);
			cell.setCellValue(donHang.getDonVi());
			cell = row.createCell(3);
			cell.setCellValue(donHang.getSoLuong());
			cell = row.createCell(4);
			cell.setCellValue(donHang.getSoLuong() * donHang.getDonGia());
		}
		
		// Thiết lập headers và kiểu nội dung của tệp Excel
        resp.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        resp.setHeader("Content-Disposition", "attachment; filename=\"FSDH"+danhSachDonHang.getId()+".xlsx\"");
        
        workbook.write(resp.getOutputStream());
        workbook.close();
	}

}
