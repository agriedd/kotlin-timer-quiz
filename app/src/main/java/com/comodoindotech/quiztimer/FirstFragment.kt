package com.comodoindotech.quiztimer

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.comodoindotech.quiztimer.databinding.FragmentFirstBinding
import com.comodoindotech.quiztimer.packages.connection.client.ConnectionListener
import com.comodoindotech.quiztimer.packages.connection.client.TCPClient
import com.comodoindotech.quiztimer.packages.connection.models.Commands
import com.comodoindotech.quiztimer.packages.connection.models.DataRequest
import com.google.gson.Gson
import java.io.IOException
import java.lang.Exception
import java.net.UnknownHostException

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private var connection: TCPClient? = null

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
        connection = TCPClient(requireContext())
        connection!!.makeConnection(host, port)
        connection!!.setConnectionListener(this.connectionClientListener)
        try {
            connection!!.connect();
            Log.d("connect", "after connect has run.")
        } catch (e: IOException){

        } catch (e: UnknownHostException){

        } catch (e: Exception){

        }
    }

    private val connectionClientListener = object : ConnectionListener {
        override fun onConnected() {
            Log.d("client", "onConnected: Terhubung")
            try{
                val gson = Gson()
                val model = DataRequest("Salam kenal", Commands.EMPTY)
                val data = gson.toJson(model)
                connection!!.sendData(data)

            } catch (e: Exception){
                Log.d("client", "tidak dapat mengirim pesan")
            }
        }

        override fun onDisconnected() {
        }

        override fun onResume() {
        }

        override fun beforeSend(data: String) {
        }

        override fun afterSend(data: String) {
        }

    }
}