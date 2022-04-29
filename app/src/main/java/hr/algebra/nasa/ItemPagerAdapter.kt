package hr.algebra.nasa

import android.app.AlertDialog
import android.content.ContentUris
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import hr.algebra.nasa.framework.startActivity
import hr.algebra.nasa.model.Item
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import java.io.File

class ItemPagerAdapter(private val context: Context, private val items: MutableList<Item>) :
    RecyclerView.Adapter<ItemPagerAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivItem = itemView.findViewById<ImageView>(R.id.ivItem)
        private val tvRoverName = itemView.findViewById<TextView>(R.id.tvRoverName)
        private val tvLandingDate = itemView.findViewById<TextView>(R.id.tvLandingDate)
        private val tvStatus = itemView.findViewById<TextView>(R.id.tvStatus)
        private val tvPictureDate = itemView.findViewById<TextView>(R.id.tvPictureDate)
        private val tvCameraSensor = itemView.findViewById<TextView>(R.id.tvCameraSensor)
        fun bind(item: Item) {

            Picasso.get()
                //.load(File(item.picturePath))
                .load(item.picturePath)
                .error(R.drawable.nasa)
                .transform(RoundedCornersTransformation(50, 2))
                .into(ivItem)

            tvRoverName.text = item.roverName
            tvLandingDate.text = item.landingDate
            tvStatus.text = item.roverStatus
            tvPictureDate.text = item.earthDate
            tvCameraSensor.text = item.cameraName
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_pager, parent, false)
    )


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size
}