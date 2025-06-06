package com.example.testing_day1


import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.testing_day1.Event
import com.example.testing_day1.tasks.TasksFilterType
import com.example.testing_day1.tasks.TasksViewModel
import getOrAwaitValue
import org.junit.Before
import org.junit.runner.RunWith
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.not
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.robolectric.annotation.Config

@Config(manifest= Config.NONE)
@RunWith(AndroidJUnit4::class)
class TasksViewModelTest{
    private lateinit var app: Application
    private lateinit var tasksViewModel: TasksViewModel


    @Before
    fun setupViewModel(){
        app = ApplicationProvider.getApplicationContext()
        tasksViewModel = TasksViewModel(app)
    }

    @Test
    fun addNewTask_notifyAdditionOfTask(){
        //Given
        tasksViewModel.addNewTask()
        //When
        val result = tasksViewModel.newTaskEvent.getOrAwaitValue()
        //Then
        assertThat(result, (not(nullValue())))
    }

    @Test
    fun setFiltering_AllTasks_notify(){
        //Given
        val filtering = TasksFilterType.ALL_TASKS
        //When
        tasksViewModel.setFiltering(filtering)
        val currFilteringLabel = tasksViewModel.currentFilteringLabel.getOrAwaitValue()
        //Then
        assertThat(currFilteringLabel, (not(nullValue())))
    }
}