package com.peaut.vegetables.net.basis.exception.base

import com.peaut.vegetables.net.basis.config.HttpCode

/**
 * @author peaut
 * @package  com.peaut.httpmodule.http.basis.exception.base
 * @fileName BaseException
 * @date on  2019/2/14  15:19
 */
open class BaseException : RuntimeException {
    private var errorCode: Int = HttpCode.CODE_UNKOWN

    constructor(errorCode: Int, errorMessage: String) : super(errorMessage) {
        this.errorCode = errorCode
    }

    fun getErrorCode(): Int = errorCode
}