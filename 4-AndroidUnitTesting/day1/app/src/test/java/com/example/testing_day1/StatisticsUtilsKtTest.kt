package com.example.testing_day1


import com.example.testing_day1.data.Task
import com.example.testing_day1.statistics.getActiveAndCompletedStats
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test


class StatisticsUtilsKtTest{

    @Test
    fun getActiveAndCompletedStats_nullList_zeroActiveZeroComplete(){
        /*Given*/
        val tasks : List<Task>? = null

        /*When*/
        val result = getActiveAndCompletedStats(tasks)

        /*Then*/
        assertThat(result.activeTasksPercent, `is`(0f))
        assertThat(result.completedTasksPercent, `is`(0f))
    }

    @Test
    fun getActiveAndCompletedStats_emptyList_zeroActiveZeroComplete(){
        /*Given*/
        val tasks : List<Task> = emptyList()

        /*When*/
        val result = getActiveAndCompletedStats(tasks)

        /*Then*/
        assertThat(result.activeTasksPercent, `is`(0f))
        assertThat(result.completedTasksPercent, `is`(0f))
    }

    @Test
    fun getActiveAndCompletedStats_2complete3activeTask_40complete60active(){
        /*Given*/
        val tasks : List<Task> = listOf(
            Task().apply { isCompleted = true },
            Task().apply { isCompleted = true },
            Task().apply { isCompleted = false },
            Task().apply { isCompleted = false },
            Task().apply { isCompleted = false }
        )

        /*When*/
        val result = getActiveAndCompletedStats(tasks)

        /*Then*/
        assertThat(result.activeTasksPercent, `is`(60f))
        assertThat(result.completedTasksPercent, `is`(40f))
    }

    @Test
    fun getActiveAndCompletedStats_noComplete_100activeZeroComplete(){
        /*Given*/
        val tasks : List<Task> = listOf(
            Task().apply { isCompleted = false },
            Task().apply { isCompleted = false },
            Task().apply { isCompleted = false },
            Task().apply { isCompleted = false },
            Task().apply { isCompleted = false }
        )

        /*When*/
        val result = getActiveAndCompletedStats(tasks)

        /*Then*/
        assertThat(result.activeTasksPercent, `is`(100f))
        assertThat(result.completedTasksPercent, `is`(0f))
    }

}