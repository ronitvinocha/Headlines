package com.byjus.headlines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import com.byjus.headlines.di.component.DaggerDetailsComponent
import com.byjus.headlines.di.component.DetailsComponent
import com.byjus.headlines.di.module.ActivityContextModule
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_description.*
import javax.inject.Inject

class DescriptionActivity : AppCompatActivity() {
    @Inject lateinit var picasso: Picasso
    lateinit var detailsComponent:DetailsComponent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_description)
        val applicationComponent=MyApplication.get(this).getapplicationComponent()
        detailsComponent= DaggerDetailsComponent.builder()
            .applicationComponent(applicationComponent).build()
        detailsComponent.inject(this)
        println(picasso)
        picasso.load(intent.getStringExtra("urltoimage")).fit().centerCrop().into(image)
        newstitle.text=intent.getStringExtra("title")
        description.text=intent.getStringExtra("description")
        date.text=intent.getStringExtra("date")
        source.text=intent.getStringExtra("source")
        back.setOnClickListener { onBackPressed() }
    }

}