package com.kjpar0317.demo.control

import com.kjpar0317.demo.service.findByAll
import com.kjpar0317.demo.service.findByTest
import io.javalin.http.Context

fun getTestControl(ctx: Context) {
    ctx.result(findByTest())
}

fun getTest2Control(ctx: Context) {
    ctx.json(findByAll())
}

fun getTestError(ctx: Context) {
    ctx.result("에러테스트").status(500)
}