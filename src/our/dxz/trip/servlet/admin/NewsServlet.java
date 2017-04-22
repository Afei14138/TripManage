package our.dxz.trip.servlet.admin;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.ericsoft.bmob.restapi.Bmob;
import com.ericsoft.demo.CDN;
import com.google.gson.Gson;

import our.dxz.trip.bean.News;
import our.dxz.trip.service.admin.NewsService;
import our.dxz.trip.utils.Mog;

public class NewsServlet extends HttpServlet
{
	private static final String TAG = NewsServlet.class.getSimpleName();
	
	private File uploadPath;  
    private File tempPath;  
    	
    private String imgPath = "";
    
    private NewsService service = new NewsService();
    
	public NewsServlet()
	{
		super();
	}

	public void destroy()
	{
		super.destroy(); 
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		this.doPost(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		response.setContentType("text/html;charset=utf-8"); 
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String action = request.getParameter("action");
		Mog.d(action, "action:"+action);
		
		if (action.equals("addnews"))
		{
			this.addNews(request, response);
		}
		if (action.equals("uploadimg"))
		{
			this.uploadImage(request, response);
		}
		if (action.equals("news"))
		{
			
		}
		
	}
	
	private void addNews(HttpServletRequest request, HttpServletResponse response)
	{
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String editor = request.getParameter("editor");
		
		News news = new News();
		
		news.setTitle(title);
		news.setContent(content);
		news.setEditor(editor);
		news.setImgpath(imgPath);
		
		Date currentTime = new Date();
		String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(currentTime);
		Timestamp now = Timestamp.valueOf(time);
		
		news.setPubtime(now);
		
		Mog.d(TAG, "addNews request:"+" title:"+title+" content:"+content+" editor:"+editor + "imgpath:"+imgPath);
		
		service.addNews(news);
		
	}
	
	
	private void uploadImage(HttpServletRequest request, HttpServletResponse response)
	{
		ServletContext context = getServletContext();
		String filePath = context.getInitParameter("file-upload");
		
        DiskFileItemFactory factory = new DiskFileItemFactory();  
        factory.setSizeThreshold(5000 * 1024);
        // 本地存储的数据大于 maxMemSize.
        factory.setRepository(new File("d:\\temp"));
        
        ServletFileUpload sfu = new ServletFileUpload(factory);  
        sfu.setFileSizeMax(1024*400);  
        try 
        {  
            List<FileItem> items = sfu.parseRequest(request);  
            for (int i = 0; i < items.size(); i++) 
            {  
                FileItem item = items.get(i);  
                if(!item.isFormField())
                {  
                    ServletContext sctx = getServletContext();  
                    String path = sctx.getRealPath("/data"); 
                    Mog.d(TAG, "path:"+path);
                    
                    String fileName = item.getName();
                    Mog.d(TAG, "fileName0:"+fileName);
                                        
                    File file;
                    
                    if( fileName.lastIndexOf("\\") >= 0 )
                    {
                        file = new File( filePath , fileName.substring( fileName.lastIndexOf("\\"))) ;
                    }
                    else
                    {
                        file = new File( filePath ,fileName.substring(fileName.lastIndexOf("\\")+1)) ;
                    }
                    
                    if(!file.exists())
                    {  
                        item.write(file);  
                        Mog.d(TAG, "upflename:"+file.getAbsolutePath());
                        imgPath = uploadToRemote(file);
                        Mog.d(TAG, "remote image url::"+imgPath);
                    }  
                }  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
	}
	
	private String uploadToRemote(File file)
	{
		Bmob.initBmob("c283d1709fabafa4d515d5e8c43b1612","f4a538c3c89a856bb935ceb28f70c769");
		
		String jsonString = Bmob.uploadFile2(file.getAbsolutePath());
		
		Gson gson = new Gson();
		
		CDN cdn = new CDN();
		
		cdn = gson.fromJson(jsonString, CDN.class);
		
		return cdn.getUrl();
	}
	
	public void init() throws ServletException
	{
        
	}

}
