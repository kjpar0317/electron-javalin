package com.kjpar0317.demo.config

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import javax.sql.DataSource

fun getDataSource(): DataSource {
    return HikariConfig().let { config ->
        config.jdbcUrl = ""
        config.username = ""
        config.password = ""
        HikariDataSource(config)
    }
}