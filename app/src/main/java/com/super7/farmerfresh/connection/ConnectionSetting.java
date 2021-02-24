package com.super7.farmerfresh.connection;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

import static java.lang.Class.forName;

public class ConnectionSetting {
    ConnectionSetting con;
    String uname, pass, ip, port, database;
    @SuppressLint("NewApi")
    public Connection connectionclass() {
        ip = "172.0.0.1";
        database = "farmerfresh";
        uname = "root";
        pass = "root";
        port = "3306";
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String ConnectionURL = null;

        try{
            Class.forName("net.sourceforge.jtds.jdbc.Driver");

            ConnectionURL = "jdbc:jtds:sqlserver://"+ ip + ":" + port + ";"
                    + "databasename=" + database + ";user=" + uname + ";password=" + pass + ";";

            connection = DriverManager.getConnection(ConnectionURL);
        }
        catch (Exception ex){
            Log.e("Error",ex.getMessage() );
        }

        return connection;
    }
}
