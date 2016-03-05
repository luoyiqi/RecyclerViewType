# RecyclerViewType

CommonAdapter 在 0.4 版本中加入了 RecyclerView 里 viewType 的支持，这里演示使用它借助 viewType 实现 RecyclerView 的多种布局。

## 主要原理

直接使用 RecyclerView 来实现 ViewType 时，需要做这样几件事：

1. 定义 N 种 ViewType 常量
2. 定义 N 个 ViewHolder（继承 RecyclerView.ViewHolder）
3. 在 OnCreateHolder 中重写创建 holder 的逻辑，也就是从 viewType 到 holder 的映射
4. 在 getItemViewType 中重写获得 ViewType 的逻辑，也就是从 item（实体）或者 position 到 viewType 的映射

在 CommonAdapter 中，对这个逻辑进行了简化，因为 viewType 的最终目的就是完成从实体或者位置到 Holder 的映射，所以在 CommonAdapter 中只需要做两件事：

1. 定义 N 中 ViewHolder（继承 CommonHolder<ViewTypeItem>）
2. 实现 ViewTypeMapper 接口（从 Item 或者 position 到 holder 的映射）

这里写了一个小例子，在一个 RecyclerView 中，有两种类型的数据，它们分别是文章（Article）和（照片）。它们分别使用不同的布局并且定了不同的 ViewHolder（这里继承的是 CommonHolder）

核心代码：

```Java
RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

MultiTypeAdapter adapter = new MultiTypeAdapter(this, new ViewTypeMapper() {
    @Override
    public Class<? extends CommonHolder<? extends ViewTypeItem>> getViewType(ViewTypeItem item, int position) {
        // 设定从实体类型到 Holder 类型的映射
        if (item instanceof Article) return ArticleHolder.class;
        if (item instanceof Photo) return PhotoHolder.class;
        return null;
    }
});

recyclerView.setAdapter(adapter);
```

Holder：

1. Article.java

```java
@LayoutId(R.layout.item_article)
public class ArticleHolder extends CommonHolder<Article> {

    @ViewId(R.id.textTitle) public   TextView textTitle;
    @ViewId(R.id.textContent) public TextView textContent;

    public ArticleHolder(View itemView) {
        super(itemView);
    }

    @Override public void bindData(Article article) {
        textTitle.setText(article.title);
        textContent.setText(article.content);
    }
}
```

2. PhotoHolder.java
```Java
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
```
