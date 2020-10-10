package developers.android.attendencemanager;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class FeedbackActivity extends AppCompatActivity {
    String url = "https://swarnavadutta.000webhostapp.com/Attendance%20Manager/feedback.php";
    RequestQueue requestQueue;
    StringRequest stringRequest;
    Button feedback;
    Toolbar toolbar;
    String s1, s2;
    EditText editfeedback, editemail;
    ProgressDialog pDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        feedback = (Button) findViewById(R.id.feedback);
        editfeedback = findViewById(R.id.editfeedback);
        editemail=findViewById(R.id.editemail);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        pDialog= new ProgressDialog(this);
        pDialog.setMessage("Submitting your feedback...");

        requestQueue = Volley.newRequestQueue(getApplicationContext());


    }

    public void feedback(View view) {
        s1 = editemail.getText().toString();
        s2 = editfeedback.getText().toString();
        if (editemail.getText().toString().equals("") && editfeedback.getText().toString().equals("")) {
            editemail.setError("Please enter the following field");
            editfeedback.setError("Please enter the following field");
        } else if (editemail.getText().toString().equals(""))
            editemail.setError("Please enter the following field");
        else if (editfeedback.getText().toString().equals(""))
            editfeedback.setError("Please enter the following field");
        else {
            stringRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            pDialog.hide();
                            showMsg("Server Response", response);


                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            pDialog.hide();
                            showMsg("Page not reachable", "Check Your Internet Connection and try again");
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("Email", s1);
                    params.put("Comments", s2);
                    return params;
                }
            };
            requestQueue.add(stringRequest);
            pDialog.show();
        }
    }

    public void showMsg(String title, String msg) {
        android.support.v7.app.AlertDialog.Builder mBuilder = new android.support.v7.app.AlertDialog.Builder(FeedbackActivity.this);

        mBuilder.setTitle(title);
        mBuilder.setMessage(msg);
        mBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                editemail.setText("");
                editfeedback.setText("");
                editemail.requestFocus();
                dialog.dismiss();
            }
        });
        android.support.v7.app.AlertDialog dialog = mBuilder.create();
        dialog.show();
    }
}

