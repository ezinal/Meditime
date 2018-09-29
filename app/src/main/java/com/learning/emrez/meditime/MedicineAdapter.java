package com.learning.emrez.meditime;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MedicineAdapter extends RecyclerView.Adapter<MedicineAdapter.MyViewHolder> {

    private Context mContext;
    private List<Medicine> medList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView medName;
        //public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            medName = (TextView) view.findViewById(R.id.title_text);
            //thumbnail = (ImageView) view.findViewById(R.id.card_medicine_img);
        }
    }


    public MedicineAdapter(Context mContext, List<Medicine> medList) {
        this.mContext = mContext;
        this.medList = medList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.medicine_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Medicine medicine = medList.get(position);
        holder.medName.setText(medicine.getMedName());

        // loading medicine cover using Glide library
        //Glide.with(mContext).load(medicine.getThumbnail()).into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return medList.size();
    }
}
