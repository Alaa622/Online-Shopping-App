package com.example.onlineshopping.model

import android.util.Base64


fun String.base64Encode(): String = Base64.encodeToString(this.toByteArray(Charsets.UTF_8), Base64.NO_WRAP)
fun String.base64Decode(): String = String(Base64.decode(this, Base64.NO_WRAP), Charsets.UTF_8)
