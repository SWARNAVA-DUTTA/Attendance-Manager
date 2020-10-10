package developers.android.attendencemanager;



import android.content.Context;
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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Department extends AppCompatActivity {
    GridView gridView;
TextView btnlogout;

    String[] items = {"", "", "", "", "", ""};
    Integer[] logo = {R.drawable.imagescomputer, R.drawable.imagesinformation, R.drawable.imageselectronic, R.drawable.imageselectrical, R.drawable.imagescivil, R.drawable.imagesmechanical};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department);


        //call the CustomAdapter constructor and pass the values you want to be shown in the gridview
        CustomAdapter adapter = new CustomAdapter(Department.this, items, logo);

        gridView = (GridView) findViewById(R.id.gridView);
        btnlogout=findViewById(R.id.btnlogout);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                switch (pos) {
                    case 0:
                        Intent san0 = new Intent(Department.this, YearCSE.class);
                        startActivity(san0);
                        break;
                    case 1:
                        Intent san1 = new Intent(Department.this, YearIT.class);
                        startActivity(san1);
                        break;
                    case 2:
                        Intent san2 = new Intent(Department.this, YearECE.class);
                        startActivity(san2);
                        break;
                    case 3:
                        Intent san3 = new Intent(Department.this, YearEE.class);
                        startActivity(san3);
                        break;
                    case 4:
                        Intent san4 = new Intent(Department.this, YearCIVIL.class);
                        startActivity(san4);
                        break;
                    case 5:
                        Intent san5 = new Intent(Department.this, YearMECHANICAL.class);
                        startActivity(san5);
                        break;

                }


            }
        });

    }


    //create a seperate ArrayAdapter class for your specific layout design for the gridview
    class CustomAdapter extends ArrayAdapter<String> {

        private final Context context;
        private final String[] items;
        private final Integer[] logo;

        //the constructor gets the values to be shown
        public CustomAdapter(Context context, String[] items, Integer[] logo) {
            super(context, R.layout.custom_layout, items);
            this.context = context;
            this.items = items;
            this.logo = logo;

        }

        //getview method inflates the values given from the mainactivity on the custom design layout for gridview and returns the layout with inflated values in it
        @Override
        public View getView(int position, View view, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.custom_layout, null, true);

            //initialize the textview and imageview we declared in the custom_layout.xml file

            TextView title = (TextView) rowView.findViewById(R.id.text);

            ImageView image = (ImageView) rowView.findViewById(R.id.image);

            title.setText(items[position]);

            image.setImageResource(logo[position]);

            return rowView;
        }

    }
    public void logout(View view) {
        showDialoglogout();
    }
    public void showDialoglogout(){
        AlertDialog.Builder mBuilder=new AlertDialog.Builder(Department.this);

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

