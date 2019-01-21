package com.tpaga.minitienda.productlist

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.tpaga.minitienda.R
import com.tpaga.minitienda.productlist.usecase.CreateProductsUseCase.Companion.createProducts
import com.tpaga.minitienda.productlist.usecase.CreateSaleRequestUseCase.Companion.createSaleRequestUseCase
import com.tpaga.minitienda.productlist.usecase.GetProductsUseCase.Companion.getProductsUseCase
import com.tpaga.minitienda.productlist.usecase.SaveLocalSaleUseCase.Companion.saveLocalSaleUseCase
import com.tpaga.minitienda.salelist.SaleListActivity
import com.tpaga.minitienda.utils.exception.ErrorMessageFactory.Companion.errorMessageFactory
import com.tpaga.minitienda.utils.extensions.addFragment
import com.tpaga.minitienda.utils.extensions.findFragment

class ProductListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)
        val fragment = findFragment(R.id.contentFrame)?:
        addFragment(ProductListFragment.newInstance(), R.id.contentFrame)

        ProductListPresenter(
            fragment,
            getProductsUseCase(),
            createProducts(),
            createSaleRequestUseCase(),
            saveLocalSaleUseCase(),
            errorMessageFactory())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_p, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.showSales) {
            val intent = Intent(this, SaleListActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
