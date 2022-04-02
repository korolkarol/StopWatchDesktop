package extenstion


fun String.asByteResource(resourceOperation: (ByteArray) -> Unit) {
    val content = object {}.javaClass.getResource(this).readBytes()
    resourceOperation(content)
}