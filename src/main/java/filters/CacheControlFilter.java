package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

/**
 * 캐시 컨트롤 필터
 * @author 임주연
 * @since 2024-04-15
 */
@WebFilter(description ="캐시 리셋 구현", urlPatterns = { "/*" })
public class CacheControlFilter implements Filter {
	private static final long serialVersionUID = 1L;

	/**
	 * 초기화 작업
	 */
	public void init(ServletConfig config) throws ServletException {
	}

	/**
	 * @see Servlet#destroy()
	 */
	@Override
	public void destroy() {
	}

	// 실제 필터 실행
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		// Cache-Control 헤더 설정(캐시 삭제)
		httpResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		httpResponse.setHeader("Pragma", "no-cache");
		httpResponse.setDateHeader("Expires", 0);
		
		// 요청에 따른 필터 시작 (처리하기 직전)
		long begin = System.currentTimeMillis();
		/*****/
		
		// 필터 처리
		chain.doFilter(request, response);
		
		/*****/
		// 요청에 따른 필터 시작 (처리한 후)
		long end = System.currentTimeMillis();
		System.out.println("필터 작업 시간: " + (end - begin));
		
	}

}
