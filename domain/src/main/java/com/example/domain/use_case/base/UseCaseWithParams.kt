package com.example.domain.use_case.base

import com.example.domain.exception.traceErrorException
import kotlinx.coroutines.*

abstract class UseCaseWithParams<T, Params>() {

    abstract suspend fun run(params: Params): T


    fun invoke(scope: CoroutineScope, params: List<Params>, onResult: UseCaseCallback<T>) {
        val results = ArrayList<T>()
        scope.launch {
            supervisorScope {
                        try {
                             params.forEach {
                               val result =  async { run(it) }.await()
                               results.add(result)
                            }
                            onResult.onSuccess(results)
                        } catch (e: Throwable) {
                            e.printStackTrace()
                            onResult.onError(traceErrorException(e))
                        }catch (e: CancellationException) {
                            e.printStackTrace()
                            onResult.onError(traceErrorException(e))
                        }
                }


        }
    }

}