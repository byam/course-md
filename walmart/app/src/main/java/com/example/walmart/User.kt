package com.example.walmart

import java.io.Serial
import java.io.Serializable

class User(
    val firstName: String?,
    val lastName: String?,
    val username: String?,
    var password: String?
): Serializable {
    fun updatePassword(newPassword: String) {
        require(newPassword.length >= 8) {
            "Password must be at least 8 characters long"
        }
        password = newPassword
    }

    override fun toString(): String {
        return "User(firstName='$firstName', lastName='$lastName', username='$username')"
    }
}

// Usage example
fun main() {
    try {
        val user = User("John", "Doe", "john.doe@example.com", "password123")
        println(user)

        user.updatePassword("newPassword123")
        println("Password updated successfully!")
    } catch (e: IllegalArgumentException) {
        println(e.message)
    }
}
