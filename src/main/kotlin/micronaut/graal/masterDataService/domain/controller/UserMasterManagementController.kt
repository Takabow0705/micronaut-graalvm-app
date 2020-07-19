package micronaut.graal.masterDataService.domain.controller

import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.annotation.*
import io.reactivex.Flowable
import io.reactivex.Single
import micronaut.graal.masterDataService.domain.entity.User
import micronaut.graal.masterDataService.domain.service.UserMasterManagementService
import org.slf4j.LoggerFactory
import net.logstash.logback.argument.StructuredArguments
import javax.inject.Inject

@Controller("/users")
class UserMasterManagementController
@Inject
constructor(private val service: UserMasterManagementService)
{

    @Get("/")
    @Produces(MediaType.APPLICATION_JSON)
    fun index(@Body req: Flowable<String>): Single<MutableHttpResponse<List<User>>> {
        val users = service.findAll()
        logger.info("users: {}", users.toString())
        return Single.just(HttpResponse.created(users))
    }

    @Post("/echo")
    @Produces(MediaType.APPLICATION_JSON)
    fun echo(@Body text: Flowable<String>): Single<MutableHttpResponse<String>> {
        return text.collect({ StringBuffer() }, { obj, str -> obj.append(str) }).map { buffer -> HttpResponse.ok(buffer.toString()) }
    }

    companion object{
        private val logger = LoggerFactory.getLogger(this::class.java)
    }

}


