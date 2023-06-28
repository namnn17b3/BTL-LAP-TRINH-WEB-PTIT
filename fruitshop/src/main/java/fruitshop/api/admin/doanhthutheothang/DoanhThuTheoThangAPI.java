package fruitshop.api.admin.doanhthutheothang;

import java.io.IOException;
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

@WebServlet("/api/admin/doanh-thu-theo-thang")
public class DoanhThuTheoThangAPI extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final DanhSachDonHangDao danhSachDonHangDao = new DanhSachDonHangDaoImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int nam = -1;
		try {
			nam = Integer.parseInt(req.getParameter("nam"));
			List<Long> list = new ArrayList<>();
			for (int thang = 1; thang <= 12; thang++) {
				long doanhThuTheoThang = danhSachDonHangDao.getDoanhThuTheoThang(nam, thang);
				list.add(doanhThuTheoThang);
			}
			Gson gson = new Gson();
			String json = gson.toJson(list);
			
			resp.setContentType("application/json");
			resp.getWriter().println(json);
		}
		catch (Exception e) {
			e.printStackTrace();
			resp.sendError(500, "invalid parameter!");
			return;
		}
	}
}
