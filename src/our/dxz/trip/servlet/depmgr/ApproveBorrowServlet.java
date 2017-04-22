package our.dxz.trip.servlet.depmgr;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import our.dxz.trip.bean.Approve;
import our.dxz.trip.bean.BorrowApp;
import our.dxz.trip.service.depmgr.ApproveBorrowService;

public class ApproveBorrowServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	ApproveBorrowService taService = new ApproveBorrowService();

	/**
	 * Constructor of the object.
	 */
	public ApproveBorrowServlet()
	{
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy()
	{
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{

	        doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		
		      response.setContentType("text/html;charset=utf-8"); 
		      request.setCharacterEncoding("utf-8"); 
		      String action=request.getParameter("action");
              String trapp_no=request.getParameter("trapp_no");
               if("ApproveBorrow".equals(action))
       		   {//获取申请单列表
       			String result = ApproveBorrow(request,response);
       			request.setAttribute("result", result);
       			request.getRequestDispatcher("/dep_mgr/borrow_approve.jsp").forward(request, response);
       		   }else  if("agree".equals(action)){
       
       			agreeBorrowApp(request,response);	
       			
       			}else if("updateApp".equals(action))
               	{
       			
            	   System.out.println("update.equals(action)");
       				disagreeBorrowApp(request,response);
       			}
		
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException
	{
		// Put your code here
	}
	public void agreeBorrowApp(HttpServletRequest request, HttpServletResponse response){//保存申请表

		  Approve approve=new Approve();
          String trapp_no = request.getParameter("trapp_no");
          Date currentTime = new Date();
  		  String approve_date_dapmanager = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(currentTime);
  		  //System.out.println("trapp_no"+trapp_no);
  		  //System.out.println("approve_date_depmanager"+approve_date_dapmanager);
  		  approve.setTrapp_no(trapp_no);
  		  approve.setApprove_date_dapmanager(Timestamp.valueOf(approve_date_dapmanager));
		
		//获取表单参数
	
		 taService.agreeBorrowApp(approve);//只传实体类即可
	
	}
	public void disagreeBorrowApp(HttpServletRequest request, HttpServletResponse response){//保存申请表

		Approve approve=new Approve();
		
        String trapp_no = request.getParameter("trapp_no");
        String approve_depmanager=request.getParameter("approve_depmanager");
        String return_reason_depmanager=request.getParameter("return_reason_depmanager");
        
        approve.setApprove_depmanager(approve_depmanager);
        approve.setTrapp_no(trapp_no);
        approve.setReturn_reason_depmanager(return_reason_depmanager);
        
      
		//获取表单参数
	
		 taService.disagreeBorrowApp(approve);//只传实体类即可
		
	}
    public String ApproveBorrow(HttpServletRequest request, HttpServletResponse response){//获取申请单列表
	    
		List<BorrowApp> list = taService.getBorrowAppList();
		Gson gson = new Gson();
		String resultJson = gson.toJson(list);
		return resultJson;//easyui接收一个resultJson
	}

}
