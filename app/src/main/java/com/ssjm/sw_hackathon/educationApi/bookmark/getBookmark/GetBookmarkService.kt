package com.ssjm.sw_hackathon.educationApi.bookmark.getBookmark

import com.ssjm.sw_hackathon.token.GloabalApplication
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

private var accessTokenValue: String = "Bearer " + GloabalApplication.prefs.getString("accessToken", "")
private var refreshTokenValue: String = GloabalApplication.prefs.getString("refreshToken", "")

interface GetBookmarkService {
    @GET("education/bookmarks")
    fun getBookmark(
        @Header("Authorization") authorization: String = accessTokenValue, // 로그인으로 발급받은 AccessToken: JWT {발급받은 토큰} 형태로 입력
        @Header("refresh-token") refreshToken: String = refreshTokenValue,  // 로그인으로 발급받은 RefreshToken
    ): Call<GetBookmarkResponse>
}