package micronaut.graal.masterDataService.domain.entity

data class User(val id: Long
                ,val userId: UserId
                ,val userName: String
                ,val password: String
                ,val isDeleted: Boolean
                ,val isLocked: Boolean){

}