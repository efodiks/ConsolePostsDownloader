import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import kotlinx.serialization.json.Json

class ConsolePostsDownloader(args: Array<String>) {
    private val json = Json {
        prettyPrint = true
    }
    private val options = CommandLineParser(APPLICATION_NAME).parseArgs(args)
    private val client = createClient(json)
    private val fileSystemHelper = FileSystemHelper(options.output)

    suspend fun start() {
        val posts = client.get<List<Post>>(options.postsEndPointUrl)
        for (post in posts) {
            fileSystemHelper.saveFile(
                fileName = "${post.id}.json",
                fileContent = post.serialize(json)
            )
        }
    }

    private fun createClient(json: Json) = HttpClient(CIO) {
        install(JsonFeature) {
            serializer = KotlinxSerializer(json)
        }
    }

    companion object {
        const val APPLICATION_NAME = "Console Post Downloader"
    }
}