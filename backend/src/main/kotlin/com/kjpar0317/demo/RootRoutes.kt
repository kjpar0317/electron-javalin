package com.kjpar0317.demo

import com.kjpar0317.demo.control.getTestControl
import com.kjpar0317.demo.control.getTest2Control
import com.kjpar0317.demo.control.getTestError
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