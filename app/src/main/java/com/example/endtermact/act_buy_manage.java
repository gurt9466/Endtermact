package com.example.endtermact;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class act_buy_manage extends AppCompatActivity {

    private static Button btnQuery;
    TextView textView,txtDefault,txtDefault_buyername,txtDefault_buyerqty,txtDefault_buyercontact,txtDefault_Pqty,txtDefault_Pname,txtDefault_Pprice,txtDefault_Cfname,txtDefault_Clname,txtDefault_Ccontact,txtDefault_ID;
    private static  EditText edtitemcode;
    private static com.example.endtermact.JSONParser jParser = new com.example.endtermact.JSONParser();
    private static String urlHost = "http://192.168.1.11/veggi/selectPNAME.php";
    private static String urlHostDelete = "http://192.168.1.11/veggi/delete.php";
    private static String urlHostID= "http://192.168.1.11/veggi/selectid.php";
    private static String urlHostCLNAME= "http://192.168.1.11/veggi/selectCLNAME.php";
    private static String urlHostPQTY= "http://192.168.1.11/veggi/selectPQTY.php";
    private static String urlHostCFNAME= "http://192.168.1.11/veggi/SelectItemDetails.php";
    private static String urlHostPPRICE= "http://192.168.1.11/veggi/selectPPRICE.php";
    private static String urlCCONTACT="http://192.168.1.11/veggi/selectCCONTACT.php";
    private static String urlBUYERNAME="http://192.168.1.11/veggi/selectBUYERNAME.php";
    private static String urlBUYERQTY="http://192.168.1.11/veggi/selectBUYERQTY.php";
    private static String urlBUYERCONTACT="http://192.168.1.11/veggi/selectBUYERCONTACT.php";
    private static String TAG_MESSAGE = "message", TAG_SUCCESS = "success";
    private static String online_dataset = "";
    private static String cItemcode = "";

    private String aydi,cfname,clname,pqty,pprice,pname,ccontact,bbuyername,bbuyerqty,bbuyercontact;


    String cItemSelected,cltemSelected_cfname,cltemSelected_Buyername,cltemSelected_Buyerqty,cltemSelected_Buyercontact,cltemSelected_clname,cltemSelected_Pqty,cltemSelected_Pname,cltemSelected_Pprice,cltemSelected_Ccontact,cItemSelected_ID;
    ArrayAdapter <String> adapter_cfname;
    ArrayAdapter <String> adapter_pqty;
    ArrayAdapter <String> adapter_clname;
    ArrayAdapter <String> adapter_Pname;
    ArrayAdapter <String> adapter_Pprice;
    ArrayAdapter <String> adapter_Ccontact;
    ArrayAdapter <String> adapter_Buyername;
    ArrayAdapter <String> adapter_Buyerqty;
    ArrayAdapter <String> adapter_Buyercontact;
    ArrayAdapter <String> adapter_ID;
    ArrayList <String> list_cfname;
    ArrayList <String> list_clname;
    ArrayList <String> list_pqty;
    ArrayList <String> list_pname;
    ArrayList <String> list_pprice;
    ArrayList <String> list_Ccontact;
    ArrayList <String> list_itemdescription;
    ArrayList <String> list_price;
    ArrayList <String> list_Totalprice;
    ArrayList <String> list_buyername;
    ArrayList <String> list_buyerqty;
    ArrayList <String> list_buyercontact;
    ArrayList <String> list_ID;
    Context context = this;

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_sell_manage);

        btnQuery = (Button) findViewById(R.id.btnQuery);
        edtitemcode = (EditText) findViewById(R.id.edtitemcode);
        listView = (ListView) findViewById(R.id.listview);
        textView = (TextView) findViewById(R.id.textView4);

        txtDefault_Cfname = (TextView) findViewById(R.id.txt_Cfname);
        txtDefault_buyername = (TextView) findViewById(R.id.txt_buyername);
        txtDefault_buyerqty = (TextView) findViewById(R.id.txt_buyerqty);
        txtDefault_buyercontact = (TextView) findViewById(R.id.txt_buyercontact);
        txtDefault_Clname = (TextView) findViewById(R.id.txt_Clname);
        txtDefault_Pqty = (TextView) findViewById(R.id.txt_Pqty);
        txtDefault_Pname = (TextView) findViewById(R.id.txt_Pname);
        txtDefault_Pprice = (TextView) findViewById(R.id.txt_Pprice);
        txtDefault_Ccontact = (TextView) findViewById(R.id.txt_Ccontact);
        txtDefault_ID = (TextView) findViewById(R.id.txt_ID);

        txtDefault_Cfname.setVisibility(View.GONE);
        txtDefault_Clname.setVisibility(View.GONE);
        txtDefault_Pqty.setVisibility(View.GONE);
        txtDefault_Pname.setVisibility(View.GONE);
        txtDefault_Pprice.setVisibility(View.GONE);
        txtDefault_Ccontact.setVisibility(View.GONE);
        txtDefault_ID.setVisibility(View.GONE);
        txtDefault_buyername.setVisibility(View.GONE);
        txtDefault_buyerqty.setVisibility(View.GONE);
        txtDefault_buyercontact.setVisibility(View.GONE);


        Toast.makeText(act_buy_manage.this, "Nothing Selected", Toast.LENGTH_SHORT).show();

        btnQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cItemcode = edtitemcode.getText().toString();
                new uploadDataToURL().execute();
                new CLNAME().execute();
                new PQTY().execute();
                new CFNAME().execute();
                new PPRICE().execute();
                new CCONTACT().execute();
                new BUYERNAME().execute();
                new BUYERQTY().execute();
                new BUYERCONTACT().execute();
                new id().execute();

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                cltemSelected_cfname = adapter_cfname.getItem(position);
                cltemSelected_clname = adapter_clname.getItem(position);
                cltemSelected_Pqty = adapter_pqty.getItem(position);
                cltemSelected_Pname = adapter_Pname.getItem(position);
                cltemSelected_Pprice = adapter_Pprice.getItem(position);
                cltemSelected_Ccontact = adapter_Ccontact.getItem(position);
                cItemSelected_ID = adapter_ID.getItem(position);

                androidx.appcompat.app.AlertDialog.Builder alert_confirm =
                        new androidx.appcompat.app.AlertDialog.Builder(context);
                alert_confirm.setMessage("Do you want to edit your purchases ");
                alert_confirm.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        txtDefault_Cfname.setText(cltemSelected_cfname);
                        txtDefault_Clname.setText(cltemSelected_clname);
                        txtDefault_Pqty.setText(cltemSelected_Pqty);
                        txtDefault_Pname.setText(cltemSelected_Pname);
                        txtDefault_Pprice.setText(cltemSelected_Pprice);
                        txtDefault_Ccontact.setText(cltemSelected_Ccontact);
                        txtDefault_buyername.setText(cltemSelected_Buyername);
                        txtDefault_buyerqty.setText(cltemSelected_Buyerqty);
                        txtDefault_buyercontact.setText(cltemSelected_Buyercontact);
                        txtDefault_ID.setText(cItemSelected_ID);

                        cfname = txtDefault_Cfname.getText().toString().trim();
                        clname = txtDefault_Clname.getText().toString().trim();
                        pqty = txtDefault_Pqty.getText().toString().trim();
                        pname = txtDefault_Pname.getText().toString().trim();
                        pprice = txtDefault_Pprice.getText().toString().trim();
                        ccontact  = txtDefault_Ccontact.getText().toString().trim();
                        bbuyername  = txtDefault_buyername.getText().toString().trim();
                        bbuyerqty  = txtDefault_buyerqty.getText().toString().trim();
                        bbuyercontact  = txtDefault_buyercontact.getText().toString().trim();
                        aydi = txtDefault_ID.getText().toString().trim();

                        Intent intent = new Intent(act_buy_manage.this, act_buy_manage_editrecords.class);
                        intent.putExtra(act_buy_manage_editrecords.CFNAME, cfname);
                        intent.putExtra(act_buy_manage_editrecords.CLNAME, clname);
                        intent.putExtra(act_buy_manage_editrecords.PQTY, pqty);
                        intent.putExtra(act_buy_manage_editrecords.PNAME, pname);
                        intent.putExtra(act_buy_manage_editrecords.PPRICE, pprice);
                        intent.putExtra(act_buy_manage_editrecords.CCONTACT, ccontact);
                        intent.putExtra(act_buy_manage_editrecords.BBNAME, bbuyername);
                        intent.putExtra(act_buy_manage_editrecords.BBQTY, bbuyerqty);
                        intent.putExtra(act_buy_manage_editrecords.BBCONTACT, bbuyercontact);
                        intent.putExtra(act_buy_manage_editrecords.ID, aydi);

                        startActivity(intent);
                    }
                });
                alert_confirm.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                alert_confirm.show();
            }
        });
    }


    private class uploadDataToURL extends AsyncTask<String, String, String> {
        String cPOST = "", cPostSQL = "", cMessage = "Querying data...";
        int nPostValueIndex;
        ProgressDialog pDialog = new ProgressDialog(act_buy_manage.this);

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

                cPostSQL = cItemcode;
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
            android.app.AlertDialog.Builder alert = new AlertDialog.Builder(act_buy_manage.this);
            if (s != null) {
                if (isEmpty.equals("") && !s.equals("HTTPSERVER_ERROR")) { }
                //toast.makeText(act_buy_manage.this, s, Toast.LENGTH_SHORT).show();
                String wew = s;

                String str = wew;
                final String pnames[] = str.split("-");
                list_pname = new ArrayList<String>(Arrays.asList(pnames));
                adapter_Pname = new ArrayAdapter<String>(act_buy_manage.this,
                        android.R.layout.simple_list_item_1,list_pname);

                listView.setAdapter(adapter_Pname);
                textView.setText(listView.getAdapter().getCount() + " " +"record(s) found.");


            } else {
                alert.setMessage("Query Interrupted... \nPlease Check Internet connection");
                alert.setTitle("Error");
                alert.show();
            }
        }
    }

    private class CLNAME extends AsyncTask<String, String, String> {
        String cPOST = "", cPostSQL = "", cMessage = "Querying data...";
        int nPostValueIndex;
        ProgressDialog pDialog = new ProgressDialog(act_buy_manage.this);

        public CLNAME() {
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

                cPostSQL = cItemcode;
                cv.put("code", cPostSQL);

                JSONObject json = jParser.makeHTTPRequest(urlHostCLNAME, "POST", cv);
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


            } catch (JSONException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String CLNAME) {
            super.onPostExecute(CLNAME);
            pDialog.dismiss();
            String isEmpty = "";
            android.app.AlertDialog.Builder alert = new AlertDialog.Builder(act_buy_manage.this);
            if (CLNAME != null) {
                if (isEmpty.equals("") && !CLNAME.equals("HTTPSERVER_ERROR")) { }


                String clname = CLNAME;

                String str = clname;
                final String clnames[] = str.split("-");
                list_clname = new ArrayList<String>(Arrays.asList(clnames));
                adapter_clname = new ArrayAdapter<String>(act_buy_manage.this,
                        android.R.layout.simple_list_item_1,list_clname);

                //listView.setAdapter(adapter_clname);



            } else {
                alert.setMessage("Query Interrupted... \nPlease Check Internet connection");
                alert.setTitle("Error");
                alert.show();
            }
        }
    }

    private class PQTY extends AsyncTask<String, String, String> {
        String cPOST = "", cPostSQL = "", cMessage = "Querying data...";
        int nPostValueIndex;
        ProgressDialog pDialog = new ProgressDialog(act_buy_manage.this);

        public PQTY() {
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

                cPostSQL = cItemcode;
                cv.put("code", cPostSQL);

                JSONObject json = jParser.makeHTTPRequest(urlHostPQTY, "POST", cv);
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


            } catch (JSONException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String PQTY) {
            super.onPostExecute(PQTY);
            pDialog.dismiss();
            String isEmpty = "";
            android.app.AlertDialog.Builder alert = new AlertDialog.Builder(act_buy_manage.this);
            if (PQTY != null) {
                if (isEmpty.equals("") && !PQTY.equals("HTTPSERVER_ERROR")) { }


                String pqty = PQTY;

                String str = pqty;
                final String pqtys[] = str.split("-");
                list_pqty = new ArrayList<String>(Arrays.asList(pqtys));
                adapter_pqty = new ArrayAdapter<String>(act_buy_manage.this,
                        android.R.layout.simple_list_item_1,list_pqty);

                //listView.setAdapter(adapter_gender);



            } else {
                alert.setMessage("Query Interrupted... \nPlease Check Internet connection");
                alert.setTitle("Error");
                alert.show();
            }
        }
    }

    private class CFNAME extends AsyncTask<String, String, String> {
        String cPOST = "", cPostSQL = "", cMessage = "Querying data...";
        int nPostValueIndex;
        ProgressDialog pDialog = new ProgressDialog(act_buy_manage.this);

        public CFNAME() {
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

                cPostSQL = cItemcode;
                cv.put("code", cPostSQL);

                JSONObject json = jParser.makeHTTPRequest(urlHostCFNAME, "POST", cv);
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


            } catch (JSONException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String CFNAME) {
            super.onPostExecute(CFNAME);
            pDialog.dismiss();
            String isEmpty = "";
            android.app.AlertDialog.Builder alert = new AlertDialog.Builder(act_buy_manage.this);
            if (CFNAME != null) {
                if (isEmpty.equals("") && !CFNAME.equals("HTTPSERVER_ERROR")) { }


                String cfnaame = CFNAME;

                String str = cfnaame;
                final String qtys[] = str.split("-");
                list_cfname = new ArrayList<String>(Arrays.asList(qtys));
                adapter_cfname = new ArrayAdapter<String>(act_buy_manage.this,
                        android.R.layout.simple_list_item_1,list_cfname);

                //listView.setAdapter(adapter_gender);



            } else {
                alert.setMessage("Query Interrupted... \nPlease Check Internet connection");
                alert.setTitle("Error");
                alert.show();
            }
        }
    }

    private class PPRICE extends AsyncTask<String, String, String> {
        String cPOST = "", cPostSQL = "", cMessage = "Querying data...";
        int nPostValueIndex;
        ProgressDialog pDialog = new ProgressDialog(act_buy_manage.this);

        public PPRICE() {
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

                cPostSQL = cItemcode;
                cv.put("code", cPostSQL);

                JSONObject json = jParser.makeHTTPRequest(urlHostPPRICE, "POST", cv);
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


            } catch (JSONException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String PPRICE) {
            super.onPostExecute(PPRICE);
            pDialog.dismiss();
            String isEmpty = "";
            android.app.AlertDialog.Builder alert = new AlertDialog.Builder(act_buy_manage.this);
            if (PPRICE != null) {
                if (isEmpty.equals("") && !PPRICE.equals("HTTPSERVER_ERROR")) { }


                String pprice = PPRICE;

                String str = pprice;
                final String pprices[] = str.split("-");
                list_pprice = new ArrayList<String>(Arrays.asList(pprices));
                adapter_Pprice = new ArrayAdapter<String>(act_buy_manage.this,
                        android.R.layout.simple_list_item_1,list_pprice);

                //listView.setAdapter(adapter_gender);



            } else {
                alert.setMessage("Query Interrupted... \nPlease Check Internet connection");
                alert.setTitle("Error");
                alert.show();
            }
        }
    }

    private class BUYERNAME extends AsyncTask<String, String, String> {
        String cPOST = "", cPostSQL = "", cMessage = "Querying data...";
        int nPostValueIndex;
        ProgressDialog pDialog = new ProgressDialog(act_buy_manage.this);

        public BUYERNAME() {
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

                cPostSQL = cItemcode;
                cv.put("code", cPostSQL);

                JSONObject json = jParser.makeHTTPRequest(urlBUYERNAME, "POST", cv);
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


            } catch (JSONException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String BUYERNAME) {
            super.onPostExecute(BUYERNAME);
            pDialog.dismiss();
            String isEmpty = "";
            android.app.AlertDialog.Builder alert = new AlertDialog.Builder(act_buy_manage.this);
            if (BUYERNAME != null) {
                if (isEmpty.equals("") && !BUYERNAME.equals("HTTPSERVER_ERROR")) { }


                String buyern = BUYERNAME;

                String str = buyern;
                final String buyerns[] = str.split("-");
                list_buyername = new ArrayList<String>(Arrays.asList(buyerns));
                adapter_Buyername = new ArrayAdapter<String>(act_buy_manage.this,
                        android.R.layout.simple_list_item_1,list_buyername);

                //listView.setAdapter(adapter_gender);



            } else {
                alert.setMessage("Query Interrupted... \nPlease Check Internet connection");
                alert.setTitle("Error");
                alert.show();
            }
        }
    }

    private class BUYERQTY extends AsyncTask<String, String, String> {
        String cPOST = "", cPostSQL = "", cMessage = "Querying data...";
        int nPostValueIndex;
        ProgressDialog pDialog = new ProgressDialog(act_buy_manage.this);

        public BUYERQTY() {
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

                cPostSQL = cItemcode;
                cv.put("code", cPostSQL);

                JSONObject json = jParser.makeHTTPRequest(urlBUYERQTY, "POST", cv);
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


            } catch (JSONException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String BUYERQTY) {
            super.onPostExecute(BUYERQTY);
            pDialog.dismiss();
            String isEmpty = "";
            android.app.AlertDialog.Builder alert = new AlertDialog.Builder(act_buy_manage.this);
            if (BUYERQTY != null) {
                if (isEmpty.equals("") && !BUYERQTY.equals("HTTPSERVER_ERROR")) { }


                String buyerq = BUYERQTY;

                String str = buyerq;
                final String buyerqs[] = str.split("-");
                list_buyerqty = new ArrayList<String>(Arrays.asList(buyerqs));
                adapter_Buyerqty = new ArrayAdapter<String>(act_buy_manage.this,
                        android.R.layout.simple_list_item_1,list_buyerqty);

                //listView.setAdapter(adapter_gender);



            } else {
                alert.setMessage("Query Interrupted... \nPlease Check Internet connection");
                alert.setTitle("Error");
                alert.show();
            }
        }
    }

    private class BUYERCONTACT extends AsyncTask<String, String, String> {
        String cPOST = "", cPostSQL = "", cMessage = "Querying data...";
        int nPostValueIndex;
        ProgressDialog pDialog = new ProgressDialog(act_buy_manage.this);

        public BUYERCONTACT() {
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

                cPostSQL = cItemcode;
                cv.put("code", cPostSQL);

                JSONObject json = jParser.makeHTTPRequest(urlBUYERCONTACT, "POST", cv);
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


            } catch (JSONException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String BUYERCONTACT) {
            super.onPostExecute(BUYERCONTACT);
            pDialog.dismiss();
            String isEmpty = "";
            android.app.AlertDialog.Builder alert = new AlertDialog.Builder(act_buy_manage.this);
            if (BUYERCONTACT != null) {
                if (isEmpty.equals("") && !BUYERCONTACT.equals("HTTPSERVER_ERROR")) { }


                String buyerc = BUYERCONTACT;

                String str = buyerc;
                final String buyercs[] = str.split("-");
                list_buyercontact = new ArrayList<String>(Arrays.asList(buyercs));
                adapter_Buyercontact = new ArrayAdapter<String>(act_buy_manage.this,
                        android.R.layout.simple_list_item_1,list_buyercontact);

                //listView.setAdapter(adapter_gender);



            } else {
                alert.setMessage("Query Interrupted... \nPlease Check Internet connection");
                alert.setTitle("Error");
                alert.show();
            }
        }
    }
    private class CCONTACT extends AsyncTask<String, String, String> {
        String cPOST = "", cPostSQL = "", cMessage = "Querying data...";
        int nPostValueIndex;
        ProgressDialog pDialog = new ProgressDialog(act_buy_manage.this);

        public CCONTACT() {
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

                cPostSQL = cItemcode;
                cv.put("code", cPostSQL);

                JSONObject json = jParser.makeHTTPRequest(urlCCONTACT, "POST", cv);
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


            } catch (JSONException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String CCONTACT) {
            super.onPostExecute(CCONTACT);
            pDialog.dismiss();
            String isEmpty = "";
            android.app.AlertDialog.Builder alert = new AlertDialog.Builder(act_buy_manage.this);
            if (CCONTACT != null) {
                if (isEmpty.equals("") && !CCONTACT.equals("HTTPSERVER_ERROR")) { }


                String ccontact = CCONTACT;

                String str = ccontact;
                final String ccontacts[] = str.split("-");
                list_Ccontact = new ArrayList<String>(Arrays.asList(ccontacts));
                adapter_Ccontact = new ArrayAdapter<String>(act_buy_manage.this,
                        android.R.layout.simple_list_item_1,list_Ccontact);

                //listView.setAdapter(adapter_gender);



            } else {
                alert.setMessage("Query Interrupted... \nPlease Check Internet connection");
                alert.setTitle("Error");
                alert.show();
            }
        }
    }


    private class id extends AsyncTask<String, String, String> {
        String cPOST = "", cPostSQL = "", cMessage = "Querying data...";
        int nPostValueIndex;
        ProgressDialog pDialog = new ProgressDialog(act_buy_manage.this);

        public id() {
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

                cPostSQL = cItemcode;
                cv.put("code", cPostSQL);

                JSONObject json = jParser.makeHTTPRequest(urlHostID, "POST", cv);
                if (json != null) {
                    nSuccess = json.getInt(TAG_SUCCESS);
                    if (nSuccess == 1){
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
        protected void onPostExecute(String aydi) {
            super.onPostExecute(aydi);
            pDialog.dismiss();
            String isEmpty = "";
            android.app.AlertDialog.Builder alert = new AlertDialog.Builder(act_buy_manage.this);
            if (aydi != null) {
                if (isEmpty.equals("") && !aydi.equals("HTTPSERVER_ERROR")) { }
                Toast.makeText(act_buy_manage.this, "Data selected", Toast.LENGTH_SHORT).show();

                String AYDI = aydi;

                String str = AYDI;
                final String ayds[] = str.split("-");
                list_ID = new ArrayList<String>(Arrays.asList(ayds));
                adapter_ID = new ArrayAdapter<String>(act_buy_manage.this,
                        android.R.layout.simple_list_item_1,list_ID);

                //listView.setAdapter(adapter_gender);



            } else {
                alert.setMessage("Query Interrupted... \nPlease Check Internet connection");
                alert.setTitle("Error");
                alert.show();
            }
        }
    }

    private class delete extends AsyncTask<String, String, String> {
        String cPOST = "", cPostSQL = "", cMessage = "Querying data...";
        int nPostValueIndex;
        ProgressDialog pDialog = new ProgressDialog(act_buy_manage.this);

        public delete() {
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

                cPostSQL = cItemSelected_ID;
                cv.put("id", cPostSQL);

                JSONObject json = jParser.makeHTTPRequest(urlHostDelete, "POST", cv);
                if (json != null) {
                    nSuccess =json.getInt(TAG_SUCCESS);
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
        protected void onPostExecute(String del) {
            super.onPostExecute(del);
            pDialog.dismiss();
            String isEmpty = "";
            android.app.AlertDialog.Builder alert = new AlertDialog.Builder(act_buy_manage.this);
            if (aydi != null) {
                if (isEmpty.equals("") && !del.equals("HTTPSERVER_ERROR")) { }
                Toast.makeText(act_buy_manage.this, "Data Deleted", Toast.LENGTH_SHORT).show();
            } else {
                alert.setMessage("Query Interrupted... \nPlease Check Internet connection");
                alert.setTitle("Error");
                alert.show();
            }
        }
    }
}
