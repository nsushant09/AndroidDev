package com.neupanesushant.learnar.ArCore

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import com.google.ar.core.Anchor
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.assets.RenderableSource
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode
import com.neupanesushant.learnar.ArCore.UriRetriever.getRawUri

class ModelManager(private val context: Context) {
    fun buildModel(url: String, onModelBuilt: (ModelRenderable) -> Unit) {
        val uri = context.getRawUri(url)
        ModelRenderable.builder()
            .setSource(
                context,
                RenderableSource.builder().setSource(context, uri, RenderableSource.SourceType.GLB).setScale(0.50f).build()
            )
            .setRegistryId(uri.toString())
            .build()
            .thenAccept {
                onModelBuilt(it)
            }
            .exceptionally {
                null
            }
    }

    fun addModel(fragment: ArFragment, anchor: Anchor, renderable: ModelRenderable){
        val node = AnchorNode(anchor)
        node.renderable = renderable
        fragment.arSceneView.scene.addChild(node)
    }
    fun addTransformableNodeModel(fragment : ArFragment, anchor: Anchor, renderable: ModelRenderable) {
        val node = AnchorNode(anchor)
        val transformableNode = TransformableNode(fragment.transformationSystem)
        transformableNode.setParent(node)
        transformableNode.renderable = renderable
        fragment.arSceneView.scene.addChild(node)
        transformableNode.select()
    }
}