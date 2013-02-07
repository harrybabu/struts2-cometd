package com.harry.cometd.chat.services;

import java.io.IOException;
import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.cometd.bayeux.server.BayeuxServer;

/**
 *
 * @author harrybabu
 */
public class ConfigurationServlet extends GenericServlet{
    @Override
    public void init() throws ServletException
    {
        BayeuxServer bayeux = (BayeuxServer)getServletContext().getAttribute(BayeuxServer.ATTRIBUTE);

        getServletContext().setAttribute("chat_service", bayeux);
        ChatService chatService = new ChatService(bayeux);
      }

    @Override
    public void service(ServletRequest sr, ServletResponse sr1) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
