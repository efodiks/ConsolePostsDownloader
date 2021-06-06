# Console Posts Downloader

This kotlin console application downloads posts
from https://jsonplaceholder.typicode.com and
saves each of them to new .json file.

## How to run this project

To see available options use:

`./gradlew run --args="-h"`

To run with default arguments 
(
output directory - "./download", 
api endpoint url - "https://jsonplaceholder.typicode.com/posts" 
):

`./gradlew run`