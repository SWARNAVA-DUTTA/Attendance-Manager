package developers.android.attendencemanager;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class StudentCIVIL2 extends AppCompatActivity {

    CIVILDatabaseHelper2 mycivilDb2;
Toolbar toolbar;
    EditText idroll,idname;
    Button submit,update;
    TextView btnlogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_civil2);
        mycivilDb2 = new CIVILDatabaseHelper2(this);
        idroll=findViewById(R.id.idroll);
        idname=findViewById(R.id.idname);
        submit=findViewById(R.id.submit);
        update=findViewById(R.id.update);
        toolbar=findViewById(R.id.toolbar);
        btnlogout=findViewById(R.id.btnlogout);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void submitbtn(View v) {
        boolean isInserted=mycivilDb2.insertData(idroll.getText().toString(),idname.getText().toString());
        if((idroll.getText().toString().equals("")) && idname.getText().toString().equals("")){
            idroll.setError("Please enter the following field");
            idname.setError("Please enter the following field");
            idroll.requestFocus();}
        else if((idroll.getText().toString().equals(""))){
            idroll.setError("Please enter the following field");
            idroll.requestFocus();}
        else if((idname.getText().toString().equals(""))){
            idname.setError("Please enter the following field");
            idname.requestFocus();}
        else if(isInserted == true){
            showDialog();
            idroll.setText("");
            idname.setText("");
            idroll.requestFocus();}
        else{
            errorDialog();
            idroll.setText("");
            idname.setText("");
            idroll.requestFocus();
        }
    }
        public void showDialog(){
            AlertDialog.Builder mBuilder=new AlertDialog.Builder(StudentCIVIL2.this);

            mBuilder.setTitle("Thank You");
            mBuilder.setMessage("Have a nice day");
            mBuilder.setPositiveButton("Welcome", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    dialog.dismiss();
                }
            });
            AlertDialog dialog=mBuilder.create();
            dialog.show();
        }
        public void errorDialog(){
            AlertDialog.Builder mBuilder=new AlertDialog.Builder(StudentCIVIL2.this);

            mBuilder.setTitle("Sorry");
            mBuilder.setMessage("The roll no you entered is already present");
            mBuilder.setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    dialog.dismiss();
                }
            });
            AlertDialog dialog=mBuilder.create();
            dialog.show();
        }
    public void update(View view) {
        Boolean updateData=mycivilDb2.updateData(idroll.getText().toString(),idname.getText().toString());
        if((idroll.getText().toString().equals("")) && idname.getText().toString().equals("")){
            idroll.setError("Please enter the following field");
            idname.setError("Please enter the following field");
            idroll.requestFocus();}
        else if((idroll.getText().toString().equals(""))){
            idroll.setError("Please enter the following field");
            idroll.requestFocus();}
        else if((idname.getText().toString().equals(""))){
            idname.setError("Please enter the following field");
            idname.requestFocus();}
        else if(updateData == true){
            idroll.setText("");
            idname.setText("");
            idroll.requestFocus();
            showDialog1();}
        else
            errorDialog1();
        idroll.requestFocus();
    }
    public void showDialog1(){
        AlertDialog.Builder mBuilder=new AlertDialog.Builder(StudentCIVIL2.this);

        mBuilder.setTitle("Thank You");
        mBuilder.setMessage("Your data has been updated");
        mBuilder.setPositiveButton("Welcome", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });
        AlertDialog dialog=mBuilder.create();
        dialog.show();
    }
    public void errorDialog1(){
        AlertDialog.Builder mBuilder=new AlertDialog.Builder(StudentCIVIL2.this);

        mBuilder.setTitle("Sorry");
        mBuilder.setMessage("We faced some error uploading your data");
        mBuilder.setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });
        AlertDialog dialog=mBuilder.create();
        dialog.show();
    }

    public void logout(View view) {
        showDialoglogout();
    }
    public void showDialoglogout(){
        AlertDialog.Builder mBuilder=new AlertDialog.Builder(StudentCIVIL2.this);

        mBuilder.setTitle("Logging Out...");
        mBuilder.setMessage("Do you really want to log out?");
        mBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent log=new Intent(getApplicationContext(),LogInActivity.class);
                startActivity(log);
                dialog.dismiss();
            }
        });
        mBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });
        AlertDialog dialog=mBuilder.create();
        dialog.show();
    }
}

