package net.jo.chat;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;

import java.util.List;

/**
 * Created by Johnny on 2017/12/3.
 */

public class MsgAdapter extends RecyclerView.Adapter {
    private final String TAG = "MsgAdapter";
    DataSnapshot dataSnapshot;
    private List<DataSnapshot> dataList;

    public MsgAdapter(List<DataSnapshot> dataList) {
        this.dataSnapshot = dataSnapshot;
        this.dataList = dataList;
        Log.d(TAG, "dataList = " + dataList.toString());
    }

    private class JoViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout msgLL;
        public TextView name;
        public TextView msgWord;
        public ImageView msgImage;

        public JoViewHolder(View itemView, int viewType) {
            super(itemView);

            msgLL = itemView.findViewById(R.id.msgLL);
            name = itemView.findViewById(R.id.name);
            msgWord = itemView.findViewById(R.id.msgWord);
            msgImage = itemView.findViewById(R.id.msgImage);

            switch (viewType) {
                case JoMsg.MSG_TEXT:
                    Log.d(TAG, "viewType = TEXT");
                    msgWord.setVisibility(View.VISIBLE);
                    msgImage.setVisibility(View.GONE);
                    break;
                case JoMsg.MSG_IMAGE:
                    Log.d(TAG, "viewType = IMAGE");
                    msgWord.setVisibility(View.GONE);
                    msgImage.setVisibility(View.VISIBLE);
                    break;
            }
        }
    }

//    @Override
//    public int getItemViewType(int position) {
//        JoMsg joMsg = dataList.get(position).getValue(JoMsg.class);
//        int viewType = (joMsg.getPhotoUrl() == null) ? JoMsg.MSG_TEXT : JoMsg.MSG_IMAGE;
//        return viewType;
//    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_msg, parent, false);
        JoMsg joMsg = dataSnapshot.getValue(JoMsg.class);
        int type = (joMsg.getPhotoUrl() == null) ? JoMsg.MSG_TEXT : JoMsg.MSG_IMAGE;

        Log.d(TAG, "onCreateViewHolder, viewType = " + viewType);
        return new JoViewHolder(view, type);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder(), viewType = " + holder.getItemViewType());
        JoMsg joMsg = dataSnapshot.getValue(JoMsg.class);
        ((JoViewHolder) holder).name.setText(joMsg.getName());
//        joMsg.viewType = (joMsg.getPhotoUrl() == null) ? JoMsg.MSG_TEXT : JoMsg.MSG_IMAGE;
//        switch (joMsg.viewType) {
//            case JoMsg.MSG_TEXT:
//                ((JoViewHolder) holder).msgWord.setText(joMsg.getText());
//                break;
//            case JoMsg.MSG_IMAGE:
////                ((JoViewHolder) holder).msgImage.setText(dataSnapshot.getValue(JoMsg.class).getText());
//                break;
//        }
    }

    @Override
    public int getItemCount() {
//        return ((int) dataSnapshot.getChildrenCount());
        return dataList.size();
    }
}
