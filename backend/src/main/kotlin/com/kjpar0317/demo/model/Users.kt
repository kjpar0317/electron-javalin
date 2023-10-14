package com.kjpar0317.demo.model

data class Users(val id: String,
                 val name: String,
                 val password: String,
                 val role: String? = null)