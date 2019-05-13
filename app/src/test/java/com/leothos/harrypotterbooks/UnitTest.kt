package com.leothos.harrypotterbooks

import com.leothos.harrypotterbooks.model.Book
import com.leothos.harrypotterbooks.model.Offers
import com.leothos.harrypotterbooks.model.OffersItem
import com.leothos.harrypotterbooks.utils.computeTotalPrice
import com.leothos.harrypotterbooks.utils.generateStringFromList
import com.leothos.harrypotterbooks.utils.generateStringPath
import com.leothos.harrypotterbooks.utils.giveBestCommercialOffer
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

val h = HashMap<String, Book>()
val book1 = Book(price = 30)
val book2 = Book(price = 29)
val listOffer = Offers(
    listOf(
        OffersItem(null, "percentage", 5),
        OffersItem(null, "minus", 15),
        OffersItem(100, "slice", 12)
    )
)

class UnitTest {
    @Test
    fun concat_String() {
        h["c8fabf68-8374-48fe-a7ea-a00ccd07afff"] = book1
        h.put("a460afed-e5e7-4e39-a39d-c885c05db861", book2)
        assertEquals("c8fabf68-8374-48fe-a7ea-a00ccd07afff,a460afed-e5e7-4e39-a39d-c885c05db861", generateStringPath(h))
    }

    @Test
    fun compute_total() {
        h.put("c8fabf68-8374-48fe-a7ea-a00ccd07afff", book1)
        h.put("a460afed-e5e7-4e39-a39d-c885c05db861", book2)
        assertEquals(59, computeTotalPrice(h))
    }

    @Test
    fun compute_best_offer() {
        val total = 95
        val total2 = 218
        val total3 = 65

        assertEquals("80.0€", giveBestCommercialOffer(total, listOffer))
        assertEquals("194.0€", giveBestCommercialOffer(total2, listOffer))
        assertEquals("50.0€", giveBestCommercialOffer(total3, listOffer))
    }

    @Test
    fun generate_string_from_array() {
        val synopsis = listOf("text1", "text2", "text3")

        assertEquals("text1 text2 text3", generateStringFromList(synopsis))
    }

}
