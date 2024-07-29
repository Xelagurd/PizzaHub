package xelagurd.pizzahub.dto

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails


@Entity(name = "users")
class User(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,
    @JvmField
    var username: String = "",
    @JvmField
    var password: String = "",
    var fullname: String = "",
    var street: String = "",
    var city: String = "",
    var state: String = "",
    var zip: String = "",
    var phoneNumber: String = ""
) : UserDetails {
    override fun getAuthorities(): List<GrantedAuthority> {
        return listOf(SimpleGrantedAuthority("ROLE_USER"))
    }

    override fun getUsername(): String {
        return username
    }

    fun setUsername(username: String) {
        this.username = username
    }

    override fun getPassword(): String {
        return password
    }

    fun setPassword(password: String) {
        this.password = password
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

    override fun toString(): String {
        return "User(username=$username, fullname=$fullname)"
    }
}