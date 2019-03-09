package com.peaut.vegetables.db.ext

import android.content.Context
import com.peaut.vegetables.db.DatabaseOpenHelper

/**
 * @author peaut
 * @package  com.peaut.vegetables.db.ext
 * @fileName DatabaseExtension
 * @date on  2019/3/6  17:10
 */
val Context.database: DatabaseOpenHelper
    get() = DatabaseOpenHelper.instance