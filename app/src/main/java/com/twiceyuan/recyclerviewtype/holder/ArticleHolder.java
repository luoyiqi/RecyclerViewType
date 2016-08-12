package com.twiceyuan.recyclerviewtype.holder;

import android.widget.TextView;

import com.twiceyuan.commonadapter.library.LayoutId;
import com.twiceyuan.commonadapter.library.ViewId;
import com.twiceyuan.commonadapter.library.holder.CommonHolder;
import com.twiceyuan.recyclerviewtype.R;
import com.twiceyuan.recyclerviewtype.model.Article;

/**
 * Created by twiceYuan on 3/4/16.
 * Email: i@twiceyuan.com
 * Site: http://twiceyuan.com
 */
@LayoutId(R.layout.item_article)
public class ArticleHolder extends CommonHolder<Article> {

    @ViewId(R.id.textTitle) public   TextView textTitle;
    @ViewId(R.id.textContent) public TextView textContent;

    @Override public void bindData(Article article) {
        textTitle.setText(article.title);
        textContent.setText(article.content);
    }
}
