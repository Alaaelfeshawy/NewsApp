//package com.example.domain.use_case.base
//
//import com.example.domain.exception.traceErrorException
//import kotlinx.coroutines.CancellationException
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.launch
//
//abstract class UseCaseWithoutParams<T>() {
//
//    abstract suspend fun run(): T
//
//
//    fun invoke(scope: CoroutineScope, onResult: UseCaseCallback<T>) {
//
//        scope.launch {
//            try {
//                val result = run()
//                onResult.onSuccess(result)
//            } catch (e: CancellationException) {
//                e.printStackTrace()
//                onResult.onError(traceErrorException(e))
//            } catch (e: Exception) {
//                e.printStackTrace()
//                onResult.onError(traceErrorException(e))
//            }
//        }
//    }
//
//}