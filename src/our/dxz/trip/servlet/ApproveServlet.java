package our.dxz.trip.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import our.dxz.trip.service.emp.ApproveService;
import our.dxz.trip.utils.Mog;

public class ApproveServlet extends HttpServlet
{
	private static final String TAG = ApproveServlet.class.getSimpleName()+" ";
	
	private ApproveService service = new ApproveService();
	
	public ApproveServlet()
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
		String action = request.getParameter("action");
		
		Mog.d(TAG, "action:"+action);
		
		if (action.equals("approve"))
		{
			String approvesString = this.getApprovesString(request, response);
			
			request.setAttribute("result", approvesString);
			request.getRequestDispatcher("/approve.jsp").forward(request,response);
		}
		
	}
	
	public void init() throws ServletException
	{
		
	}
	
	private String getApprovesString(HttpServletRequest request, HttpServletResponse response)
	{
		String trapp_no = request.getParameter("trapp_no");
		return new Gson().toJson(service.getApproves(trapp_no));
	}

}
