package our.dxz.trip.servlet.mgr;

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
import our.dxz.trip.service.mgr.MApproveBorrowService;

public class MApproveBorrowServlet extends HttpServlet
{private static final long serialVersionUID = 1L;
MApproveBorrowService taService = new MApproveBorrowService();

	/**
	 * Constructor of the object.
	 */
	public MApproveBorrowServlet()
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

	     this.doPost(request,response);
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
	      System.out.println("action"+action);
         String trapp_no=request.getParameter("trapp_no");
          if("MApproveBorrow".equals(action))
  		   {//获取申请单列表
  			String result = ApproveBorrow(request,response);
  			request.setAttribute("result", result);
  			request.getRequestDispatcher("/mgr/mborrow_approve.jsp").forward(request, response);
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
		  String approve_date_manager = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(currentTime);
		  //System.out.println("trapp_no"+trapp_no);
		  //System.out.println("approve_date_depmanager"+approve_date_dapmanager);
		  approve.setTrapp_no(trapp_no);
		  approve.setApprove_date_manager(Timestamp.valueOf(approve_date_manager));
		
		//获取表单参数
	
		 taService.agreeBorrowApp(approve);//只传实体类即可
	
	}
	public void disagreeBorrowApp(HttpServletRequest request, HttpServletResponse response){//保存申请表

		Approve approve=new Approve();
		
      String trapp_no = request.getParameter("trapp_no");
      String approve_manager=request.getParameter("approve_manager");
      String refuse_reason_manager=request.getParameter("refuse_reason_manager");
      
      approve.setApprove_manager(approve_manager);
      approve.setTrapp_no(trapp_no);
      approve.setRefuse_reason_manager(refuse_reason_manager);
      
    
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



