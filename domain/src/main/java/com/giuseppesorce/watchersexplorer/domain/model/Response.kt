package com.giuseppesorce.watchersexplorer.domain.model

import com.giuseppesorce.watchersexplorer.domain.interactors.UseCaseParams

/**
 * @author Giuseppe Sorce
 */
open class Response( var result:Boolean= true, var message:String= "",  var error : Throwable? =null ): UseCaseParams