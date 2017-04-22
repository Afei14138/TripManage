package our.dxz.trip.dao.admin;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import our.dxz.trip.bean.News;
import our.dxz.trip.bean.User;
import our.dxz.trip.db.DBConn;

public class NewsDao extends DBConn
{
	public int addNews(String sql,Object[] params)
	{
		int efr = 0;
		
		try
		{
			efr = super.executeSQL(sql, params);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return efr;
	}
	
	public List<News> getNews(String sql)
	{
		List<News> newslist = new ArrayList<News>();
		
		try
		{
			pstm = super.getConnection().prepareStatement(sql);
			rs = pstm.executeQuery();
			
//			private int id;
//			private String editor;
//			private String title;
//			private String content;
//			private Date pubtime;
//			private int clickcnt;
//			private String imgpath;
			
			while (rs.next()) 
			{
				News news = new News();
				news.setId(rs.getInt("id"));
				news.setEditor(rs.getString("editor"));
				news.setTitle(rs.getString("title"));
				news.setContent(rs.getString("content"));
				news.setPubtime(rs.getDate("pubtime"));
				news.setClickcnt(rs.getInt("clickcnt"));
				news.setImgpath(rs.getString("imgpath"));
				
				newslist.add(news);
			}
			
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return newslist;
	}
}
