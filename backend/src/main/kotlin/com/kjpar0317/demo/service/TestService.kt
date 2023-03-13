package com.kjpar0317.demo.service

import com.kjpar0317.demo.repository.TestRepository

object TestService {
//    private lateinit var repository: TestRepository

//    init {
//    }

    fun findById(): String {
        return TestRepository.test();
    }
}