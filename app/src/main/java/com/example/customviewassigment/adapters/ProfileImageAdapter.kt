package com.example.customviewassigment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.customviewassigment.R
import com.example.customviewassigment.delegate.MainItemDelegate
import com.example.customviewassigment.views.viewholders.ProfileImageViewHolder
import kotlinx.android.synthetic.main.pf_image_list_item.view.*

class ProfileImageAdapter(private val delegate: MainItemDelegate) : RecyclerView.Adapter<ProfileImageViewHolder>() {

    private val profileImageList = listOf(
        R.drawable.avg,
        R.drawable.spiderman,
        R.drawable.avg,
        R.drawable.plus
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileImageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pf_image_list_item, parent, false)
        return ProfileImageViewHolder(view,delegate)
    }

    override fun onBindViewHolder(holder: ProfileImageViewHolder, position: Int) {
        val profileImage = profileImageList[position]
        holder.itemView.ivProfileImage.setImageResource(profileImage)

        holder.itemView.setOnClickListener {
            if(holder.adapterPosition == profileImageList.lastIndex) {
                delegate.onTapTask()
            } else {
                delegate.onTapProfileImage()
            }
        }
    }

    override fun getItemCount(): Int {
        return profileImageList.size
    }
}