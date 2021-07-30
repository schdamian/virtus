package com.schonfeld.virtus

import org.joda.time.DateTime
import org.ocpsoft.prettytime.PrettyTime

fun String.toReadableDate(): String {
    return try {
        PrettyTime().format(DateTime(this).toDate())
    } catch (ex: Exception) {
        this
    }
}