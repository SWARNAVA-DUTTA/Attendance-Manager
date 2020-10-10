package developers.android.attendencemanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MyCustomAdapter extends BaseAdapter {
String l_names[];
    String[] developer_name;
    int[] imageos;
    Context c;

    LayoutInflater li;      //LayoutInflater is android System Service Convert XML to Java Object

    MyCustomAdapter(String[] l_names, int[] imageos, String[] developer_name, DevelopersActivity tech){

        c=tech;
        li=LayoutInflater.from(c);
            //from() takes Context as an argument and returns LayoutInflater reference
        this.l_names=l_names;
        this.imageos=imageos;
        this.developer_name=developer_name;
    }

    @Override
    public int getCount() {
        return l_names.length;
    }

    @Override
    public Object getItem(int pos) {
        return pos;
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //inflate method says What you want to inflate and where you want to inflate your View
        MyViewHolder holder=null;
        if(convertView==null) {
            convertView = li.inflate(R.layout.layout_c_adapter, null);
            holder=new MyViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder=(MyViewHolder) convertView.getTag();
        }

        holder.tv_lang.setText(developer_name[position]);
        holder.tv_type.setText(l_names[position]);
        holder.imageView.setImageResource(imageos[position]);

       /* convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(c, "College: "+college_name[position], Toast.LENGTH_LONG).show();
            }
        });*/
        return convertView;
    }

    class MyViewHolder{
        TextView tv_lang;
        TextView tv_type;
        ImageView imageView;

        MyViewHolder(View v){
            tv_lang=(TextView)v.findViewById(R.id.tv_lang);
            tv_type=(TextView)v.findViewById(R.id.tv_type);
            imageView=(ImageView)v.findViewById(R.id.iv);
        }
    }
}
