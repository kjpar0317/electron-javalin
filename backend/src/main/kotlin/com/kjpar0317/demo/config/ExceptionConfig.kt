package com.kjpar0317.demo.config

import com.auth0.jwt.exceptions.JWTVerificationException
import io.javalin.Javalin
import io.javalin.http.*
import mu.KotlinLogging
import org.eclipse.jetty.http.HttpStatus

private val logger = KotlinLogging.logger { }

internal data class ErrorResponse(val errors: Map<String, List<String?>>)

fun registException(app: Javalin) {
    app.exception(Exception::class.java) { e, ctx ->
        logger.error("Exception occurred for req -> ${ctx.url()}", e)
        val error = ErrorResponse(mapOf("Unknow Error" to listOf(e.message ?: "Error occurred!")))
        ctx.json(error).status(HttpStatus.INTERNAL_SERVER_ERROR_500)
    }
    app.exception(BadRequestResponse::class.java) { _, ctx ->
        logger.warn("BadRequestResponse occurred for req -> ${ctx.url()}")
        val error = ErrorResponse(mapOf("body" to listOf("can't be empty or invalid")))
        ctx.json(error).status(HttpStatus.UNPROCESSABLE_ENTITY_422)
    }
    app.exception(UnauthorizedResponse::class.java) { _, ctx ->
        logger.warn("UnauthorizedResponse occurred for req -> ${ctx.url()}")
        val error = ErrorResponse(mapOf("login" to listOf("User not authenticated!")))
        ctx.json(error).status(HttpStatus.UNAUTHORIZED_401)
    }
    app.exception(ForbiddenResponse::class.java) { _, ctx ->
        logger.warn("ForbiddenResponse occurred for req -> ${ctx.url()}")
        val error = ErrorResponse(mapOf("login" to listOf("User doesn't have permissions to perform the action!")))
        ctx.json(error).status(HttpStatus.FORBIDDEN_403)
    }
    app.exception(JWTVerificationException::class.java) { e, ctx ->
        logger.error("JWTVerificationException occurred for req -> ${ctx.url()}", e)
        val error = ErrorResponse(mapOf("token" to listOf(e.message ?: "Invalid JWT token!")))
        ctx.json(error).status(HttpStatus.UNAUTHORIZED_401)
    }
    app.exception(NotFoundResponse::class.java) { _, ctx ->
        logger.warn("NotFoundResponse occurred for req -> ${ctx.url()}")
        val error = ErrorResponse(mapOf("body" to listOf("Resource can't be found to fulfill the request.")))
        ctx.json(error).status(HttpStatus.NOT_FOUND_404)
    }
    app.exception(HttpResponseException::class.java) { e, ctx ->
        logger.warn("HttpResponseException occurred for req -> ${ctx.url()}")
        val error = ErrorResponse(mapOf("body" to listOf(e.message)))
        ctx.json(error).status(e.status)
    }
}