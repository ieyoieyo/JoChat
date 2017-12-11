package net.jo.chat;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Johnny on 2017/12/5.
 */

public class FireUIAdapter extends FirebaseRecyclerAdapter<JoMsg, FireUIAdapter.JoViewHolder> {

    private static String TAG = "FireUIAdapter";
    public static final int MSG_TEXT = 1;
    public static final int MSG_IMAGE = 2;

    public Context mContext;

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public FireUIAdapter(FirebaseRecyclerOptions options, Context context) {
        super(options);
        mContext = context;
    }

    @Override
    public int getItemViewType(int position) {
        return (getItem(position).getPhotoUrl() == null) ? MSG_TEXT : MSG_IMAGE;
    }

    @Override
    protected void onBindViewHolder(JoViewHolder joViewHolder, int position, JoMsg joMsg) {
        Log.d(TAG, "onBindViewHolder, position = " + position);
        joViewHolder.name.setText(joMsg.getName());
        switch (((int) joViewHolder.msgLL.getTag())) {
            case MSG_TEXT:
                Log.d(TAG, "viewType = TEXT");
                joViewHolder.msgWord.setText(joMsg.getText());
                break;
            case MSG_IMAGE:
                Log.d(TAG, "viewType = IMAGE");
                Glide.with(joViewHolder.msgImage.getContext())
                        .load(joMsg.getPhotoUrl())
                        .into(joViewHolder.msgImage);
                break;
        }
    }

    @Override
    public void onDataChanged() {
        // Called each time there is a new data snapshot. You may want to use this method
        // to hide a loading spinner or check for the "no documents" state and update your UI.
        ((MainActivity)mContext).recyclerView.smoothScrollToPosition(this.getItemCount());
    }

    @Override
    public void onError(DatabaseError e) {
        // Called when there is an error getting data. You may want to update
        // your UI to display an error message to the user.
        // ...
    }

    @Override
    public JoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder, viewType = " + viewType);

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_msg, parent, false);
        return new JoViewHolder(view, viewType);
    }


    public class JoViewHolder extends RecyclerView.ViewHolder {
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
                case MSG_TEXT:
                    Log.d(TAG, "viewType = TEXT");
                    msgLL.setTag(MSG_TEXT);
                    msgWord.setVisibility(View.VISIBLE);
                    msgImage.setVisibility(View.GONE);
                    break;
                case MSG_IMAGE:
                    Log.d(TAG, "viewType = IMAGE");
                    msgLL.setTag(MSG_IMAGE);
                    msgWord.setVisibility(View.GONE);
                    msgImage.setVisibility(View.VISIBLE);
                    break;
            }
        }
    }
}
