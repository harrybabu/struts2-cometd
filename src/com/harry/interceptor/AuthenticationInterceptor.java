package com.harry.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author harrybabu
 */
public class AuthenticationInterceptor implements Interceptor{
    private static final Logger log = Logger.getLogger(AuthenticationInterceptor.class);

    public void destroy() {
    }

    public void init() {
    }

    public String intercept(ActionInvocation actionInvocation) throws Exception {
        log.info("intercept ");
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();
        response.setContentType("text/html; charset=utf-8");
        String returnString = null;
        HttpSession session = request.getSession();
        Object action = (Object) actionInvocation.getAction();
        
        log.info("Got action ");
        if(action instanceof UserAware) {
            UserAware userAware = (UserAware) action;
            String existingUser = (String) session.getAttribute("user");
            String user = request.getParameter("user");
            if (user != null && user.trim().length() == 0) {
                user = null;
            }
            if (user == null && existingUser == null) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            } else if (existingUser != null && existingUser.equals(user)) {
                userAware.setUser(existingUser);
            } else if (user != null && existingUser == null) {
                session.setAttribute("user", user);
                userAware.setUser(user);
                response.setStatus(HttpServletResponse.SC_OK);
            }else{
                userAware.setUser(existingUser);
            }
        }
        returnString = actionInvocation.invoke();
        
        return returnString;
      
    }

}
