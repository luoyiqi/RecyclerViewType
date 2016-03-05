package com.twiceyuan.recyclerviewtype.holder;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.twiceyuan.commonadapter.library.LayoutId;
import com.twiceyuan.commonadapter.library.ViewId;
import com.twiceyuan.commonadapter.library.holder.CommonHolder;
import com.twiceyuan.recyclerviewtype.R;
import com.twiceyuan.recyclerviewtype.model.Photo;

/**
 * Created by twiceYuan on 3/4/16.
 * Email: i@twiceyuan.com
 * Site: http://twiceyuan.com
 */
@LayoutId(R.layout.item_photo)
public class PhotoHolder extends CommonHolder<Photo> {

    @ViewId(R.id.imagePicture) ImageView imagePicture;
    @ViewId(R.id.textDesc)     TextView  textDesc;

    public PhotoHolder(View itemView) {
        super(itemView);
    }

    @Override public void bindData(Photo photo) {
        Context context = getItemView().getContext();
        imagePicture.setImageDrawable(ContextCompat.getDrawable(context, photo.photoId));
        textDesc.setText(photo.description);
    }
}
