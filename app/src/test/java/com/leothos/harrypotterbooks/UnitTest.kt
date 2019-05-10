package com.leothos.harrypotterbooks

import com.leothos.harrypotterbooks.utils.generateStringPath
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class UnitTest {
    @Test
    fun concat_String() {
        val h = HashMap<String, Int>()
        h.put("c8fabf68-8374-48fe-a7ea-a00ccd07afff", 30)
        h.put("a460afed-e5e7-4e39-a39d-c885c05db861", 29)
        assertEquals("c8fabf68-8374-48fe-a7ea-a00ccd07afff,a460afed-e5e7-4e39-a39d-c885c05db861", generateStringPath(h))
    }

}
