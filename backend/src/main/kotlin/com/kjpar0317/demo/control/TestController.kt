package com.kjpar0317.demo.control

import com.kjpar0317.demo.service.TestService
import io.javalin.http.Context

object TestController {
    fun getTest(ctx: Context) {
        ctx.result(TestService.findById())
    }
}