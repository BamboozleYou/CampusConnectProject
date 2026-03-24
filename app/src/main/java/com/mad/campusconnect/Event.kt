package com.mad.campusconnect

data class Event(
    val id: Int,
    val title: String,
    val description: String,
    val date: String,
    val time: String,
    val status: String,       // "Completed", "Upcoming", "Ongoing"
    val price: String,        // "Free" or "₹500" etc.
    val tags: List<String>,
    val exclusiveTag: String = ""   // e.g. "EXCLUSIVELY FOR 1ST YEARS"
)

data class NewsItem(
    val title: String,
    val date: String,
    val body: String
)