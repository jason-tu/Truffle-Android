package io.synople.truffle.client

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.amazonaws.regions.Regions
import com.amazonaws.auth.CognitoCachingCredentialsProvider
import com.amazonaws.services.rekognition.AmazonRekognitionClient
import com.google.firebase.storage.FirebaseStorage
import io.synople.truffle.common.model.User
import kotlinx.android.synthetic.main.fragment_face_id.*
import java.io.ByteArrayOutputStream
import java.nio.ByteBuffer
import kotlin.concurrent.thread


private const val PROFILE = "profile"

class IDFaceFragment : Fragment() {
    private lateinit var profile: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            profile = it.getParcelable(PROFILE)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_face_id, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bCapturePhoto.setOnClickListener {
            captureFace()

            fragmentManager!!.beginTransaction().replace(
                R.id.fragmentFrame,
                ProfileFragment.newInstance(profile)
            ).commit()
        }

        cameraKit.visibility = View.VISIBLE

    }

    fun captureFace() {
        cameraKit.captureImage { _, photo ->
            thread(true) {
                FirebaseStorage.getInstance().reference.child(profile.id + ".jpg").putBytes(photo)
                    .addOnSuccessListener {
                        activity!!.runOnUiThread {
                            Toast.makeText(context, "Uploaded!", Toast.LENGTH_SHORT).show()
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
        fun newInstance(profile: User) =
            io.synople.truffle.client.IDFaceFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(PROFILE, profile)
                }
            }
    }

}