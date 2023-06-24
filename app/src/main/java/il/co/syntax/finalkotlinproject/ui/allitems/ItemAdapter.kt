package il.co.syntax.finalkotlinproject.ui.allitems

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import il.co.syntax.finalkotlinproject.databinding.ItemLayoutBinding
import il.co.syntax.finalkotlinproject.data.models.Item



class ItemAdapter(val items:List<Item>, private val callback: ItemListener) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    interface ItemListener {
        fun onItemClicked(index:Int)
    }

    inner class ItemViewHolder(private val binding:ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root),View.OnClickListener {

        init {
            binding.root.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            callback.onItemClicked(adapterPosition)
        }

        fun bind(item: Item) {
            binding.itemTitle.text = item.title
            binding.itemDescription.text = item.description
            binding.itemDate.text = item.date
            Glide.with(binding.root).load(item.photo).circleCrop().into(binding.itemImage)
        }
    }


    fun itemAt(position: Int) = items[position]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemViewHolder(ItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount() = items.size
}