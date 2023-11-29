package com.example.cashregisterapp_java;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ProductsRecyclerAdaptor extends RecyclerView.Adapter<ProductsRecyclerAdaptor.ProductViewHolder>{//step 2 : extend from adaptor class

    private ArrayList<History> histories;
    private OnItemClickListener onItemClickListener;
    public ProductsRecyclerAdaptor (ArrayList<History> histories, OnItemClickListener onItemClickListener){
        this.histories = histories;
        this.onItemClickListener = onItemClickListener;//step3
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = (parent.getContext());
        View view = LayoutInflater.from(context).inflate(R.layout.product_list_row,parent,false);
        return new ProductViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        History history = histories.get(position);
        holder.productName.setText(history.getName());
        holder.productQuantity.setText(String.valueOf(history.getQuantity()));
        holder.totalAmount.setText(String.valueOf(history.getTotalPrice()));
    }
    @Override
    public int getItemCount() {
        return histories.size();
    }

    // view holder class
    public class ProductViewHolder extends RecyclerView.ViewHolder{
        private TextView productName;
        private TextView productQuantity;
        private TextView totalAmount;
        public ProductViewHolder(@NonNull View itemView){
            super(itemView);
            productName = itemView.findViewById(R.id.topLeftTextView);
            productQuantity = itemView.findViewById(R.id.bottomLeftTextView);
            totalAmount = itemView.findViewById(R.id.topRightTextView);
            // Set an OnClickListener on the item view
            itemView.setOnClickListener(v -> onClick(v));
        }
        void onClick(View v){
            if (onItemClickListener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    onItemClickListener.onItemClick(position);
                }
            }
        }
    }

    // Interface for item click listener
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
