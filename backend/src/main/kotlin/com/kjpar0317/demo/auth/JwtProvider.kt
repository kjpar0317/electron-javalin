package com.kjpar0317.demo.auth

import com.auth0.jwt.JWT
import com.auth0.jwt.interfaces.DecodedJWT
import com.kjpar0317.demo.model.Users
import io.javalin.security.RouteRole
import java.util.*

fun decodeJWT(token: String): DecodedJWT = JWT.require(Cipher.algorithm).build().verify(token)

fun createJWT(user: Users, role: RouteRole): String? =
    JWT.create()
        .withIssuedAt(Date())
        .withSubject(user.id)
        .withClaim("role", role.toString())
        .withExpiresAt(Date(System.currentTimeMillis() + 1 * 24 * 60 * 60 * 1000))
        .sign(Cipher.algorithm)