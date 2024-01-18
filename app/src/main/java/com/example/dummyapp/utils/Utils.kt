package com.example.dummyapp.utils

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.dummyapp.R
import com.example.dummyapp.utils.Constants.AES_API_IV
import com.example.dummyapp.utils.Constants.AES_API_KEY
import com.example.dummyapp.utils.Constants.HEADER_AUTH_KEY
import java.math.BigDecimal
import java.util.Base64
import java.util.WeakHashMap
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

@RequiresApi(Build.VERSION_CODES.O)
fun encryptData(data: String): String {
    val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
    val secretKeySpec = SecretKeySpec(AES_API_KEY.toByteArray(), "AES")

    val finalIvs = ByteArray(16)
    val len = AES_API_IV.toByteArray().size.coerceAtMost(16)
    System.arraycopy(AES_API_IV.toByteArray(), 0, finalIvs, 0, len)

    val ivps = IvParameterSpec(finalIvs)
    cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivps)

    val encrypted = cipher.doFinal(data.toByteArray())
    return Base64.getEncoder().encodeToString(encrypted)
        .trim()
        .replace("\n", "")
        .replace("\r", "")
}

fun getHeaders(): HashMap<String, String>? {
    val headers = HashMap<String, String>()
    headers["longitude"]="44.416270"
    headers["latitude"] =  "33.341658"
    headers["app_version"] = "1.3.00"
    headers["auth_key"] = HEADER_AUTH_KEY
    headers["device_type"] = "android"
    headers["language"] = "en"
    return headers
}

fun homeOrderDeliveryTimeConverter(context: Context ,start:Int?, end:Int?):String{
    return ("$start - $end ${context.getString(R.string.home_chip_text_mins)}")
}

fun homeOrderDistanceConverter(context: Context ,input:Double?):String{
    val roundedNumber = input?.let { BigDecimal(it).setScale(2, BigDecimal.ROUND_HALF_UP).toDouble() }
    return ("$roundedNumber ${context.getString(R.string.home_chip_text_away)}")
}