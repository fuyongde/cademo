package com.ca.ssl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.TrustManagerFactory;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.security.KeyStore;
import java.security.Provider;

public class SSLServer {

    private static Logger logger = LoggerFactory.getLogger(SSLServer.class);

    private static final int DEFAULT_PORT = 7777;

    private static final String SERVER_KEY_STORE_PASSWORD = "111111";
    private static final String SERVER_TRUST_KEY_STORE_PASSWORD = "111111";

    private SSLServerSocket serverSocket;

    /**
     * 启动程序
     *
     * @param args
     */
    public static void main(String[] args) {
        SSLServer server = new SSLServer();
        server.init();
        server.start();
    }

    /**
     * 监听SSL Server Socket
     */
    public void start() {
        if (serverSocket == null) {
            System.out.println("ERROR");
            return;
        }
        while (true) {
            try {
                Socket s = serverSocket.accept();
                InputStream input = s.getInputStream();
                OutputStream output = s.getOutputStream();

                BufferedInputStream bis = new BufferedInputStream(input);
                BufferedOutputStream bos = new BufferedOutputStream(output);

                byte[] buffer = new byte[11];
                bis.read(buffer);
                System.out.println(new String(buffer));

                bos.write("I am server".getBytes("UTF-8"));
                bos.flush();

                //s.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * ssl连接的重点:
     * 初始化SSLServerSocket
     * 导入服务端私钥KeyStore，导入服务端受信任的KeyStore(客户端的证书)
     */
    public void init() {
        try {
            SSLContext ctx = SSLContext.getInstance("SSL");

            Provider provider = ctx.getProvider();
            provider.forEach((key, value)-> logger.info("{} : {}", key, value));

            KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
            TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");

            KeyStore ks = KeyStore.getInstance("JKS");
            KeyStore tks = KeyStore.getInstance("JKS");

            ks.load(SSLServer.class.getResourceAsStream("/certs/server.keystore"), SERVER_KEY_STORE_PASSWORD.toCharArray());
            tks.load(SSLServer.class.getResourceAsStream("/certs/server.keystore"), SERVER_TRUST_KEY_STORE_PASSWORD.toCharArray());

            kmf.init(ks, SERVER_KEY_STORE_PASSWORD.toCharArray());
            tmf.init(tks);

            ctx.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);

            serverSocket = (SSLServerSocket) ctx.getServerSocketFactory().createServerSocket(DEFAULT_PORT);
            serverSocket.setNeedClientAuth(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
