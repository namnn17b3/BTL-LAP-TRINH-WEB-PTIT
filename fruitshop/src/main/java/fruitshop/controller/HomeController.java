package fruitshop.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fruitshop.dao.SanPhamDao;
import fruitshop.dao.impl.SanPhamDaoImpl;
import fruitshop.model.SanPham;

@WebServlet("/home")
public class HomeController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SanPhamDao sanPhamDao =  new SanPhamDaoImpl();
		List<SanPham> listSanPhamOderBySoLuongBan = sanPhamDao.getSanPhamOrderBySoLuongBan(8);
		List<SanPham> listSanPhamOrderBySoSao = sanPhamDao.getSanPhamOrderBySoSao(4);
		req.setAttribute("listSanPhamOderBySoLuongBan", listSanPhamOderBySoLuongBan);
		req.setAttribute("listSanPhamOrderBySoSao", listSanPhamOrderBySoSao);
		req.getRequestDispatcher("/home.jsp").forward(req, resp);
	}
}
