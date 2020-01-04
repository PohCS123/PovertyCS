package com.example.poverty.homepage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.poverty.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_post_details.*

class PostDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_details)

        val navigationVarTitle = intent.getStringExtra(PostAdapter.PostViewHolder.POST_TITLE_KEY)
        supportActionBar?.setTitle(navigationVarTitle)
        textViewDetailsTitle.setText(navigationVarTitle)
        val navigationVarImage = intent.getStringExtra(PostAdapter.PostViewHolder.POST_IMAGE_KEY)
        Picasso.with(applicationContext).load(navigationVarImage).into(imageViewPostDetails);
        val navigationVarDesc = intent.getStringExtra(PostAdapter.PostViewHolder.POST_DESC_KEY)
        textViewDetailsDes.setText(navigationVarDesc)
        //supportActionBar.set
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_actionback_24dp)
    }


}
