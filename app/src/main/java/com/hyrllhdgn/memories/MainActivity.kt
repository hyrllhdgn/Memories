package com.hyrllhdgn.memories

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.hyrllhdgn.memories.databinding.ActivityMainBinding
import java.lang.Exception
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.LinearLayout

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var memList: ArrayList<Mem>
    private lateinit var memAdapter: MemAdapter



    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        memList= ArrayList<Mem>()
        memAdapter= MemAdapter(memList)
        binding.recyclerView.layoutManager=LinearLayoutManager(this)
        binding.recyclerView.adapter = memAdapter


        try {
            val database= this.openOrCreateDatabase("Mems", Context.MODE_PRIVATE,null)

            val cursor = database.rawQuery("SELECT * FROM mems",null)
            val memNameIx = cursor.getColumnIndex("memname")
            val idIx = cursor.getColumnIndex("id")

            while (cursor.moveToNext()){
                val name = cursor.getString(memNameIx)
                val id= cursor.getInt(idIx)
                val mem = Mem(name,id)
                memList.add(mem)
            }
            memAdapter.notifyDataSetChanged()

            cursor.close()

        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.mem_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == R.id.add_mem_item){
            val intent = Intent(this@MainActivity,MemActivity::class.java)
            intent.putExtra("info","new")
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }


}