package com.tmobile.subbu.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.tmobile.subbu.TestCoroutineRule
import com.tmobile.subbu.model.data.*
import com.tmobile.subbu.model.repository.CardsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import java.lang.Exception

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CardsViewModelTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var cardsObserver: Observer<List<Card>>

    @Mock
    private lateinit var progressObserver: Observer<Int>

    @Mock
    private lateinit var shouldDisplayErrorObserver: Observer<Boolean>

    @Mock
    private lateinit var cardsRepository: CardsRepository

    @Test
    fun `givenServer Response 200 When Get Cards Returns Success`() {
        val list = ArrayList<Card>()
        list.add(
            Card(
                1,
                "title",
                CardInfo("", Attributes("#ffffff", Font(10)), null, null, null)
            )
        )
        testCoroutineRule.runBlockingTest {
            doReturn(list)
                .`when`(cardsRepository)
                .getCards()
            val viewModel = CardsViewModel(cardsRepository)
            viewModel.cards.observeForever(cardsObserver)
            viewModel.loaderVisibility.observeForever(progressObserver)
            viewModel.shouldDisplayError.observeForever(shouldDisplayErrorObserver)
            verify(progressObserver).onChanged(ArgumentMatchers.anyInt())
            verify(cardsRepository).getCards()
            verify(cardsObserver).onChanged(list)
            verify(shouldDisplayErrorObserver).onChanged(false)
            verify(progressObserver).onChanged(ArgumentMatchers.anyInt())
            viewModel.cards.removeObserver(cardsObserver)
            viewModel.loaderVisibility.removeObserver(progressObserver)
            viewModel.shouldDisplayError.removeObserver(shouldDisplayErrorObserver)
        }
    }

    @Test
    fun `given Server Response When Get Cards Returns Exception`() {
        testCoroutineRule.runBlockingTest {
            doReturn(Exception())
                .`when`(cardsRepository)
                .getCards()
            val viewModel = CardsViewModel(cardsRepository)
            viewModel.cards.observeForever(cardsObserver)
            viewModel.loaderVisibility.observeForever(progressObserver)
            viewModel.shouldDisplayError.observeForever(shouldDisplayErrorObserver)
            verify(shouldDisplayErrorObserver).onChanged(false)
            verify(progressObserver).onChanged(ArgumentMatchers.anyInt())
            verify(cardsRepository).getCards()
            verify(progressObserver).onChanged(ArgumentMatchers.anyInt())
            viewModel.cards.removeObserver(cardsObserver)
            viewModel.loaderVisibility.removeObserver(progressObserver)
            viewModel.shouldDisplayError.removeObserver(shouldDisplayErrorObserver)
        }
    }
}