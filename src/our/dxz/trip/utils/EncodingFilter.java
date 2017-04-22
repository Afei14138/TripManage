/**
 * Copyright: Copyright (c) 2004 Handson
 * Title: EncodingFilter.java
 * @author HandsOn
 */
package our.dxz.trip.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EncodingFilter implements Filter {
	// 定义默认字符集
	private String encoding = "utf-8";

	public void init(FilterConfig filterConfig) throws ServletException 
	{
		// 从web.xml文件中读取字符集;
		encoding = filterConfig.getInitParameter("encoding");
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		// 设置请求的字符集
		httpRequest.setCharacterEncoding(encoding);
		// 设置响应的字符集
		httpResponse.setCharacterEncoding(encoding);
		chain.doFilter(httpRequest, httpResponse);
	}

	public void destroy() {

	}

}
