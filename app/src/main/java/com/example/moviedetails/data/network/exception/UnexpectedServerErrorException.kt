package com.example.moviedetails.data.network.exception

import java.io.IOException

class UnexpectedServerErrorException(reason: String) : IOException(reason)