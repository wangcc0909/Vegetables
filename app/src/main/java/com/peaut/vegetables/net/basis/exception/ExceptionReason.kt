package com.peaut.vegetables.net.basis.exception

/**
 * @author peaut
 * @package  com.peaut.httpmodule.http.basis.exception
 * @fileName ExceptionReason
 * @date on  2019/2/20  17:04
 */
enum class ExceptionReason{
    /**
     * 解析数据失败
     */
    PARSE_ERROR,
    /**
     * 网络问题
     */
    BAD_NETWORK,
    /**
     * 连接错误
     */
    CONNECT_ERROR,
    /**
     * 连接超时
     */
    CONNECT_TIMEOUT,
    /**
     * 未知错误
     */
    UNKNOWN_ERROR
}