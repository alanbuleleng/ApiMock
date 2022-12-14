package com.piranha.apimock

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.gson.JsonObject
import com.piranha.apimock.databinding.ActivityAddTodoBinding
import com.piranha.apimock.model.ResponseAdd
import com.piranha.apimock.network.ApiServis
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

class AddTodoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddTodoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTodoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        addTODO()
//        addTODOFORMDATA()

    }

//    private fun addTODOFORMDATA() {
//        ApiServis.endNetwork.AddTODOFORMDATA(
//            binding.etTitle.text.toString(),true,binding.etContent.text.toString()
//        ).enqueue(object : Callback<ResponseAdd>{
//            override fun onResponse(call: Call<ResponseAdd>, response: Response<ResponseAdd>) {
//                TODO("Not yet implemented")
//            }
//
//            override fun onFailure(call: Call<ResponseAdd>, t: Throwable) {
//                TODO("Not yet implemented")
//            }
//        })
//
//    }

    private fun addTODO() {
        binding.btnAddtodo.setOnClickListener {
            var job = JsonObject()
            job.addProperty("title", binding.etTitle.text.toString())
            job.addProperty("complete", true)
            job.addProperty("content", binding.etContent.text.toString())
//            Toast.makeText(this, "$job", Toast.LENGTH_SHORT).show()

            if (binding.etTitle.text.isEmpty()) {
                binding.etTitle.error = "Kosong"
                binding.etTitle.requestFocus()
            }else if (binding.etContent.text.isEmpty()) {
                binding.etContent.error = "Kosong"
                binding.etContent.requestFocus()
            } else {
                ApiServis.endNetwork.Addtodo(job).enqueue(object : Callback<ResponseAdd> {
                    override fun onResponse(
                        call: Call<ResponseAdd>,
                        response: Response<ResponseAdd>
                    ) {
                        if (response.isSuccessful) {
                            finish()
                            Toast.makeText(
                                this@AddTodoActivity,
                                "Add TODO Succes",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            Toast.makeText(
                                this@AddTodoActivity,
                                "Gagal Add Todo",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    override fun onFailure(call: Call<ResponseAdd>, t: Throwable) {
                        Toast.makeText(this@AddTodoActivity, "$t", Toast.LENGTH_SHORT).show()
                    }

                })
            }

        }
    }

}