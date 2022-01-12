package com.sachy.firstbook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.sachy.firstbook.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() , HomeAdapter.AdapterCallback {
    private lateinit var binding: ActivityHomeBinding
    private val data=ArrayList<String>()
    private var type=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_home)
        setData()
    }
    private fun setData()
    {
        data.add("A")
        data.add("1")
        data.add("अ")
        data.add("A")
        init()
    }
    private fun init()
    {
        binding.rvCycle.adapter=HomeAdapter(this,data)
        binding.rvCycle.layoutManager= GridLayoutManager(this,2)
    }

    override fun itemClick(position:Int) {

        when(position)
        {
            0->{
                type=0
            }
            1->{
               type=1
            }
            2->{
                type=2
            }
            3->{
                type=3
            }
        }
        startActivity(Intent(this,AzActivity::class.java).putExtra("type",type))

    }
}