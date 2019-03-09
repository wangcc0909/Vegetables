package com.peaut.vegetables.net.basis.exception

import com.peaut.vegetables.net.basis.exception.base.BaseException

/**
 * @author peaut
 * @package  com.peaut.httpmodule.http.basis.exception
 * @fileName ServerResultException
 * @date on  2019/2/14  17:08
 */
class ServerResultException: BaseException {
    constructor(code: Int,message: String):super(code,message)
}