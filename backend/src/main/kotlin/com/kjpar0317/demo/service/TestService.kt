package com.kjpar0317.demo.service

import com.kjpar0317.demo.repository.findByTest
import java.util.*

// 똑같은 이름으로 테스트
fun findByTest(): String {
    return findByTest();
}

fun findByAll(): List<String> {
    return listOf("a", "b", "c")
}