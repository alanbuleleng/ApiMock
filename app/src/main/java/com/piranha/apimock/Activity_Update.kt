package com.piranha.apimock

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.gson.JsonObject
import com.piranha.apimock.databinding.ActivityUpdateBinding
import com.piranha.apimock.model.ResponseUpdate
import com.piranha.apimock.network.ApiServis
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Activity_Update : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateBinding
    var id = 0
    var title: String?=""
     var content : String?=""
    var complete: Boolean= true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val i = intent
        id = i.getIntExtra("id",0)
        title = i.getStringExtra("title")
        content = i.getStringExtra("content")
        complete = i.getBooleanExtra("complete",true)
        binding.etUpTitle.setText(title)
        binding.etUpContent.setText(content)
        updateTODO()
//        binding.btnUpTodo.setOnClickListener {
//            Toast.makeText(this, "$id", Toast.LENGTH_SHORT).show()
//        }

    }

    private fun updateTODO() {

        binding.btnUpTodo.setOnClickListener {
            val job = JsonObject()
            job.addProperty("title", binding.etUpTitle.text.toString())
            job.addProperty("complete", false)
            job.addProperty("content", binding.etUpContent.text.toString())

            if (binding.etUpTitle.text.isEmpty()) {
                binding.etUpTitle.error = "Kosong"
                binding.etUpTitle.requestFocus()
            }else if (binding.etUpContent.text.isEmpty()) {
                binding.etUpContent.error = "Kosong"
                binding.etUpContent.requestFocus()
            } else {
            ApiServis.endNetwork.UpadateTODO(id.toString(), job).enqueue(object :Callback<ResponseUpdate>{
                override fun onResponse(
                    call: Call<ResponseUpdate>,
                    response: Response<ResponseUpdate>
                ) {

                    if (response.isSuccessful){
                        finish()
                        Toast.makeText(this@Activity_Update,
                            "Update TODO Succes",
                            Toast.LENGTH_SHORT)
                            .show()
                    }else{
                        Toast.makeText(this@Activity_Update,
                            "Gagal",
                            Toast.LENGTH_SHORT)
                            .show()
                    }
                }

                override fun onFailure(call: Call<ResponseUpdate>, t: Throwable) {
                    Toast.makeText(this@Activity_Update,
                        "$t",
                        Toast.LENGTH_SHORT)
                        .show()
                }
            })
        }
        }

    }
}