package dev.medicine;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by dream on 4/13/2017 AD.
 */

class FoodRecyclerAdapter extends RecyclerView.Adapter<FoodRecyclerAdapter.ViewHolder> {
    Context context;
    List<FoodModel> food_list;

    public FoodRecyclerAdapter(Context context, List<FoodModel> food_list) {
        this.context = context;
        this.food_list = food_list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycleview, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.foodImage.setImageResource(R.drawable.icon_food_s);
        holder.foodName.setText(food_list.get(position).getName());
        holder.foodRisk.setText(food_list.get(position).getRisk());
    }

    @Override
    public int getItemCount() {
        return food_list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView foodImage;
        TextView foodName;
        TextView foodRisk;
        public ViewHolder(View itemView) {
            super(itemView);
            foodImage = (ImageView)itemView.findViewById(R.id.item_image);
            foodName = (TextView)itemView.findViewById(R.id.item_name);
            foodRisk = (TextView)itemView.findViewById(R.id.item_risk);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Log.i(TAG, "Food Click Item"+ foodName);
                }
            });
        }
    }
}
