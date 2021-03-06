package com.leothos.harrypotterbooks.utils

import com.leothos.harrypotterbooks.model.BestOffer
import com.leothos.harrypotterbooks.model.Book
import com.leothos.harrypotterbooks.model.Offers

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

/**
 * Compute the total of books price
 * @param h the hash map contains Book values which allow us to retrieve book price
 * */
fun computeTotalPrice(h: HashMap<String, Book>): Int {
    var total: Int = 0
    for (p in h) {
        total += p.value.price!!
    }
    return total
}

/**
 * Series of computation to identify the best offer between several proposal
 * @param total the total of all the book price selected
 * @param offer the Offer object allow to identify the type and get the value of the offers
 * */
fun giveBestCommercialOffer(total: Int, offer: Offers): BestOffer {
    var bestProposal = Double.MAX_VALUE
    var reduction = ""

    for (o in 0 until offer.offers?.size!!) {
        when (offer.offers[o]?.type) {
            "percentage" -> {
                val percentage = total.toDouble() - ((total.toDouble() * offer.offers[o]?.value!!) / 100.0)
                if (percentage < bestProposal) {
                    bestProposal = percentage
                    reduction = "-${offer.offers[o]?.value}%"
                }
            }
            "minus" -> {
                val minus = total - offer.offers[o]?.value!!
                if (minus < bestProposal) {
                    bestProposal = minus.toDouble()
                    reduction = "-${offer.offers[o]?.value}€"
                }
            }
            "slice" -> {
                var temp = total
                var count = 0

                while (temp >= offer.offers[o]?.sliceValue!!) {
                    temp -= offer.offers[o]?.sliceValue!!
                    count++
                }
                if (count > 0) {
                    val c = offer.offers[o]?.value!! * count
                    val slice = total - c
                    if (slice < bestProposal) {
                        bestProposal = slice.toDouble()
                        reduction = "-${c}€"
                    }
                }

            }
        }
    }
    // select the most interesting offer
    return BestOffer(bestProposal, reduction)
}

/**
 * The synopsis is a list of string, so the aim is to get all the string and concatenate them
 * @param listOfString
 * */
fun generateStringFromList(listOfString: List<String?>?): String {
    val sb = StringBuilder()
    for (s in listOfString!!) {
        sb.append(s).append(" ")
    }
    return sb.removeSuffix(" ").toString()
}