package it.unibo.comm2022.Sprint4.context;

import it.unibo.comm2022.Sprint4.interfaces.IApplMsgHandler;
import it.unibo.comm2022.Sprint4.interfaces.IContext;
import it.unibo.comm2022.Sprint4.interfaces.IContextMsgHandler;
import it.unibo.comm2022.Sprint4.tcp.TcpServer;

/*
 * Decorator
 * TcpContextServer � un singleton che si crea un proprio gestore di messaggi di tipo ContextMsgHandler
 * E' un decorator di TcpServer che offre i metodi addComponent/removeComponent che delega al ContextMsgHandler
 * Il ContextMsgHandler gestisce messaggi della forma 'estesa':
 *   msg( MSGID, MSGTYPE, SENDER, RECEIVER, CONTENT, SEQNUM ) e 
 *  ridirige al RECEIVER il CONTENT con la connessione
 *  il RECEIVER elabora il messaggio e invia un msg di risposta sulla connessione 
 *  per i messaggi che costituiscono richieste
 *  
 */
public class TcpContextServer extends TcpServer implements IContext {
	private IContextMsgHandler ctxMsgHandler;
	public TcpContextServer(String name, String portStr ) {  
 		this( name,Integer.parseInt(portStr) );
	}

	public TcpContextServer(String name, int port ) {  
		super(name, port,  new ContextMsgHandler("ctxH"));
		this.ctxMsgHandler = (ContextMsgHandler) userDefHandler;  //Inherited
 	}
	
	public void addComponent( String devname, IApplMsgHandler h) {
		ctxMsgHandler.addComponent(devname, h);
	}
	public void removeComponent( String devname ) {
		ctxMsgHandler.removeComponent( devname );
	}
 
}
