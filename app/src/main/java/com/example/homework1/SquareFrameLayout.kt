package com.example.homework1

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout

class SquareFrameLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    override fun onMeasure(
        widthMeasureSpec: Int,
        heightMeasureSpec: Int
    ) {
        val width = MeasureSpec.getSize(widthMeasureSpec)

        val squareSize = MeasureSpec.makeMeasureSpec(
            width,
            MeasureSpec.EXACTLY
        )

        super.onMeasure(
            widthMeasureSpec,
            squareSize
        )
    }
}