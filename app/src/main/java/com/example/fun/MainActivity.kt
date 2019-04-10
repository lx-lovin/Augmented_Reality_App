package com.example.`fun`

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AlertDialog.*
import android.widget.Toast
import com.google.ar.core.Anchor
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode

class MainActivity : AppCompatActivity() {

    private lateinit var arFragmentt : ArFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        arFragmentt = supportFragmentManager.findFragmentById(R.id.arFragment) as ArFragment
        arFragmentt.setOnTapArPlaneListener { hitResult, plane, motionEvent ->
            var anchor:Anchor = hitResult.createAnchor()

            ModelRenderable.builder()
                .setSource(this, Uri.parse("model.sfb"))
                .build()
                .thenAccept {
                    addModelToScene(anchor,it)
                }

        }
    }

    private fun addModelToScene(anchor: Anchor, it: ModelRenderable?) {
        var anchorNode:AnchorNode = AnchorNode(anchor)
        var transformableNode:TransformableNode = TransformableNode(arFragmentt.transformationSystem)
        transformableNode.setParent(anchorNode)
        transformableNode.renderable = it
        arFragmentt.arSceneView.scene.addChild(anchorNode)
        transformableNode.select()
    }
}
