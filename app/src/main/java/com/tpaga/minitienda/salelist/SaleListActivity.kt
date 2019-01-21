package com.tpaga.minitienda.salelist

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.tpaga.minitienda.R
import com.tpaga.minitienda.productlist.ProductListActivity
import com.tpaga.minitienda.salelist.usecase.GetSalesUseCase.Companion.getSales
import com.tpaga.minitienda.utils.exception.ErrorMessageFactory.Companion.errorMessageFactory
import com.tpaga.minitienda.utils.extensions.addFragment
import com.tpaga.minitienda.utils.extensions.findFragment

class SaleListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sale_list)
        val fragment = findFragment(R.id.contentFrame)?:
        addFragment(SaleListFragment.newInstance(), R.id.contentFrame)

        SaleListPresenter(
            fragment,
            getSales(),
            errorMessageFactory())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_s, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.showProducts) {
            val intent = Intent(this, ProductListActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}
