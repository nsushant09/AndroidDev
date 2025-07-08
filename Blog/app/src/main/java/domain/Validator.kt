package domain

interface Validator {
    fun isValid() : Pair<Boolean, String>
}