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

public class act_buy_manage_editrecords extends AppCompatActivity {
    private static Button btnQuery;
    private static EditText edtbuyername, edtbuyerqty, edtbuyercontact;
    private static TextView edtcfname,edtclname,editpname,edtpqty,edtpprice,edtccontact,txt1,txt2,txt3,txt4,txt5,txt6;
    private static TextView tv_civ;
    private static String cItemcode = "";
    private static com.example.endtermact.JSONParser jParser = new com.example.endtermact.JSONParser();
    private static String urlHost = "http://192.168.1.11/veggi/UpdateQty.php";
    private static String TAG_MESSAGE = "message" , TAG_SUCCESS = "success";
    private static String online_dataset = "";
    public static final String ID = "ID";
    private String aydi,cfname, clname, pname,pqty,pprice,ccontact, buyername,buyercontact,buyerqty;
    public static final String CFNAME = "CFNAME";
    public static final String CLNAME = "CLNAME";
    public static final String PNAME = "PNAME";
    public static final String PQTY = "PQTY";
    public static final String PPRICE = "PPRICE";
    public static final String CCONTACT = "CCONTACT";
    public static final String BBNAME = "BBNAME";
    public static final String BBQTY = "BBQTY";
    public static final String BBCONTACT = "BBCONTACT";



    public static String CustomerFirstName = "";
    public static String CustomerLastName = "";
    public static String ProductName = "";
    public static String ProductQuantity = "";
    public static String ProductPrice = "";
    public static String CustomerContact = "";
    public static String BuyerName = "";
    public static String BuyerQTY = "";
    public static String BuyerContact = "";



    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_buy_manage_editrecords);

        btnQuery = (Button) findViewById(R.id.btnQuery);
        edtbuyername = (EditText) findViewById(R.id.editbuyername);
        edtbuyerqty = (EditText) findViewById(R.id.editbuyerqty);
        edtbuyercontact = (EditText) findViewById(R.id.editbuyercontact);
        edtcfname = (TextView) findViewById(R.id.edtcfname);
        edtclname = (TextView) findViewById(R.id.edtclname);
        editpname = (TextView) findViewById(R.id.editpname);
        edtpqty = (TextView) findViewById(R.id.edtpqty);
        edtpprice = (TextView) findViewById(R.id.edtpprice);
        edtccontact = (TextView) findViewById(R.id.edtccontact);
        btnQuery = (Button) findViewById(R.id.btnQuery);
        tv_civ = (TextView) findViewById(R.id.textView3);

        txt1 = (TextView) findViewById(R.id.tp);
        txt2 = (TextView) findViewById(R.id.textView4);
        txt3 = (TextView) findViewById(R.id.textView2);
        txt4 = (TextView) findViewById(R.id.dop2);
        txt5 = (TextView) findViewById(R.id.dop);
        txt6 = (TextView) findViewById(R.id.textView3);


        Intent i = getIntent();
        cfname = i.getStringExtra(CFNAME);
        clname = i.getStringExtra(CLNAME);
        pname = i.getStringExtra(PNAME);
        pqty = i.getStringExtra(PQTY);
        pprice = i.getStringExtra(PPRICE);
        ccontact = i.getStringExtra(CCONTACT);
        buyername = i.getStringExtra(BBNAME);
        buyerqty = i.getStringExtra(BBQTY);
        buyercontact = i.getStringExtra(BBCONTACT);
        aydi = i.getStringExtra(ID);

        edtcfname.setText(cfname);
        edtclname.setText(clname);
        editpname.setText(pname);
        edtpqty.setText(pqty);
        edtpprice.setText(pprice);
        edtccontact.setText(ccontact);
        edtbuyername.setText(buyername);
        edtbuyerqty.setText(buyerqty);
        edtbuyercontact.setText(buyercontact);

        edtcfname.setVisibility(View.GONE);
        edtclname.setVisibility(View.GONE);
        editpname.setVisibility(View.GONE);
        edtpqty.setVisibility(View.GONE);
        edtpprice.setVisibility(View.GONE);
        edtccontact.setVisibility(View.GONE);

        txt1.setVisibility(View.GONE);
        txt2.setVisibility(View.GONE);
        txt3.setVisibility(View.GONE);
        txt4.setVisibility(View.GONE);
        txt5.setVisibility(View.GONE);
        txt6.setVisibility(View.GONE);


        btnQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomerFirstName = edtcfname.getText().toString();
                CustomerLastName = edtclname.getText().toString();
                ProductName = editpname.getText().toString();
                ProductQuantity = edtpqty.getText().toString();
                ProductPrice = edtpprice.getText().toString();
                CustomerContact = edtccontact.getText().toString();
                BuyerName = edtbuyername.getText().toString();
                BuyerQTY = edtbuyerqty.getText().toString();
                BuyerContact = edtbuyercontact.getText().toString();


                new uploadDataToURL().execute();
            }
        });

    }

    private class uploadDataToURL extends AsyncTask<String, String, String> {
        String cPOST = "", cPostSQL = "", cMessage = "Querying data...";
        String gens,civil;
        int nPostValueIndex;
        ProgressDialog pDialog = new ProgressDialog(act_buy_manage_editrecords.this);

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
                cv.put("id", cPostSQL);

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

                cPostSQL = " '" + BuyerName + "' ";
                cv.put("Buyer_name", cPostSQL);

                cPostSQL = " '" + BuyerQTY + "' ";
                cv.put("Buying_qty", cPostSQL);

                cPostSQL = " '" + BuyerContact + "' ";
                cv.put("Buyer_contactnumber", cPostSQL);



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
            AlertDialog.Builder alert = new AlertDialog.Builder(act_buy_manage_editrecords.this);
            if (s !=null) {
                if (isEmpty.equals("") && !s.equals("HTTPSERVER_ERROR")) { }
                Toast.makeText(act_buy_manage_editrecords.this, s , Toast.LENGTH_SHORT).show();
            } else {
                alert.setMessage("Query Interrupted... \nPlease Check Internet connection");
                alert.setTitle("Error");
                alert.show();
            }
        }
    }

}