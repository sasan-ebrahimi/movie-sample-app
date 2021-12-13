package com.sebrahimi.moviesample.util

object StringUtil {

    fun shortenMoneyNumber(num : Long): String{
        if(num>= 1000000)
            return "${num/1000000} M"
        if(num >= 1000)
            return "${num/1000} K"
        return "$num"
    }

}