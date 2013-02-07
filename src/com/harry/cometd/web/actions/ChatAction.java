package com.harry.cometd.web.actions;

import com.harry.cometd.chat.services.ExternalEventBroadcaster;
import com.harry.interceptor.UserAware;
import com.opensymphony.xwork2.ActionSupport;
import java.io.InputStream;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.IOUtils;
import org.apache.struts2.ServletActionContext;
import org.cometd.bayeux.server.BayeuxServer;
/**
 *
 * @author harrybabu
 */
public class ChatAction extends ActionSupport implements UserAware{
    
    private static final long serialVersionUID = 1L;
    
    private InputStream inputStream;
    
    private String user;
    
    private String message;

    protected static final String CHARSET="UTF-8";
    
    public ServletContext servletContext;
	
	
    public String authorize() throws Exception {
        this.setInputStream(IOUtils.toInputStream("Authorized",CHARSET));
        return SUCCESS;
    }
    
    public String chat() throws Exception {
        ServletContext sc = ServletActionContext.getServletContext();
        BayeuxServer bayeux = (BayeuxServer) sc.getAttribute("chat_service");
        ExternalEventBroadcaster broadcaster = new ExternalEventBroadcaster(bayeux);
        broadcaster.onExternalEvent(this.user+":"+ message,"/chatroom");
        this.setInputStream(IOUtils.toInputStream("Published",CHARSET));
        return SUCCESS;
    }
    
    public String logout() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        request.getSession().invalidate();
        this.setInputStream(IOUtils.toInputStream("loggedOut",CHARSET));
        return SUCCESS;
    }
    
    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getUser() {
        return this.user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setServletContext(ServletContext sc) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
