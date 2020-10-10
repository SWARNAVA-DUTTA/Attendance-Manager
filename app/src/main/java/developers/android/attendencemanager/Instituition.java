package developers.android.attendencemanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

public class Instituition extends AppCompatActivity {
ImageView imageview;
TextView textview;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instituition);
        imageview=findViewById(R.id.imageview);
        textview=findViewById(R.id.textview);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        textview.setMovementMethod(new ScrollingMovementMethod());
        textview.setText("JIS College of Engineering is an engineering college located in West Bengal, India. The college was established in the millennium year 2000 by JIS Foundation and over the last decade grown in rapid strides to transform it into an Autonomous Institute." +
                "\n\t\t\t"+"The Institution is declared Autonomous by the University Grant Commission University Grants Commission (UGC) in 2011. The autonomous status conferred with qualification of 2(f) and 12(b). The Institution is approved by All India Council for Technical Education (AICTE) and affiliated to West Bengal University of Technology (WBUT). The Institution was accredited by National Assessment and Accreditation Council (NAAC) in 2009 and all its eligible technological departments are accredited by National Board of Accreditation (NBA) or under re accreditation process. Further, the Institution has qualified for the World Bank Grant of Rs. 4 crore under Technical Education Quality Improvement Programme (TEQIP) Phase II in the Subcomponent 1.2, for PG and Demand driven Research and Development and Innovation, as the only private self-financed institution from the State of West Bengal after three lead Universities JU, BESU and CU.");

    }
}
