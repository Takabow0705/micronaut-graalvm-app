package micronaut.graal.masterDataService.domain.service

import micronaut.graal.masterDataService.domain.entity.User

interface UserMasterManagementService {
    abstract fun findAll(): List<User>
}
