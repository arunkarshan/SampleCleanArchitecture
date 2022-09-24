package com.example.domain.base


interface BaseFlowResultWrapperUseCase<in Parameter, out Result> :
    BaseFlowUseCase<Parameter, ResultWrapper<Result>>