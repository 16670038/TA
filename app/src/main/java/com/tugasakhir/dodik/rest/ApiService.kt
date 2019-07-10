package com.tugasakhir.dodik.rest

import com.tugasakhir.dodik.BuildConfig
import com.tugasakhir.dodik.model.ModelRootData
import retrofit.http.GET
import rx.Observable

interface ApiService {

    @GET("detil_jadwal?key="+ BuildConfig.API_KEY + "&npm=" + BuildConfig.NPM)
    fun getJadwalKuliah(): Observable<ModelRootData>

    @GET("detil_pribadi?key=" + BuildConfig.API_KEY + "&npm=" + BuildConfig.NPM)
    fun getProfil(): Observable<ModelRootData>

}