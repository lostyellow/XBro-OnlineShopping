package servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadBase;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

/**
 * Servlet implementation class fileUpload
 */
@WebServlet("/fileUpload")
public class fileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public fileUpload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 // 检查是否是文件上传请求
        if (ServletFileUpload.isMultipartContent(request)) {
            try {
                // 创建一个FileItem工厂
                DiskFileItemFactory factory = new DiskFileItemFactory();

                // 设置上传文件的临时目录
                File tempDir = (File) getServletContext().getAttribute("javax.servlet.context.tempdir");
                factory.setRepository(tempDir);

                // 创建文件上传处理器
                FileUploadBase fileUpload = new ServletFileUpload(factory);

                // 解析请求
                List<FileItem> items = fileUpload.parseRequest(new ServletRequestContext(request));

                // 处理上传的文件
                for (FileItem item : items) {
                    if (!item.isFormField()) {
                        // 获取文件名
                        String fileName = new File(item.getName()).getName();

                        // 保存文件到服务器
                        String uploadPath = "C:/pic";
                        File uploadDir = new File(uploadPath);
                        if (!uploadDir.exists()) {
                            uploadDir.mkdir();
                        }

                        File uploadedFile = new File(uploadPath + File.separator + fileName);
                        item.write(uploadedFile);

                        response.getWriter().write("文件上传成功！");
                    }
                }
            } catch (FileUploadException e) {
                e.printStackTrace();
                response.getWriter().write("文件上传失败: " + e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
                response.getWriter().write("文件上传失败: " + e.getMessage());
            }
        } else {
            response.getWriter().write("不是文件上传请求");
        }
    }

}
