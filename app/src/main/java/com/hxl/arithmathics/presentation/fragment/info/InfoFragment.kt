package com.hxl.arithmathics.presentation.fragment.info

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hxl.arithmathics.R
import com.hxl.arithmathics.databinding.FragmentInfoBinding
import com.hxl.arithmathics.presentation.activity.MainActivity
import com.hxl.arithmathics.presentation.fragment.info.credits.CreditFragment
import java.util.*


class InfoFragment : Fragment() {
    lateinit var binding: FragmentInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInfoBinding.inflate(layoutInflater, container, false)

        binding.year = Calendar.getInstance().get(Calendar.YEAR).toString()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvEmail.setOnClickListener { openEmail() }
        binding.tvGithub.setOnClickListener {
            openWeb("https://" + requireContext().getString(R.string.github_page))
        }
        binding.tvPrivacyPolicy.setOnClickListener { openWeb(requireContext().getString(R.string.privacy_policy)) }
        binding.tvCredits.setOnClickListener { (requireActivity() as MainActivity).replaceFragment(CreditFragment(), true) }

    }

    private fun openEmail() {
        startActivity(
            Intent(
                Intent.ACTION_SENDTO,
                Uri.parse("mailto:${requireContext().getString(R.string.developer)}")
            )
        )
    }

    private fun openWeb(url: String) {
        requireActivity().startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }

}