package domain

import domain.Utils.isEmptyAfterTrim

class RegistrationValidator(
    private val firstName: String,
    private val email: String,
    private val password: String,
) : Validator {
    override fun isValid(): Pair<Boolean, String> {
        if (firstName.isEmptyAfterTrim()) {
            return Pair(false, "Please enter your name")
        }

        if (email.isEmptyAfterTrim()) {
            return Pair(false, "Please enter email")
        }

        if (password.isEmptyAfterTrim()) {
            return Pair(false, "Please enter password")
        }

        if (password.length < 8) {
            return Pair(false, "Your password should contain at least 8 letters")
        }

        return Pair(true, "")
    }
}