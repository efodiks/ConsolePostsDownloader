import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.createFile
import kotlin.io.path.exists

@Serializable
data class Post(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String,
) {
    fun serialize(json: Json): String =
        json.encodeToString(serializer(), this)
}