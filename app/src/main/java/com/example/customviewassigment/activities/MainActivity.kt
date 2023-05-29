package com.example.customviewassigment.activities

import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.customviewassigment.R
import com.example.customviewassigment.adapters.ProfileImageAdapter
import com.example.customviewassigment.adapters.ProfileImageItemDecoration
import com.example.customviewassigment.adapters.TaskAdapter
import com.example.customviewassigment.delegate.MainItemDelegate
import com.example.customviewassigment.utils.MDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_profile.*

class MainActivity : AppCompatActivity(), MainItemDelegate {

    private lateinit var mProfileImageAdapter: ProfileImageAdapter
    private lateinit var mTaskAdapter: TaskAdapter

    private var originalStatusBarColor = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpProfileImageRecyclerView()
        setUpTaskRecyclerView()
        setUpListeners()
    }

    private fun setUpListeners() {
        btnBackMain.setOnClickListener {
            finish()
        }
    }

    private fun setUpProfileImageRecyclerView() {
        mProfileImageAdapter = ProfileImageAdapter(this)
        rvProfileImage.adapter = mProfileImageAdapter
        rvProfileImage.addItemDecoration(ProfileImageItemDecoration())
        rvProfileImage.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setUpTaskRecyclerView() {
        mTaskAdapter = TaskAdapter(this)
        rvTask.adapter = mTaskAdapter
        rvTask.layoutManager = LinearLayoutManager(this)
    }

    private fun setUpProfileBottomSheet() {
        val dialog = MDialog(this)
        dialog.setContentView(R.layout.activity_profile)
        dialog.setCancelable(true)
        dialog.show()
        dialog.window?.setBackgroundDrawable(ColorDrawable(R.layout.activity_main))

        originalStatusBarColor = window.statusBarColor

        // Removing Status bar color while showing dialog
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setUpDialogListeners(dialog)
    }

    private fun restoreStatusBar() {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
        window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        window.decorView.systemUiVisibility = window.decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        window.statusBarColor = originalStatusBarColor
    }

    private fun setUpDialogListeners(dialog: MDialog) {

        dialog.setOnDismissListener {
            restoreStatusBar()
        }

        dialog.btnCancelProfile.setOnClickListener {
            dialog.dismiss()
            restoreStatusBar()
        }

        // For tab layout
        val titleList = listOf("Project Tasks", "Contacts", "About You", "Jobs")
        titleList.forEach {
            dialog.tlTitleProfile.newTab().apply {
                text = it
                dialog.tlTitleProfile.addTab(this)
            }
        }

        // For task recyclerview
        setUpTaskProfileRecyclerView(dialog)
    }

    private fun setUpTaskProfileRecyclerView(dialog: MDialog) {
        mTaskAdapter = TaskAdapter(this)
        dialog.rvTaskProfile.adapter = mTaskAdapter
        dialog.rvTaskProfile.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)


        dialog.rvTaskProfile.addOnItemTouchListener(object : RecyclerView.OnItemTouchListener {
            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                when (e.action) {
                    MotionEvent.ACTION_DOWN -> {
                        rv.parent.requestDisallowInterceptTouchEvent(false)
                    }
                    MotionEvent.ACTION_UP -> {
                        rv.parent.requestDisallowInterceptTouchEvent(false)
                    }
                }
                rv.onTouchEvent(e)
                return true
            }

            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
            }

            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}
        })
    }

    override fun onTapProfileImage() {
//        startActivity(ProfileScreenActivity.newIntent(this))
        setUpProfileBottomSheet()
    }

    override fun onTapTask() {
        startActivity(TaskActivity.newIntent(this))
    }
}