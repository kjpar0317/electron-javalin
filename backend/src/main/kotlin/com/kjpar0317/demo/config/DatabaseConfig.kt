package com.kjpar0317.demo.config

import com.sksamuel.hoplite.ConfigLoaderBuilder
import com.sksamuel.hoplite.addResourceSource
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import javax.sql.DataSource

data class Database(val jdbcUrl: String, val username: String, val password: String)

fun getDataSource(): DataSource {
    val databaseConfig = ConfigLoaderBuilder.default()
        .addResourceSource("/application.yml")
        .build()
        .loadConfigOrThrow<Database>()

//    return HikariConfig().let { config ->
//        config.jdbcUrl = databaseConfig.jdbcUrl
//        config.username = databaseConfig.username
//        config.password = databaseConfig.password
//        HikariDataSource(config)
//    }

     return HikariConfig().let { config ->
        config.jdbcUrl = "jdbc:mariadb://127.0.0.1:3306/coininfos"
        config.username = "coininfos"
        config.password = "kocodebank"
        HikariDataSource(config)
    }
}