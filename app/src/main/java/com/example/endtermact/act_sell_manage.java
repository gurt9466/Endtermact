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

public class act_sell_manage extends AppCompatActivity {

    private static Button btnQuery;
    TextView textView,txtDefault,txtDefault_Pqty,txtDefault_Pname,txtDefault_Pprice,txtDefault_Cfname,txtDefault_Clname,txtDefault_Ccontact,txtDefault_ID;
    private static  EditText edtitemcode;
    private static com.example.endtermact.JSONParser jParser = new com.example.endtermact.JSONParser();
    private static String urlHost = "http://192.168.1.11/veggi/SelectItemDetails.php";
    private static String urlHostDelete = "http://192.168.1.11/veggi/delete.php";
    private static String urlHostID= "http://192.168.1.11/veggi/selectid.php";
    private static String urlHostCLNAME= "http://192.168.1.11/veggi/selectCLNAME.php";
    private static String urlHostPQTY= "http://192.168.1.11/veggi/selectPQTY.php";
    private static String urlHostPNAME= "http://192.168.1.11/veggi/selectPNAME.php";
    private static String urlHostPPRICE= "http://192.168.1.11/veggi/selectPPRICE.php";
    private static String urlCCONTACT="http://192.168.1.11/veggi/selectCCONTACT.php";
    private static String TAG_MESSAGE = "message", TAG_SUCCESS = "success";
    private static String online_dataset = "";
    private static String cItemcode = "";

    private String aydi,cfname,clname,pqty,pprice,pname,ccontact;


    String cItemSelected,cltemSelected_cfname,cltemSelected_clname,cltemSelected_Pqty,cltemSelected_Pname,cltemSelected_Pprice,cltemSelected_Ccontact,cItemSelected_ID;
    ArrayAdapter <String> adapter_cfname;
    ArrayAdapter <String> adapter_pqty;
    ArrayAdapter <String> adapter_clname;
    ArrayAdapter <String> adapter_Pname;
    ArrayAdapter <String> adapter_Pprice;
    ArrayAdapter <String> adapter_Ccontact;
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


        Toast.makeText(act_sell_manage.this, "Nothing Selected", Toast.LENGTH_SHORT).show();

        btnQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cItemcode = edtitemcode.getText().toString();
                new uploadDataToURL().execute();
                new CLNAME().execute();
                new PQTY().execute();
                new PNAME().execute();
                new PPRICE().execute();
                new CCONTACT().execute();
                new id().execute();

            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                cltemSelected_cfname = adapter_cfname.getItem(position);
                cltemSelected_clname = adapter_clname.getItem(position);
                cltemSelected_Pqty = adapter_pqty.getItem(position);
                cltemSelected_Pname = adapter_Pname.getItem(position);
                cltemSelected_Pprice = adapter_Pprice.getItem(position);
                cltemSelected_Ccontact = adapter_Ccontact.getItem(position);
                cItemSelected_ID = adapter_ID.getItem(position);

                androidx.appcompat.app.AlertDialog.Builder alert_confirm =
                        new androidx.appcompat.app.AlertDialog.Builder(context);
                alert_confirm.setMessage("Edit the records of" + " " + cltemSelected_cfname);
                alert_confirm.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        txtDefault_Cfname.setText(cltemSelected_cfname);
                        txtDefault_Clname.setText(cltemSelected_clname);
                        txtDefault_Pqty.setText(cltemSelected_Pqty);
                        txtDefault_Pname.setText(cltemSelected_Pname);
                        txtDefault_Pprice.setText(cltemSelected_Pprice);
                        txtDefault_Ccontact.setText(cltemSelected_Ccontact);
                        txtDefault_ID.setText(cItemSelected_ID);

                        cfname = txtDefault_Cfname.getText().toString().trim();
                        clname = txtDefault_Clname.getText().toString().trim();
                        pqty = txtDefault_Pqty.getText().toString().trim();
                        pname = txtDefault_Pname.getText().toString().trim();
                        pprice = txtDefault_Pprice.getText().toString().trim();
                        ccontact  = txtDefault_Ccontact.getText().toString().trim();
                        aydi = txtDefault_ID.getText().toString().trim();

                        Intent intent = new Intent(act_sell_manage.this,act_sell_editrecords.class);
                        intent.putExtra(act_sell_editrecords.CFNAME,cfname);
                        intent.putExtra(act_sell_editrecords.CLNAME,clname);
                        intent.putExtra(act_sell_editrecords.PQTY,pqty);
                        intent.putExtra(act_sell_editrecords.PNAME,pname);
                        intent.putExtra(act_sell_editrecords.PPRICE,pprice);
                        intent.putExtra(act_sell_editrecords.CCONTACT,ccontact);
                        intent.putExtra(act_sell_editrecords.ID,aydi);

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
                return false;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                cltemSelected_cfname = adapter_cfname.getItem(position);
                cltemSelected_clname = adapter_clname.getItem(position);
                cltemSelected_Pqty = adapter_pqty.getItem(position);
                cltemSelected_Pname = adapter_Pname.getItem(position);
                cltemSelected_Pprice = adapter_Pprice.getItem(position);
                cItemSelected_ID = adapter_ID.getItem(position);

                androidx.appcompat.app.AlertDialog.Builder alert_confirm =
                        new androidx.appcompat.app.AlertDialog.Builder(context);
                alert_confirm.setMessage("Are you sure you want to delete" + " " + cltemSelected_cfname);
                alert_confirm.setPositiveButton(R.string.msg2, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        txtDefault_ID.setText(cItemSelected_ID);
                        aydi = txtDefault_ID.getText().toString().trim();
                        new delete().execute();
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
        ProgressDialog pDialog = new ProgressDialog(act_sell_manage.this);

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
            android.app.AlertDialog.Builder alert = new AlertDialog.Builder(act_sell_manage.this);
            if (s != null) {
                if (isEmpty.equals("") && !s.equals("HTTPSERVER_ERROR")) { }
                //toast.makeText(act_sell_manage.this, s, Toast.LENGTH_SHORT).show();
                String wew = s;

                String str = wew;
                final String cnames[] = str.split("-");
                list_cfname = new ArrayList<String>(Arrays.asList(cnames));
                adapter_cfname = new ArrayAdapter<String>(act_sell_manage.this,
                        android.R.layout.simple_list_item_1,list_cfname);

                listView.setAdapter(adapter_cfname);
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
        ProgressDialog pDialog = new ProgressDialog(act_sell_manage.this);

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
            android.app.AlertDialog.Builder alert = new AlertDialog.Builder(act_sell_manage.this);
            if (CLNAME != null) {
                if (isEmpty.equals("") && !CLNAME.equals("HTTPSERVER_ERROR")) { }


                String clname = CLNAME;

                String str = clname;
                final String clnames[] = str.split("-");
                list_clname = new ArrayList<String>(Arrays.asList(clnames));
                adapter_clname = new ArrayAdapter<String>(act_sell_manage.this,
                        android.R.layout.simple_list_item_1,list_clname);

                //listView.setAdapter(adapter_gender);



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
        ProgressDialog pDialog = new ProgressDialog(act_sell_manage.this);

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
            android.app.AlertDialog.Builder alert = new AlertDialog.Builder(act_sell_manage.this);
            if (PQTY != null) {
                if (isEmpty.equals("") && !PQTY.equals("HTTPSERVER_ERROR")) { }


                String pqty = PQTY;

                String str = pqty;
                final String pqtys[] = str.split("-");
                list_pqty = new ArrayList<String>(Arrays.asList(pqtys));
                adapter_pqty = new ArrayAdapter<String>(act_sell_manage.this,
                        android.R.layout.simple_list_item_1,list_pqty);

                //listView.setAdapter(adapter_gender);



            } else {
                alert.setMessage("Query Interrupted... \nPlease Check Internet connection");
                alert.setTitle("Error");
                alert.show();
            }
        }
    }

    private class PNAME extends AsyncTask<String, String, String> {
        String cPOST = "", cPostSQL = "", cMessage = "Querying data...";
        int nPostValueIndex;
        ProgressDialog pDialog = new ProgressDialog(act_sell_manage.this);

        public PNAME() {
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

                JSONObject json = jParser.makeHTTPRequest(urlHostPNAME, "POST", cv);
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
        protected void onPostExecute(String PNAME) {
            super.onPostExecute(PNAME);
            pDialog.dismiss();
            String isEmpty = "";
            android.app.AlertDialog.Builder alert = new AlertDialog.Builder(act_sell_manage.this);
            if (PNAME != null) {
                if (isEmpty.equals("") && !PNAME.equals("HTTPSERVER_ERROR")) { }


                String pname = PNAME;

                String str = pname;
                final String qtys[] = str.split("-");
                list_pname = new ArrayList<String>(Arrays.asList(qtys));
                adapter_Pname = new ArrayAdapter<String>(act_sell_manage.this,
                        android.R.layout.simple_list_item_1,list_pname);

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
        ProgressDialog pDialog = new ProgressDialog(act_sell_manage.this);

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
            android.app.AlertDialog.Builder alert = new AlertDialog.Builder(act_sell_manage.this);
            if (PPRICE != null) {
                if (isEmpty.equals("") && !PPRICE.equals("HTTPSERVER_ERROR")) { }


                String pprice = PPRICE;

                String str = pprice;
                final String pprices[] = str.split("-");
                list_pprice = new ArrayList<String>(Arrays.asList(pprices));
                adapter_Pprice = new ArrayAdapter<String>(act_sell_manage.this,
                        android.R.layout.simple_list_item_1,list_pprice);

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
        ProgressDialog pDialog = new ProgressDialog(act_sell_manage.this);

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
            android.app.AlertDialog.Builder alert = new AlertDialog.Builder(act_sell_manage.this);
            if (CCONTACT != null) {
                if (isEmpty.equals("") && !CCONTACT.equals("HTTPSERVER_ERROR")) { }


                String ccontact = CCONTACT;

                String str = ccontact;
                final String ccontacts[] = str.split("-");
                list_Ccontact = new ArrayList<String>(Arrays.asList(ccontacts));
                adapter_Ccontact = new ArrayAdapter<String>(act_sell_manage.this,
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
        ProgressDialog pDialog = new ProgressDialog(act_sell_manage.this);

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
            android.app.AlertDialog.Builder alert = new AlertDialog.Builder(act_sell_manage.this);
            if (aydi != null) {
                if (isEmpty.equals("") && !aydi.equals("HTTPSERVER_ERROR")) { }
                Toast.makeText(act_sell_manage.this, "Data selected", Toast.LENGTH_SHORT).show();

                String AYDI = aydi;

                String str = AYDI;
                final String ayds[] = str.split("-");
                list_ID = new ArrayList<String>(Arrays.asList(ayds));
                adapter_ID = new ArrayAdapter<String>(act_sell_manage.this,
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
        ProgressDialog pDialog = new ProgressDialog(act_sell_manage.this);

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
            android.app.AlertDialog.Builder alert = new AlertDialog.Builder(act_sell_manage.this);
            if (aydi != null) {
                if (isEmpty.equals("") && !del.equals("HTTPSERVER_ERROR")) { }
                Toast.makeText(act_sell_manage.this, "Data Deleted", Toast.LENGTH_SHORT).show();
            } else {
                alert.setMessage("Query Interrupted... \nPlease Check Internet connection");
                alert.setTitle("Error");
                alert.show();
            }
        }
    }
}
