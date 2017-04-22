package our.dxz.trip.servlet.emp;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import our.dxz.trip.bean.TravelApp;
import our.dxz.trip.bean.TravelSum;
import our.dxz.trip.service.emp.TravelSumService;
import com.google.gson.Gson;
public class TravelSumServlet extends HttpServlet
{
	TravelSumService travelSumService = new TravelSumService();

	public TravelSumServlet()
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
		request.setCharacterEncoding("utf-8"); 
		String action=request.getParameter("action");
		System.out.println("action:"+action);
		if("submitSum".equals(action)){
			//调用提交表单
			saveSumList(request, response);
			
		}else if("getTravelSum".equals(action)){
			//调用得到表单
			String result = getSumList(request, response);
			System.out.println(result);
			request.setAttribute("result",result);
			request.getRequestDispatcher("./emp/Sum_list.jsp").forward(request, response); 
		}else if(action.equals("writeSum")){
			String result = getTrappNo(request, response);
			System.out.println("result is:"+result);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/emp/WriteSum.jsp").forward(request, response);
		}else if(action.equals("edit")){
			updateSumList(request, response);
			
		}
		



	}
	//控制获取出差总结表
	public String getSumList(HttpServletRequest request,HttpServletResponse response){
		List<TravelSum> list = travelSumService.getSumList();
		Gson gson = new Gson();
		String resultJson = gson.toJson(list);
		return resultJson;
	}
	//从出差申请表里获取出差单号
	public String getTrappNo(HttpServletRequest request,HttpServletResponse response){
		List<TravelApp> list = travelSumService.getTrappNo();
		Gson gson = new Gson();
		String resultJson = gson.toJson(list);
		return resultJson;	
		
	}
	public void saveSumList(HttpServletRequest request,HttpServletResponse response){
		int row;
		String title = (String)request.getParameter("title");
		String no = (String)request.getParameter("no");
		String name = (String)request.getParameter("name");
		String content = (String)request.getParameter("content");
		System.out.println(title+' '+no+' '+name+' '+content);
		TravelSum travelSum = new TravelSum();
		travelSum.setTitle(title);
		travelSum.setTrapp_no(no);
		travelSum.setName(name);
		travelSum.setContent(content);
		travelSum.setState("0");
		row= travelSumService.saveSums(travelSum);
		if(row == 0){
			request.setAttribute("result","保存失败");
		}else{
			request.setAttribute("result","保存成功");
		}
		
		
		
	}
	public void updateSumList(HttpServletRequest request,HttpServletResponse response){
		int row;
		String trapp_no = (String)request.getParameter("trapp_no");
		String title = (String)request.getParameter("title");
		String content = (String)request.getParameter("content");
		String name = (String)request.getParameter("name");
		TravelSum travelSum = new TravelSum();
		travelSum.setTrapp_no(trapp_no);
		travelSum.setTitle(title);
		travelSum.setContent(content);
		travelSum.setName(name);
		row=travelSumService.updateSum(travelSum);
		if(row==0){
			request.setAttribute("result", "更新失败");
			
		}else{
			request.setAttribute("result", "更新成功");
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

}
