package developers.android.attendencemanager;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class TeacherStudentSpinnerECE2 extends AppCompatActivity {

    Spinner spnmode;
Toolbar toolbar;
    TextView btnlogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_student_spinner_ece2);
        spnmode=(Spinner)findViewById(R.id.spnmode);
        toolbar=findViewById(R.id.toolbar);
        btnlogout=findViewById(R.id.btnlogout);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String[] modeList={"","Student Activity","Teacher Activity"};
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,modeList);

        spnmode.setAdapter(adapter);

        spnmode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 1:
                        Intent intent1=new Intent(getApplicationContext(),StudentECE2.class);
                        startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2=new Intent(getApplicationContext(),TeacherECE2.class);
                        startActivity(intent2);
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    public void logout(View view) {
        showDialoglogout();
    }
    public void showDialoglogout(){
        AlertDialog.Builder mBuilder=new AlertDialog.Builder(TeacherStudentSpinnerECE2.this);

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


