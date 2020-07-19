package micronaut.graal.masterDataService.domain.repository

import micronaut.graal.masterDataService.domain.entity.User
import javax.inject.Singleton

interface UserMasterRepository {
    fun findAllActiveUser(): List<User>
}
