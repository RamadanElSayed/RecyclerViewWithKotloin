package com.example.kotlinrecyclerview

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
class Main2Activity : AppCompatActivity() {
    private var myCompositeDisposable: CompositeDisposable? = null
    private var myRetroCryptoArrayList: ArrayList<dataObject>? = null
    private val BASE_URL="http://www.arablancer.org/cplasplas/public/api/"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        // to make navigation view in arabic
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            window.decorView.layoutDirection=View.LAYOUT_DIRECTION_RTL
        }

        var value= intent.extras.get("name")
        supportActionBar?.title=value.toString()
        loadData()
        makeRetrofitLink()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId)
        {

            R.id.app_bar_switch->{
              Toast.makeText(this,"Goooooo",Toast.LENGTH_LONG)  .show()
            }

            R.id.name->{
                Toast.makeText(this,"jjjjjjjjjjjjjjj",Toast.LENGTH_LONG)  .show()

            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun makeRetrofitLink()
    {
      val gson = provideGson()
    val url="http://www.arablancer.org/cplasplas/public/api/"
      val retrofitObject=Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create(gson))
          .build()
        val api=retrofitObject.create(RetrofitService::class.java)
        val data=api.getListData2()
        var item=data.enqueue(object :Callback<ArrayList<dataObject>>{
            override fun onFailure(call: Call<ArrayList<dataObject>>, t: Throwable) {
                Log.v("vvvvv",t.message)
            }

            override fun onResponse(call: Call<ArrayList<dataObject>>, response: Response<ArrayList<dataObject>>) {

                Toast.makeText(applicationContext,"size "+response.body()!!.size,Toast.LENGTH_LONG).show()
            }

        })

//
//        val retrofitService:RetrofitService=retrofitObject.create(RetrofitService::class.java)
//        retrofitService.getListData().subscribeOn(Schedulers.io()).
//            observeOn(AndroidSchedulers.mainThread()).subscribe(()
    }


    private fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        return gsonBuilder.create()
    }
    //Implement loadData//

    private fun loadData() {

//Define the Retrofit request//

        val requestInterface = Retrofit.Builder()

//Set the API’s base URL//

            .baseUrl(BASE_URL)

//Specify the converter factory to use for serialization and deserialization//

            .addConverterFactory(GsonConverterFactory.create())

//Add a call adapter factory to support RxJava return types//

            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

//Build the Retrofit instance//

            .build().create(RetrofitService::class.java)

//Add all RxJava disposables to a CompositeDisposable//

        myCompositeDisposable?.add(requestInterface.getListData()

//Send the Observable’s notifications to the main UI thread//

            .observeOn(AndroidSchedulers.mainThread())

//Subscribe to the Observer away from the main UI thread//

            .subscribeOn(Schedulers.io())
            .subscribe(this::handleResponse))

    }

    private fun handleResponse(cryptoList: List<dataObject>) {

        Toast.makeText(this,"sgshghs"+cryptoList.size,Toast.LENGTH_LONG).show()
          myRetroCryptoArrayList = ArrayList(cryptoList)

          for(i in 0 until myRetroCryptoArrayList!!.size )
          {
              supportActionBar?.title=myRetroCryptoArrayList!![i].index_name
          }
//        myAdapter = MyAdapter(myRetroCryptoArrayList!!, this)
//
////Set the adapter//
//
//        cryptocurrency_list.adapter = myAdapter

    }

    override fun onDestroy() {
        super.onDestroy()

//Clear all your disposables//

        myCompositeDisposable?.clear()

    }
}
