package fruitshop.api.admin.doanhthutheongay;

import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import fruitshop.dao.DanhSachDonHangDao;
import fruitshop.dao.impl.DanhSachDonHangDaoImpl;

@WebServlet("/api/admin/doanh-thu-theo-ngay")
public class DoanhThuTheoNgayAPI extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final DanhSachDonHangDao danhSachDonHang = new DanhSachDonHangDaoImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int[] soNgayTrongThang = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		int nam = -1;
		int thang = -1;
		try {
			nam = Integer.parseInt(req.getParameter("nam"));
			thang = Integer.parseInt(req.getParameter("thang"));
			int namHienTai = LocalDate.now().getYear();
			if (nam > namHienTai || thang > 12) {
				resp.sendError(400, "invalid parameter!");
				return;
			}
			if (nam % 400 == 0 || (nam % 100 != 0 && nam % 4 == 0)) {
				soNgayTrongThang[2] = 29;
			}
			List<Long> list = new ArrayList<>();
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			for (int ngay = 1; ngay <= soNgayTrongThang[thang]; ngay++) {
				String pattern = "";
				pattern += (ngay < 10) ? ("0" + ngay + "/") : (ngay + "/");
				pattern += (thang < 10) ? ("0" + thang + "/") : (thang + "/");
				pattern += nam;
				Date date = format.parse(pattern);
				long doanhThuTheoNgay = danhSachDonHang.getDoanhThuTheoNgay(date);
				list.add(doanhThuTheoNgay);
			}
			Gson gson = new Gson();
			String json = gson.toJson(list);
			resp.setContentType("application/json");
			resp.getWriter().println(json);
			return;
		}
		catch (Exception e) {
			resp.sendError(400, "invalid parameter!");
			return;
		}
	}
}
