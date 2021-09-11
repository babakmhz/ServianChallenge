package com.babakmhz.servianchallenge.ui.base.main

import android.os.Bundle
import com.babakmhz.servianchallenge.R
import com.babakmhz.servianchallenge.ui.base.BaseActivity

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun initializeUi() {
    }
}