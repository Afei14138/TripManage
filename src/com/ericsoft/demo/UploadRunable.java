package com.ericsoft.demo;

import java.util.List;

import com.ericsoft.bmob.restapi.Bmob;
import com.sun.xml.internal.ws.developer.StatefulWebServiceManager.Callback;

public class UploadRunable implements Runnable
{

	private String path;
	private UploadCallback callback;
	
	public UploadRunable(String path,UploadCallback callback)
	{
		this.path = path;
		this.callback = callback;
	}
	
	public void run()
	{
		upload(path);
	}

	private static void upload(String ph)
	{
		String res=Bmob.uploadFile2(ph);
		
	}
	
	public interface UploadCallback
	{
		public void onSuccess(String url);
		public void onFailure(String why);
	}
	
}
