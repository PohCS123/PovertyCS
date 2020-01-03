package com.example.poverty.homepage

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.FutureTarget
import com.bumptech.glide.request.RequestOptions
import com.example.poverty.Database.RecycleViewPost
import com.example.poverty.MainActivity
import com.example.poverty.R
import com.squareup.picasso.Picasso
import java.io.File
import kotlin.coroutines.coroutineContext
import android.util.Log
import kotlinx.android.synthetic.main.layout_post_list_item.view.*


class PostAdapter internal constructor(context: Context) :
    RecyclerView.Adapter<PostAdapter.PostViewHolder>(){

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var posts = emptyList<RecycleViewPost>() // Cached copy of words

    /*var data = listOf<RecycleViewPost>() // Cached copy of words
        set(value) {
            field = value
            notifyDataSetChanged()
        }*/


    class PostViewHolder(itemView: View,var posted:RecycleViewPost?=null) : RecyclerView.ViewHolder(itemView) {

        companion object{
            val POST_TITLE_KEY="com.example.poverty.homepage.POSTTITLE"
            val POST_IMAGE_KEY="com.example.poverty.homepage.POSTIMAGE"
            val POST_DESC_KEY="com.example.poverty.homepage.POSTDESC"
        }

        init{
            itemView.setOnClickListener {
                print("Testing123")
                val intent = Intent(itemView.context,PostDetails::class.java)
                intent.putExtra(POST_TITLE_KEY,posted?.posttitle)
                intent.putExtra(POST_IMAGE_KEY,posted?.postImg)
                intent.putExtra(POST_DESC_KEY,posted?.postdesc)
                itemView.context.startActivity(intent)
            }
        }
        val postTitle: TextView = itemView.findViewById(R.id.post_title)
        val postDesc: TextView = itemView.findViewById(R.id.post_desc)
        val postImage: ImageView = itemView.findViewById(R.id.post_image)

        /*fun bind(post: RecycleViewPost){
            postTitle.setText(post.posttitle)
            postDesc.setText(post.postdesc)

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            GlideApp.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(post.postImg)
                .into(postImage)
        }*/
    }

    //telling recycle view to create different view holder in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        /*return PostViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_post_list_item,parent,false)
        )*/
        val itemView = inflater.inflate(R.layout.layout_post_list_item, parent, false)
        return PostViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        //create a variable for one item at a given position in the data.
        val current = posts.get(position)
        val title = current.postID.toString()
        Log.d("TAG", title)
        holder.postTitle.text = current.posttitle
        holder.postDesc.text = current.postdesc
        val thumbnailImage = holder?.itemView?.post_image
        Picasso.with(holder.itemView.context).load(current.postImg).into(thumbnailImage)
        /*when(holder){
            is PostViewHolder->{
                holder.bind(posts.get(position))
            }
        }*/

        holder?.posted = current

    }

    internal fun setPosts(posts: List<RecycleViewPost>) {
        this.posts = posts
        notifyDataSetChanged()
    }

    //how many items inside your list
    override fun getItemCount() = posts.size
}
