package underTest

interface MyLogger {
    fun write(text:String)
}

class RealLogger:MyLogger{
    override fun write(text: String) {
        TODO("not implemented")
    }
}

