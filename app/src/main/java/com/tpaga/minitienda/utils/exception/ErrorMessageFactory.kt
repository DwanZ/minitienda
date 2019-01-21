package com.tpaga.minitienda.utils.exception

import android.content.Context
import com.sumset.patiosygruas.utils.exception.EmptyProductListException
import com.sumset.patiosygruas.utils.exception.WTFException
import com.tpaga.minitienda.R
import java.net.ConnectException
import java.net.UnknownHostException

class ErrorMessageFactory private constructor(private val mContext: Context) {

    fun create(exception: Throwable): String {

        exception.printStackTrace()

        return when (exception) {
            is ConnectException -> mContext.getString(R.string.exception_connect_message)
            is WTFException -> mContext.getString(R.string.wtf_exception)
            is EmptyProductListException -> mContext.getString(R.string.no_products)
            is SaleAlreadyExistException -> mContext.getString(R.string.sale_already_exist)
            is DataSentErrorException -> mContext.getString(R.string.data_sent_error)
            is NotPermissionException -> mContext.getString(R.string.no_permissions)
            is UnknownHostException -> mContext.getString(R.string.conection_error)
            else -> "Error: "+exception.message!!
        }
    }

    companion object {
        fun Context.errorMessageFactory(): ErrorMessageFactory = ErrorMessageFactory(applicationContext)
    }
}
