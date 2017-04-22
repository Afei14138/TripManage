package our.dxz.trip.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class ToolsUtil 
{
	
	//获取系统当前时间并处理格式为yyyyMMdd
	//生成no
	public static String getNo()
	{
		//格式化
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String nowDate = sdf.format(new Date());
		
		Random r = new Random();
		return "C"+nowDate+r.nextInt(10000);
	}
	
}
