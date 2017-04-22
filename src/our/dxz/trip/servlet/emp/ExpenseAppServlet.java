package our.dxz.trip.servlet.emp;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import our.dxz.trip.bean.ExpenseApp;
import our.dxz.trip.bean.ExpenseItem;
import our.dxz.trip.bean.TravelSum;
import our.dxz.trip.service.emp.ExpenseAppService;
import our.dxz.trip.utils.Mog;

import com.ericsoft.bmob.restapi.Bmob;
import com.ericsoft.demo.CDN;
import com.google.gson.Gson;

public class ExpenseAppServlet extends HttpServlet
{

	private static final String TAG = ExpenseAppServlet.class.getSimpleName();
	
	private ExpenseAppService eaService ;
	private String imgPath = "";
	
	public ExpenseAppServlet()
	{
		super();
		
		eaService = new ExpenseAppService();
	}

	public void destroy()
	{
		super.destroy();
		Mog.d(TAG, "destroy()");
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		this.doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String action = (String)request.getParameter("action");
		
		Mog.d(TAG, "action:"+action);
		
		if (action.equals("getExpenseAppList"))
		{
			String resString = this.getExpenseAppListString();
			String resString2 = this.getExpenseItemString();
			request.setAttribute("result", resString);
			request.setAttribute("result2", resString2);
						
			request.getRequestDispatcher("/emp/list_expenseapp.jsp").forward(request, response);
			
		}
		if (action.equals("newExpenseApp"))
		{
			String appString = getTravelSumString();
			request.setAttribute("appsResult", appString);
			
			request.getRequestDispatcher("/emp/add_expenseapp.jsp").forward(request, response);
		}
		if (action.equals("newExpenseAppItem"))
		{
			this.addNewExpenseItemApp(request,response);
		}
		
		if (action.equals("saveExpenseApp"))
		{
			this.addNewExpenseApp(request, response);
		}
		
		if (action.equals("reEditExpenseApp"))
		{
			this.reEditExpenseApp(request, response);
		}
		if (action.equals("uploadimg"))
		{
			this.uploadImage(request, response);
		}
		if (action.equals("download"))
		{
			this.downloadImage(request, response);
		}
	}

	public void init() throws ServletException 
	{
		Mog.d(TAG, "init()");
	}
	
	////////////////////////////////////////////////////////////
	
	private void reLoad(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String resString = this.getExpenseAppListString();
		
		request.setAttribute("result", resString);
					
		request.getRequestDispatcher("/emp/list_expenseapp.jsp").forward(request, response);
	}
	
	private String getExpenseAppListString()
	{
		
		List<ExpenseApp> apps = eaService.getExpenseApps();
		
		Gson gson = new Gson();
		String resultJson = gson.toJson(apps);
		
		Mog.d(TAG, "getExpenseAppListString() resultJson "+resultJson);
		
		return resultJson;
	}
	
	private void downloadImage(HttpServletRequest request, HttpServletResponse response)
	{
		String url = request.getParameter("url");
		Mog.d(TAG,"downloadImage url:"+url);
		
			URL u;
			try
			{
				u = new URL(url);
				HttpURLConnection connection = (HttpURLConnection) u.openConnection();
			    DataInputStream in = new DataInputStream(connection.getInputStream());
				
			    String [] pre = url.split("\\.");
			    for(int i = 0; i < pre.length ; i++)
			    {
			    	Mog.d(TAG, "pre[]:"+i+":"+pre[i]);
			    }
			    String suffix = "."+pre[pre.length-1];
			    
			    response.addHeader("Content-Disposition","attachment;filename=" + System.currentTimeMillis()+suffix);
				
			    OutputStream  os = response.getOutputStream(); 
			   
			    
			    byte[] b = new byte[1024];  
			    int i = 0;  
			    
			    while((i = in.read(b)) > 0)  
			    {  
			    	os.write(b, 0, i);  
			    }  
			    
			    
			} catch (MalformedURLException e)
			{
				e.printStackTrace();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
	      
	}
	
	private String getExpenseItemString()
	{

		List<ExpenseItem> apps = eaService.getExpenseItems();
		
		Gson gson = new Gson();
		String resultJson = gson.toJson(apps);
		
		Mog.d(TAG, "getExpenseItemString() resultJson "+resultJson);
		
		return resultJson;
	}
	
	private String getTravelSumString()
	{

		List<TravelSum> apps = eaService.getTravelSums();
		
		Gson gson = new Gson();
		String resultJson = gson.toJson(apps);
		
		Mog.d(TAG, "getTravelSumString() resultJson "+resultJson);
		
		return resultJson;
	}
	
	List<ExpenseItem> tempExpenseApps = new ArrayList<ExpenseItem>();
	
	private void addNewExpenseItemApp(HttpServletRequest request, HttpServletResponse response)
	{
		Mog.d(TAG, "addNewExpenseItemApp()");
		
		String itemCost = request.getParameter("item_cost");
		String itemType = request.getParameter("item_type");
		String itemDesc = request.getParameter("item_desc");
		
		Mog.d(TAG, "request result-itemCost:"+itemCost);
		Mog.d(TAG, "request result-itemType:"+itemType);
		Mog.d(TAG, "request result-itemDesc:"+itemDesc);
		
		ExpenseItem expenseApp = new ExpenseItem();
		expenseApp.setCost(itemCost);
		expenseApp.setType(itemType);
		expenseApp.setDesc(itemDesc);
		
		tempExpenseApps.add(expenseApp);
		
	}
	
	private void addNewExpenseApp(HttpServletRequest request, HttpServletResponse response)
	{
		Mog.d(TAG, "addNewExpenseApp()");
		
		String trapp_no = request.getParameter("expense_trappno");
		String expense_cost = request.getParameter("expense_cost");
		String expense_cardno = request.getParameter("expense_cardno");
		
		Mog.d(TAG, "request result-ExpenseApp trapp_no:"+trapp_no);
		Mog.d(TAG, "request result-ExpenseApp expense_cost:"+expense_cost);
		Mog.d(TAG, "request result-ExpenseApp expense_cardno:"+expense_cardno);
		
		if (trapp_no.equals("") || trapp_no == null || tempExpenseApps.size() == 0 || tempExpenseApps == null)
		{
			String erro = "trapp_no.equals(\"\") || trapp_no == null || tempExpenseApps.size() == 0 || tempExpenseApps == null";
			Mog.d(TAG, erro);
			
			return ;
		}
		
		for (int i = 0; i < tempExpenseApps.size(); i++)
		{
			tempExpenseApps.get(i).setTrapp_no(trapp_no);
		}
		
		Mog.d(TAG, "tempExpenseApps.size()"+tempExpenseApps.size());
		
		ExpenseApp expenseApp = new ExpenseApp();
		expenseApp.setTrapp_no(trapp_no);
		expenseApp.setExpense(Float.valueOf(expense_cost));
		expenseApp.setCard_no(expense_cardno);
		expenseApp.setState("0");
		expenseApp.setImgpath(imgPath);
		
		int rows = eaService.insertExpenseApp(expenseApp);
		
		Mog.d(TAG, "eaService.insertExpenseApp() rows"+rows);
		
		for (int i = 0; i < tempExpenseApps.size(); i++)
		{
			int rowsi = eaService.insertExpenseItem(tempExpenseApps.get(i));
			
			Mog.d(TAG, "eaService.insertExpenseItem() rowsi"+rowsi);
		}
		
	}
	
	private void reEditExpenseApp(HttpServletRequest request, HttpServletResponse response)
	{
		Mog.d(TAG, "reEditExpenseApp()");
		
		String trapp_no = request.getParameter("trapp_no");
		String expense_cost = request.getParameter("expense");
		String expense_cardno = request.getParameter("card_no");
		
		
		Mog.d(TAG, "request result-ExpenseApp trapp_no:"+trapp_no);
		Mog.d(TAG, "request result-ExpenseApp expense_cost:"+expense_cost);
		Mog.d(TAG, "request result-ExpenseApp expense_cardno:"+expense_cardno);
		
		
		ExpenseApp expenseApp = new ExpenseApp();
		expenseApp.setTrapp_no(trapp_no);
		expenseApp.setExpense(Float.valueOf(expense_cost));
		expenseApp.setCard_no(expense_cardno);
		expenseApp.setState("0");
			
		eaService.reEditExpenseApp(expenseApp);
		
		try
		{
			this.reLoad(request, response);
			
		} catch (ServletException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
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
	
	
}
