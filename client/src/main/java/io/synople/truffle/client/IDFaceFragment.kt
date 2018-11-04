package io.synople.truffle.client

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.amazonaws.regions.Regions
import com.amazonaws.auth.CognitoCachingCredentialsProvider
import com.amazonaws.services.rekognition.AmazonRekognitionClient
import io.synople.truffle.common.model.User
import kotlinx.android.synthetic.main.fragment_face_id.*
import java.io.ByteArrayOutputStream
import java.nio.ByteBuffer
import kotlin.concurrent.thread


private const val PROFILE = "profile"

class IDFaceFragment: Fragment() {
    private lateinit var profile: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let{
            profile = it.getParcelable(PROFILE)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bCapturePhoto.setOnClickListener {
            fragmentManager!!.beginTransaction().replace(
                R.id.fragmentFrame,
                ProfileFragment.newInstance(profile)
            ).commit()
        }
        captureFace()
    }

    fun captureFace() {

        ivProfileImage.visibility = View.INVISIBLE
        cameraKit.visibility = View.VISIBLE

        cameraKit.captureImage { _, photo ->
            thread(true) {
                val cameraImage = com.amazonaws.services.rekognition.model.Image().withBytes(ByteBuffer.wrap(photo))

                val drawable = context!!.getDrawable(R.drawable.jason)
                val bitmap = (drawable as BitmapDrawable).bitmap
                val stream = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
                val storedImage =
                    com.amazonaws.services.rekognition.model.Image()
                        .withBytes(ByteBuffer.wrap(stream.toByteArray()))
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