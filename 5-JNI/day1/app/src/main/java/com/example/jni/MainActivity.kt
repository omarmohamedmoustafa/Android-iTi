package com.example.jni

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jni.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var middleware: JavaMiddleWare

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        middleware = JavaMiddleWare(this)

        binding.calculateButton.setOnClickListener {
            val inputText = binding.numberInput.text.toString()
            val number = inputText.toInt()
            middleware.startComputeFactorial(number)

        }

    }

    fun showResult(result: String) {
        runOnUiThread {
            binding.resultText.text = result
        }
    }

    companion object {
        init {
            System.loadLibrary("jni")
        }
    }
}