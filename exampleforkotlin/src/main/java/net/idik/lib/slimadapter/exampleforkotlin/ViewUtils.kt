package net.idik.lib.slimadapter.exampleforkotlin

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue

/**
 * Created on 6/6/21.
 */

fun getPixels(dipValue: Int, context: Context): Int {
    val r: Resources = context.resources
    return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dipValue.toFloat(),
            r.displayMetrics
    ).toInt()
}