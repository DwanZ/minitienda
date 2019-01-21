package com.tpaga.minitienda.data.source.remote.api

import android.annotation.SuppressLint
import android.content.Context
import android.util.Base64
import com.sumset.patiosygruas.utils.exception.WTFException
import io.reactivex.Observable
import com.tpaga.minitienda.data.source.local.room.entities.LocalProduct
import com.tpaga.minitienda.data.source.remote.entities.SaleRequest
import com.tpaga.minitienda.utils.exception.NotPermissionException
import com.tpaga.minitienda.utils.exception.SaleAlreadyExistException
import io.reactivex.functions.Function
import okhttp3.*
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*


class MinitiendaAPI:APIBuilder {

    @SuppressLint("SimpleDateFormat")
    override fun createSaleRequest(product: LocalProduct):Observable<SaleRequest> {
        return Observable.fromCallable{
            val client = OkHttpClient()
            val expiration = Calendar.getInstance()
            expiration.add(Calendar.MONTH, 1)
            val sdf = SimpleDateFormat("yyyy-MM-dd")
            sdf.timeZone = expiration.timeZone
            sdf.format(expiration.time)
            val jsonObj = JSONObject()
            val p = product.price!!.toInt()
            jsonObj.put("cost",p)
            jsonObj.put("purchase_details_url","https://minitienda.com/compras")
            jsonObj.put("voucher_url","https://minitienda.com/comprobante")
            jsonObj.put("idempotency_token",product.id)
            jsonObj.put("order_id",product.id)
            jsonObj.put("terminal_id","centro")
            jsonObj.put("purchase_description","Compra en minitienda")
            jsonObj.put("user_ip_address","61.1.224.56")
            jsonObj.put("expires_at",sdf.format(expiration.time)+"T23:55:00.549653+00:00")
            val request = Request.Builder()
                .addHeader("Authorization",AUTH_HEADER)
                .addHeader("Cache-Control","no-cache")
                .addHeader("Content-Type","application/json")
                .url("https://stag.wallet.tpaga.co/merchants/api/v1/payment_requests/create")
                .post(RequestBody.create(MediaType.parse("application/json"),jsonObj.toString()))
                .build()
            client.newCall(request).execute()
        }.onErrorResumeNext(Function { error ->
            Observable.error(error)
        }).flatMap { response ->
            val json = JSONObject(response.body()!!.string())
            when(response.code()){
                422 -> return@flatMap Observable.error<SaleRequest>(SaleAlreadyExistException())
                401,403 -> return@flatMap Observable.error<SaleRequest>(NotPermissionException())
                409 -> {
                    val data = json.getJSONObject("data")
                    val sdf = SimpleDateFormat("yyyy-MM-dd h:m a")
                    val currentDate = sdf.format(Date())
                    val sale = SaleRequest(data.getString("token"),data.getString("tpaga_payment_url"),
                        data.getString("status"),product.price!!,currentDate,product.id)
                    return@flatMap Observable.just(sale)
                }
                200,201 -> {
                    val sdf = SimpleDateFormat("yyyy-MM-dd h:m a")
                    val currentDate = sdf.format(Date())
                    val sale = SaleRequest(json.getString("token"),json.getString("tpaga_payment_url"),
                        json.getString("status"),product.price!!,currentDate,product.id)
                    return@flatMap Observable.just(sale)
                }
                else -> return@flatMap Observable.error<SaleRequest>(WTFException())
            }
        }
    }

    companion object {
        val text = "miniapp-gato3:miniappma-123"
        val AUTH_HEADER ="Basic "+Base64.encodeToString(text.toByteArray(charset("UTF-8")), Base64.NO_WRAP)

        private var INTANCE_PG_API: MinitiendaAPI? = null
        fun Context.minitiendaAPI(): MinitiendaAPI {
            if (INTANCE_PG_API == null) {
                INTANCE_PG_API = MinitiendaAPI()
            }
            return INTANCE_PG_API!!
        }
    }
}