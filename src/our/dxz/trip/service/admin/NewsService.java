package our.dxz.trip.service.admin;

import java.util.List;

import our.dxz.trip.bean.News;
import our.dxz.trip.dao.admin.NewsDao;

public class NewsService
{
	private NewsDao dao = new NewsDao();
	
	public int addNews(News news)
	{
		String sql = "insert into news(editor,title,content,pubtime,imgpath) values(?,?,?,?,?)";
		
		Object[] params = 
		{
				news.getEditor(),
				news.getTitle(),
				news.getContent(),
				news.getPubtime(),
				news.getImgpath()
		};
		
		return dao.addNews(sql, params);
		
	}
	
	public List<News> getNews()
	{
		String sql = "select * from news order by pubtime desc";
		return dao.getNews(sql);
	}
}
