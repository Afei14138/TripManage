package our.dxz.trip.servlet.emp;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;


import our.dxz.trip.bean.BorrowApp;
import our.dxz.trip.bean.TravelApp;
import our.dxz.trip.service.emp.BorrowAppService;
//import our.dxz.trip.bean.TravelApp;
//import our.dxz.trip.service.emp.TravelAppService;
import our.dxz.trip.utils.ToolsUtil;



public class BorrowAppServlet extends HttpServlet
{
	BorrowAppService taService = new BorrowAppService();
	ToolsUtil toolsUtil = new ToolsUtil();
	
	public BorrowAppServlet()
	{
		super();
	}

	public void destroy()
	{
		super.destroy(); // Just puts "destroy" string in log
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
		request.setCharacterEncoding("utf-8"); 
		String action = request.getParameter("action");
		if("saveApp".equals(action)){
			saveBorrowApp(request,response);//进行保存
		}
		else if("getBorrowAppList".equals(action)){
			String result = getBorrowAppList(request,response);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/emp/borrow_list.jsp").forward(request, response);
		}
		else if(action.equals("addBorrowApp"))
		{
			//String result = getBorrowAppList(request,response);
			String result = getTravelAppList(request,response);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/emp/borrow_application.jsp").forward(request, response);
		}
		else if(action.equals("updateApp"))
		{
			updateBorrowApp(request,response);	
		}
			
	}

	public void init() throws ServletException
	{
	}
	public void saveBorrowApp(HttpServletRequest request, HttpServletResponse response){//保存申请表

		//no需要按照规则生成，规则：CYYYYMMDD0001
		//state需要默认 0 新建状态1待审核状态 2打回状态 3审核完毕状态 
		
		//获取表单参数
		String no =request.getParameter("trapp_no");
		//System.out.println("no："+no);
		String Cost=request.getParameter("cost");
		float cost=new Float(Cost);
		//System.out.println("cost："+cost);	
		//String reason=request.getParameter("reason");//获取填写的参数
		String reason = request.getParameter("reason");
		//System.out.println("rease："+reason);
			
		//生成no
		//String no = toolsUtil.getNo();
		//System.out.println(no);
		
		BorrowApp borrowApp = new BorrowApp();
		
		borrowApp.setTrapp_no(no);//借款申请编号
		borrowApp.setCost(cost);//借款费用
		borrowApp.setReason(reason);
		borrowApp.setState("0");
		int row= taService.saveBorrowApp(borrowApp);//只传实体类即可
		
		if(row == 0){
			request.setAttribute("result","保存失败");
		}else{
			request.setAttribute("result","保存成功");
		}
	}
	public void updateBorrowApp(HttpServletRequest request, HttpServletResponse response){//保存申请表

		//no需要按照规则生成，规则：CYYYYMMDD0001
		//state需要默认 0 新建状态1待审核状态 2打回状态 3审核完毕状态 
		
		//获取表单参数
		String no =request.getParameter("trapp_no");
		//System.out.println("no："+no);
		String Cost=request.getParameter("cost");
		float cost=new Float(Cost);
		//System.out.println("cost："+cost);	
		//String reason=request.getParameter("reason");//获取填写的参数
		String reason = request.getParameter("reason");
	    //	System.out.println("rease："+reason);
			
		//生成no
		//String no = toolsUtil.getNo();
		//System.out.println(no);
		
		BorrowApp borrowApp = new BorrowApp();
		
		borrowApp.setTrapp_no(no);//借款申请编号
		borrowApp.setCost(cost);//借款费用
		borrowApp.setReason(reason);
		borrowApp.setState("0");
		int row= taService.updateBorrowApp(borrowApp);//只传实体类即可
		
		if(row == 0){
			request.setAttribute("result","保存失败");
		}else{
			request.setAttribute("result","保存成功");
		}
	}
		public String getBorrowAppList(HttpServletRequest request, HttpServletResponse response){//获取申请单列表
	    
		List<BorrowApp> list = taService.getBorrowAppList();
		System.out.println("list length:"+list.size());
		Gson gson = new Gson();
		String resultJson = gson.toJson(list);
		return resultJson;//easyui接收一个resultJson
	}
		public String getTravelAppList(HttpServletRequest request, HttpServletResponse response){
			
			List<TravelApp> list = taService.getTravelAppList();
			//List<TravelApp> listp = poService.getTravelAppList();
			
			
			Gson gson = new Gson();
			String resultJson = gson.toJson(list);
			//String resultJsonpo = gson.toJson(listp);
			
			return resultJson;
			
			
		}
}
