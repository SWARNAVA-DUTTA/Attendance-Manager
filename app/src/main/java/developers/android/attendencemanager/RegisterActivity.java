package developers.android.attendencemanager;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    EditText etUsername,etPassword,etConfirmPassword;
    Toolbar toolbar;
    Button btnRegister;
    String s1,s2;

    ProgressDialog pDialog;

    String url="https://swarnavadutta.000webhostapp.com/Attendance%20Manager/register.php";


    RequestQueue requestQueue;
    StringRequest stringRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etUsername=(EditText) findViewById(R.id.etUsername);
        etPassword=(EditText) findViewById(R.id.etPassword);
        etConfirmPassword=(EditText)findViewById(R.id.etConfirmPassword);
        btnRegister=(Button) findViewById(R.id.btnRegister);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        pDialog= new ProgressDialog(this);
        pDialog.setMessage("Wait... We are processing your request");

        requestQueue= Volley.newRequestQueue(getApplicationContext());
    }

    public void registerbutton(View view) {
        if(etConfirmPassword.getText().toString().equals(etPassword.getText().toString())){
        s1=etUsername.getText().toString();
        s2=etPassword.getText().toString();
        if(etUsername.getText().toString().equals("") && etPassword.getText().toString().equals("")){
            etUsername.setError("Please enter the following field");
            etPassword.setError("Please enter the following field");}
            else if(etUsername.getText().toString().equals(""))
            etUsername.setError("Please enter the following field");
        else if(etPassword.getText().toString().equals(""))
            etPassword.setError("Please enter the following field");
        else{
        stringRequest=new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pDialog.hide();
                        showMsg("Server Response",response );


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pDialog.hide();
                        showMsg("Page not reachable","Check Your Internet Connection and try again");
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<String,String>();
                params.put("Email",s1);
                params.put("Password",s2);
                return params;
            }
        };
        requestQueue.add(stringRequest);
        pDialog.show();
    }}
        else{
            etPassword.setText("");
            etConfirmPassword.setText("");
            etPassword.setError("The passwords do not match");
            etConfirmPassword.setError("The passwords do not match");
        }}


    public void showMsg(String title, String msg){
        android.support.v7.app.AlertDialog.Builder mBuilder=new android.support.v7.app.AlertDialog.Builder(RegisterActivity.this);

        mBuilder.setTitle(title);
        mBuilder.setMessage(msg);
        mBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                etUsername.setText("");
                etPassword.setText("");
                etConfirmPassword.setText("");
                etUsername.requestFocus();
                dialog.dismiss();
            }
        });
        android.support.v7.app.AlertDialog dialog=mBuilder.create();
        dialog.show();
    }

    public void log(View view) {
        Intent swarnava=new Intent(getApplicationContext(),LogInActivity.class);
        startActivity(swarnava);
    }
}
