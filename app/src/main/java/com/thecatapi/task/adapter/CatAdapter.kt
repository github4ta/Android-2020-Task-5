package com.thecatapi.task.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.thecatapi.task.ImageFullActivity
import com.thecatapi.task.R
import com.thecatapi.task.data.Cat


class CatAdapter : RecyclerView.Adapter<CatViewHolder>() {
    private val items = mutableListOf<Cat>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_item, null)
        return CatViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        val context = holder.itemView.context
        val intent = Intent(context, ImageFullActivity::class.java)
        val catId = items[position].catId ?: ""
        val catImageUrl = items[position].catImageUrl ?: ""
        holder.bind(catId, catImageUrl)

        holder.itemView.setOnClickListener {
            intent.putExtra("CAT_ID", catId)
            intent.putExtra("CAT_IMAGE_URL", catImageUrl)
            context.startActivity(intent)
            Animatoo.animateShrink(context)
        }
    }

    fun addItems(newItems: List<Cat>) {
        items.addAll(newItems)
        notifyDataSetChanged()
    }
}

class CatViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    //private val textView = view.findViewById<TextView>(R.id.textView)
    private val imageView = view.findViewById<ImageView>(R.id.imageView)

    fun bind(catId: String, catImageUrl: String) {
        //textView.text = catId
        imageView.load(catImageUrl)
    }
}