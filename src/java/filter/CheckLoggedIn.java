package filter;

import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import managedbean.PersonBean;

@WebFilter(filterName = "CheckLoggedIn", urlPatterns =
{
    "/*"
})
public class CheckLoggedIn implements Filter
{

    private FilterConfig filterConfig = null;

    @Inject
    PersonBean user;

    public CheckLoggedIn()
    {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException
    {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);
        String loginURI = request.getContextPath() + "/faces/login.xhtml";
        String registerURI = request.getContextPath() + "/faces/register.xhtml";
        String assetsURI = request.getContextPath() + "/resources";
        String rootURI = request.getContextPath();

        boolean loggedIn = user.credentialsAreOK();
        boolean loginRequest = request.getRequestURI().equals(loginURI);
        boolean registerRequest = request.getRequestURI().equals(registerURI);
        boolean assetsRequest = request.getRequestURI().contains(assetsURI);
//        boolean rootRequest = request.getRequestURI().contains(rootURI);

        if (loggedIn || assetsRequest || loginRequest || registerRequest)
        {
            chain.doFilter(request, response);
        }
        else
        {
            response.sendRedirect(loginURI);
        }
    }

    @Override
    public void destroy()
    {
        filterConfig = null;
    }

    @Override
    public void init(FilterConfig filterConfig)
    {
        this.filterConfig = filterConfig;
    }

    public void log(String msg)
    {
        filterConfig.getServletContext().log(msg);
    }

}
