package fruitshop.api.admin.top10sanpham;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import fruitshop.dao.SanPhamDao;
import fruitshop.dao.impl.SanPhamDaoImpl;
import fruitshop.model.SanPham;

@WebServlet("/api/admin/top-10-san-pham")
public class Top10SanPhamAPI extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final SanPhamDao sanPhamDao = new SanPhamDaoImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int choose = -1;
		try {
			choose = Integer.parseInt(req.getParameter("choose"));
			if (choose == 0) {
				List<SanPham> list = sanPhamDao.getListTop10SanPhamTheoDoanhThu();
				// ObjectMapper mapper = new ObjectMapper();
				// String json = new String(mapper.writeValueAsString(list.get(0)).getBytes("ISO-8859-1"), "UTF-8");
				ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
				String json = ow.writeValueAsString(list);
				
				resp.setCharacterEncoding("UTF-8");
				resp.setContentType("application/json;charset=UTF-8");
				resp.getWriter().println(json);
			}
			else if (choose == 1) {
				List<SanPham> list = sanPhamDao.getListTop10SanPhamTheoSoLuong();
				ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
				String json = ow.writeValueAsString(list);
				
				resp.setCharacterEncoding("UTF-8");
				resp.setContentType("application/json;charset=UTF-8");
				resp.getWriter().println(json);
			}
			else {
				resp.sendError(500, "invalid parameter!");
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.sendError(500, "invalid parameter!");
			return;
		}
	}
}
