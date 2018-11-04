package io.synople.truffle.vendor.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.synople.truffle.common.model.Item
import io.synople.truffle.vendor.R
import kotlinx.android.synthetic.main.card_item.view.*
import java.text.NumberFormat

class CardItemAdapter(
    private val items: MutableList<Item>,
    private val itemClick: (Item) -> Unit
) : RecyclerView.Adapter<CardItemAdapter.ViewHolder>() {
    class ViewHolder(private val v: View, private val itemClick: (Item) -> Unit) : RecyclerView.ViewHolder(v) {
        fun bind(item: Item) {

            // mother of god help
            when (item.name) {
                "Hot Dog" -> v.ivItem.setImageResource(R.drawable.hotdog)
                "Cheese Fries" -> v.ivItem.setImageResource(R.drawable.cheesefries)
                "Chili Cheese Fries" -> v.ivItem.setImageResource(R.drawable.chilicheesedog)
                "Curly Fries" -> v.ivItem.setImageResource(R.drawable.curlyfries)
                "Sweet Potato Fries" -> v.ivItem.setImageResource(R.drawable.sweetpotatofries)
                "Bean Salad" -> v.ivItem.setImageResource(R.drawable.beansalad)
                "Coleslaw" -> v.ivItem.setImageResource(R.drawable.coleslaw)
                "Onion Rings" -> v.ivItem.setImageResource(R.drawable.onionrings)
                "Mini Cheese Balls" -> v.ivItem.setImageResource(R.drawable.minicheeseballs)
                "Jalapeno Poppers" -> v.ivItem.setImageResource(R.drawable.jalapenopoppers)
                "Soda" -> v.ivItem.setImageResource(R.drawable.soda)
                "Lemonade" -> v.ivItem.setImageResource(R.drawable.lemonade)
                "Jarritos" -> v.ivItem.setImageResource(R.drawable.jarritos)
                "Mineral Water" -> v.ivItem.setImageResource(R.drawable.mineralwater)
                "Churros" -> v.ivItem.setImageResource(R.drawable.churros)
                "Hush Puppies" -> v.ivItem.setImageResource(R.drawable.hushpuppies)
                "Ice Cream" -> v.ivItem.setImageResource(R.drawable.icecream)
                "Popsicle" -> v.ivItem.setImageResource(R.drawable.popsicle)
                "Corn Dog" -> v.ivItem.setImageResource(R.drawable.corndog)
                "Mozzarella Sticks" -> v.ivItem.setImageResource(R.drawable.mozzerallasticks)
                "Potato Chips" -> v.ivItem.setImageResource(R.drawable.potatochips)
                "Pretzel" -> v.ivItem.setImageResource(R.drawable.pretzel)
                "Pizza" -> v.ivItem.setImageResource(R.drawable.pizza)
                "Burger" -> v.ivItem.setImageResource(R.drawable.burger)
                "Funnel Cake" -> v.ivItem.setImageResource(R.drawable.funnelcake)
                "Quesadilla" -> v.ivItem.setImageResource(R.drawable.quesadilla)
                "Po' Boy" -> v.ivItem.setImageResource(R.drawable.poboy)
                "Pop-corn Shrimp" -> v.ivItem.setImageResource(R.drawable.popcornshrimp)
                "Corn" -> v.ivItem.setImageResource(R.drawable.corn)
            }

            v.tvItemName.text = item.name
            v.tvItemPrice.text = NumberFormat.getCurrencyInstance().format(item.price)

            v.setOnClickListener {
                itemClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_item, parent, false)
        return ViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, pos: Int) {
        holder.bind(items[pos])
    }

    override fun getItemCount() = items.size
}