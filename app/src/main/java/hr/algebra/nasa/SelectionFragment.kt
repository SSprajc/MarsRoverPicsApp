package hr.algebra.nasa

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import hr.algebra.nasa.databinding.FragmentSelectionBinding
import hr.algebra.nasa.framework.callDelayed
import hr.algebra.nasa.framework.startActivity


class SelectionFragment : Fragment(R.layout.fragment_selection) {

    private var _binding: FragmentSelectionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSelectionBinding.inflate(inflater, container, false)
        binding.autoCompleteTV


        binding.btnGo.setOnClickListener {
            binding.btnGo.isEnabled = false

            var sol = binding.sol.text.toString()
            var rover = binding.autoCompleteTV.text.toString()
            if (sol != "" && rover != "") {

                requireContext().startActivity<SplashScreenActivity>("sol", sol, "rover", rover)
                /*Intent(requireActivity(), NasaService::class.java).apply {
                    NasaService.enqueue(
                        requireActivity()
                        this
                    )
                    putExtra("sol", sol)
                    putExtra("rover", rover)
                }

                 */
                // mozda je do start fragment


            } else {
                AlertDialog.Builder(context).apply {
                    setTitle(R.string.error)
                    setMessage(context.getString(R.string.values))
                    setIcon(R.drawable.red_flag)
                    setCancelable(true)
                    setPositiveButton("OK", null)
                    show()
                }
                binding.btnGo.isEnabled = true
            }


            //enqueue work, disable navMenu /delete


        }

        return binding.root

    }

    override fun onResume() {
        super.onResume()
        val rovers = resources.getStringArray(R.array.rovers)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, rovers)
        binding.autoCompleteTV.setAdapter(arrayAdapter)
        binding.btnGo.isEnabled = true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}