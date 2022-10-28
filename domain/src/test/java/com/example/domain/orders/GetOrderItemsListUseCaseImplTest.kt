package com.example.domain.orders

import com.example.domain.base.ResultWrapper
import com.example.domain.models.DeliveryItemDetailDomain
import com.example.domain.models.DeliveryItemDomain
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.koin.core.KoinApplication
import org.koin.java.KoinJavaComponent.get
import org.koin.java.KoinJavaComponent.inject
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule

/*********************************************************
 * Class   :  GetOrderItemsListUseCaseImplTest
 * Author  :  Arun Nair
 * Created :  16/10/2022
 *
 * Purpose :
 *
 * Rework Details:
 * 1) {Author} :  {Date} : {Details}
 */

@OptIn(ExperimentalCoroutinesApi::class)
internal class GetOrderItemsListUseCaseImplTest{

    private val repository: OrderRepository = mockk()

    private val testDispatcher: CoroutineDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

    private var getOrderItemsListUseCase: GetOrderItemsListUseCase = GetOrderItemsListUseCaseImpl(repository, testDispatcher)


    @Before
    fun init(){
        KoinApplication.init()
    }

    @BeforeEach
    fun setUp() {
        clearAllMocks()
        every { runBlocking { repository.getOrders() } } returns flowOf(listOf(1L, 2L))

        every { runBlocking { repository.getOrderItems(any()) } } returns flowOf(listOf(mockk(), mockk(), mockk(), mockk(), mockk()))
        every { runBlocking { repository.getDeliveryItemDiscount(any()) } } returns flowOf(mockk())
    }

    @Test
    fun `Test Invoke function when repository returns success, and load combined as false`() {
        testScope.runBlockingTest {
            val result = getOrderItemsListUseCase.invoke(GetOrderItemsListUseCase.Params(true)).toList()
            assert(result is ResultWrapper.Success<*>)
        }
    }
}