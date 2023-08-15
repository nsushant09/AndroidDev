package com.neupanesushant.learntesting

object RegistrationUtil {

    private val existingUsers = listOf(
        "Peter",
        "Carl"
    )

    /**
     * The input is not valid if..
     * ...the user name is empty
     * ...the password is empty
     * ...the username is already taken
     * ...the confirmed password does not match with password
     * ...the password contains less than 2 digits
     */
    fun validateRegistrationInput(
        username: String,
        password: String,
        confirmedPassword: String
    ): Boolean {

        if (username.isEmpty()) return false
        if (password.isEmpty()) return false
        if (existingUsers.contains(username)) return false
        if (password.length < 3) return false
        if (!password.equals(confirmedPassword, false)) return false

        return true
    }

}