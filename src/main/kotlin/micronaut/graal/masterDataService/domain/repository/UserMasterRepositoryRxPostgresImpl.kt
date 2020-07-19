package micronaut.graal.masterDataService.domain.repository

import io.reactiverse.reactivex.pgclient.PgClient
import io.reactiverse.reactivex.pgclient.PgIterator
import micronaut.graal.masterDataService.domain.entity.User
import io.reactiverse.reactivex.pgclient.PgRowSet
import micronaut.graal.masterDataService.domain.entity.UserId
import org.slf4j.LoggerFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserMasterRepositoryRxPostgresImpl
@Inject
constructor(val pgClient: PgClient): UserMasterRepository {

    override fun findAllActiveUser(): List<User>{
        return pgClient.rxQuery("select * from users where is_deleted = false and is_locked = false")
                .doOnSuccess { logger.info(it.toList().toString()) }
                .map {entityMapper(it)}.blockingGet()
    }

    companion object{
        private val logger = LoggerFactory.getLogger(this::class.java)
    }

}

    fun entityMapper(pgRowSet: PgRowSet): List<User>{
        var size : Int = 0;
        val iter : PgIterator = pgRowSet.iterator()
        val result : MutableList<User> = mutableListOf()

        while(iter.hasNext()){
            val row = iter.next()
            val userId = UserId(
                    row.getLong("version"),
                    row.getString("mail_address")
            )
            val user = User(
                    row.getLong("id"),
                    userId,
                    row.getString("user_name"),
                    row.getString("password"),
                    row.getBoolean("is_locked"),
                    row.getBoolean("is_deleted")
            )
            size++
            result.add(user)
        }
        return result.toList()
    }