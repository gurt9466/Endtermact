package com.example.endtermact;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;


public class act_sell extends AppCompatActivity {

    EditText etdpname, edtqty, edtprice, edtfsellername, edtlsellername, edtsellercontactnumber;
    Button btnsubmit;
    private static com.example.endtermact.JSONParser jParser = new com.example.endtermact.JSONParser();
    private static String urlHost = "http://192.168.1.11/veggi/InsertTrans.php";
    private static String TAG_MESSAGE = "message", TAG_SUCCESS = "success";
    private static String online_dataset = "";
    private static String Pname = "";
    private static String Pqty = "";
    private static String Pprice = "";
    private static String Cfname = "";
    private static String Clname = "";
    private static String Ccontact = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_sell);

        etdpname = findViewById(R.id.edtpname);
        edtqty = findViewById(R.id.edtqty);
        edtprice = findViewById(R.id.edtprice);
        edtfsellername = findViewById(R.id.edtfsellername);
        edtlsellername = findViewById(R.id.edtlsellername2);
        edtsellercontactnumber = findViewById(R.id.edtcontactnumber);
        btnsubmit = findViewById(R.id.btnsubmit);

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pname = etdpname.getText().toString();
                Pqty = edtqty.getText().toString();
                Pprice = edtprice.getText().toString();
                Cfname = edtfsellername.getText().toString();
                Clname = edtlsellername.getText().toString();
                Ccontact = edtsellercontactnumber.getText().toString();

                new uploadDataToURL().execute();
            }
        });
    }


    private class uploadDataToURL extends AsyncTask<String, String, String> {
        String CPOST = "", cPostSQL = "", cMessage = "Querying data...";
        int nPostValueIndex;
        ProgressDialog pDialog = new ProgressDialog(act_sell.this);

        public uploadDataToURL() {
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.setMessage(cMessage);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            int nSuccess;
            try {
                ContentValues cv = new ContentValues();
                //insert anything in this code
                cPostSQL = " '" + Pname + "' , '" + Pqty + "' , '" + Pprice + "' , '" + Cfname +"' , '" + Clname + "', '" + Ccontact +"' ";
                cv.put("code", cPostSQL);

                JSONObject json = jParser.makeHTTPRequest(urlHost, "POST", cv);
                if (json != null) {
                    nSuccess = json.getInt(TAG_SUCCESS);
                    if (nSuccess == 1) {
                        online_dataset = json.getString(TAG_MESSAGE);
                        return online_dataset;
                    } else {
                        return json.getString(TAG_MESSAGE);
                    }
                } else {
                    return "HTTPSERVER_ERROR";
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pDialog.dismiss();
            String isEmpty = "";
            AlertDialog.Builder alert = new AlertDialog.Builder(act_sell.this);
            if (s != null) {
                if (isEmpty.equals("") && !s.equals("HTTPSERVER_ERROR")) {
                }
                Toast.makeText(act_sell.this, s, Toast.LENGTH_SHORT).show();
            } else {
                alert.setMessage("Query Interrupted... \nPlease Check Internet connection");
                alert.setTitle("Error");
                alert.show();
            }
        }
    }
}