package com.example.endtermact;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class act_sell_editrecords extends AppCompatActivity {
    private static Button btnQuery;
    private static EditText edtitemcode,names,cname,qty,idt,rate,Totalrate,edtcfname,edtclname,editpname,edtpqty,edtpprice,edtccontact;
    private static TextView tv_civ;
    private static String cItemcode = "";
    private static com.example.boparaiyoshidame1_delicioso.JSONParser jParser = new com.example.boparaiyoshidame1_delicioso.JSONParser();
    private static String urlHost = "http://192.168.1.16/burger/UpdateQty.php";
    private static String TAG_MESSAGE = "message" , TAG_SUCCESS = "success";
    private static String online_dataset = "";

    public static final String CFNAME = "CFNAME";
    public static final String CLNAME = "CLNAME";
    public static final String PNAME = "PNAME";
    public static final String PQTY = "PQTY";
    public static final String PPRICE = "PPRICE";
    public static final String CCONTACT = "CCONTACT";

    public static final String ID = "ID";
    private String aydi,Cname,Qty,Idt,Pce,tp, cfname, clname, pname,pqty,pprice,ccontact;

    public static String CustomerFirstName = "";
    public static String CustomerLastName = "";
    public static String ProductName = "";
    public static String ProductQuantity = "";
    public static String ProductPrice = "";
    public static String CustomerContact = "";



    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_sell_editrecords);

        btnQuery = (Button) findViewById(R.id.btnQuery);
        edtcfname = (EditText) findViewById(R.id.edtcfname);
        edtclname = (EditText) findViewById(R.id.edtclname);
        editpname = (EditText) findViewById(R.id.editpname);
        edtpqty = (EditText) findViewById(R.id.edtpqty);
        edtpprice = (EditText) findViewById(R.id.edtpprice);
        edtccontact = (EditText) findViewById(R.id.edtccontact);
        btnQuery = (Button) findViewById(R.id.btnQuery);
        tv_civ = (TextView) findViewById(R.id.textView3);


        Intent i = getIntent();
        cfname = i.getStringExtra(CFNAME);
        clname = i.getStringExtra(CLNAME);
        pname = i.getStringExtra(PNAME);
        pqty = i.getStringExtra(PQTY);
        pprice = i.getStringExtra(PPRICE);
        ccontact = i.getStringExtra(CCONTACT);
        aydi = i.getStringExtra(ID);

        edtclname.setText(cfname);
        edtclname.setText(clname);
        editpname.setText(pname);
        edtpqty.setText(pqty);
        edtpprice.setText(pprice);
        edtccontact.setText(ccontact);


        btnQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomerFirstName = edtcfname.getText().toString();
                CustomerLastName = edtclname.getText().toString();
                ProductName = editpname.getText().toString();
                ProductQuantity = edtpqty.getText().toString();
                ProductPrice = edtpprice.getText().toString();
                CustomerContact = edtccontact.getText().toString();


                new uploadDataToURL().execute();
            }
        });

    }

    private class uploadDataToURL extends AsyncTask<String, String, String> {
        String cPOST = "", cPostSQL = "", cMessage = "Querying data...";
        String gens,civil;
        int nPostValueIndex;
        ProgressDialog pDialog = new ProgressDialog(act_sell_editrecords.this);

        public uploadDataToURL() { }
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
                //insert anything in this cod

                cPostSQL = aydi;
                cv.put("ID", cPostSQL);

                cPostSQL = " '" + CustomerFirstName + "' ";
                cv.put("Cfname", cPostSQL);

                cPostSQL = " '" + CustomerLastName + "' ";
                cv.put("Clname", cPostSQL);

                cPostSQL = " '" + ProductName + "' ";
                cv.put("Pname",cPostSQL);

                cPostSQL = " '" + ProductQuantity + "' ";
                cv.put("Pqty", cPostSQL);

                cPostSQL = " '" + ProductPrice + "' ";
                cv.put("Pprice", cPostSQL);

                cPostSQL = " '" + CustomerContact + "' ";
                cv.put("Ccontact", cPostSQL);



                JSONObject json = jParser.makeHTTPRequest(urlHost, "POST" , cv);
                if(json != null) {
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
            AlertDialog.Builder alert = new AlertDialog.Builder(act_sell_editrecords.this);
            if (s !=null) {
                if (isEmpty.equals("") && !s.equals("HTTPSERVER_ERROR")) { }
                Toast.makeText(act_sell_editrecords.this, s , Toast.LENGTH_SHORT).show();
            } else {
                alert.setMessage("Query Interrupted... \nPlease Check Internet connection");
                alert.setTitle("Error");
                alert.show();
            }
        }
    }

}