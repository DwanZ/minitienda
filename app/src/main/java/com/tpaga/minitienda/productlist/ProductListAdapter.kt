package com.tpaga.minitienda.productlist

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.tpaga.minitienda.R
import com.tpaga.minitienda.data.source.local.room.entities.LocalProduct



class ProductListAdapter(val buyAction:(localProduct:LocalProduct)->Unit) : RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {

    var mProductList: MutableList<LocalProduct>? = null

    fun setData(request: MutableList<LocalProduct>) {
        mProductList = request
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent!!.context)
        return ViewHolder(layoutInflater.inflate(R.layout.card_product, parent, false))
    }

    override fun getItemCount(): Int {
        return mProductList?.size ?: 0
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = mProductList!![position]

        holder.productName!!.text = product.name
        holder.productDescription!!.text = product.description
        holder.productPrice!!.text = "$ "+(product.price!!.toInt())
        when (product.name){
            "Shampoo" ->Picasso.get().load(R.drawable.shampoo).into(holder.image)
            "Control inalámbrico"->Picasso.get().load(R.drawable.control).into(holder.image)
            "Acondicionador"->Picasso.get().load(R.drawable.acondicionador).into(holder.image)
            "Atún Calvo"->Picasso.get().load(R.drawable.atun).into(holder.image)
            "Crema dental"->Picasso.get().load(R.drawable.crema_dental).into(holder.image)
            "Salsa de tomate Fruco"->Picasso.get().load(R.drawable.salsa).into(holder.image)
        }
        holder.button.setOnClickListener { buyAction(product) }

    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productName: TextView? = itemView.findViewById(R.id.productName)
        val productDescription: TextView? = itemView.findViewById(R.id.productDescription)
        val productPrice : TextView? = itemView.findViewById(R.id.productPrice)
        val image : ImageView = itemView.findViewById(R.id.productImage)
        val button:Button = itemView.findViewById(R.id.actionBuy)
    }

}