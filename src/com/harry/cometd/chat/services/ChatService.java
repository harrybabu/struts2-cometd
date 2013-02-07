package com.harry.cometd.chat.services;

import javax.annotation.PostConstruct;
import org.cometd.bayeux.server.*;
import javax.inject.Inject;
import org.cometd.bayeux.ChannelId;
import org.cometd.java.annotation.Configure;
import org.cometd.java.annotation.Listener; 
import org.cometd.server.AbstractService;
import org.cometd.server.authorizer.GrantAuthorizer;

/**
 *
 * @author harrybabu
 */

public final class ChatService extends AbstractService{
    
    @Inject
    BayeuxServer bayeuxServer;
    public ChatService(BayeuxServer bayeux){
        super(bayeux,null);
        bayeuxServer = bayeux;
    }
    
}
