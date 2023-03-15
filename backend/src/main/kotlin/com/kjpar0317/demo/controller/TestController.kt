package com.kjpar0317.demo.controller

import com.kjpar0317.demo.service.findByAll
import com.kjpar0317.demo.service.findByTest
import io.javalin.http.Context
import mu.KotlinLogging

private val logger = KotlinLogging.logger { }

fun getTestControl(ctx: Context) {
    logger.info { "gg" }
    ctx.result(findByTest())
}

fun getTest2Control(ctx: Context) {
    ctx.json(findByAll())
}

fun getTestError(ctx: Context) {
    ctx.result("에러테스트").status(500)
}