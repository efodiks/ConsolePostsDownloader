import java.net.URL
import java.nio.file.Path

data class CommandLineOptions(
    val output: Path,
    val postsEndPointUrl: URL
)