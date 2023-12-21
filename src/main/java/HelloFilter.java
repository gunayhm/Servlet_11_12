import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/first")
public class HelloFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response= (HttpServletResponse) servletResponse;

        String header=request.getHeader("Cool-Header");

        if(header.equals("bad-header")){
            RequestDispatcher requestDispatcher= request.getRequestDispatcher("/bye");
            requestDispatcher.forward(servletRequest, servletResponse);
        }

        response.setHeader("Cool-Header", header+ "_Approved");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}






//@Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        Filter.super.init(filterConfig);
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
//                         FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest request= (HttpServletRequest) servletRequest;
//        HttpServletResponse response= (HttpServletResponse) servletResponse;
//
//        String header=request.getHeader("Cool-Header");
//        if(header.equals("bad-header")){
//            throw new IllegalArgumentException();
//        }
//        response.setHeader("Cool-Header", header+ "_Approved");
//        filterChain.doFilter(servletRequest, servletResponse);
//    }
//
//    @Override
//    public void destroy() {
//        Filter.super.destroy();
//    }