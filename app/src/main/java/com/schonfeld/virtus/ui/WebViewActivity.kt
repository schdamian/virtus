package com.schonfeld.virtus.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.schonfeld.virtus.databinding.ActivityWebviewBinding

class WebViewActivity : AppCompatActivity() {

    private lateinit var view: ActivityWebviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view = ActivityWebviewBinding.inflate(layoutInflater)
        setContentView(view.root)

        if (intent.hasExtra(EXTRA_URL))
            intent.extras!!.getString(EXTRA_URL)?.let { view.webView.loadUrl(it) }
        else
            finish()
    }

    companion object {
        const val EXTRA_URL = "extra_url"
    }
}