package com.example.campusconnect

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.campusconnect.databinding.ActivityEventDetailsBinding

class EventDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEventDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Using ViewBinding (ensure it's enabled in build.gradle)
        binding = ActivityEventDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.btnRegister.setOnClickListener {
            Toast.makeText(this, "Registration Clicked!", Toast.LENGTH_SHORT).show()
        }

        binding.btnInstagram.setOnClickListener {
            // Logic to open Instagram link
        }

        binding.btnFeedback.setOnClickListener {
            // Logic to open feedback form
        }
    }
}