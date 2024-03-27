package com.example.horoscapp.data

import android.util.Log
import com.example.horoscapp.data.network.HoroscopeApiService
import com.example.horoscapp.domain.Repository
import com.example.horoscapp.domain.model.PredictionModel
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val horoscopeApiService: HoroscopeApiService):
    Repository {
    override suspend fun getPrediction(sign: String): PredictionModel? {
        runCatching {
            horoscopeApiService.getHoroscope(sign)
        }
            .onSuccess{
                return it.toDomain()
            }
            .onFailure{
                Log.i("mdz", "error ${it.message}")
                return null
            }
        return null;
    }
}