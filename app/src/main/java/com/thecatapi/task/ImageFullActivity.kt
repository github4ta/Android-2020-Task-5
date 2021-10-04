package com.thecatapi.task

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_image_full.*


class ImageFullActivity: AppCompatActivity() {
    private var catId: String? = ""
    private var catImageUrl: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_full)

        catId = intent.getStringExtra("CAT_ID")
        catImageUrl = intent.getStringExtra("CAT_IMAGE_URL")

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

        val mi = menu!!.findItem(R.id.btn_download_image)
        mi.setOnMenuItemClickListener {
                item ->
            when (item.itemId) {
                R.id.btn_download_image -> {
                    // put your code here
                    println("icon download is clicked.\nImage ${catId} will be downloaded from\n${catImageUrl}")
                    true
                }
            }
            false
        }

        return true
    }
}