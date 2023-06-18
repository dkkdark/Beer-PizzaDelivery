package com.kseniabl.data.main

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

inline fun <R> cashData(
    crossinline apiCall: suspend () -> List<R>?,
    crossinline dbCall: () -> Flow<List<R>>,
    crossinline saveData: (List<R>?) -> Unit
) = flow {
    val dbData = dbCall().first()
    val apiData = apiCall()

    emit(dbData)

    if (dbData.toMutableList() != apiData?.toMutableList() && apiData != null) {
        saveData(apiData)
        emit(apiData)
    }
}