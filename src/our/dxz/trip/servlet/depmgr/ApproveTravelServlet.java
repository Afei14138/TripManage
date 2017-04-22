package our.dxz.trip.servlet.depmgr;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;


import our.dxz.trip.bean.Approve;
import our.dxz.trip.bean.TravelApp;
import our.dxz.trip.utils.Mog;

import our.dxz.trip.service.depmgr.TravelApproveService;
import our.dxz.trip.utils.Mog;


public class ApproveTravelServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	TravelApproveService taService = new TravelApproveService();
	/**
	 * Constructor of the object.
	 */
	public ApproveTravelServlet()
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
      doPost(request,response);
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
		String action = request.getParameter("action");
		
		String no = request.getParameter("no");
		Mog.d("所有的ACTION:",action);
		
		//设置编码
		if("travelAppApprove".equals(action))
		{
			String result = getTravelAppList(request,response);
			request.setAttribute("result", result);
			request.getRequestDispatcher("dep_mgr/travelApp_approve.jsp").forward(request, response);
		}
		if("agree".equals(action)){
			//调用保存
			taService.agree(no);
			correntDate(request,response,no);
			
		}
		else if("disagree".equals(action)){
			//获取申请单列表
			taService.disagree(no);
		}
		else if("refuse".equals(action))
		{
			taService.refuse(no);
		}
		if("updateApproveRf".equals(action))
		{
			updateApproveRf(request,response);
		}
		else if("updateApproveDis".equals(action))
		{
			updateApproveDis(request,response);
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
	
	public String getTravelAppList(HttpServletRequest request, HttpServletResponse response){
		
		List<TravelApp> list = taService.getTravelAppList();
		
		
		Gson gson = new Gson();
		String resultJson = gson.toJson(list);
		
		
		return resultJson;
		
		
	}
	public void updateApproveRf(HttpServletRequest request, HttpServletResponse response){

		//no需要按照规则生成，规则：CYYYYMMDD0001
		//state需要默认 0 新建状态1待审核状态 2打回状态 3审核完毕状态 
		
		//获取表单参数
		String no=request.getParameter("trapp_no");
		String name=request.getParameter("approve_depmanager");
		String reason=request.getParameter("refuse_reason_depmanager");
		Mog.d("打回trapp_no:",no);
		Mog.d("打回return_reason_depmanager:",reason);
		Mog.d("打回approve_depmanager:",name);
		
		Approve approve = new Approve();
		
		approve.setTrapp_no(no);
		approve.setApprove_depmanager(name);
		approve.setRefuse_reason_depmanager(reason);
		
		
		int row= taService.updateApproveRf(approve);
		
		if(row == 0){
			request.setAttribute("result","保存失败");
		}else{
			request.setAttribute("result","保存成功");
		}
	
		
	}
	public void updateApproveDis(HttpServletRequest request, HttpServletResponse response) {

		//no需要按照规则生成，规则：CYYYYMMDD0001
		//state需要默认 0 新建状态1待审核状态 2打回状态 3审核完毕状态 
		
		//获取表单参数

		String no=request.getParameter("trapp_no");
		String name=request.getParameter("approve_depmanager");
		String reason=request.getParameter("return_reason_depmanager");
		Mog.d("打回trapp_no:",no);
		Mog.d("打回return_reason_depmanager:",reason);
		Mog.d("打回approve_depmanager:",name);
		
		Approve approve = new Approve();
		
		approve.setTrapp_no(no);
		approve.setApprove_depmanager(name);
		approve.setReturn_reason_depmanager(reason);
		
		
		int row= taService.updateApproveDis(approve);
		
		if(row == 0){
			request.setAttribute("result","保存失败");
		}else{
			request.setAttribute("result","保存成功");
		}
	
		
	}
	public void correntDate(HttpServletRequest request, HttpServletResponse response,String no){

		//no需要按照规则生成，规则：CYYYYMMDD0001
		//state需要默认 0 新建状态1待审核状态 2打回状态 3审核完毕状态 
		
		//获取表单参数
		String num=no;
		Approve approve = new Approve();
		Date currentTime = new Date();
		String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(currentTime);
		
		approve.setApprove_date_dapmanager(Timestamp.valueOf(time));
		approve.setTrapp_no(num);
		Mog.d("通过trapp_no:",num);
		Mog.d("通过时间：",time);
		
		int row= taService.updateApprove(approve);
		
		if(row == 0){
			request.setAttribute("result","保存失败");
		}else{
			request.setAttribute("result","保存成功");
		}
	
		
	}
}
