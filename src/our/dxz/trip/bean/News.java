package our.dxz.trip.bean;

import java.util.Date;

public class News
{

	private int id;
	private String editor;
	private String title;
	private String content;
	private Date pubtime;
	private int clickcnt;
	private String imgpath;
	
	public String getImgpath()
	{
		return imgpath;
	}
	public void setImgpath(String imgpath)
	{
		this.imgpath = imgpath;
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getEditor()
	{
		return editor;
	}
	public void setEditor(String editor)
	{
		this.editor = editor;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
	}
	public Date getPubtime()
	{
		return pubtime;
	}
	public void setPubtime(Date pubtime)
	{
		this.pubtime = pubtime;
	}
	public int getClickcnt()
	{
		return clickcnt;
	}
	public void setClickcnt(int clickcnt)
	{
		this.clickcnt = clickcnt;
	}
	
	
}
