package com.thecatapi.task

import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import coil.ImageLoader
import coil.load
import coil.request.ImageRequest
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.thecatapi.task.Utils.Companion.toast
import kotlinx.android.synthetic.main.activity_image_full.*
import kotlinx.coroutines.launch


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
                    lifecycleScope.launch {
                        item.isEnabled = false
                        item.isVisible = false
                        val context = this@ImageFullActivity
                        val imageLoader = ImageLoader(context)
                        val request = ImageRequest.Builder(context)
                            .data(catImageUrl)
                            .build()
                        val drawable = imageLoader.execute(request).drawable
                        val uri = saveImage(drawable, catId.toString())
                        toast("Image saved : $uri")
                    }
                    true
                }
            }
            false
        }

        return true
    }

    private fun saveImage(drawable: Drawable?, title: String): Uri {
        val bitmap = (drawable as BitmapDrawable).bitmap
        val savedImageURL = MediaStore.Images.Media.insertImage(
            contentResolver,
            bitmap,
            title,
            "Image of $title"
        )

        return Uri.parse(savedImageURL)
    }
}