package com.example.poverty.homepage


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.example.poverty.Database.PostDatabaseDao
import com.example.poverty.Database.PostRoomDatabase
import com.example.poverty.Database.RecycleViewPost
import com.example.poverty.R
import com.example.poverty.databinding.FragmentHomeBinding
//import com.example.poverty.databinding.FragmentHomeBindingImpl
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.layout_post_list_item.*
import kotlinx.coroutines.CoroutineScope

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment( ) {

    private lateinit var homeViewModel: HomeViewModel
    private val REQUEST_CODE = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Get a reference to the binding object and inflate the fragment views.
        val binding: FragmentHomeBinding= DataBindingUtil.inflate(
            inflater, R.layout.fragment_home, container, false)

        val adapter = activity?.let { PostAdapter(it) }
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        val topSpacingItemDecoration = TopSpacingItemDecoration(50)
        binding.recyclerView.addItemDecoration(topSpacingItemDecoration)

        //Initialize ViewModel
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        //Any changes in Recycler View, it will observe
        homeViewModel.allPost.observe(viewLifecycleOwner, Observer {
            // Update the cached copy of the users in the adapter
                users -> users?.let { adapter?.setPosts(it) }
        })

        /*val vp: ViewPager? = activity?.findViewById(R.id.view_pager)
        buttonDonate.setOnClickListener {
            vp?.currentItem =2
    }*/


        /*fab.setOnClickListener {
            val intent: Intent = Intent(this, AddActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE)
        }*/

        /*val post = RecycleViewPost(1,"Hey","Hey123","123")
        val post2 = RecycleViewPost(2,"Hey","Hey123","123")
        homeViewModel.insertPost(post)
        homeViewModel.insertPost(post2)*/
        //val post3 = RecycleViewPost(3,"Hey","Hey123","https://raw.githubusercontent.com/yujune/AssignmentPostImage/master/1503608938-598.jpg")
        //homeViewModel.insertPost(post3)
        //homeViewModel.deleteAllPost()








        /*val application = requireNotNull(this.activity).application

        // Create an instance of the ViewModel Factory.
        val dataSource = PostRoomDatabase.getInstance(application).postDatabaseDao()
        val viewModelFactory =
            HomeViewModelFactory(dataSource, application)

        // Get a reference to the ViewModel associated with this fragment.
        val homeViewModel =
            ViewModelProviders.of(
                this, viewModelFactory).get(HomeViewModel::class.java)

        binding.homeViewModel = homeViewModel

        /*val adapter = PostAdapter()
        binding.recyclerView.adapter = adapter

        homeViewModel.posts.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.data = it
            }
        })*/*/

        //binding.setLifecycleOwner(this)


        return binding.root
    }





    /*override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = PostAdapter()
    }*/






}
