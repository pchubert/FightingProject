package WebIGo.admin.Service;


import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem; 
import org.apache.commons.fileupload.FileUploadException; 
import org.apache.commons.fileupload.disk.DiskFileItemFactory; 
import org.apache.commons.fileupload.servlet.ServletFileUpload; 

import org.json.JSONException;
import org.json.JSONObject;



/**
 * Servlet implementation class UploadFileServerlet
 */
@WebServlet("/UploadFileServlet")
public class UploadFileServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	//保存文件的目录
	private static String PATH_FOLDER = "D://";
	//存放临时文件的目录
	private static String TEMP_FOLDER = "/";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadFileServlet() {
        // TODO Auto-generated constructor stub
        super();
    }
    
    /**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init(ServletConfig config) throws ServletException {
		
		ServletContext servletCtx = config.getServletContext();
		

		
		//原语句
		//PATH_FOLDER = "D:/source/aaa";
		
		//初始化路径
		//缩略图保存路径
		PATH_FOLDER = servletCtx.getRealPath("/Upload");
		System.out.println("保存缩略图的目录===========" + PATH_FOLDER);
		
		//存放临时文件的目录,存放xxx.tmp文件的目录
		TEMP_FOLDER = servletCtx.getRealPath("/temp");
		System.out.println("存放临时文件的目录===========" + TEMP_FOLDER);
		
		//检测路径，没有则创建两个相关路径
		File dir = new File(PATH_FOLDER);
		if (!dir.isDirectory()) {
			dir.mkdir();
		}
		File dir2 = new File(TEMP_FOLDER);
		if (!dir2.isDirectory()) {
			dir2.mkdir();
		}
	}
	
	private String getUploadFileName(FileItem item) {
		
		//获取路径名
		String value = item.getName();
		
		//索引到最后一个反斜杠
		int start = value.lastIndexOf("/");
		
		//截取 上传文件的 字符串名字，加1是 去掉反斜杠，
		String filename = value.substring(start + 1);
 
		return filename;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//创建 json 对象
		JSONObject jsonobj = new JSONObject();
		
		//定义其他
		int returnTag = -1;
		String saveName=null;
		
		//上传的文件名
		String filename=null;
		
		//?
		String id = request.getParameter("ID");
			
		try {
			// 设置编码
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=UTF-8");
			
			// 获得磁盘文件条目工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();
		 
			// 如果没以下两行设置的话，上传大文件会占用很多内存，
			// 设置暂时存放的存储室 , 这个存储室，可以和最终存储文件的目录不同
			/**
			 * 原理 它是先存到暂时存储室，然后在真正写到对应目录的硬盘上， 按理来说当上传一个文件时，其实是上传了两份，第一个是以
			 * .tem 格式的然后再将其真正写到对应目录的硬盘上
			 */
			factory.setRepository(new File(TEMP_FOLDER));
			
			//设置缓存的大小，当上传文件的容量超过该缓存时，直接放到暂时存储室
			factory.setSizeThreshold(1024 * 1024);
					
			//高水平的API文件上传处理
			ServletFileUpload upload = new ServletFileUpload(factory);
		 
			//提交上来的信息都在这个list里面
			//这意味着可以上传多个文件
			//请自行组织代码
			List<FileItem> list = upload.parseRequest(request);
			
			//获取上传的文件
			for(FileItem file : list){
			FileItem item = file;
			
			//获取完整的文件名
			filename = getUploadFileName(item);
			System.out.println("获取的文件名============================"
							+ filename);
			String suffix = filename
							.substring(filename.lastIndexOf(".") + 1);
			System.out.println("获取的文件后缀名========================="
							+ suffix);
			String[] regular = { "JPG", "GIF","PNG","JPEG" ,"BMP"};
			boolean flag = false;
			
			//判断文件格式是否合法
			for(String str : regular){
				if(suffix.equalsIgnoreCase(str)) {
					flag = true;
				}
			}
			if(!flag){
				returnTag = 0;
				throw new Exception("文件格式不合法！");
			}
		 
			//保存后的文件名
			saveName = new Date().getTime()
						+ filename.substring(filename.lastIndexOf("."));
					
			System.out.println("存放目录:=================================="
								+ PATH_FOLDER);
			System.out.println("文件名:===================================="
								+ filename);
						
			//真正写到磁盘上
			//第三方提供的
			item.write(new File(PATH_FOLDER, saveName));	
			}
			
			System.out.println("==========以上为上传代码=============");
			
			//?
			if (returnTag != -1) {
				// 请求失败
				jsonobj.put("STATUS", "1");
			} else {
				jsonobj.put("code", 0);
				jsonobj.put("name", saveName);
			}
			response.getWriter().write(jsonobj.toString());
			response.getWriter().close();
		} catch (Exception e) {
			e.printStackTrace();
				
			try {
				jsonobj.put("errorCode", 0);
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				
			response.getWriter().write(jsonobj.toString());
			response.getWriter().close();
		}
		
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
