package com.example.bhawesh96.vso_androidapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.id.list;

public class LoadCountries extends ActionBarActivity {
    TextView lblheader;
    Typeface font;
    Button btnviewall,btnview;
    ListView lstcountry;
    EditText edtid;
    /*********** CONNECTION DATABASE VARIABLES **************/

    String usernameS;
    String datets;
    String call="192.168.0.100", db="mydatabase", un="sa", passwords="123";
    Connection connect;
    ResultSet rs;
    @SuppressLint("NewApi")
    private Connection CONN(String _user, String _pass, String _DB,
                            String _server) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conn = null;
        String ConnURL = null;
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            ConnURL = "jdbc:jtds:sqlserver://" + _server + ";"
//                    + "databaseName=" + _DB + ";user=" + _user + ";password="
//                    + _pass + ";";

            ConnURL = "jdbc:jtds:sqlserver://vso.database.windows.net:1433;database=vso;user=bhawesh96@vso;password=Clandestine@1996;encrypt=true;trustServerCertificate=true;loginTimeout=15000;";
            conn = DriverManager.getConnection(ConnURL);
        } catch (SQLException se) {
            Log.e("ERRO1", se.getMessage());
        } catch (ClassNotFoundException e) {
            Log.e("ERRO2", e.getMessage());
        } catch (Exception e) {
            Log.e("ERRO3", e.getMessage());
        }
        return conn;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.countries);
        lblheader = (TextView) findViewById(R.id.lblheader);
        lstcountry = (ListView) findViewById(R.id.lstcountry);
        btnviewall = (Button) findViewById(R.id.btnviewall);
        btnview = (Button) findViewById(R.id.btnview);
        edtid = (EditText) findViewById(R.id.edtid);
        /************* CONNECTION DATABASE VARIABLES ***************/

        connect = CONN(un, passwords, db, call);
        btnviewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    PreparedStatement statement = connect.prepareStatement("EXEC viewAllCountries");
                    final ArrayList list = new ArrayList();
                    rs = statement.executeQuery();
                    while (rs.next()) {
                        list.add(rs.getString("CountryName"));
                    }
                    ArrayAdapter adapter = new ArrayAdapter(LoadCountries.this,
                            android.R.layout.simple_list_item_1, list);

                    lstcountry.setAdapter(adapter);
                } catch (SQLException e) {
                    Toast.makeText(LoadCountries.this, e.getMessage().toString(),
                            Toast.LENGTH_LONG).show();
                }
            }
        });

//        btnview.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                try {
//
//                    PreparedStatement statement = connect.prepareStatement("EXEC viewCountry '"+edtid.getText().toString()+"'");
//                    final ArrayList list = new ArrayList();
//                    rs = statement.executeQuery();
//                    while (rs.next()) {
//                        list.add(rs.getString("CountryName"));
//                    }
//                    ArrayAdapter adapter = new ArrayAdapter(LoadCountries.this,
//                            android.R.layout.simple_list_item_1, list);
//
//                    lstcountry.setAdapter(adapter);
//                } catch (SQLException e) {
//                    Toast.makeText(LoadCountries.this, e.getMessage().toString(),
//                            Toast.LENGTH_LONG).show();
//                }
//            }
//        });

        btnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Connection con = connect;
                Statement stmt = null;
                try {
                    final ArrayList list = new ArrayList();
                    String query = "SELECT ID FROM countries";
                    stmt = con.createStatement();
                    Statement stmtx = con.createStatement();
                    ResultSet rs = stmtx.executeQuery(query);

                    while (rs.next()) {
                        list.add(rs.getString("CountryName"));
                    }
                    ArrayAdapter adapter = new ArrayAdapter(LoadCountries.this,
                            android.R.layout.simple_list_item_1, list);

                    lstcountry.setAdapter(adapter);


                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        });


        lstcountry.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub

                String item = lstcountry.getItemAtPosition(position).toString();
                Toast.makeText(LoadCountries.this, item + " selected", Toast.LENGTH_LONG).show();
            }
        });
    }
}