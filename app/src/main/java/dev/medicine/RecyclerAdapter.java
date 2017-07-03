package dev.medicine;

import android.content.Context;
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

class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {


    private final Context context;
    private List<DrugModel> drug_list;

    public RecyclerAdapter(Context context, List<DrugModel> drug_list) {
        this.context = context;
        this.drug_list = drug_list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycleview, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.drugImage.setImageResource(R.drawable.icon_drug_s);
        holder.drugName.setText(drug_list.get(position).getName());
        holder.drugRisk.setText(drug_list.get(position).getRisk());
    }

    @Override
    public int getItemCount() {
        return drug_list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView drugImage;
        TextView drugName;
        TextView drugRisk;

        public ViewHolder(View itemView) {
            super(itemView);
            drugImage = (ImageView)itemView.findViewById(R.id.item_image);
            drugName = (TextView)itemView.findViewById(R.id.item_name);
            drugRisk = (TextView)itemView.findViewById(R.id.item_risk);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    public void setFilter(ArrayList<DrugModel> newList){
        drug_list = new ArrayList<>();
        drug_list.addAll(newList);
        notifyDataSetChanged();
    }
}
