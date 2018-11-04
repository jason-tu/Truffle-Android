package io.synople.truffle.vendor

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import io.synople.truffle.common.model.User
import com.amazonaws.regions.Regions
import com.amazonaws.auth.CognitoCachingCredentialsProvider
import com.amazonaws.services.rekognition.AmazonRekognitionClient
import com.amazonaws.services.rekognition.model.CompareFacesRequest
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.fragment_customer_view.*
import kotlinx.android.synthetic.main.row_customer.view.*
import java.io.ByteArrayOutputStream
import java.nio.ByteBuffer
import kotlin.concurrent.thread


private const val CUSTOMER = "customer"

class CustomerViewFragment : Fragment() {
    private lateinit var customer: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            customer = it.getParcelable(CUSTOMER)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_customer_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // setupRecognize()

        FirebaseStorage.getInstance().reference.child(customer.id + ".jpg").downloadUrl.addOnSuccessListener {
            Glide.with(view.context)
                .load(it.toString())
                .into(ivCustomerImage)
        }

        tvCustomerName.text = customer.name
    }

    fun setupRecognize() {
        // Initialize the Amazon Cognito credentials provider
        val credentialsProvider = CognitoCachingCredentialsProvider(
            context,
            "us-east-2:8ed36110-dc2f-4f5e-9d78-da2fc47c8dfc", // Identity pool ID
            Regions.US_EAST_2 // Region
        )

        val rekognitionClient = AmazonRekognitionClient(credentialsProvider)

        ivCustomerImage.visibility = View.INVISIBLE
        cameraKit.visibility = View.VISIBLE

        tvCustomerName.setOnClickListener {
            thread(true) {
                cameraKit.captureImage { _, photo ->
                    FirebaseStorage.getInstance().reference.child(customer.id + ".jpg").getBytes(1920 * 1080)
                        .addOnSuccessListener { referenceImage ->
                            val cameraImage =
                                com.amazonaws.services.rekognition.model.Image()
                                    .withBytes(ByteBuffer.wrap(photo))
                            val storedImage =
                                com.amazonaws.services.rekognition.model.Image()
                                    .withBytes(ByteBuffer.wrap(referenceImage))

                            thread(true) {
                                val matches =
                                    rekognitionClient.compareFaces(CompareFacesRequest(cameraImage, storedImage))
                                activity!!.runOnUiThread {
                                    if (matches.faceMatches.size > 0) {
                                        Log.v("Result", matches.faceMatches[0].similarity.toString())
                                        Toast.makeText(
                                            context,
                                            "Similarity: ${matches.faceMatches.get(0).similarity}",
                                            Toast.LENGTH_SHORT
                                        ).show()

                                        if (matches.faceMatches[0].similarity > 70) {
                                            val builder = AlertDialog.Builder(
                                                context!!,
                                                android.R.style.Theme_Material_Dialog_Alert
                                            )
                                            builder.setMessage("Confirmed")
                                            builder.setCancelable(false)
                                            builder.setPositiveButton("Ok") { _, _ ->
                                                (activity as TicketActivity).ticketFragment.clearTicket()
                                            }
                                            builder.create().show()
                                        }
                                    } else {
                                        Log.v("Result", "No face match")
                                        Toast.makeText(context, "No face match", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            }
                        }
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        cameraKit.onStart()
    }

    override fun onResume() {
        super.onResume()
        cameraKit.onResume()
    }

    override fun onPause() {
        super.onPause()
        cameraKit.onPause()
    }

    override fun onStop() {
        super.onStop()
        cameraKit.onStop()
    }

    companion object {
        @JvmStatic
        fun newInstance(customer: User) =
            io.synople.truffle.vendor.CustomerViewFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(CUSTOMER, customer)
                }
            }
    }
}
