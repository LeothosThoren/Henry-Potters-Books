package com.leothos.harrypotterbooks.utils

import com.leothos.harrypotterbooks.model.Book

/**
 * Concat a series of string to perform the path in the api uri
 * */
fun generateStringPath(h: HashMap<String, Book>): String {
    val sb = StringBuilder()
    for (k in h) {
        sb.append(k.key).append(",")
    }
    return sb.removeSuffix(",").toString()
}

fun computeTotalPrice(h: HashMap<String, Book>): Int {
    var total: Int = 0
    for (p in h) {
        total += p.value.price!!
    }
    return total
}

fun giveBestCommercialOffer(amount: Int): Double {
    // Compute percentage
    // compute subtraction
    // compute subtraction if a certain amount is reached the subtract

    //compare the three offer and select the most interesting
    return 0.0
}