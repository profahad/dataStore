package com.versatilogics.apps.datastoreex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.asLiveData
import com.versatilogics.apps.datastoreex.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.first

class MainActivity : AppCompatActivity() {

    private val dataHandler by lazy {
        DataHandler(this)
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_main
        )
        setupObservers()
        setupListeners()
    }

    private fun setupObservers() {
        dataHandler.userNameFlow.asLiveData().observe(this, {
            binding.textView.text = "Welcome Mr.%s".format(it)
        })
    }

    private fun setupListeners() {
        binding.materialButton2.setOnClickListener {
            DataHandler(this).storeUser(26, "Muhammad")
        }
    }
}