package it.unibo.comm2022.Sprint4.tcp;


import it.unibo.comm2022.Sprint4.interfaces.Interaction;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class TcpConnection implements Interaction {

        private Socket socket;
        private BufferedReader inputChannel;
        private DataOutputStream outputChannel;

        public TcpConnection(Socket socket) throws IOException {
            this.socket=socket;
            inputChannel = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            outputChannel = new DataOutputStream (socket.getOutputStream());
        }

        @Override
        public void forward(String msg) {
            try {
                outputChannel.writeBytes(msg+"\n");
                outputChannel.flush();
            }catch(IOException e) {
                System.out.println(e.getMessage());
            }
        }

        @Override
        public String request(String msg) {
            forward(msg);
            String answer = receiveMsg();
            return answer;
        }

        @Override
        public String receiveMsg() {
            try {
                return inputChannel.readLine();
            }catch(Exception e) {
                System.out.println("TcpConnection");
                return null;
            }
        }

        @Override
        public void reply(String msg) {
            forward(msg);
        }

        @Override
        public void close(){
            try {
                socket.close();
                System.out.println("TcpConnection Closed");
            }catch(IOException e) {
                System.out.println("TcpConnectionClose | ERROR "+e.getMessage());
            }
        }
}
