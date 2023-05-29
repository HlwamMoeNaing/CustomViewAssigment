package com.example.customviewassigment.activities

import android.content.Context
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.customviewassigment.R
import com.example.customviewassigment.adapters.TaskAdapter
import com.example.customviewassigment.delegate.MainItemDelegate
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity(), MainItemDelegate {

    private val titleList = listOf("Project Tasks","Contacts","About You","Jobs")
    private lateinit var mTaskProfileAdapter: TaskAdapter


    companion object {
        fun newIntent(context: Context) : Intent {
            return Intent(context,ProfileActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        window.setBackgroundDrawable(ColorDrawable(R.layout.activity_main))
        window.setDimAmount(0f)

        changeStatusBar()

        setUpTitleTabLayout()
        setUpTaskProfileRecyclerView()
        setUpListeners()
    }

    private fun changeStatusBar() {
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, android.R.color.transparent)
        window.decorView.systemUiVisibility = 0
    }

    private fun setUpTitleTabLayout() {
        titleList.forEach {
            tlTitleProfile.newTab().apply {
                text = it
                tlTitleProfile.addTab(this)
            }
        }
    }

    private fun setUpListeners() {
        btnCancelProfile.setOnClickListener {
            finish()
        }
    }

    private fun setUpTaskProfileRecyclerView() {
        mTaskProfileAdapter = TaskAdapter(this)
        rvTaskProfile.adapter = mTaskProfileAdapter
        rvTaskProfile.layoutManager = LinearLayoutManager(this)
    }

    override fun onTapProfileImage() {

    }

    override fun onTapTask() {
        startActivity(TaskActivity.newIntent(this))
    }
}