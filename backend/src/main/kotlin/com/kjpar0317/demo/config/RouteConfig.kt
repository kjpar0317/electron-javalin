package com.kjpar0317.demo.config

import com.kjpar0317.demo.controller.getTestControl
import com.kjpar0317.demo.controller.getTest2Control
import com.kjpar0317.demo.controller.getTestError
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.path
import io.javalin.apibuilder.ApiBuilder.get

fun registRouter(app: Javalin) {
    app.routes {
        path("/api") {
            get("/test", ::getTestControl)
            get("/test2", ::getTest2Control)
            get("/error", ::getTestError)
        }
    }
}