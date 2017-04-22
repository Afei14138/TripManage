package our.dxz.trip.servlet.emp;

import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import our.dxz.trip.bean.TravelApp;
import our.dxz.trip.bean.Approve;

import our.dxz.trip.service.emp.TravelAppService;
import our.dxz.trip.utils.Mog;
import our.dxz.trip.utils.ToolsUtil;

import com.google.gson.Gson;

public class TravelAppServlet extends HttpServlet
{
	private static final String TAG = TravelAppServlet.class.getSimpleName();
	
	private static final long serialVersionUID = 1L;

	private static final String newTimeString = null;

	TravelAppService taService = new TravelAppService();
	//TravelAppService taService1 = new TravelAppService();
	ToolsUtil toolsUtil = new ToolsUtil();
	/**
	 * Constructor of the object.
	 */
	public TravelAppServlet()
	{
		super();
		Mog.d(TAG, "construct");
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy()
	{
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}


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
		Mog.d(TAG, "action:"+action);
		
		//设置编码
	
		if("saveApp".equals(action)){
			//调用保存
			//System.out.println(action);
			saveTravelApp(request,response);
			
		}
		else if("updateApp".equals(action))
		{
			updateTravelApp(request,response);
		}
		else if("getTravelAppList".equals(action)){
			//获取申请单列表
			System.out.println("保存成功！");
			String result = getTravelAppList(request,response);
			request.setAttribute("result", result);
			request.getRequestDispatcher("emp/travel_list.jsp").forward(request, response);
		}
		


	}

	

	public void init() throws ServletException{
	}

	public void saveTravelApp(HttpServletRequest request, HttpServletResponse response){

		//no需要按照规则生成，规则：CYYYYMMDD0001
		//state需要默认 0 新建状态1待审核状态 2打回状态 3审核完毕状态 
		
		//获取表单参数
		
		String dept=request.getParameter("dept");
		String name=request.getParameter("name");
		String followName=request.getParameter("follow_name");
		String borrowMoney=request.getParameter("borrow_money");
		String costType=request.getParameter("cost_type");
		String travelReason=request.getParameter("travel_reason");
		String travelPlace=request.getParameter("travel_place");
	    String planTime=request.getParameter("plan_time");
		String leave_time=request.getParameter("leave_time");
		String back_time=request.getParameter("back_time");
		String travelPlan=request.getParameter("travel_plan");
		Mog.d("TR:","name"+name);
		
		Mog.d("TR:","leave_time"+leave_time);
		Mog.d("TR:","back_time"+back_time);
		
		String no = toolsUtil.getNo();
		
		TravelApp travelApp = new TravelApp();
		Approve approve =new Approve();
		approve.setTrapp_no(no);
		travelApp.setNo(no);
		travelApp.setDept(dept);
		travelApp.setName(name);
		travelApp.setFollow_name(followName);
		travelApp.setBorrow_money(borrowMoney);
		travelApp.setCost_type(costType);
		travelApp.setTravel_reason(travelReason);
		travelApp.setTravel_place(travelPlace);
		travelApp.setPlan_time(planTime);
		
		SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd");
		SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
	
		if(!back_time.equals("") && !back_time.equals(leave_time))
		{
			try {
				
				Date backTime = formatter.parse(back_time);
				String newTimeString =  formatter1.format(backTime);
				Mog.d("TRavelAppServlet:","newTimeString backTime:"+newTimeString );
				travelApp.setLeave_time(Timestamp.valueOf(newTimeString+" 00:00:00"));
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}else
		{
			
		}
		
		if(!leave_time.equals("") && !leave_time.equals(back_time))
		{
			try {
				Date lTime =  formatter.parse(leave_time);
				String newTimeString =  formatter1.format(lTime);
				Mog.d("TRavelAppServlet:","newTimeString lTime:"+newTimeString );
				travelApp.setBack_time(Timestamp.valueOf(newTimeString+" 00:00:00"));
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}else
		{
			
		}
		
		
		Date currentTime = new Date();
		String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(currentTime);
		
		travelApp.setCreate_time(Timestamp.valueOf(time));
		travelApp.setTravel_plan(travelPlan);
		travelApp.setState("0");
		
		int row= taService.saveTravelApp(travelApp);
		int line=  taService.saveApprove(approve);
		
		if(row != 0&&line != 0)
		{
			request.setAttribute("result","保存成功");
			try
			{
				request.getRequestDispatcher("emp/travel_application.jsp").forward(request, response);
			} catch (ServletException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else
		{
			request.setAttribute("result","保存失败");
			try
			{
				request.getRequestDispatcher("emp/travel_application.jsp").forward(request, response);
			} catch (ServletException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		
	}
	public void updateTravelApp(HttpServletRequest request, HttpServletResponse response){

		//no需要按照规则生成，规则：CYYYYMMDD0001
		//state需要默认 0 新建状态1待审核状态 2打回状态 3审核完毕状态 
		
		//获取表单参数
		String no=request.getParameter("no");
		
		Mog.d(TAG, "no:"+no);
		
		String dept=request.getParameter("dept");
		String name=request.getParameter("name");
		String followName=request.getParameter("follow_name");
		String borrowMoney=request.getParameter("borrow_money");
		String costType=request.getParameter("cost_type");
		String travelReason=request.getParameter("travel_reason");
		String travelPlace=request.getParameter("travel_place");
	    String planTime=request.getParameter("plan_time");
		String leave_time=request.getParameter("leave_time");
		String back_time=request.getParameter("back_time");
		String travelPlan=request.getParameter("travel_plan");
		Mog.d("TR:","name"+name);
		
		Mog.d("TR:","leave_time"+leave_time);
		Mog.d("TR:","back_time"+back_time);
		
		
		
		TravelApp travelApp = new TravelApp();
		
		travelApp.setNo(no);
		travelApp.setDept(dept);
		travelApp.setName(name);
		travelApp.setFollow_name(followName);
		travelApp.setBorrow_money(borrowMoney);
		travelApp.setCost_type(costType);
		travelApp.setTravel_reason(travelReason);
		travelApp.setTravel_place(travelPlace);
		travelApp.setPlan_time(planTime);
		
		SimpleDateFormat formatter = new SimpleDateFormat( "MM/dd/yyyy");
		SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
	
		if(!back_time.equals("") && !back_time.equals(leave_time))
		{
			try {
				
				Date backTime = formatter.parse(back_time);
				String newTimeString =  formatter1.format(backTime);
				Mog.d("TRavelAppServlet:","newTimeString backTime:"+newTimeString );
				travelApp.setLeave_time(Timestamp.valueOf(newTimeString+" 00:00:00"));
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}else
		{
			
		}
		
		if(!leave_time.equals("") && !leave_time.equals(back_time))
		{
			try {
				Date lTime =  formatter.parse(leave_time);
				String newTimeString =  formatter1.format(lTime);
				Mog.d("TRavelAppServlet:","newTimeString lTime:"+newTimeString );
				travelApp.setBack_time(Timestamp.valueOf(newTimeString+" 00:00:00"));
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}else
		{
			
		}
		
		
		Date currentTime = new Date();
		String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(currentTime);
		
		travelApp.setCreate_time(Timestamp.valueOf(time));
		travelApp.setTravel_plan(travelPlan);
		travelApp.setState("0");
		
		int row= taService.updateTravelApp(travelApp);
		
		if(row == 0){
			request.setAttribute("result","保存失败");
		}else{
			request.setAttribute("result","保存成功");
		}
		System.out.println(travelApp);
		
	}
	public String getTravelAppList(HttpServletRequest request, HttpServletResponse response){
		
		List<TravelApp> list = taService.getTravelAppList();
		
		
		Gson gson = new Gson();
		String resultJson = gson.toJson(list);
		
		
		return resultJson;
		
		
	}

}
