package com.example.moviedetails.data.network.exception

import com.example.moviedetails.data.network.ErrorType
import java.io.IOException

class ServerErrorException (val type : ErrorType, val reason : String) : IOException(reason)