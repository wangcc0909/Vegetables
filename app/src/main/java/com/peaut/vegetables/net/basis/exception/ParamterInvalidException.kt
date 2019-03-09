package com.peaut.vegetables.net.basis.exception

import com.peaut.vegetables.net.basis.config.HttpCode
import com.peaut.vegetables.net.basis.exception.base.BaseException

/**
 * @author peaut
 * @package  com.peaut.httpmodule.http.basis.exception
 * @fileName ParamterInvalidException
 * @date on  2019/2/14  15:26
 */
class ParamterInvalidException : BaseException {
    constructor() : super(HttpCode.CODE_PARAMETER_INVALID, "参数有误")
}