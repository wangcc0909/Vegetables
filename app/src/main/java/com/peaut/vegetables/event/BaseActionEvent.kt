package com.peaut.vegetables.event

import com.peaut.vegetables.event.base.BaseEvent

/**
 * @author peaut
 * @package  com.peaut.vegetables.event
 * @fileName BaseActionEvent
 * @date on  2019/2/25  11:30
 */
class BaseActionEvent(action: Int) : BaseEvent(action) {

    private var message: String = ""

    fun getMessage(): String = message


    fun setMessage(message: String) {
        this.message = message
    }

    companion object {

        val SHOW_LOADING_DIALOG = 1

        val DISMISS_LOADING_DIALOG = 2

        val SHOW_TOAST = 3

        val FINISH = 4

        val FINISH_WITH_RESULT_OK = 5

        val CONNECT_ERROR = 6

        val CONNECT_TIMEOUT = 7

        val BAD_NETWORK = 8

        val PARSE_ERROR = 9

        val UNKNOWN_ERROR = 10
    }
}