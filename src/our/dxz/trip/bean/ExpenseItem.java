package our.dxz.trip.bean;

public class ExpenseItem
{
    private int id;
    private String trapp_no;
    private String cost;
    private String type;
    private String desc;
	
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
	public String getCost()
	{
		return cost;
	}
	public void setCost(String cost)
	{
		this.cost = cost;
	}
	public String getType()
	{
		return type;
	}
	public void setType(String type)
	{
		this.type = type;
	}
	public String getDesc()
	{
		return desc;
	}
	public void setDesc(String desc)
	{
		this.desc = desc;
	}

	
	
}