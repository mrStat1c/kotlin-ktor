import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class KtorControllerTest {


    @Test
    fun getTest() =
        runBlocking {
            HttpClient(CIO).use {
                val res = it.get<String>("http://localhost:8080/info?id=FF3450AB")
                assertEquals("""{"id":"FF3450AB"}""", res)
            }
        }

    @Test
    fun postTest() =
        runBlocking {
            HttpClient(CIO).use {
                val res = it.post<String>("http://localhost:8080/info") {
                    body = "{\"name\":\"Mr Jack\"}"
                }
                assertEquals("{\"greeting\":\"Hello, Mr Jack\"}", res)
            }
        }
}