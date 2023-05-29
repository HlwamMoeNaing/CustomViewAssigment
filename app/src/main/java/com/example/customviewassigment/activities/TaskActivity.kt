package com.example.customviewassigment.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.customviewassigment.R
import com.example.customviewassigment.adapters.ProfileImageAdapter
import com.example.customviewassigment.adapters.ProfileImageItemDecoration
import com.example.customviewassigment.delegate.MainItemDelegate
import kotlinx.android.synthetic.main.activity_task.*


class TaskActivity : AppCompatActivity(), MainItemDelegate {

    private lateinit var mProfileImageTaskAdapter: ProfileImageAdapter

    companion object {
        fun newIntent(context: Context) : Intent {
            return Intent(context,TaskActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)

        changeStatusBar(R.color.taskScreenBackgroundColor)
        setUpProfileImageRecyclerView()
        setUpListeners()
    }

    private fun setUpListeners() {

        btnBackMainTask.setOnClickListener {
            finish()
        }

        chipDesign.setOnClickListener {
            if(chipDesign.isChecked) {
                chipDesign.setChipBackgroundColorResource(R.color.colorAccent)
            } else {
                chipDesign.setChipBackgroundColorResource(android.R.color.transparent)
            }
        }

        chipFrontend.setOnClickListener {
            if(chipFrontend.isChecked) {
                chipFrontend.setChipBackgroundColorResource(R.color.colorAccent)
            } else {
                chipFrontend.setChipBackgroundColorResource(android.R.color.transparent)
            }
        }

        chipBackend.setOnClickListener {
            if(chipBackend.isChecked) {
                chipBackend.setChipBackgroundColorResource(R.color.colorAccent)
            } else {
                chipBackend.setChipBackgroundColorResource(android.R.color.transparent)
            }
        }
    }

    private fun changeStatusBar(color: Int) {
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, color)
    }

    private fun setUpProfileImageRecyclerView() {
        mProfileImageTaskAdapter = ProfileImageAdapter(this)
        rvProfileImageTask.adapter = mProfileImageTaskAdapter
        rvProfileImageTask.addItemDecoration(ProfileImageItemDecoration())
        rvProfileImageTask.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
    }

    override fun onTapProfileImage() {
        startActivity(ProfileActivity.newIntent(this))
    }

    override fun onTapTask() {

    }
}