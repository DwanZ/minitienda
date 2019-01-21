@file:Suppress("DEPRECATION")

package com.tpaga.minitienda.productlist


import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tpaga.minitienda.BaseFragment
import android.support.v7.widget.RecyclerView
import com.tpaga.minitienda.R
import com.tpaga.minitienda.data.source.local.room.entities.LocalProduct
import kotlinx.android.synthetic.main.fragment_product_list.*
import android.content.Intent
import android.net.Uri


class ProductListFragment : BaseFragment<ProductListContract.Presenter>(),ProductListContract.View {

    private val mRecyclerView by lazy { activity!!.findViewById(R.id.recyclerProducts) as RecyclerView }
    private val mAdapter: ProductListAdapter by lazy { ProductListAdapter { product -> mPresenter.onBuyCLicked(product)} }
    private var sendingData: ProgressDialog? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_product_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mRecyclerView.layoutManager = LinearLayoutManager(this@ProductListFragment.context)
        mRecyclerView.adapter = mAdapter
    }

    override fun showLoading() {
        swipeRefresh.isRefreshing = true
    }

    override fun hideLoading() {
        swipeRefresh.isRefreshing = false
    }

    override fun showErrorMessage(error: String) {
        AlertDialog.Builder(context!!)
            .setTitle(R.string.warning)
            .setMessage(error)
            .setNegativeButton(R.string.accept) { _, _ ->  }
            .show()
    }

    override fun showProductList(pl: List<LocalProduct>) {
        mAdapter.setData(pl.toMutableList())
    }

    override fun showSendingData() {
        sendingData = ProgressDialog.show(context, getString(R.string.sending_data),
            getString(R.string.creating_sale), true)
    }

    override fun hideSendingData() {
        if (sendingData != null && sendingData!!.isShowing) {
            sendingData!!.dismiss()
        }
    }

    override fun showSuccessMessage(link:String) {
        AlertDialog.Builder(context!!)
            .setTitle("La compra a sido registrada")
            .setMessage("Puedes verificar tus compras en la sección de compras del menú")
            .setNegativeButton(R.string.accept) { _, _ -> showTpaga(link) }
            .setOnDismissListener { showTpaga(link) }
            .show()
    }

    override fun showTpaga(url:String) {
        val webpage = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, webpage)
        if (intent.resolveActivity(activity!!.packageManager) != null) {
            startActivity(intent)
        } else {
            showErrorMessage("El link de pago no se pudo resolver")
        }
    }

    companion object {
        fun newInstance() = ProductListFragment()
    }
}
