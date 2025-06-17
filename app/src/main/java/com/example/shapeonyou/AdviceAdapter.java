// AdviceAdapter.java
package com.example.shapeonyou;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class AdviceAdapter extends RecyclerView.Adapter<AdviceAdapter.AdviceViewHolder> {

    private final List<Advice> adviceList;

    public AdviceAdapter(List<Advice> adviceList) {
        this.adviceList = adviceList;
    }

    @NonNull
    @Override
    public AdviceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_advice, parent, false);
        return new AdviceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdviceViewHolder holder, int position) {
        Advice advice = adviceList.get(position);
        holder.tvTitle.setText(advice.getTitle());
        holder.tvDescription.setText(advice.getDescription());
    }

    @Override
    public int getItemCount() {
        return adviceList.size();
    }

    static class AdviceViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDescription;

        public AdviceViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvAdviceTitle);
            tvDescription = itemView.findViewById(R.id.tvAdviceDescription);
        }
    }
}
