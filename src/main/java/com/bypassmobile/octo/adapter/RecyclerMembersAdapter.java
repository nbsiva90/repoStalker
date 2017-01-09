package com.bypassmobile.octo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bypassmobile.octo.FollowerActivity;
import com.bypassmobile.octo.R;
import com.bypassmobile.octo.image.ImageLoader;
import com.bypassmobile.octo.model.User;
import com.bypassmobile.octo.utils.RoundedTransformation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sivanatarajanbalasubramania on 12/28/16.
 */
public class RecyclerMembersAdapter extends RecyclerView.Adapter<RecyclerMembersAdapter.MemberViewHolder> {

    private List<User> mDataset;
    private Context mContext;


    private final View.OnClickListener memberOnClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Toast.makeText(mContext, "Clicked", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(mContext, FollowerActivity.class);
            mContext.startActivity(intent);
        }
    };

    public static class MemberViewHolder extends RecyclerView.ViewHolder{
        private TextView mTxtName;
        private ImageView mImgAvatar;

        public MemberViewHolder(View v){
            super(v);
            mTxtName = (TextView) v.findViewById(R.id.txt_item_name);
            mImgAvatar = (ImageView) v.findViewById(R.id.img_item_display);
        }
    }

    public RecyclerMembersAdapter(Context context, List<User> dataset){
        this.mContext = context;
        this.mDataset = dataset;
    }

    @Override
    public RecyclerMembersAdapter.MemberViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_member_item, parent, false);
        MemberViewHolder vh = new MemberViewHolder(v);
        v.setOnClickListener(memberOnClickListener);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerMembersAdapter.MemberViewHolder holder, int position) {
        User user  = mDataset.get(position);
        holder.mTxtName.setText(user.getName());
        ImageLoader.createImageLoader(mContext).load(user.getProfileURL()).transform(new RoundedTransformation()).into(holder.mImgAvatar);

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
