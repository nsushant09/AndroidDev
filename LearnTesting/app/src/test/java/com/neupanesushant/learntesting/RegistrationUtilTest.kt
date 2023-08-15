package com.neupanesushant.learntesting

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class RegistrationUtilTest {

    @Test
    fun `empty username returns false`() {
        val result = RegistrationUtil.validateRegistrationInput("", "123", "123")
        assertThat(result).isFalse()
    }

    @Test
    fun `empty password returns false`() {
        val result = RegistrationUtil.validateRegistrationInput("validUsername", "", "1234")
        assertThat(result).isFalse()
    }

    @Test
    fun `username already taken returns false`() {
        val result = RegistrationUtil.validateRegistrationInput("Peter", "12345", "12345")
        assertThat(result).isFalse()
    }

    @Test
    fun `confirmed and password not matching returns false`() {
        val result = RegistrationUtil.validateRegistrationInput("validUsername", "12345", "1234")
        assertThat(result).isFalse()
    }

    @Test
    fun `less than 2 digit password returns false`() {
        val result = RegistrationUtil.validateRegistrationInput("validUsername", "1", "1")
        assertThat(result).isFalse()
    }

    @Test
    fun `valid details returns true`() {
        val result = RegistrationUtil.validateRegistrationInput("validUsername", "12345", "12345")
        assertThat(result).isTrue()
    }
}