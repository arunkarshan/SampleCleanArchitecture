package com.example.domain.base

interface BaseUseCase<in Parameter, out Result> {
    suspend operator fun invoke(params: Parameter): Result
}

