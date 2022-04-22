package it.unibo.comm2022.Sprint4.proxy;


import it.unibo.comm2022.Sprint4.interfaces.Interaction;
import it.unibo.comm2022.Sprint4.tcp.TcpClientSupport;
import it.unibo.comm2022.Sprint4.udp.UdpClientSupport;
import it.unibo.comm2022.Sprint4.utils.ProtocolType;

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
            case tcp: {
                conn = TcpClientSupport.connect(host, Integer.parseInt(entry), 5);
                break;
            }
            case udp: {
                try {
                    conn = UdpClientSupport.connect(host,Integer.parseInt(entry));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            }
            default: {
                System.out.println("setConnectionProtocol | ERROR ");
                break;
            }
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

