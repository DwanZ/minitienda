@file:Suppress("DEPRECATION")

package com.tpaga.minitienda.salelist


import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tpaga.minitienda.BaseFragment

import com.tpaga.minitienda.R
import com.tpaga.minitienda.data.source.local.room.entities.LocalSale
import kotlinx.android.synthetic.main.fragment_sale_list.*


@Suppress("DEPRECATION")
class SaleListFragment : BaseFragment<SaleListContract.Presenter>(),SaleListContract.View {

    private val mRecyclerView by lazy { activity!!.findViewById(R.id.recyclerProducts) as RecyclerView }
    private val mAdapter: SaleListAdapter by lazy { SaleListAdapter() }
    private var loadingData: ProgressDialog? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_sale_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mRecyclerView.layoutManager = LinearLayoutManager(this@SaleListFragment.context)
        mRecyclerView.adapter = mAdapter
    }

    override fun showLoading() {
        loadingData = ProgressDialog.show(context, getString(R.string.loading_purchases),
            "", true)
    }

    override fun hideLoading() {
        if (loadingData != null && loadingData!!.isShowing) {
            loadingData!!.dismiss()
        }
    }

    override fun showErrorMessage(error: String) {
        AlertDialog.Builder(context!!)
            .setTitle(R.string.warning)
            .setMessage(error)
            .setNegativeButton(R.string.accept) { _, _ ->  }
            .show()
    }

    override fun showProductList(pl: List<LocalSale>) {
        mAdapter.setData(pl.toMutableList())
        mRecyclerView.visibility = View.VISIBLE
        textViewSalesNotFound.visibility = View.INVISIBLE
    }

    override fun showSalesNotFound() {
        mRecyclerView.visibility = View.INVISIBLE
        textViewSalesNotFound.visibility = View.VISIBLE
    }

    companion object {
        fun newInstance() = SaleListFragment()
    }
}
