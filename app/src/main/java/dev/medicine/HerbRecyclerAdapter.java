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


/**
 * Created by dream on 4/13/2017 AD.
 */

class HerbRecyclerAdapter extends RecyclerView.Adapter<HerbRecyclerAdapter.ViewHoler> {
    private final Context context;
    private final List<HerbModel> herb_list;

    public HerbRecyclerAdapter(Context context, List<HerbModel> herb_list) {
        this.context = context;
        this.herb_list = herb_list;
    }

    @Override
    public ViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycleview, parent, false);
        ViewHoler viewHolder = new ViewHoler(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHoler holder, int position) {
        holder.herbImage.setImageResource(R.drawable.icon_herb_s);
        holder.herbName.setText(herb_list.get(position).getName());
        holder.herbRisk.setText(herb_list.get(position).getRisk());
    }

    @Override
    public int getItemCount() {
        return herb_list.size();
    }

    class ViewHoler extends RecyclerView.ViewHolder {
        ImageView herbImage;
        TextView herbName;
        TextView herbRisk;

        public ViewHoler(View itemView) {
            super(itemView);
            herbImage = (ImageView)itemView.findViewById(R.id.item_image);
            herbName = (TextView)itemView.findViewById(R.id.item_name);
            herbRisk = (TextView)itemView.findViewById(R.id.item_risk);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Log.i("HERB","HERB CLICK ITEM");
                }
            });
        }
    }
}
