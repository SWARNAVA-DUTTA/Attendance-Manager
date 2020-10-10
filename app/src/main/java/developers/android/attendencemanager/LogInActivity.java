package developers.android.attendencemanager;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LogInActivity extends AppCompatActivity {
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    TextView register;
    EditText etusername,etpassword;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    Button btnlogin;
    String s1,s2;

    ProgressDialog pDialog;
    String url="https://swarnavadutta.000webhostapp.com/Attendance%20Manager/login.php";

    RequestQueue requestQueue;
    StringRequest stringRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        register=findViewById(R.id.register);
        etusername=findViewById(R.id.etusername);
        etpassword=findViewById(R.id.etpassword);
        btnlogin=findViewById(R.id.btnlogin);
        setupToolbar();
        navigationView=(NavigationView)findViewById(R.id.navigation_view);
        menuItemClick();
        pDialog= new ProgressDialog(this);
        pDialog.setMessage("Please wait till verification...");
        requestQueue= Volley.newRequestQueue(getApplicationContext());
    }
    private void setupToolbar(){
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout=(DrawerLayout) findViewById(R.id.drawerLayout);

        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);//Implement Hamburg Icon

        drawerLayout.addDrawerListener(toggle);//It takes the response of the Hamburger icon
        toggle.syncState();//It verifies whether DrawerLayout is Open or Close state

    }
    private void menuItemClick() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override

        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                switch (id) {
                    case R.id.instituition:
                       Intent i1=new Intent(getApplicationContext(),Instituition.class);
                       startActivity(i1);
                       break;
                    case R.id.feedback:

                        Intent i2=new Intent(getApplicationContext(),FeedbackActivity.class);
                        startActivity(i2);
                        break;
                    case R.id.developers:
                        Intent i3=new Intent(getApplicationContext(),DevelopersActivity.class);
                        startActivity(i3);
                        break;

                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

    }

    private void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.drawer_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        switch (id) {
            case R.id.about:
                showDialog();

                break;

        }
        return super.onOptionsItemSelected(item);
    }

    public void showDialog(){
        AlertDialog.Builder mBuilder=new AlertDialog.Builder(LogInActivity.this);

        mBuilder.setTitle("About The App");
        mBuilder.setMessage("This app introduces us to digital attendance system.Using this app,the teacher can take and view the attendance of the students according to their departments and year.");
        mBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });
        AlertDialog dialog=mBuilder.create();
        dialog.show();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void log(View view) {
        s1=etusername.getText().toString();
        s2=etpassword.getText().toString();
        if(etusername.getText().toString().equals("") && etpassword.getText().toString().equals("")){
            etusername.setError("Please enter the following field");
            etpassword.setError("Please enter the following field");}
            else if(etusername.getText().toString().equals(""))
    etusername.setError("Please enter the following field");
      else if(etpassword.getText().toString().equals(""))
            etpassword.setError("Please enter the following field");

    else{
        stringRequest=new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("You are Welcome")){
                            pDialog.hide();
                            openUser();
                        }else{
                            etusername.setText("");
                            etpassword.setText("");
                            etusername.requestFocus();
                            pDialog.hide();
                            showMsg("Log In Failed","Please check your email and password");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                         ;
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

    private void openUser() {
        Intent intent=new Intent(this,Department.class);
        intent.putExtra("Email",s1);
        startActivity(intent);
    }

    public void register(View view) {
        Intent intent2=new Intent(this,RegisterActivity.class);
        startActivity(intent2);

    }
    public void showMsg(String title, String msg){
        android.support.v7.app.AlertDialog.Builder mBuilder=new android.support.v7.app.AlertDialog.Builder(LogInActivity.this);

        mBuilder.setTitle(title);
        mBuilder.setMessage(msg);
        mBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                etusername.setText("");
                etpassword.setText("");
                etusername.requestFocus();
                dialog.dismiss();
            }
        });
        android.support.v7.app.AlertDialog dialog=mBuilder.create();
        dialog.show();
    }
}

