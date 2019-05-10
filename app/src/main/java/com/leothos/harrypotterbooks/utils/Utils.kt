package com.leothos.harrypotterbooks.utils

/**
 * Concat a series of string to perform the path in the api uri
 * */
fun generateStringPath(h: HashMap<String, Int>): String {
    val sb = StringBuilder()
    for (k in h) {
        sb.append(k.key).append(",")
    }
    return sb.removeSuffix(",").toString()
}