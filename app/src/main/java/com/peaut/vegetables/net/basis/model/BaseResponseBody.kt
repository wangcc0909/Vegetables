package com.peaut.vegetables.net.basis.model

import com.google.gson.annotations.SerializedName



/**
 * @author peaut
 * @package  com.peaut.httpmodule.http.basis.model
 * @fileName BaseResponseBody
 * @date on  2019/2/14  16:51
 */

class BaseResponseBody<T> {

    @SerializedName("error_code")
    var code: Int = 0

    @SerializedName("reason")
    var msg: String? = null

    @SerializedName("result")
    var data: T? = null

}