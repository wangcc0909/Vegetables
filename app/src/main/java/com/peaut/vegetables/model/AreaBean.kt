package com.peaut.vegetables.model

/**
 * @author peaut
 * @package  com.peaut.vegetables.model
 * @fileName AreaBean
 * @date on  2019/3/19  11:56
 */
data class Province(
    val children: List<City>,
    val label: String,
    val value: String
)

data class City(
    val label: String,
    val value: String,
    val children: List<Area>
)

data class Area (
    val label: String,
    val value: String
)