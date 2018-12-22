package com.project.shis.roompractice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.project.shis.roompractice.room.Cat
import com.project.shis.roompractice.room.CatDB
import kotlinx.android.synthetic.main.activity_add_cat.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

class AddCatActivity : AppCompatActivity() {

    private lateinit var catDb : CatDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_cat)


        catDb = CatDB.getInstance(applicationContext)

//        /* 새로운 cat 객체를 생성, id 이외의 값을 지정 후 DB에 추가 */
////        val addRunnable = Runnable {
////            val newCat = Cat()
////            newCat.catName = etAddName.text.toString()
////            newCat.lifeSpan = etAddLifeSpan.text.toString().toInt()
////            newCat.origin = etAddOrigin.text.toString()
////            catDb?.catDao()?.insert(newCat)
////        }
////
////        btnAddCat.setOnClickListener {
////            val addThread = Thread(addRunnable)
////            addThread.start()
////
////            val i = Intent(this, MainActivity::class.java)
////            startActivity(i)
////            finish()
////        }

        btnAddCat.setOnClickListener {
            val newCat = Cat()
            newCat.catName = etAddName.text.toString()
            newCat.lifeSpan = Integer.valueOf(etAddLifeSpan.text.toString())
            newCat.origin = etAddOrigin.text.toString()


            doAsync {
                // Put the student in database
                catDb.catDao().insert(newCat)

                uiThread {
                    toast("One record inserted.")
                }
            }

            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            finish()
        }





    }

    override fun onDestroy() {
        catDb.destroyInstance()
        super.onDestroy()
    }
}
