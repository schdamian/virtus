package com.schonfeld.virtus.models

import com.squareup.moshi.Json

class Hits (
    @Json(name = "hits") private val hits: List<Hit>?
) {
    fun getHitList(): List<Hit> = hits?: ArrayList()
}

//

class Hit (
    @Json(name = "story_id") private val id: Int?,
    @Json(name = "title") private val title: String?,
    @Json(name = "story_title") private val storyTitle: String?,
    @Json(name = "author") private val author: String?,
    @Json(name = "created_at") private val createdAt: String?,
    @Json(name = "story_url") private val storyUrl: String?
) {

    fun getId(): Int? = id

    fun getTitle(): String  = title?: storyTitle?: ""

    fun getAuthor(): String  = author?: ""

    fun getCreatedAt(): String  = createdAt?: ""

    fun url(): String?  = storyUrl
}