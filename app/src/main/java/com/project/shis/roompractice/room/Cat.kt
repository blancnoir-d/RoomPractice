package com.project.shis.roompractice.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//Entity는 고유 식별자인 PrimaryKey(기본키)가 반드시 팔요. 큰 의미가 없다면 autoGenerate 를 이용해서 자동으로 생성되게 하는 것도 가능

@Entity(tableName = "cat")

//class Cat(@PrimaryKey var id :Long?,
//          @ColumnInfo(name = "catname") var catName: String?,
//          @ColumnInfo(name = "lifespan") var lifeSpan: Int,
//          @ColumnInfo(name = "origin") var origin: String){
//    constructor():this(null, "",0,"")
//
//}


class Cat(@PrimaryKey(autoGenerate = true) var id :Long?,
          @ColumnInfo(name = "catname") var catName: String?,
          @ColumnInfo(name = "lifespan") var lifeSpan: Int,
          @ColumnInfo(name = "origin") var origin: String){
    constructor():this(null, "",0,"")

}