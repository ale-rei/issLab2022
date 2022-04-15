package it.unibo.radarSystem22.Sprint4.comm.proxy;

import it.unibo.radarSystem22.Sprint4.comm.interfaces.Interaction;
import it.unibo.radarSystem22.Sprint4.comm.tcp.TcpClientSupport;
import it.unibo.radarSystem22.Sprint4.comm.utils.ProtocolType;

public class ProxyAsClient {
    private Interaction conn;
    protected String name;
    protected ProtocolType protocol;

    public ProxyAsClient (String name, String host, String entry, ProtocolType protocol) {
        this.name=name;
        this.protocol=protocol;
        setConnection(host,entry,protocol);
    }

    private void setConnection(String host, String entry, ProtocolType protocol) {
        switch(protocol) {
            case tcp:
                conn = TcpClientSupport.connect(host, Integer.parseInt(entry), 5);
                break;
            default:
                System.out.println("setConnectionProtocol | ERROR ");
                break;
        }
    }

    public void sendCommandOnConnection(String cmd) {
        try {
            conn.forward(cmd);
        } catch (Exception e) {
            System.out.println("sendCommandOnConection | ERROR "+e.getMessage());
        }
    }

    public String sendRequestOnConnection(String request)  {
        try {
            String answer = conn.request(request);
            return answer;
        } catch (Exception e) {
            System.out.println("sendRequestOnConnection | ERROR "+e.getMessage());
            return null;}
    }

    public Interaction getConn(){
        return conn;
    }

    public void close(){
        try{
            conn.close();
        }catch(Exception e){
            System.out.println("ProxyAsClient | ERROR "+e.getMessage());
        }
    }
}

