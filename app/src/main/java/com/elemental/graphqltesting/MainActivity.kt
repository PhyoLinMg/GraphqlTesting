package com.elemental.graphqltesting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.elemental.graphqltesting.data.model.GraphqlQuery
import com.elemental.graphqltesting.data.response.Response
import com.elemental.graphqltesting.data.response.Users
import com.elemental.graphqltesting.network.ApiService
import com.elemental.graphqltesting.network.ConnectivityInterceptorImpl
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val api=ApiService.invoke(ConnectivityInterceptorImpl(baseContext))
        val query=Queries.getUserName("auth0|5e4f941dcaf6ae0f3a4e1ec0")
        val graphqlQuery1=GraphqlQuery(query)
        val response1=api.getUserName(query = graphqlQuery1)

        response1.enqueue(object:Callback<Users>{
            override fun onFailure(call: Call<Users>, t: Throwable) {

            }

            override fun onResponse(call: Call<Users>, response: retrofit2.Response<Users>) {
                text.text = response.body()!!.data.users[0].name
            }

        })


        btn_submit.setOnClickListener {
            val query=Queries.insertToDo(et_title.text.toString(),true)
            val graphqlQuery=GraphqlQuery(query)
            val response=api.insertTodo(query = graphqlQuery)
            response.enqueue(object:Callback<Response>{
                override fun onFailure(call: Call<Response>, t: Throwable) {
                    Log.d("failure",call.toString())
                }

                override fun onResponse(
                    call: Call<Response>,
                    response: retrofit2.Response<Response>
                ) {
                    Log.d("response",response.body().toString())
                }

            })
        }
    }
}
