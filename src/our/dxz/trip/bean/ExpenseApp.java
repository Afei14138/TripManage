package our.dxz.trip.bean;

public class ExpenseApp
{

    private int id;
    private String trapp_no;
    private float expense;
    private String card_no;
    private String state;
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
	public String getTrapp_no()
	{
		return trapp_no;
	}
	public void setTrapp_no(String trappNo)
	{
		trapp_no = trappNo;
	}
	public float getExpense()
	{
		return expense;
	}
	public void setExpense(float expense)
	{
		this.expense = expense;
	}
	public String getCard_no()
	{
		return card_no;
	}
	public void setCard_no(String cardNo)
	{
		card_no = cardNo;
	}
	public String getState()
	{
		return state;
	}
	public void setState(String state)
	{
		this.state = state;
	}

	
	
}