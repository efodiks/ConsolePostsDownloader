import kotlinx.cli.ArgParser
import kotlinx.cli.ArgType
import kotlinx.cli.default
import java.net.URL
import java.nio.file.Path
import kotlin.io.path.Path

class CommandLineParser(private val applicationName: String) {
    fun parseArgs(args: Array<String>): CommandLineOptions {
        val parser = ArgParser(applicationName)
        val outputPath by parser.option(
            type = ArgType.Path,
            fullName = "output",
            shortName = "o",
            description = "Output directory path",
        ).default(Path("./download/"))
        val postsEndpoint by parser.option(
            type = ArgType.URL,
            fullName = "endpoint",
            shortName = "e",
            description = "Posts api endpoint url",
        ).default(URL("https://jsonplaceholder.typicode.com/posts"))
        parser.parse(args)
        return CommandLineOptions(outputPath, postsEndpoint)
    }
}

private val ArgType.Companion.Path
    get() = object : ArgType<Path>(true) {
        override val description: kotlin.String
            get() = "{ Absolute path or relative directory path. Does not need to exist. }"

        override fun convert(value: kotlin.String, name: kotlin.String): Path = try {
            Path(value)
        } catch (e: Exception) {
            throw ParserException("Parameter $name is expected to be a valid file path. $value provided")
        }
    }

private val ArgType.Companion.URL: ArgType<URL>
    get() = object : ArgType<URL>(true) {
        override val description: kotlin.String
            get() = "{ URL }"

        override fun convert(value: kotlin.String, name: kotlin.String): URL = try {
            URL(value)
        } catch (e: Exception) {
            throw ParserException("Parameter $name is expected to be a valid url. $value provided")
        }
    }