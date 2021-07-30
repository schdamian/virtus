package com.schonfeld.virtus

import org.junit.Assert
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*
import java.util.Locale

class ExtensionTest {

    private val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.getDefault())

    @Test
    fun checkNowDateToReadableDate() {
        val date = sdf.format(Calendar.getInstance().time)
        Assert.assertEquals("hace instantes", date.toReadableDate())
    }

    @Test
    fun checkYesterdayDateToReadableDate() {
        val yesterday = Calendar.getInstance()
        yesterday.add(Calendar.DATE, -1)
        val date = sdf.format(yesterday.time)
        Assert.assertEquals("hace 1 día", date.toReadableDate())
    }

    @Test
    fun checkWeekDateToReadableDate3() {
        val week = Calendar.getInstance()
        week.add(Calendar.DATE, -7)
        val date = sdf.format(week.time)
        Assert.assertEquals("hace 1 semana", date.toReadableDate())
    }
}