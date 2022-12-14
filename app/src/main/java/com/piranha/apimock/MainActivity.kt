package com.piranha.apimock

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.piranha.apimock.adapter.AdapterList
import com.piranha.apimock.databinding.ActivityMainBinding
import com.piranha.apimock.network.ApiServis
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    val dataAdapter = AdapterList(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.fbAddtodo.setOnClickListener {
            startActivity(Intent(this, AddTodoActivity::class.java))
        }

        rv_item_list.layoutManager = LinearLayoutManager(this)
        rv_item_list.adapter = dataAdapter
        binding.sfContent.setOnRefreshListener {
            getData1()
        }




    }


    private fun getData1() {
        binding.sfContent.isRefreshing = false
        ApiServis.endNetwork.getList().enqueue(object : Callback<ResponseList>{
            override fun onResponse(call: Call<ResponseList>, response: Response<ResponseList>) {
                if (response.isSuccessful){
                    val responseList: ResponseList? = response.body()
                    onResultData(responseList!!)
                    binding.sfContent.isRefreshing = false
                }else{
                    Toast.makeText(this@MainActivity, "Gagal", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseList>, t: Throwable) {
                Toast.makeText(this@MainActivity, "$t", Toast.LENGTH_SHORT).show()
                Log.d("Error","$t")
                binding.sfContent.isRefreshing = true
            }
        })
    }

    private fun onResultData(responseList: ResponseList?) {
        val test = responseList?.data
        dataAdapter.setData(test as List<ListData>)

    }
}