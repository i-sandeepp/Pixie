package com.sandeep.pixie.interfaces

/**
 * Created by Sandeep Pramanik on 20 February,2024.
 * Red Apple Technologies Private Limited.
 */
interface RowSelectListener {

    fun onRowSelected(id: String, url: String, desc: String)
}