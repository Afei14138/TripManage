package our.dxz.trip.servlet.admin;

import java.util.List;

import our.dxz.trip.bean.News;
import our.dxz.trip.service.admin.NewsService;

public class NewsHandler
{
	private NewsService service = new NewsService();
	
	public List<News> getNewsList()
	{
		return service.getNews();
	}
	
}
