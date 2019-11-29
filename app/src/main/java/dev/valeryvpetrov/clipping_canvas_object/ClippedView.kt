package dev.valeryvpetrov.clipping_canvas_object

import android.content.Context
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import android.view.View
import kotlin.jvm.JvmOverloads

class ClippedView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint = Paint()

    private val path = Path()

    private val clipRectTop = resources.getDimension(R.dimen.clipRectTop)
    private val clipRectBottom = resources.getDimension(R.dimen.clipRectBottom)
    private val clipRectLeft = resources.getDimension(R.dimen.clipRectLeft)
    private val clipRectRight = resources.getDimension(R.dimen.clipRectRight)

    private val rectInset = resources.getDimension(R.dimen.rectInset)
    private val smallRectOffset = resources.getDimension(R.dimen.smallRectOffset)
    private val circleRadius = resources.getDimension(R.dimen.circleRadius)
    private val textOffset = resources.getDimension(R.dimen.textOffset)
    private val textSize = resources.getDimension(R.dimen.textSize)

    private val columnOne = rectInset
    private val columnTwo = columnOne + rectInset + clipRectRight
    private val rowOne = rectInset
    private val rowTwo = rowOne + rectInset + clipRectBottom
    private val rowThree = rowTwo + rectInset + clipRectBottom
    private val rowFour = rowThree + rectInset + clipRectBottom
    private val rowText = rowFour + (1.5* clipRectBottom)

    init {
        paint.isAntiAlias = true
        paint.strokeWidth = resources.getDimension(R.dimen.strokeWidth)
        paint.textSize = resources.getDimension(R.dimen.textSize)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawBackAndUnclippedRectangle(canvas)
        drawDifferenceClippingExample(canvas)
        drawCircularClippingExample(canvas)
        drawIntersectionClippingExample(canvas)
        drawCombinedClippingExample(canvas)
        drawRoundedRectangleClippingExample(canvas)
        drawOutsideClippingExample(canvas)
        drawSkewedTextExample(canvas)
        drawTranslatedTextExample(canvas)
        // drawQuickRejectExample(canvas)
    }

    private fun drawClippedRect(canvas: Canvas) {
        canvas.clipRect(clipRectLeft, clipRectTop, clipRectRight, clipRectBottom)

        canvas.drawColor(Color.WHITE)

        paint.color = Color.RED
        canvas.drawLine(clipRectLeft, clipRectTop, clipRectRight, clipRectBottom, paint)

        paint.color = Color.GREEN
        canvas.drawCircle(circleRadius, clipRectBottom - circleRadius, circleRadius, paint)

        paint.color = Color.BLUE
        paint.textSize = textSize
        paint.textAlign = Paint.Align.RIGHT
        canvas.drawText(resources.getString(R.string.clipping), clipRectRight, textOffset, paint)
    }

    private fun drawBackAndUnclippedRectangle(canvas: Canvas) {
        canvas.drawColor(Color.GRAY)
        canvas.save()
        canvas.translate(columnOne, rowOne)
        drawClippedRect(canvas)
        // remove all modifications to the matrix/clip state since the last save call
        canvas.restore()
    }

    private fun drawDifferenceClippingExample(canvas: Canvas) {
        canvas.save()
        // move the origin to the right for the next rectangle
        canvas.translate(columnTwo, rowOne)
        // use the subtraction of two clipping rectangles to create a frame
        canvas.clipRect(2 * rectInset, 2 * rectInset,
            clipRectRight - 2 * rectInset, clipRectBottom - 2 * rectInset)
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            canvas.clipRect(4 * rectInset, 4 * rectInset,
                clipRectRight - 4 * rectInset, clipRectBottom - 4 * rectInset,
                Region.Op.DIFFERENCE)
        } else {
            canvas.clipOutRect(4 * rectInset, 4 * rectInset,
                clipRectRight - 4 * rectInset, clipRectBottom - 4 * rectInset)
        }
        drawClippedRect(canvas)
        canvas.restore()
    }

    private fun drawCircularClippingExample(canvas: Canvas) {
        // TODO
    }

    private fun drawIntersectionClippingExample(canvas: Canvas) {
        // TODO
    }

    private fun drawCombinedClippingExample(canvas: Canvas) {
        // TODO
    }

    private fun drawRoundedRectangleClippingExample(canvas: Canvas) {
        // TODO
    }

    private fun drawOutsideClippingExample(canvas: Canvas) {
        // TODO
    }

    private fun drawSkewedTextExample(canvas: Canvas) {
        // TODO
    }

    private fun drawTranslatedTextExample(canvas: Canvas) {
        // TODO
    }
}
