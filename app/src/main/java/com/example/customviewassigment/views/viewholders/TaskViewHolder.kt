package com.example.customviewassigment.views.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.customviewassigment.delegate.MainItemDelegate
import kotlinx.android.synthetic.main.view_pod_task.view.*

class TaskViewHolder(itemView: View, private val delegate: MainItemDelegate) : RecyclerView.ViewHolder(itemView) {

    init {
        setUpListeners()
    }

    private fun setUpListeners() {
        itemView.ivProfileImageTask.setOnClickListener {
            delegate.onTapProfileImage()
        }

        itemView.setOnClickListener {
            delegate.onTapTask()
        }
    }
}