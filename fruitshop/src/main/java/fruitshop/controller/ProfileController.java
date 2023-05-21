package fruitshop.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import fruitshop.dao.UserDao;
import fruitshop.dao.impl.UserDaoImpl;
import fruitshop.model.User;
import fruitshop.utils.Sha1;

@WebServlet("/profile")
public class ProfileController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final UserDao userDao = new UserDaoImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("./profile_user.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User currentUser = (User) session.getAttribute("currentUser");
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		String pathGoc = super.getServletContext().getRealPath("./img_user");
		diskFileItemFactory.setRepository(new File(pathGoc));
		
		ServletFileUpload fileUpload = new ServletFileUpload(diskFileItemFactory);
		String coYeuCauXoaAnh = "";
		try {
			List<FileItem> fileItems = fileUpload.parseRequest(req);
			for (FileItem item : fileItems) {
				if (item.isFormField()) {
					if (item.getFieldName().equals("ten")) {
						currentUser.setTen(item.getString());
					}
					else if (item.getFieldName().equals("mat-khau")) {
						currentUser.setMatKhau(item.getString());
					}
					else if (item.getFieldName().equals("co-yeu-cau-xoa-anh")) {
						// System.out.println("profile controller line 57");
						coYeuCauXoaAnh = item.getString();
					}
				}
				else {
					// có file update
					if (item.getSize() > 0) {
						// String fileName = item.getName();
						// String extendFile = fileName.substring(fileName.lastIndexOf("."));
						String anh = Sha1.encryptThisString("user" + currentUser.getEmail()) + ".jpg";
						File file = new File(pathGoc + File.separator + anh);
						currentUser.setAnh("./img_user/" + anh);
						if (file.exists() == true) {
							file.delete();
						}
						item.write(new File(pathGoc + File.separator + anh));
					}
					// Không tải upload ảnh nhưng mà tên ảnh != ./img_uer/fb-no-img.png
					// -> đã tồn tại ảnh
					// trong csdl và có yêu cầu xóa ảnh -> xóa
					else if (currentUser.getAnh().equals("./img_user/fb-no-img.png") == false && coYeuCauXoaAnh.equals("") == false) {
						// System.out.println("profile controller line 75 " + currentUser.getAnh());
						File file = new File(super.getServletContext().getRealPath(currentUser.getAnh()));
						file.delete();
						currentUser.setAnh("./img_user/fb-no-img.png");
					}
				}
				// System.out.println("profile controller line 82 " + coYeuCauXoaAnh);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// System.out.println("profile controller line 88 " + currentUser.getAnh());
		userDao.upDateUserByEmail(currentUser);
		session.setAttribute("currentUser", currentUser);
		req.setAttribute("thongBaoCapNhatThanhCong", 1);
		req.getRequestDispatcher("./profile_user.jsp").forward(req, resp);
	}
}
