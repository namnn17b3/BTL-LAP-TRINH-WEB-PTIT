package fruitshop.api.danhgia;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import fruitshop.dao.DanhGiaDao;
import fruitshop.dao.impl.DanhGiaDaoImpl;
import fruitshop.model.DanhGia;

@WebServlet("/api/danh-gia/danh-gia-theo-loai")
public class DanhGiaTheoLoai extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final DanhGiaDao danhGiaDao = new DanhGiaDaoImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		int choose = Integer.parseInt(req.getParameter("choose"));
		int page = Integer.parseInt(req.getParameter("page"));
		
		List<DanhGia> currentListDanhGia = danhGiaDao.getDanhGiaChoSanPhamByIdAndPageChoose(id, choose, page);
		
		PrintWriter writer = resp.getWriter();
		Gson gson = new Gson();
		
		String json = new String(gson.toJson(currentListDanhGia).getBytes("ISO-8859-1"), "UTF-8");
		
		resp.setContentType("application/json");
		writer.println(json);
	}
}
