package com.project.shis.roompractice.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//Entity 모델을 기반으로 하고 DAO의 메소드를 가지고 있는 데이터 베이스 생성

@Database(entities = [Cat::class], version = 1)
abstract class CatDB : RoomDatabase() {

    abstract fun catDao():MyDao

    companion object {
        private var INSTANCE:CatDB? = null

        fun getInstance(context: Context):CatDB?{
            if(INSTANCE == null){
                synchronized(CatDB::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, CatDB::class.java, "cat.db").fallbackToDestructiveMigration().build()
                }
            }
            return INSTANCE
        }
    }
}