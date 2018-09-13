package com.agroapp.mobile.agroapp.Adapters;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.agroapp.mobile.agroapp.Entities.Stage;
import com.agroapp.mobile.agroapp.R;

import java.util.List;

public class StageAdapter extends RecyclerView.Adapter<StageAdapter.MyViewHolder> {

    private List<Stage> stageList;
    private int postemp = 0;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, genre;
        public CardView cardView;
        public ImageView imageView;
        public TableLayout tableLayout;
        public TableRow tableRow;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.lbl_name);
            genre = (TextView) view.findViewById(R.id.lbl_description);
            year = (TextView) view.findViewById(R.id.lbl_image);
            cardView = (CardView) view.findViewById(R.id.card_stage);
            imageView = (ImageView) view.findViewById(R.id.imgstage);
            tableLayout = (TableLayout) view.findViewById(R.id.tbl_stage);
            tableRow = (TableRow) view.findViewById(R.id.row_stage);
        }
    }

    public StageAdapter(List<Stage> stagesList) {
        Stage s = new Stage("", "", "");
        int tam = stagesList.size()*2 - 1;
        for (int i = 1; i < tam; i+=2){
            stagesList.add(i, s);
        }
        this.stageList = stagesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.stage_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (position % 2 != 0) {
            holder.imageView.setImageResource(R.drawable.union);
            holder.cardView.setBackgroundColor(Color.TRANSPARENT);
            holder.tableLayout.setMinimumHeight(0);
        } else {
            Stage stage = stageList.get(position);
            holder.title.setText(stage.getName());
            holder.genre.setText(stage.getDescription());
            holder.year.setText(stage.getName());
            holder.tableRow.setPadding(20,5,20,5);
            holder.cardView.setRadius(20f);
            if (position == 0){
                holder.tableLayout.setMinimumHeight(300);
            }else {
                holder.tableLayout.setMinimumHeight(220);
            }
        }
    }

    @Override
    public int getItemCount() {
        return stageList.size();
    }
}
