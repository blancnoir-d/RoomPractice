package com.project.shis.roompractice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.shis.roompractice.room.Cat
import com.project.shis.roompractice.room.CatDB
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    private lateinit var catDB: CatDB
    private var catList = listOf<Cat>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Activity에서 Room에 접근하기 위해서는 메인쓰레드가 아닌 백그라운드에서 작업해야함 (Thread, AsyncTask)

        //CatDataBase의 Room.databaseBuilder를 호출해 db 객체를 만들어 데이터 읽기, 쓰기는 서브 쓰레드에서 실행

        catDB = CatDB.getInstance(applicationContext)

//        val r = Runnable {
//            // 여기에서 데이터를 읽고 쓴다
//            catList = catDB.catDao().getAll()
//        }
//
//        val thread = Thread(r)
//        thread.start()

        val layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this@MainActivity)
        mRecyclerView.layoutManager = layoutManager
        val mAdapter = CatListAdapter(applicationContext,catList)
        mRecyclerView.adapter = mAdapter
        mRecyclerView.setHasFixedSize(true) // 이게 뭘까낭?


        // 여기에서 데이터를 읽고 쓴다
        doAsync {
            // Get the student list from database
            catList = catDB.catDao().getAll()

            uiThread {
                toast("${catList.size} records found.")

                mAdapter.setList(catList)

            }
        }


        mAddBtn.setOnClickListener {
            val intent = Intent(this,AddCatActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

  override fun onDestroy() {
     catDB.destroyInstance()
        super.onDestroy()
    }
}
