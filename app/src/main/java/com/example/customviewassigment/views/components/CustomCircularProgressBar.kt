package com.example.customviewassigment.views.components

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.content.withStyledAttributes
import com.example.customviewassigment.R

class CustomCircularProgressBar(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private var contextCircular = context

    companion object {
        private const val DEFAULT_PROGRESS = 0
    }

    private var trackColor = contextCircular?.let { ContextCompat.getColor(it,R.color.colorSecondary) } ?: 0
    private var indicatorColor = contextCircular?.let { ContextCompat.getColor(it,R.color.progressCircularBarColor) } ?: 0
    private var progress = DEFAULT_PROGRESS

    private val paintForIndicatorColor = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = indicatorColor
        style = Paint.Style.STROKE
        strokeWidth = 10f
    }

    private val paintForTrackColor = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = trackColor
        style = Paint.Style.STROKE
        strokeWidth = 10f
    }

    private val paintForPercentText = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textAlign = Paint.Align.CENTER
        textSize = 38.0f
        color = contextCircular?.let { ContextCompat.getColor(it,R.color.colorSecondary) } ?: 0
        typeface = Typeface.create("", Typeface.BOLD)
    }

    init {
        setUpAttributes(attrs)
    }

    private fun setUpAttributes(attrs: AttributeSet?) {
        context.withStyledAttributes(attrs,R.styleable.CustomProgressBar){
            trackColor = getColor(R.styleable.CustomProgressBar_customTrackColor, trackColor)
            indicatorColor = getColor(R.styleable.CustomProgressBar_customIndicatorColor, indicatorColor)
            progress = getInteger(R.styleable.CustomProgressBar_customProgress, DEFAULT_PROGRESS)
        }
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {

        val centerX = width / 2f
        val centerY = height / 2f
        val radius = width / 2f - 10f

        // For indicator color
        canvas?.drawCircle(centerX,centerY,radius,paintForIndicatorColor)

        // track color
        val progressRectF = RectF(centerX - radius, centerY - radius, centerX + radius, centerY + radius)
        canvas?.drawArc(progressRectF, -90f, progress.toFloat(), false, paintForTrackColor)

        // percent text
        val percentText = "${((progress.toFloat() / 360f) * 100f).toInt()}%"
        canvas?.drawText(percentText,centerX,centerY + 15f,paintForPercentText)

        super.onDraw(canvas)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val size = measuredWidth.coerceAtMost(measuredHeight)
        setMeasuredDimension(size,size)
    }
}