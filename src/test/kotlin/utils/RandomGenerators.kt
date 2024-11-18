package utils

import org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric
import org.apache.commons.lang3.RandomStringUtils.randomNumeric

object RandomGenerators {
    fun generateRandomEmail(): String {
        return generateString(6) + "@zando.com"
    }

    fun generateString(length: Int): String = randomAlphanumeric(length)

    fun generateNumericString(length: Int): String = randomNumeric(length)
}
