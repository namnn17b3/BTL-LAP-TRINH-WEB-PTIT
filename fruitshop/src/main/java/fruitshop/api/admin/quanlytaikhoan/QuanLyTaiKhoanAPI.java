package fruitshop.api.admin.quanlytaikhoan;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import fruitshop.dao.UserDao;
import fruitshop.dao.impl.UserDaoImpl;
import fruitshop.dto.ErrorResponseDto;
import fruitshop.dto.UserAdminResponseDto;
import fruitshop.dto.UserAdminUpdateResponseDto;
import fruitshop.model.User;
import fruitshop.utils.Sha1;

@WebServlet("/api/admin/quan-ly-tai-khoan")
@MultipartConfig
public class QuanLyTaiKhoanAPI extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final UserDao userDao = new UserDaoImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int itemInPage = 10;
		int pageInWebview = 5;
		int page = 1;
		String queryText = req.getParameter("queryText");
		
		try {
			itemInPage = Integer.parseInt(req.getParameter("itemInPage"));
			pageInWebview = Integer.parseInt(req.getParameter("pageInWebview"));
			page = Integer.parseInt(req.getParameter("page"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		int soLuongBanGhi = userDao.getSoLuongUserWithPagination(queryText);
		int soLuongPage = soLuongBanGhi % itemInPage == 0 ? soLuongBanGhi / itemInPage : soLuongBanGhi / itemInPage + 1;
		int startPageWebview = page - (page - 1) % pageInWebview;
		int endPageWebview = startPageWebview + pageInWebview - 1;
		endPageWebview = endPageWebview > soLuongPage ? soLuongPage : endPageWebview;
		
		List<User> listUser = userDao.getListUserWithPagination(queryText, page, itemInPage);
		UserAdminResponseDto userAdminResponseDto = new UserAdminResponseDto();
		userAdminResponseDto.setListUser(listUser);
		userAdminResponseDto.setSoLuongBanGhi(soLuongBanGhi);
		userAdminResponseDto.setSoLuongPage(soLuongPage);
		userAdminResponseDto.setStartPageWebview(startPageWebview);
		userAdminResponseDto.setEndPageWebview(endPageWebview);
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(userAdminResponseDto);
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json;charset=utf-8"); // or UTF-8
		resp.getWriter().println(json);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

		System.out.println("request content-type:" + request.getContentType());
		
        String email = request.getParameter("email");
        String ten = request.getParameter("ten");
        String matKhau = request.getParameter("matKhau");
        
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        PrintWriter pw = response.getWriter();
        ErrorResponseDto errorResponseDto = null;
        String json = null;
        
        if (userDao.tonTaiUser(email) == true) {
        	response.setStatus(400);
        	errorResponseDto = new ErrorResponseDto(400, "Địa chỉ email đã tồn tại");
        	json = ow.writeValueAsString(errorResponseDto);
			pw.println(json);
        	return;
        }
        
        int statusValidation = statusValidation(email, ten, matKhau);
        if (statusValidation != 0) {
        	switch (statusValidation) {
				case 1: {
					response.setStatus(400);
					errorResponseDto = new ErrorResponseDto(400, "Sai định dạng email");
		        	json = ow.writeValueAsString(errorResponseDto);
					pw.println(json);
		        	return;
				}
				case 2: {
					response.setStatus(400);
					errorResponseDto = new ErrorResponseDto(400, "Sai định dạng tên");
					json = ow.writeValueAsString(errorResponseDto);
					pw.println(json);
		        	return;
				}
				case 3: {
					response.setStatus(400);
					errorResponseDto = new ErrorResponseDto(400, "Sai định dạng mật khẩu");
					json = ow.writeValueAsString(errorResponseDto);
					pw.println(json);
		        	return;
				}
        	}
        }

        // Xử lý file ảnh
        Part filePart = (Part) request.getPart("avatar");
        String fileNameOrigin = null;
        try {
        	fileNameOrigin = getSubmittedFileName(filePart);
        } catch (Exception e) {
        	e.printStackTrace();
		}
        
        if (
        	fileNameOrigin != null &&
    		fileNameOrigin.lastIndexOf(".jpg") == -1 &&
    		fileNameOrigin.lastIndexOf(".jpeg") == -1 &&
    		fileNameOrigin.lastIndexOf(".png") == -1 &&
    		fileNameOrigin.lastIndexOf(".JPG") == -1 &&
    		fileNameOrigin.lastIndexOf(".JPEG") == -1 &&
    		fileNameOrigin.lastIndexOf(".PNG") == -1
        ) {
        	errorResponseDto = new ErrorResponseDto(400, "Sai định dạng file");
        	json = ow.writeValueAsString(errorResponseDto);
			pw.println(json);
        	return;
        }
        
        String fileName = Sha1.encryptThisString("user" + email) + ".jpg";
        if (fileNameOrigin != null) {	
        	// Đường dẫn lưu trữ file trên máy chủ
        	String uploadPath = getServletContext().getRealPath("") + File.separator + "img_user";
        	System.out.println("uploadPath: " + uploadPath);
        	
        	// Tạo thư mục lưu trữ file nếu chưa tồn tại
        	Path uploadDir = Path.of(uploadPath);
        	if (!Files.exists(uploadDir)) {
        		Files.createDirectories(uploadDir);
        	}
        	
        	// Di chuyển file vào thư mục lưu trữ
        	Path filePath = Paths.get(uploadPath, fileName);
        	try (InputStream input = filePart.getInputStream()) {
        		Files.copy(input, filePath, StandardCopyOption.REPLACE_EXISTING);
        	} catch (Exception e) {
        		// TODO Auto-generated catch block
        		e.printStackTrace();
        	}
        }
        
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setTen(ten);
        newUser.setMatKhau(matKhau);
        newUser.setAnh(fileNameOrigin != null ? "./img_user/" + fileName : "./img_user/fb-no-img.png");
        newUser.setVaiTro("u");
        userDao.addUser(newUser);
        
        errorResponseDto = new ErrorResponseDto(200, "Tạo tài khoản mới thành công!");
        json = ow.writeValueAsString(errorResponseDto);
        pw.println(json);
	}
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("request content-type:" + request.getContentType());
		
		int id = Integer.parseInt(request.getParameter("id"));
        String email = request.getParameter("email");
        String ten = request.getParameter("ten");
        String matKhau = request.getParameter("matKhau");
        int discardImage = Integer.parseInt(request.getParameter("discardImage"));
        
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        PrintWriter pw = response.getWriter();
        ErrorResponseDto errorResponseDto = null;
        String json = null;
        
        User userInDatabase = userDao.getUserById(id);
        
        if (!userInDatabase.getEmail().equals(email) && userDao.tonTaiUser(email) == true) {
        	response.setStatus(400);
        	errorResponseDto = new ErrorResponseDto(400, "Địa chỉ email đã tồn tại");
        	json = ow.writeValueAsString(errorResponseDto);
			pw.println(json);
        	return;
        }
        
        int statusValidation = statusValidation(email, ten, matKhau);
        if (statusValidation != 0) {
        	switch (statusValidation) {
				case 1: {
					response.setStatus(400);
					errorResponseDto = new ErrorResponseDto(400, "Sai định dạng email");
		        	json = ow.writeValueAsString(errorResponseDto);
					pw.println(json);
		        	return;
				}
				case 2: {
					response.setStatus(400);
					errorResponseDto = new ErrorResponseDto(400, "Sai định dạng tên");
					json = ow.writeValueAsString(errorResponseDto);
					pw.println(json);
		        	return;
				}
				case 3: {
					if (matKhau.equals("")) {
						break;
					}
					response.setStatus(400);
					errorResponseDto = new ErrorResponseDto(400, "Sai định dạng mật khẩu");
					json = ow.writeValueAsString(errorResponseDto);
					pw.println(json);
		        	return;
				}
        	}
        }

        // Xử lý file ảnh
        Part filePart = (Part) request.getPart("avatar");
        String fileNameOrigin = null;
        try {
        	fileNameOrigin = getSubmittedFileName(filePart);
        } catch (Exception e) {
        	e.printStackTrace();
		}
        
        if (
        	fileNameOrigin != null &&
    		fileNameOrigin.lastIndexOf(".jpg") == -1 &&
    		fileNameOrigin.lastIndexOf(".jpeg") == -1 &&
    		fileNameOrigin.lastIndexOf(".png") == -1 &&
    		fileNameOrigin.lastIndexOf(".JPG") == -1 &&
    		fileNameOrigin.lastIndexOf(".JPEG") == -1 &&
    		fileNameOrigin.lastIndexOf(".PNG") == -1
        ) {
        	errorResponseDto = new ErrorResponseDto(400, "Sai định dạng file");
        	json = ow.writeValueAsString(errorResponseDto);
			pw.println(json);
        	return;
        }
        
        String fileName = Sha1.encryptThisString("user" + email) + ".jpg";
        if (fileNameOrigin != null) {	
        	// Đường dẫn lưu trữ file trên máy chủ
        	String uploadPath = getServletContext().getRealPath("") + File.separator + "img_user";
        	System.out.println("uploadPath: " + uploadPath);
        	
        	// Tạo thư mục lưu trữ file nếu chưa tồn tại
        	Path uploadDir = Path.of(uploadPath);
        	if (!Files.exists(uploadDir)) {
        		Files.createDirectories(uploadDir);
        	}
        	
        	// Di chuyển file vào thư mục lưu trữ
        	Path filePath = Paths.get(uploadPath, fileName);
        	try (InputStream input = filePart.getInputStream()) {
        		Files.copy(input, filePath, StandardCopyOption.REPLACE_EXISTING);
        	} catch (Exception e) {
        		// TODO Auto-generated catch block
        		e.printStackTrace();
        	}
        }
        
        User newUser = new User();
        newUser.setId(id);
        newUser.setEmail(email);
        newUser.setTen(ten);
        newUser.setMatKhau(matKhau);
        newUser.setAnh(fileNameOrigin != null ? "./img_user/" + fileName : userInDatabase.getAnh());
        newUser.setAnh(discardImage == 1 ? "./img_user/fb-no-img.png" : newUser.getAnh());
        newUser.setVaiTro("u");
        userDao.upDateUserById(newUser);
        newUser.setAnh("../" + newUser.getAnh());
        
        UserAdminUpdateResponseDto userAdminUpdateResponseDto = new UserAdminUpdateResponseDto(200, "Cập nhật tài khoản thành công!", newUser);
        json = ow.writeValueAsString(userAdminUpdateResponseDto);
        pw.println(json);
	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse resposne) throws ServletException, IOException {
		PrintWriter pw = resposne.getWriter();
		ErrorResponseDto errorResponseDto = null;
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		User userInDatabase = userDao.getUserById(id);
		if (userInDatabase == null) {
			resposne.setStatus(400);
			errorResponseDto = new ErrorResponseDto(400, "User cần xóa không tồn tại trong hệ thống!");
			String json = ow.writeValueAsString(errorResponseDto);
			pw.println(json);
			return;
		}
		
		userDao.deleteUserById(id);
		errorResponseDto = new ErrorResponseDto(200, "Xóa user có id: " + id + " thành công!");
		String json = ow.writeValueAsString(errorResponseDto);
		pw.println(json);
	}

    // Phương thức để lấy tên file từ một Part
    private String getSubmittedFileName(Part part) throws Exception {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
            	System.out.println("cd: " + cd);
                String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1);
            }
        }
        return null;
    }
    
    private String readJsonBody(HttpServletRequest request) throws IOException {
        try (
    		InputStream inputStream = request.getInputStream();
        	BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))
		) {
            return reader.lines().collect(java.util.stream.Collectors.joining(System.lineSeparator()));
        }
    }
    
    private int statusValidation(String email, String ten, String matKhau) {
    	if (email.matches("^([a-zA-Z0-9\\.]+)@([a-zA-H0-9\\.].+)$") == false) {
    		return 1;
    	}
		if (ten.matches("^.{1,50}$") == false) {
			return 2;
		}
		if (matKhau.matches("^.{8,}$") == false) {
			return 3;
		}
		return 0;
	}
}
