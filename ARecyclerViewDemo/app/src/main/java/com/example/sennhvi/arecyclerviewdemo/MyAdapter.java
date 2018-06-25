package com.example.sennhvi.arecyclerviewdemo;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.stream.IntStream;

/**
 * Created by sennhvi on 3/11/17.
 */
class MyAdapter extends RecyclerView.Adapter {  // 4. fulfill content by setAdapter

    class ViewHolder extends RecyclerView.ViewHolder { // 5. create ViewHolder by extending RecyclerView.ViewHolder

        private View root;

        private TextView tvTitle;

        private TextView tvContent;

        public ViewHolder(View root) { // 6. ViewHolder constructor
            super(root);

            // 12. get widget
            tvTitle = (TextView) root.findViewById(R.id.tv_title);
            tvContent = (TextView) root.findViewById(R.id.tv_content);
        }

        // 7. return itemView for accessing
        public TextView getTvTitle() { // 7. return itemView for accessing
            return tvTitle;
        }

        public TextView getTvContent() {
            return tvContent;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
         // 8. return custom ViewHolder, null as root layout
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
         // 9. add data to itemView, need downcast
        ViewHolder vh = (ViewHolder) holder;
        ItemData id = data[position];
        vh.getTvTitle().setText(id.title);
        vh.getTvContent().setText(id.content);

    }

    @Override
    public int getItemCount() {
        return data.length; // 11. return data length
    }


    private ItemData[] data = new ItemData[]{new ItemData("1","1.1"), new ItemData("2", "2.1"), new ItemData("3", "3.1"), new ItemData("4", "3.1"), new ItemData("5", "5.1"), new ItemData("6", "6.1")}; // 10. init data
//    private String[] data = new String[]{"1", "23", "234", "345"}; // 10. init data
}
