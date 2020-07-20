package com.byjus.headlines

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.byjus.headlines.di.database.Article
import com.byjus.headlines.di.pojo.Articles
import com.squareup.picasso.Callback
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import javax.inject.Inject

class RecyclerViewAdapter @Inject constructor( var clickListener: ClickListener) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    lateinit var articles:List<Article>
    lateinit var picasso:Picasso
    init {
        articles= listOf()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycleitem, parent, false))
    }

    override fun getItemCount(): Int {
       return articles.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.source.text=articles.get(position).source
        holder.date.text=articles.get(position).date
        holder.title.text=articles.get(position).title
        if(articles.get(position).urltoimage!=null){
            picasso.load(articles.get(position).urltoimage).fit().centerCrop().networkPolicy(
            NetworkPolicy.OFFLINE).into(holder.imageView,object: Callback {
                    override fun onSuccess() {

                    }

                    override fun onError(e: java.lang.Exception?) {
                         println(e?.message)
                         picasso.load(articles.get(position).urltoimage).fit().centerCrop().into(holder.imageView)
                    }
                })
        }
        holder.itemLayout.setOnClickListener { clickListener.launchIntent(articles.get(position)) }
    }

     class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val source: TextView
         val date: TextView
         val title: TextView
         val imageView:ImageView
         val itemLayout:ConstraintLayout
         init {
            source=itemView.findViewById(R.id.source)
             date=itemView.findViewById(R.id.date)
             title=itemView.findViewById(R.id.title)
             imageView=itemView.findViewById(R.id.image)
             itemLayout=itemView.findViewById(R.id.itemlayout)
         }
    }
     interface ClickListener {
        fun launchIntent(articles: Article)
    }
    fun setData(data: List<Article>) {
        this.articles=data
        notifyDataSetChanged()
    }
    fun setPiccaso(picasso: Picasso)
    {
        this.picasso=picasso
    }
}

