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

@WebServlet("/api/san-pham/san-pham-theo-phan-loai")
public class SanPhamTheoPhanLoaiAPI extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final SanPhamDao sanPhamDao =  new SanPhamDaoImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String loaiSanPham = req.getParameter("loai");
		int page = Integer.parseInt(req.getParameter("page"));
		if (loaiSanPham.equals("tat-ca")) {
			loaiSanPham = "Tất cả";
		}
		else if (loaiSanPham.equals("gio-qua-tang")) {
			loaiSanPham = "Giỏ quà tặng trái cây";
		}
		else if (loaiSanPham.equals("mua-nguyen-thung")) {
			loaiSanPham = "Mua nguyên thùng";
		}
		else if (loaiSanPham.equals("mua-le")) {
			loaiSanPham = "Mua lẻ";
		}
		else if (loaiSanPham.equals("combo")) {
			loaiSanPham = "Combo";
		}
		else if (loaiSanPham.equals("do-kho")) {
			loaiSanPham = "Đồ khô";
		}
		else if (loaiSanPham.equals("trai-cay-nam-phi")) {
			loaiSanPham = "Nam Phi";
		}
		else if (loaiSanPham.equals("trai-cay-han-quoc")) {
			loaiSanPham = "Hàn Quốc";
		}
		else if (loaiSanPham.equals("trai-cay-my")) {
			loaiSanPham = "Mỹ";
		}
		else if (loaiSanPham.equals("trai-cay-uc")) {
			loaiSanPham = "Úc";
		}
		else if (loaiSanPham.equals("trai-cay-newzealand")) {
			loaiSanPham = "New Zealand";
		}
		else if (loaiSanPham.equals("trai-cay-chile")) {
			loaiSanPham = "Chile";
		}
		else if (loaiSanPham.equals("trai-cay-nuoc-khac")) {
			loaiSanPham = "Nhiều nước";
		}
		
		List<SanPham> listSanPham = sanPhamDao.getListSanPhamByLoai(loaiSanPham, page);
		
		PrintWriter writer = resp.getWriter();
		
		Gson gson = new Gson();
		String json = new String(gson.toJson(listSanPham).getBytes("ISO-8859-1"), "UTF-8");
		
		resp.setContentType("application/json");
		writer.println(json);
	}
}
