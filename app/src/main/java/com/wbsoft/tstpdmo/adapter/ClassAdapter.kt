package com.wbsoft.tstpdmo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wbsoft.tstpdmo.databinding.ItemFavMenuListBinding
import com.wbsoft.tstpdmo.models.AllClassM

class ClassAdapter():ListAdapter<AllClassM.ClassData, ClassAdapter.ClassViewHolder>(ComparatorDiffUtil()) {
    class ClassViewHolder(private val binding: ItemFavMenuListBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(allClass: AllClassM.ClassData){
            binding.elevenTlveTVId.text=allClass.classData.className
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassViewHolder {
        val binding= ItemFavMenuListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ClassViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ClassViewHolder, position: Int) {
        val allClass= getItem(position)
        allClass?.let {
            holder.bind(it)
        }
    }

    class ComparatorDiffUtil : DiffUtil.ItemCallback<AllClassM.ClassData>() {
        override fun areItemsTheSame(oldItem: AllClassM.ClassData, newItem: AllClassM.ClassData): Boolean {
            TODO("Not yet implemented")
        }

        override fun areContentsTheSame(oldItem: AllClassM.ClassData, newItem: AllClassM.ClassData): Boolean {
            return oldItem==newItem
        }

    }

}


