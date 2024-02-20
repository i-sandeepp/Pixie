package com.sandeep.pixie.model

/**
 * Created by Sandeep Pramanik on 20 February,2024.
 */
data class PixieItem(
    val author: String,
    val download_url: String,
    val height: Int,
    val id: String,
    val url: String,
    val width: Int
)

class PixieResponse : ArrayList<PixieItem>()