package com.byjus.headlines.model

import com.byjus.headlines.MainContract

class Model :MainContract.ModelCallBack,OnApiResponse{
    override fun getData(onFinishedListener: MainContract.ModelCallBack.OnFinishedListener) {
        TODO("Not yet implemented")
    }

    override fun onCallComplete(success: Boolean, response: String?, callerId: Int) {
        TODO("Not yet implemented")
    }

}