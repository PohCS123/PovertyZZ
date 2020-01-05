package com.example.poverty.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date
import java.util.*

// This is entity

@Entity(tableName = "recycle_post_table")
data class RecycleViewPost(

    @PrimaryKey(autoGenerate = true)
    var postID: Long = 0L,

    @ColumnInfo (name = "post_title")
    var posttitle: String ="",

    @ColumnInfo (name = "post_subtitle")
    var postsubtitle: String="",

    @ColumnInfo(name = "post_date")
    var postDate : String="",

    @ColumnInfo (name = "post_desc")
    var postdesc : String ="",

    @ColumnInfo (name = "post_img")
    var postImg: String =""

)