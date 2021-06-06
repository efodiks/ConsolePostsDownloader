import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.appendText
import kotlin.io.path.createDirectories
import kotlin.io.path.createFile
import kotlin.io.path.exists

class FileSystemHelper(
    private val rootDirectory: Path
) {
    init {
        rootDirectory.createDirectories()
    }

    fun saveFile(fileName: String, fileContent: String) {
        val path = rootDirectory.resolve(fileName)
        if (path.exists()) {
            Files.delete(path)
        }
        path.createFile()
        path.appendText(fileContent)
    }
}