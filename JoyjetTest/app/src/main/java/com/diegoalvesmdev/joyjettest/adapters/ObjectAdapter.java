package com.diegoalvesmdev.joyjettest.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.diegoalvesmdev.joyjettest.R;
import com.diegoalvesmdev.joyjettest.StarterApplication;
import com.diegoalvesmdev.joyjettest.model.Object;

import java.util.List;

/**
 * Created by diegoalves on 03/02/2017.
 */

public class ObjectAdapter extends RecyclerView.Adapter<ObjectAdapter.PlaceHolder> {

    private List<Object> objects;
    private Context context;
    private OnClickObject onClickObject;

    public ObjectAdapter(List<Object> objects, Context context, OnClickObject onClickObject) {
        this.objects = objects;
        this.context = context;
        this.onClickObject = onClickObject;
    }

    public interface OnClickObject {
        void onClick(PlaceHolder holder, int position);
    }

    @Override
    public PlaceHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_object, parent, false);
        return new PlaceHolder(view);
    }

    @Override
    public void onBindViewHolder(final PlaceHolder holder, final int position) {
        Object object = objects.get(position);

        ImageLoader imageLoader = StarterApplication.getInstance().getImageLoader();

        holder.imageView.setImageUrl(object.getPhoto(), imageLoader);
        holder.textView.setText(object.getName());
        holder.textViewDesc.setText(object.getDescription());

        if (onClickObject != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickObject.onClick(holder, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    public class PlaceHolder extends RecyclerView.ViewHolder {

        public NetworkImageView imageView;
        TextView textView;
        TextView textViewDesc;

        PlaceHolder(View itemView) {
            super(itemView);

            imageView = (NetworkImageView) itemView.findViewById(R.id.image_list);
            textView = (TextView) itemView.findViewById(R.id.text_card_view);
            textViewDesc = (TextView) itemView.findViewById(R.id.textDesc_card_view);
        }
    }

}
