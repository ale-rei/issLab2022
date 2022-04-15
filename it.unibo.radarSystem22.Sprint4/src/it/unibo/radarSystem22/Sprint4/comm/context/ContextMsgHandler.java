package it.unibo.radarSystem22.Sprint4.comm.context;

import it.unibo.radarSystem22.Sprint4.comm.interfaces.IAppMessage;
import it.unibo.radarSystem22.Sprint4.comm.interfaces.IAppMsgHandler;
import it.unibo.radarSystem22.Sprint4.comm.interfaces.IContextMsgHandler;
import it.unibo.radarSystem22.Sprint4.comm.interfaces.Interaction;
import it.unibo.radarSystem22.Sprint4.comm.utils.AppMsgHandler;

import java.util.HashMap;

public class ContextMsgHandler extends AppMsgHandler implements IContextMsgHandler {
    protected HashMap<String, IAppMsgHandler> handlerMap = new HashMap<>();

    public ContextMsgHandler(String name){
        super(name);
    }

    @Override
    public void addComponent(String name, IAppMsgHandler h) {
        handlerMap.put(name,h);
    }

    @Override
    public void removeComponent(String name) {
        handlerMap.remove(name);
    }

    @Override
    public IAppMsgHandler getHandler(String name) {
        return handlerMap.get(name);
    }

    @Override
    public void elaborate(IAppMessage message, Interaction conn) {
        String dest = message.msgReceiver();
        IAppMsgHandler h = handlerMap.get(dest);
        if(dest!=null && (! message.isReply()) ) h.elaborate(message,conn);
    }
}
