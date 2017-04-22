package our.dxz.trip.service.emp;

import java.util.List;

import com.sun.org.apache.bcel.internal.generic.RETURN;

import our.dxz.trip.bean.Approve;
import our.dxz.trip.dao.emp.ApproveDao;

public class ApproveService
{
	private ApproveDao dao = new ApproveDao();
	
	public List<Approve> getApproves(String trapp_no)
	{
		String sql = "select * from approve where trapp_no = \'"+trapp_no+"\'";
		
		return dao.getApproves(sql);
	}
	
}
