package com.blackbox.ffmpeg.examples

import android.app.Activity
import android.media.ThumbnailUtils
import android.os.Build
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.MediaController
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView
import com.blackbox.ffmpeg.examples.dialogs.VideoDialog
import java.io.File

class VideoAdapter(var list: ArrayList<File>, var activity: Activity, var listner: itemSelectorInterface) :

        RecyclerView.Adapter<VideoAdapter.ViewHolder>() {


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

       val videoView =  itemView.findViewById<ImageView>(R.id.video_view)
       val play =  itemView.findViewById<ImageView>(R.id.play)
        init {
            play.setOnClickListener(this)
        }

        fun bind(file: File)
        {
            var thumbBitmap = ThumbnailUtils.createVideoThumbnail(
                        file.absolutePath,
                        MediaStore.Video.Thumbnails.FULL_SCREEN_KIND)

            videoView.setImageBitmap(thumbBitmap)

        }

        override fun onClick(p0: View?) {

            listner.onItemClick(list[adapterPosition])
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(
                R.layout.item_video_selecter, parent,
                false
        )
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(list[position])

    }

    interface itemSelectorInterface
    {
        fun onItemClick(path:File)
    }

}