package com.example.moviedetails.services

import androidx.work.*
import kotlinx.coroutines.InternalCoroutinesApi
import java.util.concurrent.TimeUnit

@InternalCoroutinesApi
class WorkRepository {

    private val constraints = Constraints.Builder()
        .setRequiresCharging(true)
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .build()

    val constraintsRequest = PeriodicWorkRequest.Builder(SynchronizationWorker::class.java, 1, TimeUnit.HOURS)
        .setConstraints(constraints)
        .build()
}
