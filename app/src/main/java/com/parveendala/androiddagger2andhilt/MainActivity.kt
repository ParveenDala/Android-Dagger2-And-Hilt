package com.parveendala.androiddagger2andhilt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject //Field Injection
    lateinit var randomNumberClass: RandomNumberClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        println(randomNumberClass.getRandomNumber())
        println(randomNumberClass.getRandomText())
    }
}

class RandomNumberClass @Inject constructor(
    private val randomTextClass: RandomTextClass //Constructor Injection
) {
    fun getRandomNumber() = "Getting some random number"
    fun getRandomText() = randomTextClass.getRandomText()
}

class RandomTextClass @Inject constructor() {
    fun getRandomText() = "Getting some random text"
}