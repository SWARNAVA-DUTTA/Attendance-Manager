package developers.android.attendencemanager;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TeacherCSE1 extends AppCompatActivity {
Toolbar toolbar;
    CSEDatabaseHelper1 mycseDb1;
    Button read,delete;
    TextView resultshow;
    TextView btnlogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_cse1);
        mycseDb1 = new CSEDatabaseHelper1(this);
        read=findViewById(R.id.read);
        toolbar=findViewById(R.id.toolbar);
        btnlogout=findViewById(R.id.btnlogout);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        resultshow=findViewById(R.id.resultshow);
        resultshow.setMovementMethod(new ScrollingMovementMethod());
        }

        public void readbtn(View view) {
        Cursor res = mycseDb1.getAllData();
        StringBuffer stringBuffer = new StringBuffer();
        if (res != null && res.getCount() > 0) {
            while (res.moveToNext()) {
                stringBuffer.append("•"+"Roll: " + res.getString(0) + "\n");
                stringBuffer.append("Name: " + res.getString(1) + "\n\n");
            }
            resultshow.setText(stringBuffer.toString());
            //Toast.makeText(this, "Hii", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Database is blank", Toast.LENGTH_SHORT).show();
        }
    }

    public void deletebtn(View view) {
        int deletedata=mycseDb1.deleteData(null,null);
        Toast.makeText(this, "DELETED", Toast.LENGTH_SHORT).show();
        resultshow.setText("");
        }
    public void logout(View view) {
        showDialoglogout();
    }
    public void showDialoglogout(){
        AlertDialog.Builder mBuilder=new AlertDialog.Builder(TeacherCSE1.this);

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

