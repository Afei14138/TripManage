package our.dxz.trip.dao.emp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import our.dxz.trip.bean.Approve;
import our.dxz.trip.bean.TravelApp;
import our.dxz.trip.db.DBConn;

public class ApproveDao extends DBConn
{
	public List<Approve> getApproves(String sql)
	{
		List<Approve> list = new ArrayList<Approve>(); 
		try {
			pstm = super.getConnection().prepareStatement(sql);// 创建预编译的SQL语句对象
			rs = pstm.executeQuery();
			while (rs.next()) 
			{
				
				/*private int id;
				private String trapp_no;
				private String approve_depmanager;
				private Date approve_date_dapmanager;
				private String refuse_reason_depmanager;
			 	private String return_reason_depmanager;
				private String approve_costcontroller;
				private Date approve_date_costcontroller;
				private String refuse_reson_costcontroller;
				private String return_reason_costcontroller;
				private String approve_manager;
				private Date approve_date_manager;
				private String refuse_reason_manager;
				private String return_reason_manager;*/
				
				Approve approve = new Approve();
				
				approve.setId(rs.getInt("id"));
				approve.setTrapp_no(rs.getString("trapp_no"));
				approve.setApprove_depmanager(rs.getString("approve_depmanager"));
				approve.setApprove_date_dapmanager(rs.getDate("approve_date_dapmanager"));
				approve.setRefuse_reason_depmanager(rs.getString("refuse_reason_depmanager"));
				approve.setReturn_reason_depmanager(rs.getString("return_reason_depmanager"));
				approve.setApprove_costcontroller(rs.getString("approve_costcontroller"));
				approve.setApprove_date_costcontroller(rs.getDate("approve_date_costcontroller"));
				approve.setRefuse_reson_costcontroller(rs.getString("refuse_reson_costcontroller"));
				approve.setReturn_reason_costcontroller(rs.getString("return_reason_costcontroller"));
				approve.setApprove_manager(rs.getString("approve_manager"));
				approve.setApprove_date_manager(rs.getDate("approve_date_manager"));
				approve.setRefuse_reason_manager(rs.getString("refuse_reason_manager"));
				approve.setReturn_reason_manager(rs.getString("return_reason_manager"));
				
				list.add(approve);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			super.closeAll(); 
		}
		
		return list;
	}
}
