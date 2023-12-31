package com.ssjm.sw_hackathon.goalApi.getProgressGoal

import com.ssjm.sw_hackathon.token.GloabalApplication
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface GetProgressGoalService {
    @GET("goals/progress")
    fun getProgressGoal(
        @Header("Authorization") authorization: String, // 로그인으로 발급받은 AccessToken: JWT {발급받은 토큰} 형태로 입력
        @Header("refresh-token") refreshToken: String,  // 로그인으로 발급받은 RefreshToken
    ): Call<GetProgressGoalResponse>
}