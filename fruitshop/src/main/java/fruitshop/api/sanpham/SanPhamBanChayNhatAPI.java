package fruitshop.api.sanpham;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import fruitshop.dao.SanPhamDao;
import fruitshop.dao.impl.SanPhamDaoImpl;
import fruitshop.model.SanPham;

@WebServlet("/api/san-pham/san-pham-ban-chay-nhat")
public class SanPhamBanChayNhatAPI extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final SanPhamDao sanPhamDao =  new SanPhamDaoImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter writer = resp.getWriter();
		
		List<SanPham> listSanPhamOderBySoLuongBan = sanPhamDao.getSanPhamOrderBySoLuongBan(8);
		Gson gson = new Gson();
		String json = new String(gson.toJson(listSanPhamOderBySoLuongBan).getBytes("ISO-8859-1"), "UTF-8");
		
		resp.setContentType("application/json");
		writer.println(json);
	}
}
