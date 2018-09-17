package data.volive.mydata;

import android.net.wifi.p2p.WifiP2pManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class HomeScreen extends AppCompatActivity {
    String[] invoice = {"Server1", "Server2", "Server3", "Server4"};
    NotificationAdapternew adapter;
    RecyclerView recylerview;
    EditText clientid, server, port, uname, pwd;
    Button ok;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
//        recylerview=(RecyclerView)findViewById(R.id.recylerview);
//        adapter=new NotificationAdapternew(this,invoice);
//        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
//        recylerview.setLayoutManager(mLayoutManager);
//        recylerview.setItemAnimator(new DefaultItemAnimator());
//        recylerview.setAdapter(adapter);
        clientid = findViewById(R.id.clientid);
        server = findViewById(R.id.servername);
        port = findViewById(R.id.port);
        uname = findViewById(R.id.username);
        pwd = findViewById(R.id.pwd);
        ok = findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String client = clientid.getText().toString();
                String  ser=server.getText().toString();
                int pt= Integer.parseInt(port.getText().toString());
                String name=uname.getText().toString();
                String pd=pwd.getText().toString();
                connectAction(client,ser,pt,name,pd);

            }
        });
    }

    private void connectAction(String cliendid,String ser,int port,String uname,String pwd) {
        MqttConnectOptions conOpt = new MqttConnectOptions();
        /*
         * Mutal Auth connections could do something like this
         *
         *
         * SSLContext context = SSLContext.getDefault();
         * context.init({new CustomX509KeyManager()},null,null); //where CustomX509KeyManager proxies calls to keychain api
         * SSLSocketFactory factory = context.getSSLSocketFactory();
         *
         * MqttConnectOptions options = new MqttConnectOptions();
         * options.setSocketFactory(factory);
         *
         * client.connect(options);
         *
         */

        // The basic client information
//        String server = (String) data.get(ActivityConstants.server);
//        String clientId = (String) data.get(ActivityConstants.clientId);
//        int port = Integer.parseInt((String) data.get(ActivityConstants.port));
        boolean cleanSession = false;

        boolean ssl = false;
        String ssl_key = "";
        String uri = null;
        if (ssl) {
            Log.e("SSLConnection", "Doing an SSL Connect");
            uri = "ssl://";

        }
        else {
            uri = "tcp://";
        }

        uri = uri + ser + ":" + port;

        MqttAndroidClient client;
        client = Connections.getInstance(this).createClient(this, uri, cliendid);

        if (ssl){
            try {
                if(ssl_key != null && !ssl_key.equalsIgnoreCase(""))
                {
                    FileInputStream key = new FileInputStream(ssl_key);
                    conOpt.setSocketFactory(client.getSSLSocketFactory(key,
                            "mqtttest"));
                }

            } catch (MqttSecurityException e) {
                Log.e(this.getClass().getCanonicalName(),
                        "MqttException Occured: ", e);
            } catch (FileNotFoundException e) {
                Log.e(this.getClass().getCanonicalName(),
                        "MqttException Occured: SSL Key file not found", e);
            }
        }

        // create a client handle
        String clientHandle = uri + cliendid;

        // last will message
        String message = "";
        String topic = "";
        Integer qos = 0;
        Boolean retained =false;

        // connection options

//        String username = (String) data.get(ActivityConstants.username);
//
//        String password = (String) data.get(ActivityConstants.password);

        int timeout = 0;
        int keepalive =0;

        Connection connection = new Connection(clientHandle, cliendid, ser, port,
                this, client, ssl);
//        arrayAdapter.add(connection);
//
//        connection.registerChangeListener(changeListener);
        // connect client

        String[] actionArgs = new String[1];
        actionArgs[0] = cliendid;
        connection.changeConnectionStatus(Connection.ConnectionStatus.CONNECTING);
        conOpt.setCleanSession(cleanSession);
        conOpt.setConnectionTimeout(timeout);
        conOpt.setKeepAliveInterval(keepalive);
        if (!uname.isEmpty()) {
            conOpt.setUserName(uname);
        }
        if (!pwd.isEmpty()) {
            conOpt.setPassword(pwd.toCharArray());
        }

        final ActionListener callback = new ActionListener(this,
                ActionListener.Action.CONNECT, clientHandle, actionArgs);

        boolean doConnect = true;

        if ((!message.isEmpty())
                || (!topic.isEmpty())) {
            // need to make a message since last will is set
            try {
                conOpt.setWill(topic, message.getBytes(), qos.intValue(),
                        retained.booleanValue());
            }
            catch (Exception e) {
                Log.e(this.getClass().getCanonicalName(), "Exception Occured", e);
                doConnect = false;
                callback.onFailure(null, e);
            }
        }
        client.setCallback(new MqttCallbackHandler(this, clientHandle));


        //set traceCallback
        client.setTraceCallback(new MqttTraceCallback());

        connection.addConnectionOptions(conOpt);
        Connections.getInstance(this).addConnection(connection);
        if (doConnect) {
            try {
                client.connect(conOpt, null, callback);
            }
            catch (MqttException e) {
                Log.e(this.getClass().getCanonicalName(),
                        "MqttException Occured", e);
            }
        }

    }

}
