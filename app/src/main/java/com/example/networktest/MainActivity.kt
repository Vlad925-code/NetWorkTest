package com.example.networktest

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.networktest.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val apiService = retrofit.create(ApiService::class.java)

        binding.btn.setOnClickListener{
            lifecycleScope.launch{
                val response = apiService.getPosts()
                if (response.isSuccessful) {
                    val listPosts = response.body()
                    val post = listPosts?.first()
                    binding.tv1.text = post?.id.toString()
                    binding.tv2.text = post?.userId.toString()
                    binding.tv3.text = post?.title.toString()
                    binding.tv4.text = post?.body.toString()
            }



            }
        }

    }
}