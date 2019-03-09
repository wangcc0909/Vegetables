package com.peaut.vegetables.net.basis

/**
 * @author peaut
 * @package  com.peaut.httpmodule.http.basis
 * @fileName BaseRepo
 * @date on  2019/2/15  16:40
 */
open class BaseRepo<T> {
    var remoteDataSource: T

    constructor(remoteDataSource: T) {
        this.remoteDataSource = remoteDataSource
    }
}