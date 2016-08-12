package com.twiceyuan.recyclerviewtype;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.twiceyuan.commonadapter.library.adapter.MultiTypeAdapter;
import com.twiceyuan.recyclerviewtype.holder.ArticleHolder;
import com.twiceyuan.recyclerviewtype.holder.PhotoHolder;
import com.twiceyuan.recyclerviewtype.model.Article;
import com.twiceyuan.recyclerviewtype.model.Photo;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        assert recyclerView != null;

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        MultiTypeAdapter adapter = new MultiTypeAdapter(this);

        // 注册两种 ViewType，对应两种数据类型（必须在设置到 RecyclerView 上之前注册！）
        adapter.registerViewType(Photo.class, PhotoHolder.class);
        adapter.registerViewType(Article.class, ArticleHolder.class);

        recyclerView.setAdapter(adapter);

        for (int i = 0; i < 100; i++) {
            adapter.add(mockArticle(i));
            adapter.add(mockPhoto(i));
        }
    }

    public Article mockArticle(int seed) {
        Article article = new Article();
        article.title = getResources().getStringArray(R.array.mock_title)[seed % 4];
        article.content = getResources().getStringArray(R.array.mock_content)[seed % 4];
        return article;
    }

    public Photo mockPhoto(int seed) {
        Photo photo = new Photo();
        photo.photoId = new int[]{
                R.drawable.img_sample1,
                R.drawable.img_sample2,
                R.drawable.img_sample3,
                R.drawable.img_sample4
        }[seed % 4];
        photo.description = getResources().getStringArray(R.array.mock_img_desc)[seed % 4];
        return photo;
    }
}
