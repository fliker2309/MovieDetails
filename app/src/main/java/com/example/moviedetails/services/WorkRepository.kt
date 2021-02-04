/*
package com.example.moviedetails.services

import androidx.work.*
import java.util.concurrent.TimeUnit

class WorkRepository {

    //условия работы
    private val constraints = Constraints.Builder()
        .setRequiresCharging(true)
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .build()

    val constraintsRequest = PeriodicWorkRequest.Builder(WorkManager::class.java, 8, TimeUnit.HOURS)
        .setConstraints(constraints)
        .build()


}*/
