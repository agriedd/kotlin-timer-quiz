package com.comodoindotech.quiztimer

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.comodoindotech.quiztimer.databinding.FragmentFirstBinding
import com.comodoindotech.quiztimer.packages.connection.client.TCPClient
import java.io.IOException
import java.lang.Exception
import java.net.UnknownHostException

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            try {
                val port: Short =
                    resources.getInteger(R.integer.DEV_PORT).toShort();
                connectTo(
                    getString(R.string.DEV_HOST),
                    port
                );
                Log.d("client", "onViewCreated: " + port.toString());
            } catch (e: Exception){
                Log.d("client", "onViewCreated: " + e.message);
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun connectTo(host: String, port: Short){
        val connection = TCPClient(requireContext())
        connection.makeConnection(host, port)
        try {
            connection.connect();
            Log.d("connect", "after connect has run.")
        } catch (e: IOException){

        } catch (e: UnknownHostException){

        } catch (e: Exception){

        }
    }

}