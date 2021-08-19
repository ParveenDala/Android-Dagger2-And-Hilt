package com.parveendala.androiddagger2andhilt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var stringConvertor: StringConvertorInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        println(stringConvertor.convertString())
    }
}

interface StringConvertorInterface {
    fun convertString(): String
}

class StringConvertorImpl @Inject constructor(
    private val string: String
) : StringConvertorInterface {
    override fun convertString(): String {
        return string.toUpperCase()
    }
}

@InstallIn(ActivityComponent::class)
@Module
class MyModule {

    @ActivityScoped
    @Provides
    fun provideSomeStringToConvert() = "Some string to convert"
}

@InstallIn(ActivityComponent::class)
@Module
abstract class MyModuleToBind {
    @ActivityScoped
    @Binds
    abstract fun bindsStringConvertorImpl(impl: StringConvertorImpl): StringConvertorInterface
}

