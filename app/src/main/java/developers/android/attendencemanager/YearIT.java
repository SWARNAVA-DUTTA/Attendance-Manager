package developers.android.attendencemanager;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


public class YearIT extends AppCompatActivity {
RadioGroup radioGroup;
Toolbar toolbar;
TextView btnlogout;
RadioButton firstyear,secondyear,thirdyear,fourthyear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_year_it);
        radioGroup=findViewById(R.id.radioGroup);
        firstyear=findViewById(R.id.firstyear);
        secondyear=findViewById(R.id.secondyear);
        thirdyear=findViewById(R.id.thirdyear);
        fourthyear=findViewById(R.id.fourthyear);
        toolbar=findViewById(R.id.toolbar);
        btnlogout=findViewById(R.id.btnlogout);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch(i){
                    case R.id.firstyear:
                        Intent intent=new Intent(getApplicationContext(),TeacherStudentSpinnerIT1.class);
                        startActivity(intent);
                        break;
                    case R.id.secondyear:
                        Intent intent2=new Intent(getApplicationContext(),TeacherStudentSpinnerIT2.class);
                        startActivity(intent2);
                        break;
                    case R.id.thirdyear:
                        Intent intent3=new Intent(getApplicationContext(),TeacherStudentSpinnerIT3.class);
                        startActivity(intent3);
                        break;
                    case R.id.fourthyear:
                        Intent intent4=new Intent(getApplicationContext(),TeacherStudentSpinnerIT4.class);
                        startActivity(intent4);
                        break;
                }
            }
        });
    }
    public void logout(View view) {
        showDialog();
    }
    public void showDialog(){
        AlertDialog.Builder mBuilder=new AlertDialog.Builder(YearIT.this);

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
