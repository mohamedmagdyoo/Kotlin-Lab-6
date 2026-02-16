package com.example.days.day6

import android.util.Log
import androidx.compose.foundation.layout.ColumnScope
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlin.coroutines.cancellation.CancellationException

fun main() {
//    task1()
//    task2()
//    task3()
//    task4()
    task5()
}

fun task1() {
    runBlocking {
        val bk_task = async {
            val listOfNumbers = mutableListOf<Int>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
            listOfNumbers
        }

        launch {
            bk_task.await().forEach {
                delay(500)
                println(it)
            }
        }
    }
}

suspend fun factorial(n: Int): Int {
    if (n == 0 || n == 1) return 1
    var result = 1
    for (i in 2..n)
        result *= i
    return result
}

fun task2() {
    runBlocking {
        println("Enter your number : ")
        var number = readln().toInt()

        val resutl = async {
            val factorialResutl = factorial(number)
            factorialResutl
        }
        println("Factorial of $number : ${resutl.await()}")
    }
}

fun task3() {
    runBlocking {
        val job1 = launch {
            try {
                while (isActive) {
                    println("Coroutine 1 running")
                    delay(2000)
                }
            } catch (e: CancellationException) {
                println("Coroutine 1 cancelled: ${e.message}")
            } finally {
                println("Coroutine 1 cleaned up")
            }
        }

        val job2 = launch {
            try {
                while (isActive) {
                    println("Coroutine 2 running")
                    delay(2000)
                }
            } catch (e: CancellationException) {
                println("Coroutine 2 cancelled: ${e.message}")
            } finally {
                println("Coroutine 2 cleaned up")
            }
        }

        delay(10_000)

        println("Cancelling both coroutines...")
        job1.cancelAndJoin()
        job2.cancelAndJoin()
        println("Both coroutines cancelled successfully")
    }
}

suspend fun calArraySum(numbers: IntArray): Int = numbers.sum()
fun task4() {

    runBlocking {
        println(calArraySum(intArrayOf(1, 2, 3)))
    }
}

fun task5(){
    runBlocking {
       launch {  repeat(3){
           println("In task5")
           delay(3000)
       } }

        println("After join")
    }
}
