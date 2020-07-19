package micronaut.graal.masterDataService.domain.service

import micronaut.graal.masterDataService.domain.entity.User
import micronaut.graal.masterDataService.domain.repository.UserMasterRepository
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.collections.*;

@Singleton
class UserMasterManagementServiceImpl
@Inject
constructor(private val repository: UserMasterRepository): UserMasterManagementService
{

    override fun findAll(): List<User>{
        return this.repository.findAllActiveUser()
    }
}
