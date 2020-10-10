package developers.android.attendencemanager;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

public class DevelopersActivity extends AppCompatActivity {
    ListView listView;
    String[] developer_name;
    Toolbar toolbar;

    String[] l_names={
            "amisha0212singh@gmail.com",
            "arghya0203@gmail.com",
            "snehaswati98@gmail.com",
            "swarnava.dutta97@gmail.com",
            "moutiyasha.giri99@gmail.com"
                    };

    MyCustomAdapter ca;

    int[] imageos={
            R.drawable.amisha,
            R.drawable.arghya,
            R.drawable.sneha,
            R.drawable.swarnava,
            R.drawable.tiyasha
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developers);
        Resources res=getResources();
        developer_name=res.getStringArray(R.array.developer_name);

        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView=(ListView)findViewById(R.id.lv);
        ca=new MyCustomAdapter(l_names,imageos,developer_name,this);
        listView.setAdapter(ca);

    }
}
