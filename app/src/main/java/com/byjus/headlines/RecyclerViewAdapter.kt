package com.byjus.headlines

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.byjus.headlines.di.pojo.Articles
import com.squareup.picasso.Picasso
import javax.inject.Inject

class RecyclerViewAdapter @Inject constructor( var clickListener: ClickListener,var  picasso: Picasso) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    lateinit var articles:Array<Articles>
    init {
        articles= arrayOf(Articles())
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycleitem, parent, false))
    }

    override fun getItemCount(): Int {
       return articles.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.source.text=articles[position].source?.name
        holder.date.text=articles[position].publishedAt?.substring(0,10)
        holder.title.text=articles[position].title
        if(articles[position].urlToImage!=null)
        picasso.load(articles[position].urlToImage).fit().centerCrop().into(holder.imageView)
        holder.itemLayout.setOnClickListener { clickListener.launchIntent(articles[position]) }
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
        fun launchIntent(articles: Articles)
    }
    fun setData(data: Array<Articles>) {
        this.articles=data
        notifyDataSetChanged()
    }
}

