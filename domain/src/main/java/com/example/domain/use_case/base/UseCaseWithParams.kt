package com.example.domain.use_case.base

import com.example.domain.exception.traceErrorException
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

abstract class UseCaseWithParams<T, Params>() {

    abstract suspend fun run(params: Params): T


    fun invoke(scope: CoroutineScope, params: Params, onResult: UseCaseCallback<T>) {
        scope.launch {
            try {
                val result = run(params)
                onResult.onSuccess(result)
            } catch (e: CancellationException) {
                e.printStackTrace()
                onResult.onError(traceErrorException(e))
            } catch (e: Exception) {
                e.printStackTrace()
                onResult.onError(traceErrorException(e))
            }
        }
    }

}