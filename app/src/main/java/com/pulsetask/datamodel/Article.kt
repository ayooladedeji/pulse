package com.pulsetask.datamodel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by ayoola on 29/09/2017.
 */
@Parcelize
data class Article(
        val id: String,
        val title: String,
        val subtitle: String,
        val body: String?,
        val date: String
) : Parcelable