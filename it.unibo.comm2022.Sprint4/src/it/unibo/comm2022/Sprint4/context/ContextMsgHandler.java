package it.unibo.comm2022.Sprint4.context;



import it.unibo.comm2022.Sprint4.interfaces.IAppMessage;
import it.unibo.comm2022.Sprint4.interfaces.IAppMsgHandler;
import it.unibo.comm2022.Sprint4.interfaces.IContextMsgHandler;
import it.unibo.comm2022.Sprint4.interfaces.Interaction;
import it.unibo.comm2022.Sprint4.utils.AppMsgHandler;

import java.util.HashMap;
public class ContextMsgHandler extends AppMsgHandler implements IContextMsgHandler {
    protected HashMap<String, IAppMsgHandler> handlerMap = new HashMap<String,IAppMsgHandler>();


    public ContextMsgHandler(String name) {
        super(name);
    }

    @Override
    public void elaborate(IAppMessage msg, Interaction conn ) {
        System.out.println(name+" | elaborateeeeee ApplMessage:" + msg + " conn=" + conn);
        //msg( MSGID, MSGTYPE, SENDER, RECEIVER, CONTENT, SEQNUM )
        String dest  = msg.msgReceiver();
        System.out.println(name +  " | elaborate  dest="+dest);
        IAppMsgHandler h    = handlerMap.get(dest);
        System.out.println(name +  " | elaborate " + msg.msgContent() + " redirect to handler="+h.getName() + " since dest="+dest);
        if( dest != null && (! msg.isReply()) ) h.elaborate(msg,conn);
    }


    public void addComponent( String devname, IAppMsgHandler h) {
        System.out.println(name +  " | added:" + h + " to:"+devname);
        handlerMap.put(devname, h);
    }
    public void removeComponent( String devname ) {
        System.out.println(name +  " | removed:" + devname);
        handlerMap.remove( devname );
    }

    @Override
    public IAppMsgHandler getHandler(String name) {
        return handlerMap.get(name);
    }

}
