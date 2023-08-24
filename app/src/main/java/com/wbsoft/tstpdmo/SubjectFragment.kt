package com.wbsoft.tstpdmo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.wbsoft.tstpdmo.databinding.FragmentSubjectBinding
import com.wbsoft.tstpdmo.networks.AllClassAPI
import com.wbsoft.tstpdmo.networks.NetworkResult
import com.wbsoft.tstpdmo.utils.Constants
import com.wbsoft.tstpdmo.viewmodels.ClassViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class SubjectFragment : Fragment() {

    private var _binding: FragmentSubjectBinding?=null
    private val binding get()=_binding!!

    private val classViewModel by viewModels<ClassViewModel>()

    @Inject
    lateinit var classAPI: AllClassAPI

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentSubjectBinding.inflate(inflater,container, false)

        CoroutineScope(Dispatchers.IO).launch {
            val response= classAPI.getAllClass()
            Log.d(Constants.TAG, response.body().toString())
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindObservers()
    }

    private fun bindObservers() {
        classViewModel.classLiveData.observe(viewLifecycleOwner, Observer {
            //binding.progressBar.isVisible=false

            when(it){
                is NetworkResult.Success->{}
                is NetworkResult.Error -> {
                    Toast.makeText(requireContext(),it.message.toString(), Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Loading -> {
                    //binding.progressBar.isVisible=true
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}