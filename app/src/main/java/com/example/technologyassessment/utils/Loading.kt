package com.example.technologyassessment.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import com.example.technologyassessment.R

class Loading {
    private var dialog: Dialog? = null
    fun showLoading(ctx: Context?, message: String?, cancelable: Boolean) {
        dialog = Dialog(ctx!!)
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.setContentView(R.layout.loading)
        (dialog?.findViewById<View>(R.id.progress_text) as TextView).text = message
        dialog?.findViewById<View>(R.id.progress_text)?.visibility = View.VISIBLE
        dialog?.setCancelable(cancelable)
        dialog?.setCanceledOnTouchOutside(cancelable)
        dialog?.show()
    }

    fun hideLoading() {
        if (dialog != null) {
            dialog!!.dismiss()
            dialog = null
        }
    }

    companion object {
        private var loading: Loading? = null

        @JvmStatic
        val loadingInstance: Loading?
            get() {
                if (loading == null) {
                    loading = Loading()
                }
                return loading
            }
    }
}