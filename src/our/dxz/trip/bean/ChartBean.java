package our.dxz.trip.bean;

public class ChartBean
{
	private String name;
	private int[] data = new int[12];
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public int[] getData()
	{
		return data;
	}
	public void setData(int[] data)
	{
		this.data = data;
	}
	
	
	
}
