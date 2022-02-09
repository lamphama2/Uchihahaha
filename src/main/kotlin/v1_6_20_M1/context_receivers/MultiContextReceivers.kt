package v1_6_20_M1.context_receivers

import java.io.PrintStream

context(LoggingContext1)
private fun startBusinessOperation() {
    log1.println("Operation 1 has started")
}

context(LoggingContext1, LoggingContext2, LoggingContext3)
private fun startBusinessOperation() {
    log1.println("Operation 1 has started")
    log2.println("Operation 2 has started")
    log3.println("Operation 3 has started")
}

class LoggingContext1 {
    val log1: PrintStream = System.out
}

class LoggingContext2 {
    val log2: PrintStream = System.out
}

class LoggingContext3 {
    val log3: PrintStream = System.out
}

fun main() {
    val loggingContext1 = LoggingContext1()
    val loggingContext2 = LoggingContext2()
    val loggingContext3 = LoggingContext3()

    with(loggingContext1) {
        with(loggingContext2) {
            with(loggingContext3) {
                startBusinessOperation()
            }
        }
    }
    //or
    with(loggingContext1) {
        loggingContext2.apply {
            loggingContext3.run {
                startBusinessOperation()
            }
        }
    }

    with(loggingContext1) {
        startBusinessOperation()
    }
}