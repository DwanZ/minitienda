package com.tpaga.minitienda.salelist

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.tpaga.minitienda.R
import com.tpaga.minitienda.data.source.local.room.entities.LocalSale

class SaleListAdapter : RecyclerView.Adapter<SaleListAdapter.ViewHolder>() {
    var mSaleList: MutableList<LocalSale>? = null

    fun setData(request: MutableList<LocalSale>) {
        mSaleList = request
        notifyDataSetChanged()
    }

    fun setData(item: LocalSale) {
        if (!mSaleList!!.contains(item)) {
            mSaleList!!.add(item)
        }
        notifyItemInserted(mSaleList!!.size)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SaleListAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent!!.context)
        return ViewHolder(layoutInflater.inflate(R.layout.card_sale, parent, false))
    }

    override fun getItemCount(): Int {
        return mSaleList?.size ?: 0
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val sale = mSaleList!![position]

        holder.saleName!!.text = "Compra Registrada "+ (position+1)
        holder.saleDate!!.text = sale.dateS
        holder.saleTotalPrice!!.text = "$ "+(sale.totalPrice!!.toInt())
        when(sale.statusS!!){
            "created" -> {holder.saleStatus!!.text ="Disponible pago"}
            "paid" -> {holder.saleStatus!!.text ="Pagada"}
            "failed" -> {holder.saleStatus!!.text ="Pago no exitoso"}
            "expired" -> {holder.saleStatus!!.text ="Expiró"}
            "delivered" -> {holder.saleStatus!!.text ="Entregada"}
            else -> {holder.saleStatus!!.text ="Reembolsada"}
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val saleName: TextView? = itemView.findViewById(R.id.saleName)
        val saleDate: TextView? = itemView.findViewById(R.id.saleDate)
        val saleTotalPrice : TextView? = itemView.findViewById(R.id.saleTotalPrice)
        val saleStatus : TextView? = itemView.findViewById(R.id.saleStatus)
    }
}