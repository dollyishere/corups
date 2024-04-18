package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter(description ="한글 인코딩 기능을 구현", urlPatterns = { "/*" })
public class EncoderFilter implements Filter { // 잘못 import하면 오류남
	ServletContext context; // 서블릿
	
	public EncoderFilter() {
		System.out.println("EncoderFilter() 생성자 호출됨~");
	}
	
	/** 필터가 수행될 때 자동 호출되는 초기 메서드 **/
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("utf-8 인코딩 준비..");
		context = fConfig.getServletContext();
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 여기서 실제 필터링~
		System.out.println("필터링을 하기 위해 doFilter()가 자동 호출됨");
		
		// 요청 받았을 때
		// 꺼낼 때는 반드시 세팅 필요함
		request.setCharacterEncoding("UTF-8");
		
		// 경로 확인용 코드
		// 애플리케이션의 컨텍스트 이름
		// request가 ServletRequest 속성이기 때문에 request만 HttpServletRequest 타입으로 슬쩍 바꿔보기
		// 매우 주의할 것
		String context = ((HttpServletRequest) request).getContextPath();
		String pathInfo = ((HttpServletRequest) request).getRequestURI();
		
		String msg = "Context 정보 : " + context + "\n URI 정보 : " + pathInfo;
		System.out.println(msg);
		
		// 요청에 따른 필터 시작 (처리하기 직전)
		long begin = System.currentTimeMillis();
		/*****/
		
		chain.doFilter(request, response);
		// 구현받은 doFilter 메서드는, 어떤 요청이 들어왔을 때 자동으로 호출되는 메서드임
		// doFilter의 세번째 파라미터는 FilterChain인데, 얘가 가지고 있는 doFilter 메서드가 또 있음
		// 따라서 위의 doFilter는 우리가 재정의한 doFilter가 아니라 chain 자체의 doFilter임
		
		/*****/
		// 요청에 따른 필터 시작 (처리한 후)
		long end = System.currentTimeMillis();
		System.out.println("필터 작업 시간: " + (end - begin));
	}
	
	/** encoding 소멸 **/
	public void destroy() {
		System.out.println("encoding 소멸됨");
	}
	
	
}
