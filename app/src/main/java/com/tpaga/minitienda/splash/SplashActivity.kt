package com.tpaga.minitienda.splash

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.tpaga.minitienda.R
import com.tpaga.minitienda.productlist.ProductListActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val thread = object :Thread(){
            override fun run() {
                super.run()
                try {
                    Thread.sleep(2000)
                    startActivity(Intent(baseContext,ProductListActivity::class.java))
                }catch (e: Exception){
                    e.printStackTrace()
                }
            }
        }
        thread.start()
    }

}
