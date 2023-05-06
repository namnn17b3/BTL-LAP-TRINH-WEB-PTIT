package fruitshop.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fruitshop.model.SanPham;
import fruitshop.service.SanPhamService;

@WebServlet("/home")
public class HomeController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static SanPhamService sanPhamService =  new SanPhamService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<SanPham> listSanPhamOderBySoLuongBan = sanPhamService.getSanPhamOrderBySoLuongBan(8);
		List<SanPham> listSanPhamOrderBySoSao = sanPhamService.getSanPhamOrderBySoSao(4);
		req.setAttribute("listSanPhamOderBySoLuongBan", listSanPhamOderBySoLuongBan);
		req.setAttribute("listSanPhamOrderBySoSao", listSanPhamOrderBySoSao);
		req.getRequestDispatcher("/home.jsp").forward(req, resp);
	}
}
