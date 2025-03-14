package com.example.garden.data

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import androidx.room.TypeConverter
import java.io.ByteArrayOutputStream

import java.util.Date

class Converters {
    @TypeConverter
    fun fromTimeStamp (value: Long?): Date?{
        return value?.let {
            Date(it)
        }
    }
    @TypeConverter
    fun dateToTimeStamp (date: Date?): Long?{
        return date?.time?.toLong()
    }
    @TypeConverter
    fun bitmapToStart(bitmap: Bitmap?):String{
        val output = ByteArrayOutputStream()
        bitmap?.compress(Bitmap.CompressFormat.JPEG,70,output)

        return Base64.encodeToString(output.toByteArray(),Base64.DEFAULT)
    }
    @TypeConverter
    fun fromStringToBitmap(base64: String):Bitmap?{
        return try {
            val byteArray = Base64.decode(base64,Base64.DEFAULT)
            BitmapFactory.decodeByteArray(byteArray,0,byteArray.size)
        }catch (e:Exception){
            e.printStackTrace()
            null
        }

    }
}