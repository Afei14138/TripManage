package our.dxz.trip.servlet.fin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import our.dxz.trip.bean.BorrowApp;
import our.dxz.trip.bean.TravelApp;

import our.dxz.trip.service.fin.AllBorrowService;

import our.dxz.trip.utils.Mog;
import our.dxz.trip.utils.ToolsUtil;


public class AllBorrowServlet extends HttpServlet
{
	AllBorrowService taService = new AllBorrowService();
	ToolsUtil toolsUtil = new ToolsUtil();
	/**
	 * Constructor of the object.
	 */
	public AllBorrowServlet()
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
	    Mog.d("财务查看的借款申请",action );
		if("getBorrowAppList".equals(action)){
			String result = getBorrowAppList(request,response);
			request.setAttribute("result", result);
			request.getRequestDispatcher("/fin/borrow_list.jsp").forward(request, response);
		}
	}

	public void init() throws ServletException
	{
	}
		public String getBorrowAppList(HttpServletRequest request, HttpServletResponse response){//获取申请单列表
	    
		List<BorrowApp> list = taService.getBorrowAppList();
		System.out.println("list length:"+list.size());
		Gson gson = new Gson();
		String resultJson = gson.toJson(list);
		
		return resultJson;//easyui接收一个resultJson
	}
		
}
