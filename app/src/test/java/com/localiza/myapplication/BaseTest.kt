package com.localiza.myapplication

import io.mockk.mockk
import io.mockk.unmockkAll
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class BaseTest {

    @BeforeAll
    open fun setUp() {
        //TODO
    }

    @AfterAll
    open fun tearDown() {
        unmockkAll()
    }

}