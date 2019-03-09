package com.peaut.vegetables.net.basis.exception

import com.peaut.vegetables.net.basis.config.HttpCode
import com.peaut.vegetables.net.basis.exception.base.BaseException

/**
 * @author peaut
 * @package  com.peaut.httpmodule.http.basis.exception
 * @fileName ConnectionException
 * @date on  2019/2/14  16:09
 */
class ConnectionException: BaseException {
    constructor(): super(HttpCode.CODE_CONNECTION_FAILED,"网络请求失败")
}