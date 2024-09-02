package xelagurd.resourceserver.repository

import org.springframework.data.repository.CrudRepository
import xelagurd.resourceserver.dto.User

interface UserRepository : CrudRepository<User, Long> {
    fun findByUsername(username: String): User?
}