package com.example.customviewassigment.adapters

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ProfileImageItemDecoration : RecyclerView.ItemDecoration() {
    private val overlapValue = -30

    override fun getItemOffsets(outRect : Rect, view : View, parent : RecyclerView, state : RecyclerView.State) {
        outRect.set(0, 0, overlapValue, 0)
    }
}