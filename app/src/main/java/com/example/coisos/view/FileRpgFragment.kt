package com.example.coisos.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.coisos.databinding.FragmentFileRpgBinding
import com.example.coisos.viewmodel.FileRpgViewModel

class FileRpgFragment : Fragment() {

    private var _binding: FragmentFileRpgBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fileRpgViewModel =
            ViewModelProvider(this)[FileRpgViewModel::class.java]

        _binding = FragmentFileRpgBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textGallery

        fileRpgViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}