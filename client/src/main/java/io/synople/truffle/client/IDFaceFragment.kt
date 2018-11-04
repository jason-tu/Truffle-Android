package io.synople.truffle.client

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.synople.truffle.common.model.User
import kotlinx.android.synthetic.main.fragment_face_id.*


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
        setupRecognize()
    }

    fun setupRecognize() {
        // Initialize the Amazon Cognito credentials providerSurfaceView view = new SurfaceView(this);
//        c.setPreviewDisplay(view.getHolder());
//        c.startPreview();
//        c.takePicture(shutterCallback, rawPictureCallback, jpegPictureCallback);

    }

//    override fun onStart() {
//        super.onStart()
//        cameraKit.onStart()
//    }
//
//    override fun onResume() {
//        super.onResume()
//        cameraKit.onResume()
//    }
//
//    override fun onPause() {
//        super.onPause()
//        cameraKit.onPause()
//    }
//
//    override fun onStop() {
//        super.onStop()
//        cameraKit.onStop()
//    }

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