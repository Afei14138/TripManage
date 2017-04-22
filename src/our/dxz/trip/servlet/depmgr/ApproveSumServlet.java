package our.dxz.trip.servlet.depmgr;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import our.dxz.trip.bean.Approve;
import our.dxz.trip.bean.TravelSum;
import our.dxz.trip.dao.depmgr.ApproveTravelSumDao;
import our.dxz.trip.service.depmgr.ApproveSumService;
import our.dxz.trip.service.emp.TravelSumService;

import com.google.gson.Gson;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class ApproveSumServlet extends HttpServlet
{
	TravelSumService travelSumService=new TravelSumService();
	ApproveSumService approveSumService = new ApproveSumService();
	/**
	 * Constructor of the object.
	 */
	public ApproveSumServlet()
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
		this.doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		response.setContentType("text/html;charset=utf-8"); 
		request.setCharacterEncoding("utf-8"); 
		String action=request.getParameter("action");
		System.out.println("action:"+action);
		if("getTravelSum".equals(action))
		{
			String result = getSumList(request, response);
			System.out.println(result);
			request.setAttribute("result",result);
			request.getRequestDispatcher("./dep_mgr/approve_sum.jsp").forward(request, response); 
		}
		else if("pass".equals(action))
		{
			System.out.println("pass.equals(action)");
			//调用通过总结报告
			String trapp_no=request.getParameter("no");
			System.out.println("Trapp_no is:"+trapp_no);
			approveSumService.pass(trapp_no);			
			
		}else if("repulse".equals(action)){
			//打回总结报告
			repulse(request,response);
			
		}

		
	}
	public String getSumList(HttpServletRequest request,HttpServletResponse response){
		List<TravelSum> list = travelSumService.getSumList();
		Gson gson = new Gson();
		String resultJson = gson.toJson(list);
		return resultJson;
	}
	public void repulse(HttpServletRequest requset,HttpServletResponse response){
		int row;
		String trapp_no = (String)requset.getParameter("trapp_no");
		String return_reason_depmanager = (String)requset.getParameter("return_reason_depmanager");
		String approve_depmanager = (String)requset.getParameter("approve_depmanager");
		Approve approve = new Approve();
		approve.setTrapp_no(trapp_no);
		approve.setReturn_reason_depmanager(return_reason_depmanager);
		approve.setApprove_manager(approve_depmanager);
		row = approveSumService.repulse(approve);
	
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

}
