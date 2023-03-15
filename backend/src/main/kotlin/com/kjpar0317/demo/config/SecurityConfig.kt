package com.kjpar0317.demo.config

import com.auth0.jwt.interfaces.DecodedJWT
import io.javalin.Javalin
import io.javalin.http.Context
import io.javalin.http.ForbiddenResponse
import io.javalin.security.RouteRole
import com.kjpar0317.demo.auth.decodeJWT

internal enum class Roles : RouteRole {
    ANYONE, AUTHENTICATED
}

private const val headerTokenName = "Authorization"

fun registSecurity(app: Javalin) {
    app.updateConfig {
        it -> it.accessManager { handler, ctx, permittedRoles ->
            val jwtToken = getJwtTokenHeader(ctx)
            val userRole = getUserRole(jwtToken) ?: Roles.ANYONE

            when {
                listOf<String>("/api/test", "/api/test2", "/api/error").contains(ctx.req().pathInfo) -> handler.handle(ctx)
                else -> {
                    permittedRoles.takeIf { !it.contains(userRole) }?.apply { throw ForbiddenResponse() }
                    ctx.attribute("email", getEmail(jwtToken))
                    handler.handle(ctx)
                }
            }
        }
    }
}

private fun getJwtTokenHeader(ctx: Context): DecodedJWT? {
    val tokenHeader = ctx.header(headerTokenName)?.substringAfter("Token")?.trim()
        ?: return null

    return decodeJWT(tokenHeader)
}

private fun getEmail(jwtToken: DecodedJWT?): String? {
    return jwtToken?.subject
}

private fun getUserRole(jwtToken: DecodedJWT?): RouteRole? {
    val userRole = jwtToken?.getClaim("role")?.asString() ?: return null
    return Roles.valueOf(userRole)
}