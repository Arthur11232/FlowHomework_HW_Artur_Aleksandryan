package otus.homework.flowcats

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class CatsRepository(
    private val catsService: CatsService,
    private val refreshIntervalMs: Long = 5000
) {

    fun listenForCatFacts() = flow {
        while (true) {
            try {
                val latestNews = catsService.getCatFact()
                emit(latestNews)
            } catch (e: Exception){
                println()
                emit(Result.Error(e.message, e))
            }
            delay(refreshIntervalMs)
        }
    }
}