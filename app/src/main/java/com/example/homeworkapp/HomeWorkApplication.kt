package com.example.homeworkapp

import android.app.Application

class HomeWorkApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)
    }
}