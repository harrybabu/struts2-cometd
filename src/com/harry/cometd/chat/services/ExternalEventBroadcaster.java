package com.harry.cometd.chat.services;

import org.cometd.bayeux.server.BayeuxServer;
import org.cometd.bayeux.server.LocalSession;
import org.cometd.bayeux.server.ServerChannel;

/**
 *
 * @author harrybabu
 */
public class ExternalEventBroadcaster {
    
    private final BayeuxServer bayeuxServer;
    
    LocalSession session;

    public ExternalEventBroadcaster(BayeuxServer bayeuxServer)
    {
        this.bayeuxServer = bayeuxServer;

        this.session = bayeuxServer.newLocalSession("external");
        this.session.handshake();
    }

    public void onExternalEvent(String msg,String channelName)
    {
        this.bayeuxServer.createIfAbsent(channelName);
        ServerChannel channel = this.bayeuxServer.getChannel(channelName);
        if (channel != null)
        {
            channel.publish(this.session, msg, null);
        }
       
    }
  
}
