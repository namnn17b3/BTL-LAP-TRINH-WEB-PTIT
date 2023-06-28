package fruitshop.api.admin.main;

import java.util.Arrays;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpSession;
//import javax.websocket.HandshakeResponse;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
//import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpoint;
//import javax.websocket.server.ServerEndpointConfig;

import com.google.gson.Gson;

import fruitshop.dao.DanhGiaDao;
import fruitshop.dao.DanhSachDonHangDao;
import fruitshop.dao.DonHangDao;
import fruitshop.dao.SanPhamDao;
import fruitshop.dao.UserDao;
import fruitshop.dao.impl.DanhGiaDaoImpl;
import fruitshop.dao.impl.DanhSachDonHangDaoImpl;
import fruitshop.dao.impl.DonHangDaoImpl;
import fruitshop.dao.impl.SanPhamDaoImpl;
import fruitshop.dao.impl.UserDaoImpl;
//import fruitshop.model.User;

//class HttpSessionConfigurator extends ServerEndpointConfig.Configurator {
//
//	@Override
//    public void modifyHandshake(ServerEndpointConfig config, HandshakeRequest request, HandshakeResponse response) {
//        HttpSession httpSession = (HttpSession) request.getHttpSession();
//        config.getUserProperties().put(HttpSession.class.getName(), httpSession);
//    }
//}

@ServerEndpoint("/api/admin/main")
@WebServlet("/api/admin/main")
public class MainAPI extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final UserDao userDao = new UserDaoImpl();
	private static final SanPhamDao sanPhamDao = new SanPhamDaoImpl();
	private static final DanhGiaDao danhGiaDao = new DanhGiaDaoImpl();
	private static final DanhSachDonHangDao danhSachDonHangDao = new DanhSachDonHangDaoImpl();
	private static final DonHangDao donHangDao = new DonHangDaoImpl();
	
	@OnMessage
	public void onMessage(String message, Session userSession) {
//		HttpSession session = (HttpSession) userSession.getUserProperties().get(HttpSession.class.getName());
//		User currentUser = (User) session.getAttribute("currentUser");
//		System.out.println("line 53 main websocket");
//		if (currentUser == null) {
//			return;
//		}
//		if (currentUser != null && currentUser.getVaiTro().equals("u")) {
//			return;
//		}
//		System.out.println("line 60 main websocket");
		
		long tongDoanhThu = danhSachDonHangDao.getTongDoanhThu();
		long soSanPham = sanPhamDao.getSoLuongSanPhamByLoai("Tất cả");
		long soDanhGia = danhGiaDao.getSoLuongDanhGia();
		long soUser = userDao.getSoLuongUser();
		long soUserOnline = userDao.getSoLuongUserOnline();
		long soLuongSanPhamDaBan = donHangDao.getSoLuongSanPhamDaBan();
		Long[] a = {tongDoanhThu, soSanPham, soDanhGia, soUser, soUserOnline, soLuongSanPhamDaBan};
		List<Long> list = Arrays.asList(a);
		
		Gson gson = new Gson();
		String json = gson.toJson(list);
		
		userSession.getAsyncRemote().sendText(json);
	}
	
	@OnOpen
	public void onOpen() {
//		System.out.println("Open connection...");
		return;
	}
	
	@OnClose
	public void onClose() {
//		System.out.println("Close connection...");
		return;
	}
	
	@OnError
	public void onError(Throwable error) {
		error.printStackTrace();
	}
}
