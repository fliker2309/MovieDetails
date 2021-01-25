package com.example.moviedetails.services

import androidx.work.OneTimeWorkRequest
import androidx.work.NetworkType
import androidx.work.Constraints

class WorkRepository {

    private val constraints = Constraints.Builder()
        .setRequiresCharging(true)
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .build()

  /*  val constraintsRequest = */
}