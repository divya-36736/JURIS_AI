package com.example.jjurisai2


import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jjurisai2.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreferences: SharedPreferences = requireActivity().getSharedPreferences("UserPrefs", 0)
        val savedName = sharedPreferences.getString("name", "Guest")
        val savedEmail = sharedPreferences.getString("email", "No email provided")

        binding.profileName.text = savedName
        binding.profileEmail.text = savedEmail

        binding.logout.setOnClickListener {
            // Clear user data from SharedPreferences
            sharedPreferences.edit().clear().apply()

            // Navigate to LoginActivity
            val intent = Intent(requireActivity(), LoginPage::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }
}