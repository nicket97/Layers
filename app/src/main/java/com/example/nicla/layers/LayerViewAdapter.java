package com.example.nicla.layers;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

import butterknife.BindView;

/**
 * Created by nicla on 2017-07-21.
 */

 public class LayerViewAdapter extends ArrayAdapter<Layer> {
    Layer l;
        public LayerViewAdapter(Context context, ArrayList<Layer> users) {
            super(context, 0, users);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            Layer layer = getItem(position);
            l = layer;
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.layer_row, parent, false);
            }
            // Lookup view for data population
            TextView tvName = (TextView) convertView.findViewById(R.id.layerName);

            // Populate the data into the template view using the data object
            tvName.setText(layer.getName());

            final CheckBox visableBox = (CheckBox) convertView.findViewById(R.id.checkBox);
            visableBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
            {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
                {

                    l.visible(visableBox.isChecked());
                    visableBox.refreshDrawableState();
                    MainActivity.activity.repaint();

                }
            });

            ImageButton deleteLayer = (ImageButton) convertView.findViewById(R.id.layerDelete);
            deleteLayer.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    Layers.remove(l);
                    MainActivity.activity.repaint();
                }
            });

            // Return the completed view to render on screen
            return convertView;
        }


    }