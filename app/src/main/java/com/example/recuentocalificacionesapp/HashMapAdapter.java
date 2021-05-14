package com.example.recuentocalificacionesapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Map;

public class HashMapAdapter extends BaseAdapter {
    DatabaseHelper databaseHelper;
    private final ArrayList mData;
    private Context context;

    public HashMapAdapter(Context context, Map<String, Integer> map) {
        mData = new ArrayList();
        mData.addAll(map.entrySet());
        this.context=context;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Map.Entry<String, Integer> getItem(int position) {
        return (Map.Entry) mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final View result;

        databaseHelper = new DatabaseHelper(context);
        if (convertView == null) {
            result = LayoutInflater.from(context).inflate(R.layout.my_adapter_items, null, false);
        } else {
            result = convertView;
        }

        final Map.Entry<String, Integer> item = getItem(position);

        TextView t1 = (TextView) result.findViewById(R.id.text1);
        TextView t2 = (TextView) result.findViewById(R.id.text2);
        final ImageButton borrarAsgiantura = (ImageButton) result.findViewById(R.id.btn_borrar_asgiantura);

        t1.setText(item.getKey());
        t2.setText(item.getValue().toString());

        final String n=item.getKey();
        borrarAsgiantura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer bAsginatura = databaseHelper.deleteData(String.valueOf(n));
                if(bAsginatura>0){
                    Toast.makeText(context,"La asignatura se ha borrado!",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(context,"La asignatura no se ha borrado!",Toast.LENGTH_LONG).show();
                }
                mData.remove(position);
                notifyDataSetChanged();
            }
        });
        return result;
    }
}



