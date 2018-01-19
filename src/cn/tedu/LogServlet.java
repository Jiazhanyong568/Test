package cn.tedu;



import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;




public class LogServlet extends HttpServlet {

	private Logger logger = Logger.getLogger(LogServlet.class);
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			//处理中文，做url的解析
		String result = URLDecoder.decode(request.getQueryString(),"utf-8");
		String[] info = result.split("\\&");
		StringBuffer buffer = new StringBuffer();
		for(String kv:info){
			//位防止下标越界，比如来源页面的value有时可能为空，所以不能直接取下标为1
			String value=kv.split("=").length==2?kv.split("=")[1]:"";
			buffer.append(value+"|");
		}
		buffer.append(request.getRemoteAddr());
		System.out.println(buffer.toString());
		logger.info(buffer.toString());
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
