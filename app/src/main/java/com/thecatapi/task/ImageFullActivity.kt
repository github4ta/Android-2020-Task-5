package com.thecatapi.task

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import kotlinx.android.synthetic.main.activity_image_full.*


class ImageFullActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_full)

        val catId = intent.getStringExtra("CAT_ID")
        val catImageUrl = intent.getStringExtra("CAT_IMAGE_URL")

        supportActionBar?.title = catId
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        fullImageView.load(catImageUrl)
    }

    override fun onSupportNavigateUp(): Boolean {
        super.onBackPressed();
        Animatoo.animateSlideLeft(this);
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.full_image_action_bar, menu)
        return true
    }
}