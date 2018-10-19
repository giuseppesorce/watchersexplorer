package com.giuseppesorce.watchersexplorer.domain.model

import com.giuseppesorce.watchersexplorer.domain.interactors.UseCaseParams

/**
 * @author Giuseppe Sorce
 */
class SignupResponse(var result:Boolean= true, var message:String= ""  , var error : Throwable? =null): UseCaseParams {}