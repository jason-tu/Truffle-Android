package io.synople.truffle.vendor

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.support.v4.app.Fragment
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
import kotlinx.android.synthetic.main.fragment_customer_view.*
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
        setupRecognize()
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
                    val cameraImage = com.amazonaws.services.rekognition.model.Image().withBytes(ByteBuffer.wrap(photo))

                    val drawable = context!!.getDrawable(R.drawable.jason)
                    val bitmap = (drawable as BitmapDrawable).bitmap
                    val stream = ByteArrayOutputStream()
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
                    val storedImage =
                        com.amazonaws.services.rekognition.model.Image()
                            .withBytes(ByteBuffer.wrap(stream.toByteArray()))

                    thread(true) {
                        val matches = rekognitionClient.compareFaces(CompareFacesRequest(cameraImage, storedImage))
                        activity!!.runOnUiThread {
                            if (matches.faceMatches.size > 0) {
                                Log.v("Result", matches.faceMatches[0].similarity.toString())
                                Toast.makeText(
                                    context,
                                    "Similarity: ${matches.faceMatches.get(0).similarity}",
                                    Toast.LENGTH_SHORT
                                ).show()
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
