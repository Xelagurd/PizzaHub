package xelagurd.pizzahub.repository

import org.springframework.data.repository.CrudRepository
import xelagurd.pizzahub.dto.User

interface UserRepository : CrudRepository<User, Long> {
    fun findByUsername(username: String): User?
}