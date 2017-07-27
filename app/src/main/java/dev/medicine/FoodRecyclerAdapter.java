package dev.medicine;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

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
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.foodImage.setImageResource(R.drawable.icon_food_s);
        holder.foodName.setText(food_list.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, item_detail.class);
                intent.putExtra("title", food_list.get(position).getName());
                intent.putExtra("risk", food_list.get(position).getRisk());
                intent.putExtra("reference", food_list.get(position).getReference());
                intent.putExtra("howTo", food_list.get(position).getHow_to());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return food_list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView foodImage;
        TextView foodName;
        TextView foodRisk;
        public ViewHolder(final View itemView) {
            super(itemView);
            foodImage = (ImageView)itemView.findViewById(R.id.item_image);
            foodName = (TextView)itemView.findViewById(R.id.item_name);
        }
    }

    public void setFilter(ArrayList<FoodModel> newList){
        food_list = new ArrayList<>();
        food_list.addAll(newList);
        notifyDataSetChanged();
    }
}
