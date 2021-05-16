package com.work.kotinproject.utility

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.work.kotinproject.R
import java.net.URI

class load {
}
fun ImageView.loadImage(uri: String?){
    val options=RequestOptions()
        .placeholder(R.drawable.no_blocked_users)
        .circleCrop()
        .error(R.mipmap.ic_launcher)
    Glide.with(this.context)
        .setDefaultRequestOptions(options)
        .load(uri)
        .into(this)

}