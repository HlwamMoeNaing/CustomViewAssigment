package com.example.customviewassigment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.customviewassigment.R
import com.example.customviewassigment.delegate.MainItemDelegate
import com.example.customviewassigment.views.viewholders.TaskViewHolder

class TaskAdapter(private val delegate: MainItemDelegate) : RecyclerView.Adapter<TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_pod_task, parent, false)
        return TaskViewHolder(view,delegate)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 10
    }
}